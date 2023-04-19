package com.aa.act.interview.org;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Organization {

	private Position root;

	public Organization() {

		root = createOrganization();
	}
	
	protected abstract Position createOrganization();
	int id = 1;

	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */


	public Optional<Position> hire(Name person, String title) {
		// Check if the argument title and name not null
		Optional<Name> optionalName = Optional.ofNullable(person);
    	Optional<String> optionalTitel = Optional.ofNullable(title);

    	if (optionalTitel.isPresent() && optionalName.isPresent()) {
			// Method findEmplPosition to check if this title in the list
			Position position = findEmplTitle(root, person ,title);
		// if title found hire the employee
			Optional<Employee> newEmployee = Optional.of(new Employee(this.id++, person));
			position.setEmployee(newEmployee);

			return Optional.of(position);
		}
		return Optional.empty();
	}
//------------------------------------------------------------------------------------------------------------
	private Position findEmplTitle(Position root ,Name person, String title) {
		// Check if the position is found
		if (title.equals(root.getTitle())) {
			return root;
		} else {
			// If the title not equal check in the position list
			for (Position findPos : root.getDirectReports()) {
				// Recursion findEmplPosition method until find the title or finish list traversed
				Position findTitle = findEmplTitle(findPos, person ,title);
				// if the title not found
				if (findTitle == null) {
					continue;
				} else {
					return findTitle;
				}
			}
		}
		return null;
	}
//-------------------------------------------------------------------------------------------------------------------
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
