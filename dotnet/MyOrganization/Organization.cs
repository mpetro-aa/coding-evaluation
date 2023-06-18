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
        private int iden;
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
        /*
          APPROACH EXPLANATION : The CreateOrganization is built like a Tree with multiple Levels .
          One of the Recommended Approaches that is done iteratively is using a Queue by exploring each position and its Direct Reported Positions..
        */
        public Position? Hire(Name person, string title)
        {
            //your code here
            Position current = root;
            iden = 0;
            Queue<Position> queue = new Queue<Position>();

            queue.Enqueue(current);

            while (queue.Count > 0)
            {
                Position cur = queue.Dequeue();
                // validate  if the position has the that title
                if (cur.title == title)
                {
                    // if not filled...
                    if(!cur.IsFilled())
                    {
                        cur.SetEmployee(new Employee(person,iden++));
                        return cur;
                    }
                }
                else
                {
                    //enqueuing the current position Direct Reports into the Queue for exploration...
                    foreach (Position pos in cur.GetDirectReports())
                    {
                        queue.Enqueue(pos);
                    }
                }
            }
            // return empty is no position has title 
            // different use case..
            return new Position(string.Empty);
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
                sb.Append(PrintOrganization(p, prefix + "  "));
            }
            return sb.ToString();
        }
    }
}
