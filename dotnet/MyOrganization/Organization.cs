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
        private int employeeNumber;

        public Organization()
        {
            root = CreateOrganization();
            employeeNumber = 0;
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
            return Hire(person, title, root);
        }

        private Position? Hire(Name person, string title, Position position)
        {
            Position? hiredPosition = null;
            // check if the current position is the title being hired
            if(title == position.GetTitle())
            {
                Employee emp = new Employee(GetUniqueEmployeeNumber(), person);
                Position pos = new Position(title, emp);
                position.SetEmployee(emp);
                return pos; 
            }

            // check if the person has reports
            var reports = position.GetDirectReports();

            if(reports.Count > 0)
            {
                // for each report, hire recursively.
                foreach( var report in reports)
                {
                    hiredPosition = Hire(person, title, report);
                    if(hiredPosition != null)
                    {
                        return hiredPosition;
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

        protected int GetUniqueEmployeeNumber()
        {
            employeeNumber += 1;
            return employeeNumber;
        }
    }
}
