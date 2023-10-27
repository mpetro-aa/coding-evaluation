package com.aa.act.interview.org;

public class Name {

    private String first;
    private String last;
    
    public Name(String first, String last) {
    	
    	// adding some extra input validation
        if(first == null || first.isBlank())
            throw new IllegalArgumentException("first name cannot be null or empty");
        if(last == null || first.isBlank())
            throw new IllegalArgumentException("last name cannot be null or empty");
        this.first = first;
        this.last = last;
    }
    
    public String getFirst() {
        return first;
    }
    
    public String getLast() {
        return last;
    }

    @Override
    public String toString() {
        return first + " " + last;
    }
}
