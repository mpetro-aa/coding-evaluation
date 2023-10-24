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
        private int empId = 0;
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

            Position? position = GetPosition(title, root);

            if (position != null)
            {
                //Checking for availed position
                if (!position.IsFilled())
                {
                    //New hiring
                    Employee newEmployee = new Employee(GetId(), person);
                    position.SetEmployee(newEmployee);
                    return position;
                }
            }
            return null;
        }
        /// <summary>
        /// To get the position that has the title
        /// </summary>
        /// <param name="title"></param>
        /// <param name="currentPosition"></param>
        /// <returns></returns>
        private Position? GetPosition(string title, Position currentPosition)
        {
            if (currentPosition.GetTitle() != title)
            {
                foreach (Position? position in from Position pos in currentPosition.GetDirectReports()
                                               let position = GetPosition(title, pos)
                                               where position != null
                                               select position)
                {
                    return position;
                }

                return null;
            }

            return currentPosition;
        }

        private int GetId()
        {
            empId++;
            return empId;
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
