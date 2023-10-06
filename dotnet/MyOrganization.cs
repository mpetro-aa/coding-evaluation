using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyOrganization
{
    internal class MyOrganization : Organization
    {
        override protected Position CreateOrganization()
        {
            Position ceo = new Position("CEO");
            Position pres = new Position("President");
            ceo.AddDirectReport(pres);
            Position vpm = new Position("VP Marketing");
            pres.AddDirectReport(vpm);
            Position vps = new Position("VP Sales");
            pres.AddDirectReport(vps);
            Position vpf = new Position("VP Finance");
            pres.AddDirectReport(vpf);
            Position coo = new Position("COO");
            pres.AddDirectReport(coo);
            Position cio = new Position("CIO");
            ceo.AddDirectReport(cio);
            Position vpt = new Position("VP Technology");
            cio.AddDirectReport(vpt);
            Position vpi = new Position("VP Infrastructure");
            cio.AddDirectReport(vpi);
            Position dea = new Position("Director Enterprise Architecture");
            vpt.AddDirectReport(dea);
            Position dct = new Position("Director Customer Technology");
            vpt.AddDirectReport(dct);
            Position s = new Position("Salesperson");
            vps.AddDirectReport(s);

            return ceo;
        }

        static void Main()
        {
            MyOrganization org = new MyOrganization();
            org.Hire(new Name("Bob", "Smith"), "CEO");
            org.Hire(new Name("Zaphod", "Beeblebrox"), "President");
            org.Hire(new Name("Gandalf", "Gray"), "Director Enterprise Architecture");
            org.Hire(new Name("Bill", "Lumbergh"), "Director Customer Technology");
            org.Hire(new Name("Ford", "Prefect"), "VP Marketing");
            org.Hire(new Name("Jane", "Seller"), "VP Sales");
            org.Hire(new Name("Bean", "Counter"), "VP Finance");
            org.Hire(new Name("Victoria", "Sinclair"), "CIO");
            org.Hire(new Name("Head", "Geek"), "VP Technology");
            org.Hire(new Name("Steve", "Dent"), "VP Infrastructure");
            org.Hire(new Name("Slick", "Willie"), "Salesperson");
            Console.WriteLine(org);
            Console.ReadLine();
        }
    }
}
