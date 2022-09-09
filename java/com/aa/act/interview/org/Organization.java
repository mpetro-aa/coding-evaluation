package com.aa.act.interview.org;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public abstract class Organization {

	private Position root;
	
	public Organization() {
		root = createOrganization();
	}
	
	protected abstract Position createOrganization();
	
	/**
	 * Starting at the organization root, this method will perform a BFS for the position with the passed in title.
	 * If the position is found and not filled, this method will hire an employee with the given name and assign him/her
	 * to the given position title. Otherwise, an empty Optional<Position> is returned.
	 * 
	 * @param person the person who is being hired
	 * @param title the title of the position they are meant to fill
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {

		if (title == null || title.isEmpty())
			throw new IllegalArgumentException("title cannot be null or empty");

		Queue<Position> posQueue = new LinkedList<>();
		posQueue.add(root);

		while(!posQueue.isEmpty()) {
			if(title.equals(posQueue.peek().getTitle())) {
				Employee e = new Employee(person);
				posQueue.peek().setEmployee(Optional.of(e));
				return Optional.of(posQueue.peek());
			}
			posQueue.addAll(posQueue.poll().getDirectReports());
		}

		return Optional.empty();
	}

	public Position getRoot() {
		return root;
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
