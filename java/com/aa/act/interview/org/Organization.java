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
		//your code here
		
		Optional<Employee> employee = Optional.of(new Employee(++identifier, person));
		
		Position position = getPositionByTitle(root, title);
		if (position != null) 
		{
			position.setEmployee(employee);
			return Optional.of(position);
		}
	
		return Optional.empty();
	}

	public Position getPositionByTitle(Position position, String title) {

		if (position.getTitle().equals(title)) {
			
			return position;
		}

		else {
			for (Position pos : position.getDirectReports()) {

				Position pos1 = getPositionByTitle(pos, title);
				if (pos1 != null) 
					return pos1;
					
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
