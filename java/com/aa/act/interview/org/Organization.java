package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	private Position root;
	private int id = 1;

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
		// assign employee if title is CEO/root
		if (root.getTitle().equals(title)) {
			return assignEmployee(root, person);
		} else {
			// or visit direct reports
			return hire(root, person, title);
		}
	}

	private Optional<Position> hire(Position currentPosition, Name person, String title) {
		// visit all direct reports
		for (Position pos : currentPosition.getDirectReports()) {
			if (pos.getTitle().equals(title)) {
				// Assign employee if title matches
				return assignEmployee(pos, person);
			} else if (!pos.getDirectReports().isEmpty()) {
				// Recursively visit direct reports
				Optional<Position> newPos = hire(pos, person, title);
				if (newPos.isPresent()) {
					return newPos;
				}
			}
		}
		return Optional.empty();
	}

	private Optional<Position> assignEmployee(Position position, Name person) {
		if (!position.isFilled()) {
			position.setEmployee(Optional.of(new Employee(id, person)));
			id += 1;
			return Optional.of(position);
		} else {
			throw new IllegalArgumentException("Position is already filled.");
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
