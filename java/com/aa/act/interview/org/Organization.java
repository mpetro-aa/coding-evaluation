package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	private Position root;
	
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
		//your code here
		Position result = findPosition(root, title);
		
		// Checks the result and returns an Optional<Employee> if one was found.
		if (result != null) {
			result.setEmployee(Optional.of(new Employee(0, person)));
			return Optional.of(result);
		}
		
		// Target was not found by the search so and empty optional is returned.
		return Optional.empty();
	}
	
	/*
	 * Finds the position in the organization tree who's title matches the given String
	 * using a depth-first tree traversal
	 * 
	 * @param target
	 * @param title
	 * @return the wanted position or null if not found.
	 */
	public Position findPosition(Position target, String title) {
		
		if (target.getTitle().equals(title)) {
			return target;
		} else if (!target.getDirectReports().isEmpty()) {			
			for(Position position : target.getDirectReports()) {
				Position newTarget = findPosition(position, title);
				if (newTarget != null)
					return newTarget;
			}
		}
		
		return null;
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
