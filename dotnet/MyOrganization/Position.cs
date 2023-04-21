using System;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyOrganization
{
    internal class Position
    {
        public string title;
        private Employee? employee;
        private HashSet<Position> directReports;

        public Position(string title)
        {
            this.title = title;
            employee = null;
            directReports = new HashSet<Position>();
        }

        public Position(String title, Employee employee) : this(title)
        {
            if (employee != null)
                SetEmployee(employee);
        }

        public String GetTitle()
        {
            return title;
        }

        public void SetEmployee(Employee? employee)
        {
            this.employee = employee;
        }

        public Employee? GetEmployee()
        {
            return employee;
        }

        public Boolean IsFilled()
        {
            return employee != null;
        }

        public Boolean AddDirectReport(Position position)
        {
            if (position == null)
                throw new Exception("position cannot be null");
            return directReports.Add(position);
        }

        public Boolean RemovePosition(Position position)
        {
            return directReports.Remove(position);
        }

        public ImmutableList<Position> GetDirectReports()
        {
            return directReports.ToImmutableList();
        }

        override public string ToString()
        {
            return title + (employee != null ? ": " + employee.ToString() : "");
        }
    }
}
