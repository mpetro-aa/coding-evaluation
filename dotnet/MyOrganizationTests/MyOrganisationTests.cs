namespace MyOrganizationTests;

using System.Diagnostics;
using MyOrganization;


[TestClass]
public class MyOrganizationTests
{
    public MyOrganizationTests()
    {
        Trace.Listeners.Add(new ConsoleTraceListener());
    }

    private Position CreateTestPositions()
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

    [TestMethod]
    public void TestWeCanFlattenTree()
    {
        Position ceo = CreateTestPositions();
        List <Position> positions = new List<Position>(ceo.Flatten());

        Assert.AreEqual(12, positions.Count);
        foreach (var pos in positions)
        {
            Trace.WriteLine($"{pos.GetTitle()}");
        }
    }
}
