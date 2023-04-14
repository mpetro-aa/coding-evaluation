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
	public Optional<Position> findPosition(Position newPostion, String title) {
		Optional<Position> newHire = Optional.of(newPosition);
		if (newHire.get().getTitle().equals(title)) {
			return newHire;
		} else {
			for (Position pos : newPosition.getDirectReports()) {
				newHire = findPosition(pos, title);
				if (newHire.isPresent()) {
					return newHire;
				}
			}
		}
		return Optimal.empty();
	}	
	public Optional<Position> hire(Name person, String title) {
		Optional<Position> newPosition = findPosition(root, title);
		if (newPosition.isPresent()) {
			Position pos = newPosition.get();
			if (!pos.isFilled()) {
				newPosition.get().setEmployee(Optional.of(new Employee(person)));
			}
		}		
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
