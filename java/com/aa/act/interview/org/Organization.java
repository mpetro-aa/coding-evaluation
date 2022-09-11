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
	 * Utilizing {@link #findPosition(String)}, this method searches for a given position within the organization. If the
	 * position is found and not filled, the given person will be hired and the position will be returned. If the position
	 * is already filled, it will simply be returned. Finally, if no such position exists, an empty optional will be returned.
	 * @param person the person who is being hired
	 * @param title the title of the position they are meant to fill
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {

		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("title cannot be null or empty");
		}

		Optional<Position> foundPosition = findPosition(title);

		if(foundPosition.isPresent() && !foundPosition.get().isFilled()) {
			foundPosition.get().setEmployee(Optional.of(new Employee(person)));
		}

		return foundPosition;
	}

	/**
	 * Starting at the organization's root, this method will perform a BFS to find the position with the given title.
	 * If no such position is found, an empty Optional is returned.
	 * @param title the title of the position being searched for
	 * @return if a position with the given title exists then an optional describing that position is returned. Otherwise,
	 * an empty Optional is returned.
	 */
	public Optional<Position> findPosition(String title) {
		Queue<Position> posQueue = new LinkedList<>();
		posQueue.add(root);

		while(!posQueue.isEmpty()) {
			if(title.equals(posQueue.peek().getTitle())) {
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
