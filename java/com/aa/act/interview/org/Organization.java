package com.aa.act.interview.org;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Organization {

    private AtomicInteger id = new AtomicInteger(); //emp id for new hires (assigned sequentially)
    private Map<String, Position> positions = new HashMap<>();  //stores position obj by title for efficient lookup O(1)
    private Position root;
    
    public Organization() {
        root = createOrganization();
        // single call to populate all the positions in the map during org creation/setup
        populateAllPositions(root);
    }

    /**
     * update map with all the positions in the org starting at the root
     * (this function uses recursion but is still efficient as the computation is done only once at the beginning)
     *
     * @param position
     */
    private void populateAllPositions(Position position) {
        Optional.ofNullable(position)
                .ifPresent(p -> {
                            positions.put(p.getTitle(), p);
                            Optional.ofNullable(p.getDirectReports())
                                    .ifPresent(drs ->
                                            // add position(s) of direct report(s) recursively
                                            drs.forEach(this::populateAllPositions));
                        }
                );
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
        return Optional.ofNullable(positions.get(title))
                .map(position -> {
                            position.setEmployee(Optional.of(new Employee(id.incrementAndGet(), person)));
                            // update position in map to reflect hire, this isn't necessary but more of a nice-to-have
                            positions.put(title, position);
                            return position;
                        }
                );
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
