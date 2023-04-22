package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	//initializing static int so identifier for each can be instantiated and unique
    static int identifier = 1;
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
        //create option object for position based on the title; i.e. if it exists
        Optional<Position> position = getPositionFromTitle(title, root);
        //if the value is present for the title, set the @person as employee
        position.ifPresent(a -> a.setEmployee(Optional.of(new Employee(identifier++, person))));

        return position;
    }

    /**
     * method to get the Position Object from title
     *
     * @param title
     * @param position
     * @return if the position exists for @title return the Position else return empty
     */
    public Optional<Position> getPositionFromTitle(String title, Position position) {
        //optional object from position
        Optional<Position> currentPosition = Optional.of(position);
        //if the title matched current position return the position to update its employee
        if (currentPosition.get().getTitle().equals(title)) {
            return currentPosition;
        }
        //else get the position's direct reports to check and see if the title matches any
		//executing this is loop until the tile matches the position
        else {
        	//loop used as multiple direct reports can occure
            for (Position p : position.getDirectReports()) {
                currentPosition = getPositionFromTitle(title, p);
                if (currentPosition.isPresent()) {
                    return currentPosition;
                }
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
        for (Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "\t"));
        }
        return sb.toString();
    }
}
