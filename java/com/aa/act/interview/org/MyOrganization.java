package com.aa.act.interview.org;

public class MyOrganization extends Organization {

	@Override
	protected Position createOrganization() {
		Position ceo = new Position("CEO");
		Position pres = new Position("President");
		ceo.addDirectReport(pres);
		Position vpm = new Position("VP Marketing");
		pres.addDirectReport(vpm);
		Position vps = new Position("VP Sales");
		pres.addDirectReport(vps);
		Position vpf = new Position("VP Finance");
		pres.addDirectReport(vpf);
		Position coo = new Position("COO");
		pres.addDirectReport(coo);
		Position cio = new Position("CIO");
		ceo.addDirectReport(cio);
		Position vpt = new Position("VP Technology");
		cio.addDirectReport(vpt);
		Position vpi = new Position("VP Infrastructure");
		cio.addDirectReport(vpi);
		Position dea = new Position("Director Enterprise Architecture");
		vpt.addDirectReport(dea);
		Position dct = new Position("Director Customer Technology");
		vpt.addDirectReport(dct);
		Position s = new Position("Salesperson");
		vps.addDirectReport(s);

		return ceo;
	}

	public static final void main(String... args) {
		MyOrganization myOrg = new MyOrganization();
		myOrg.hire(new Name("Doug", "Parker"), "CEO");
		myOrg.hire(new Name("Robert", "Isom"), "President");
		myOrg.hire(new Name("Gandalf", "Gray"), "Director Enterprise Architecture");
		myOrg.hire(new Name("Head", "Geek"), "Director Customer Technology");
		myOrg.hire(new Name("Jane", "Smith"), "VP Marketing");
		myOrg.hire(new Name("Jim", "Jones"), "VP Sales");
		myOrg.hire(new Name("Bean", "Counter"), "VP Finance");
		myOrg.hire(new Name("Maya", "Liebman"), "CIO");
		myOrg.hire(new Name("Danielle", "Hoover"), "VP Technology");
		myOrg.hire(new Name("Scape", "Goat"), "VP Infrastructure");
		myOrg.hire(new Name("Slick", "Willie"), "Salesperson");
		System.out.println(myOrg);


	}
}
