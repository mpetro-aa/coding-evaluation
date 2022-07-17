package com.aa.act.interview.org;

public class Employee {

	private int identifier;
	private  Name name;

	public Employee(int identifier, Name name) {
		if(name == null)
			throw new IllegalArgumentException("name cannot be null");
		this.identifier = identifier;
		this.name = name;
	}
// added empty constructor
	public Employee() {
	}

// added a new one param constructor for name
	public Employee(Name person) {
		this.name = person;
	}
	
	public int getIdentifier() {
		return identifier;
	}
	
	public Name getName() {
		return name;
	}

	@Override
	public String toString() {
		return name.toString() + ": " + identifier;
	}
}
