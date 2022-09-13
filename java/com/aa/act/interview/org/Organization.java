package com.aa.act.interview.org;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Organization {

	private Position root;
	private final Logger logger;
	private final HashSet<Name> employeeNameSet = new HashSet<>();
	
	public Organization() {
		root = createOrganization();
		logger = Logger.getLogger(Organization.class.getName());
	}
	
	protected abstract Position createOrganization();
	
	/**
	 * Utilizing {@link #findPosition(String)}, this method searches for a given position within the organization. If the
	 * position is found and not filled, the given person will be hired and the position will be returned. If the position
	 * is already filled, it will simply be returned without any new hire taking place.
	 * Finally, if no such position exists, an empty optional will be returned.
	 * @param person the person who is being hired
	 * @param title the title of the position they are meant to fill
	 * @return an optional describing the newly filled position, the unchanged position if the position was already filled,
	 * or an empty optional if no position with the given title is found
	 */
	public Optional<Position> hire(Name person, String title) {

		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("title cannot be null or empty");
		}

		Optional<Position> foundPosition = findPosition(title);

		if(foundPosition.isPresent() && !foundPosition.get().isFilled()) {
			foundPosition.get().setEmployee(Optional.of(new Employee(person)));
			employeeNameSet.add(person);
		} else if(foundPosition.isPresent()) {
				logger.log(Level.INFO, "could not hire" + person + "; position " + title + " is already filled by "
						+ foundPosition.get().getEmployee().get().getName());
		}

		return foundPosition;
	}

	/**
	 * Starting at the organization's root, this method will perform a BFS to find the position with the given title.
	 * If no such position is found, an empty Optional is returned.
	 * @param title the title of the position being searched for
	 * @return an optional describing the position with the given title. Nothing if no such title exists within the organization.
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

	/**
	 * @param person the name of the person to look for in the organization
	 * @return true if the person is hired, false otherwise
	 */
	public boolean isHired(Name person) {
		return employeeNameSet.contains(person);
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
