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
        private List<Position> positions;
        private int empid = 0;

        public Organization()
        {
            root = CreateOrganization();

            // This saves us from having to walk a hierarchy each time we want to
            // lookup a position.
            positions = new List<Position>(root.Flatten());
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
            var found = positions.First(p => p.GetTitle() == title);
            if (found != null)
            {
                found.SetEmployee(new Employee(++empid, person));
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
