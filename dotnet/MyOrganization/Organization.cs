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
        int id=0;
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
        public Position? Hire(Name person, String title)
        {
            addNewEmp(root, person, title);
            displayEmpDetails(root, person, title);
            return root;
        }

        private void addNewEmp(Position position, Name person, String title)
        {
            //add new emp/Hire
            if (position.GetTitle().Equals(title))
            {
                position.SetEmployee(new Employee(++id, new Name(person.GetFirst(), person.GetLast())));
            }

        }
        private void displayEmpDetails(Position pos, Name person, String title)
        {
            //check direct report
            foreach (Position p in pos.GetDirectReports())
            {
                addNewEmp(p, person, title);
                displayEmpDetails(p, person, title);
            }
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
