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

        private int employeeId = 1;
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
            //Get employee position based on Title
            Position? position = GetTitlePosition(title, root);
            //Check if position is available.
            if (position == null)
            {
                Console.WriteLine("Position is unavailable");
                return null;
            }

            if (position != null)
            {
                //Check if existing employee
                if (position.IsFilled())
                {
                    Employee? previousEmployee = position.GetEmployee();
                    Console.WriteLine("Position is already occupied.");
                    return null;
                }
                else
                {
                    //Create new hire
                    Employee newEmployee = new Employee(GetNewId(), person);
                    position.SetEmployee(newEmployee);
                    Console.WriteLine("New employee is hired.");
                    return position;
                }
            }
            return null;
        }

        private Position? GetTitlePosition(string title, Position currentPosition)
        {
            if (currentPosition.GetTitle() == title)
            {
                return currentPosition;
            }

            foreach (Position pos in currentPosition.GetDirectReports())
            {
                Position? position = GetTitlePosition(title, pos);
                if (position != null)
                {
                    return position;
                }
            }
            return null;
        }

        private int GetNewId()
        {
            employeeId++;
            return employeeId;
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
