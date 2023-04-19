package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	private Position root;
	private int identifier = 0;
	
	public Organization() {
		root = createOrganization();
	}
	
	protected abstract Position createOrganization();
	
	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {
		addToOrgainization(root, new Employee(identifier, person), title);
		identifier++;
		return Optional.of(root);
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
	
	/**
	 * Recursively iterates through the organization chart to add newly hired employees to it
	 * 
	 * @param startPosition - the position on the organization chart this iteration is starting at
	 * @param employee
	 * @param employeeTitle
	 */
	private void addToOrgainization(Position startPosition, Employee employee, String employeeTitle) {
		if(startPosition.getTitle().equals(employeeTitle)) {
			startPosition.setEmployee(Optional.of(employee));
		} else {
			if(startPosition.getDirectReports() != null && startPosition.getDirectReports().size() !=0) {
				for(Position position : startPosition.getDirectReports()) {
					addToOrgainization(position, employee, employeeTitle);
				}
			}
		}
	}
}

