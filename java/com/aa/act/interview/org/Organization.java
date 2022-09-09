package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	private Position root;
	private Position current;
	
	public Organization() {
		root = createOrganization();
		current = root;
	}
	
	protected abstract Position createOrganization();
	/* Code provided by Caitlyn J. Huff on Sep 8, 2022
	 * Code provided for American Airlines - Coding Assessment
	 * 
	 * TODO: hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {

		/* Check to see if the current position matches that of the person
		 * Ensure the position is not filled before setting employee to position
		 * Reset current position to root
		 * And return newly filled position
		 */
		if(title == current.getTitle() && !current.isFilled()) {
			current.setEmployee(Optional.of(new Employee(0, person)));	// identifier needed unclear-> placeholder of 0?
			return Optional.of(current);
		}

		/* If the current position does NOT match that of the hired employee
		 * Recursively process through directory, updating the current location
		 */
		for(Position p : current.getDirectReports()) {
			current = p;
			hire(person, title);
		}

		/* In the event the entire directory is processed without finding a matching title
		 * Reset current position
		 * And return empty
		 */
		current = root;
		return Optional.empty();
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
