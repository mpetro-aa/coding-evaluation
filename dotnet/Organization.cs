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
        private static int CurrentEmployeeId = 1;

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
            Position? result = null;

            if (string.IsNullOrEmpty(title) == false)
            {
                result = findPosition(root, title);

                if (result != null && person != null)
                {
                    result.SetEmployee(new Employee(CurrentEmployeeId++, person));
                }
            }

            return result;
        }

        private Position? findPosition(Position currentPosition, string title)
        {
            if (currentPosition.GetTitle() == title)
            {
                return currentPosition;
            }
            else
            {
                foreach (Position p in currentPosition.GetDirectReports())
                {
                    var position = findPosition(p, title);
                    if (position != null)
                    {
                        return position;
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
                sb.Append(PrintOrganization(p, prefix + "  "));
            }
            return sb.ToString();
        }
    }
}
