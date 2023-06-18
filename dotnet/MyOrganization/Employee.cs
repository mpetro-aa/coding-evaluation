using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace MyOrganization
{
    internal class Employee
    {
        private int identifier;
        private Name name;

        public Employee(Name name, int Identifier)
        {
            if (name == null)
                throw new Exception("name cannot be null");
            this.identifier = Identifier;
            this.name = name;
        }

        public int GetIdentifier()
        {
            return identifier;
        }

        public Name GetName()
        {
            return name;
        }

        override public string ToString()
        {
            return name.ToString();
        }
    }
}
