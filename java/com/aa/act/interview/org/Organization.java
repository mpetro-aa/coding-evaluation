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

	
	public Optional<Position> findCurrentPosition(Position currPosition, String title) {
		Optional<Position> currHire = Optional.of(currPosition);
		if (currHire.get().getTitle().equals(title)) {
			return currHire;
		} else {
			for (Position p : currPosition.getDirectReports()) {
				currHire = findCurrentPosition(p, title);
				if (currHire.isPresent()) {
					return currHire;
				}
			}
		}
		return Optional.empty();
	}
	public Optional<Position> hire(Name person, String title) {
		Optional<Position> currPosition = findCurrentPosition(root, title);
		if(currPosition.isPresent()){
			Position p = currPosition.get();
			if(!p.isFilled()){
				currPosition.get().setEmployee(Optional.of(new Employee(person)));
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
