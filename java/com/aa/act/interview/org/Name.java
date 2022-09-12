package com.aa.act.interview.org;

import java.util.Objects;

public class Name {

	private String first;
	private String last;
	
	public Name(String first, String last) {
		if(first == null)
			throw new IllegalArgumentException("first name cannot be null");
		if(last == null)
			throw new IllegalArgumentException("last name cannot be null");
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

	@Override
	public int hashCode() {
		return Objects.hash(first, last);
	}
}
