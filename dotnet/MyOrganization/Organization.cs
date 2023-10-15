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

        public Organization()
        {
            root = CreateOrganization();
        }

        protected abstract Position CreateOrganization();

        // This is to generate identifier for the Employees
        int identifier = 1;

        /**
         * hire the given person as an employee in the position that has that title
         * 
         * @param person
         * @param title
         * @return the newly filled position or empty if no position has that title
         */
        public Position? Hire(Name person, string title)
        {
            Employee employee = new Employee(identifier++, person);
            return CheckDirectReportPositions(root, employee, title);
        }

        private Position? CheckDirectReportPositions(Position pos, Employee employee, string title)
        {
            if (pos.GetTitle() == title)
            {
                pos.SetEmployee(employee);
                return pos;
            }

            foreach (Position p in pos.GetDirectReports())
            {
                CheckDirectReportPositions(p, employee, title);
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