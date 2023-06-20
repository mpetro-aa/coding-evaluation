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

        /**
         * hire the given person as an employee in the position that has that title
         * 
         * @param person
         * @param title
         * @return the newly filled position or empty if no position has that title
         */
        public Position? Hire(Name person, string title)
        {
            Position? targetPosition = FindPositionByTitle(root, title);

            if (targetPosition != null)
            {
                if (targetPosition.IsFilled())
                {
                    Employee? previousEmployee = targetPosition.GetEmployee();
                    Console.WriteLine($"Position '{title}' is already filled by {previousEmployee}. Cannot hire {person}.");
                    return null;
                }

                Employee newEmployee = new Employee(GetNextEmployeeIdentifier(), person);
                targetPosition.SetEmployee(newEmployee);

                Console.WriteLine($"Hired {person} as '{title}'.");
                return targetPosition;
            }

            Console.WriteLine($"No position found with the title '{title}'. Cannot hire {person}.");
            return null;
        }

        private Position? FindPositionByTitle(Position currentPosition, string title)
        {
            if (currentPosition.GetTitle() == title)
            {
                return currentPosition;
            }

            foreach (Position directReport in currentPosition.GetDirectReports())
            {
                Position? targetPosition = FindPositionByTitle(directReport, title);
                if (targetPosition != null)
                {
                    return targetPosition;
                }
            }

            return null;
        }




        private int nextEmployeeId = 1000; // Starting employee ID

        private int GetNextEmployeeIdentifier()
        {
            int employeeId = nextEmployeeId;
            nextEmployeeId++; // Increment the employee ID for the next employee
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
