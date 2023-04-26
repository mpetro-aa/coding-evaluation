package com.aa.act.interview.org;

import java.util.ArrayList;
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
		
		ArrayList<Position> thelist = new ArrayList();
		thelist.add(root);
		
		Position thePosition = findPosition(thelist, title);
		
		if (thePosition != null) {
			thePosition.setEmployee(Optional.of(new Employee(Employee.ID++, person)));
			return Optional.of(thePosition);
		}
		
		return Optional.empty();
	}

	private Position findPosition(ArrayList<Position> thelist, String title) {
		Position thePosition = thelist.remove(0);
		
		if (thePosition.getTitle().equals(title)) {
			return thePosition;
		}
		else {
			for (Position p : thePosition.getDirectReports()) {
				thelist.add(p);
			}
			
			Position result = findPosition(thelist, title);
			if (result != null) return result;
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
