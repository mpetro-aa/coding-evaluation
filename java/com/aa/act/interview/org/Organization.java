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
	
	/*
	 * Finds the largest Identifier number in the given organization and returns it
	 * 
	 * @param Position position
	 * @param int result
	 * @return an integer primitive relating to the largest Identifier in the organization or 
	 * 		   the given integer primitive "result" if none are found.
	 */
	public int findMaxID(Position position, int result) {
		
		// compares the given position's employee's ID, if present, to the integer primitive parameter
		if (position.getEmployee().isPresent()) {
				if (position.getEmployee().get().getIdentifier() > result) {
					result = position.getEmployee().get().getIdentifier();
				}
		}
		
		// Checks the set of direct reports, if any, recursively and depth first
		if (!position.getDirectReports().isEmpty()) {
			for(Position newPosition : position.getDirectReports()) {
				int newResult = findMaxID(newPosition, result);
				if (newResult > result) {
					result = newResult;
				}
			}
		}

		return result;
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
