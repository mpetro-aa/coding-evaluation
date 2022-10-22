package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	private Position root;
	// this is to just increment for employee number
	private static int empNo;

	public Organization() {
		root = createOrganization();
	}

	protected abstract Position createOrganization();

	private String traverseAndPopulateEmpDetails(Position pos, Name person, String title) {
		for (Position p : pos.getDirectReports()) {
			fillReportiesEmpDetails(p, person, title);
			traverseAndPopulateEmpDetails(p, person, title);

		}
		return "success";
	}

	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {
		// This is to fill only root element value employee details
		fillReportiesEmpDetails(root, person, title);
		// This is to populate recursively Employee details as they are null
		traverseAndPopulateEmpDetails(root, person, title);
		// no matter what we return as we are not doing anything with this
		return Optional.of(root);
	}

	void fillReportiesEmpDetails(Position position, Name person, String title) {

		if (position.getTitle().equals(title)) {
			empNo = empNo + 1;
			Optional<Employee> employee = Optional
					.of(new Employee(empNo, new Name(person.getFirst(), person.getLast())));
			position.setEmployee(employee);
		}

	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}
	
	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for(Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}
