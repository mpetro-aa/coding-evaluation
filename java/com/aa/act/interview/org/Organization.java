package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

    private Position root;
    private int identifier = 1;

    public Organization() {
        root = createOrganization();
    }

    protected abstract Position createOrganization();
    public Optional<Position> hire(Name person, String title) {
        Employee employee = new Employee(identifier++, person);
        Optional<Position> position = Optional.of(isPositionAvailable(root, title));
        position.ifPresent(value -> value.setEmployee(Optional.of(employee)));
        return position;
    }

    public Position isPositionAvailable(Position pos, String title) {
        if (pos.getTitle().equals(title)) {
            return pos;
        } else {
            for (Position p : pos.getDirectReports()) {
                Position foundPosition = isPositionAvailable(p, title);
                if (foundPosition != null) {
                    return foundPosition;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return printOrganization(root, "");
    }

    private String printOrganization(Position pos, String prefix) {
        StringBuilder sb = new StringBuilder(prefix + "+-" + pos.toString() + "\n");
        for (Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "  "));
        }
        return sb.toString();
    }
}