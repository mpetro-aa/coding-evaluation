package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {package com.aa.act.interview.org;

import java.util.Optional;
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
        Employee employee = new Employee(generateEmployeeId(), person);
        return getEmployeePosition(title, root, employee);
    }

    private Optional<Position> getEmployeePosition(String title, Position position, Employee employee) {
        if (title.equals(position.getTitle())) {
            position.setEmployee(Optional.of(employee));
            return Optional.of(position);
        }

        for (Position childPosition : position.getDirectReports()) {
            if (childPosition.getTitle().equals(title)) {
                childPosition.setEmployee(Optional.of(employee));
                return Optional.of(childPosition);
            } else {
                Optional<Position> matchedPosition = getEmployeePosition(title, childPosition, employee);
                if(matchedPosition.isPresent()) {
                    return matchedPosition;
                }
            }
        }
        return Optional.empty();
    }

    private int generateEmployeeId() {
        return new Random().nextInt(1001);
    }

    @Override
    public String toString() {
        return printOrganization(root, "");
    }

    private String printOrganization(Position pos, String prefix) {
        StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
        for(Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "  "));
        }
        return sb.toString();
    }
}


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
        return Optional.empty();
    }

    @Override
    public String toString() {
        return printOrganization(root, "");
    }
    
    private String printOrganization(Position pos, String prefix) {
        StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
        for(Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "  "));
        }
        return sb.toString();
    }
}
