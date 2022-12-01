package com.aa.act.interview.org;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Organization {

	private Position root;
	private List<Position> positionList= new ArrayList<>();
	
	public Organization() {
		root = createOrganization();
		organizationPositionsList(root);
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
		Optional<Position> position = getPosition(title);
		if(!position.isPresent())
			return Optional.empty();
		 Position pos = position.get();
		if(pos.isFilled())
			throw new OrganizationalHireException("This position is already filled, try looking another position");
		Employee employee= new Employee(generateCustomerIdentifier(),person);
		pos.setEmployee(Optional.of(employee));
		return Optional.of(pos);
	}
    public Optional<Position> getPosition(String title){
		for(Position p:positionList){
			if(p.getTitle().equalsIgnoreCase(title)){
				return Optional.of(p);
			}

		}
		return Optional.empty();
	}

	public void organizationPositionsList(Position position){
		Optional<Position> pos=Optional.empty();
		positionList.add(position);
		for(Position p : position.getDirectReports()) {
			positionList.add(p);
			organizationPositionsList(p);
		}

	}
   public int generateCustomerIdentifier(){
	   int rand = (int)Math.floor(Math.random()*(1000-100+1)+100);
	   return rand;
   }
	@Override
	public String toString() {
		return printOrganization(root, "");
	}
	
	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for(Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}
