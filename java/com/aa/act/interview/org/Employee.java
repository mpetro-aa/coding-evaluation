package com.aa.act.interview.org;

import java.util.concurrent.atomic.AtomicLong;

public class Employee {

	private final static AtomicLong idCounter = new AtomicLong(1);
	private long identifier;
	private Name name;

	public Employee(Name name) {
		if(name == null)
			throw new IllegalArgumentException("name cannot be null");
		this.identifier = idCounter.getAndIncrement();
		this.name = name;
	}
	
	public long getIdentifier() {
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
