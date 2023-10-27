package com.aa.act.interview.org;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;

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

		// your code here

		// check if the position with title exists, starting with CEO as root

		Queue<Position> queue = new LinkedList<Position>();

		queue.add(root);

		while (!queue.isEmpty()) {

			Position current_position = queue.remove();

			// set the employee in that position in the hierarchy

			if (current_position.getTitle() == title && !current_position.isFilled()) {

				// if we have a new employee, create that employee in the current position

				current_position.setEmployee(createEmployee(person));

				// return the new hired position
				return Optional.of(current_position);

			} else if (current_position.getDirectReports() != null && !current_position.getDirectReports().isEmpty()) {

				// this position has more directreports, so we add it to the queue to further
				// iterate
				queue.addAll(current_position.getDirectReports());

			}

		}

		// No vacant position found with the matching title in the organization
		return Optional.empty();

	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}

	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for (Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "  "));
		}
		return sb.toString();
	}
 
	/**
	 * method to create an employee to be hired from the given name
	 * 
	 * @param Name
	 * @return Optional<Employee>
	 */
	private Optional<Employee> createEmployee(Name person) {

		Random rand = new Random();
		int upperbound = 12;
		int random_num = rand.nextInt(upperbound);

		Employee employee = new Employee(random_num, person);

		return Optional.of(employee);
	}
}
