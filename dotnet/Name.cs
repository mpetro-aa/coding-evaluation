using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace MyOrganization
{
    internal class Name
    {
        private string first;
        private string last;

        public Name(string first, string last)
        {
            if (first == null)
                throw new Exception("first name cannot be null");
            if (last == null) 
                throw new Exception("last name cannot be null");
            this.first = first; 
            this.last = last;
        }

        public string GetFirst()
        {
            return first;
        }

        public string GetLast()
        {
            return last;
        }

        override public string ToString()
        {
            return first + " " + last;
        }
    }
}
