package com.aa.act.interview.org;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public abstract class Organization {
    private int employeeIdentifier;
    private Position root;

    public Organization() {
        employeeIdentifier = 0;
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

        Position hiredPosition = bfsGetPositionPerTitle(title);
        hiredPosition.setEmployee(Optional.of(new Employee(++employeeIdentifier, person)));
        return Optional.ofNullable(hiredPosition);
        //return Optional.empty();
    }

    public Position bfsGetPositionPerTitle(String title) {
        /* I used breadth first search algorithm to find the position for the given title
          because since we have hierarchy of positions so the better approach is to find the shortest path
          Time Complexity = O( V + E)
          Space Complexity = O(V)
        */
        HashSet<Position> visited = new HashSet<>();
        Queue<Position> adjacent = new LinkedList<>();
        adjacent.add(root);

        // Loop through all adjacent positions
        while (!adjacent.isEmpty()) {
            Position current = adjacent.remove();
            if (current.getTitle() == title) {
                return current;
            }
            for (Position position : current.getDirectReports()) {
                if (!visited.contains(position)) {
                    adjacent.add(position);
                }
            }
            visited.add(current);
        }
        throw new IllegalArgumentException("there is no position with this title");

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
