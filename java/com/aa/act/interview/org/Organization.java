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
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {

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
