using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.ConstrainedExecution;
using System.Text;
using System.Threading.Tasks;

namespace MyOrganization
{
    internal abstract class Organization
    {
        private Position root;

        public Organization()
        {
            root = CreateOrganization();
        }

        protected abstract Position CreateOrganization();

        /**
         * hire the given person as an employee in the position that has that title
         * 
         * @param person
         * @param title
         * @return the newly filled position or empty if no position has that title
         */
        public Position? Hire(Name person, string title)
        {
            //your code here

            Position current = root;

            Queue<Position> queue = new Queue<Position>();

            queue.Enqueue(current);

            while (queue.Count > 0)
            {
                Position cur = queue.Dequeue();

                if (cur.title == title)
                {
                    cur.SetEmployee(new Employee(person));
                    return cur;
                }
                else
                {
                    foreach (Position pos in cur.GetDirectReports())
                    {
                        queue.Enqueue(pos);
                    }
                }
            }
            return null;
        }

        override public string ToString()
        {
            return PrintOrganization(root, "");
        }

        private string PrintOrganization(Position pos, string prefix)
        {
            StringBuilder sb = new StringBuilder(prefix + "+-" + pos.ToString() + "\n");
            foreach (Position p in pos.GetDirectReports())
            {
                sb.Append(PrintOrganization(p, prefix + "\t"));
            }
            return sb.ToString();
        }
    }
}
