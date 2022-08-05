package com.aa.act.interview.org;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
public abstract class Organization {

	private Position root;
	private int employeeCount;
	public Organization() {
		root = createOrganization();
		employeeCount = 0;
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
		/**
		 * Algorithm derived:
		 * traverse the organization to determine if the position exists within the structure X
		 * if the position does not exist then return empty X
		 * 		else:
		 * Create a new hire employee X
		 * Set the employee to be associated with the given position
		 * return the given position if it exists within the collection, if it does not then return empty
		 */
		if(!toString().contains(title)) //Ensure that the given title exists in the organization
			return Optional.empty();
		Optional<Employee> newHire = Optional.of(new Employee(++employeeCount, person));
		if(title.equals(root.getTitle())) { //Determine if the new hire is the CEO hire
			root.setEmployee(newHire);
			return Optional.of(root);
		}
		Iterator<Position> iterator = root.getDirectReports().iterator();
		return traverseInnerSets(root, iterator, title, newHire);
			//System.out.println("Current" + temp); //Used to determine how many levels are printed
	}

	/**
	 * This method was created to traverse the inner sets sent from the parent node to search
	 * for a given position
	 *
	 * @param temp the parent node
	 * @param iterator the iterator to traverse over the  children nodes
	 * @param title the position that this method will search for
	 * @param newHire the employee that will fill the position defined by title
	 * @return
	 */
	private Optional<Position> traverseInnerSets(Position temp, Iterator<Position> iterator, String title,
												 Optional<Employee> newHire) {
		while(iterator.hasNext()) {
			temp = iterator.next();
			if(temp.getTitle().equals(title)) {
				temp.setEmployee(newHire);
				return Optional.of(temp);
			}
			if(printOrganization(temp, "").contains(title)) {
				return traverseInnerSets(temp, temp.getDirectReports().iterator(), title, newHire);
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
