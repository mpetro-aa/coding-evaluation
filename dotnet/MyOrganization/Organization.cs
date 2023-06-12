using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyOrganization
{
    internal abstract class Organization
    {
        private Position root;

        static int identifier = 1;

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
            var p = getPositionhasTitle(root, title);
            if (p == null)
                return null;

            var emp = new Employee(identifier++, person);
            p.SetEmployee(emp);
            return p;
        }
        private Position? getPositionhasTitle(Position p, string title)
        {
            Position pos = null;
            if (p != null)
            {
                if (p.GetTitle() == title)
                    return p;
                foreach (Position position in p.GetDirectReports())
                {
                    if (pos != null)
                        break;
                    if (position.GetTitle() == title)
                    {
                        pos = position;
                        break;
                    }

                    pos = getPositionhasTitle(position, title);
                }
            }
            return pos;
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
