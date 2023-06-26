package com.aa.act.interview.org;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Organization {

    private Position root;
    private AtomicInteger employeeCounter; // Unique identifier for new employees
    
    public Organization() {
        root = createOrganization();
        employeeCounter = new AtomicInteger(0); // Initialize employee counter
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
        return hire(root, person, title);
    }
    
    private Optional<Position> hire(Position position, Name person, String title) {
        if(position.getTitle().equals(title) && !position.isFilled()) {
            Employee newEmployee = new Employee(employeeCounter.incrementAndGet(), person);
            position.setEmployee(Optional.of(newEmployee));
            return Optional.of(position);
        }
        
        for(Position report : position.getDirectReports()) {
            Optional<Position> result = hire(report, person, title);
            if(result.isPresent()) {
                return result;
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
        for(Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "  "));
        }
        return sb.toString();
    }
}
