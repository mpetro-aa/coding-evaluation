package com.aa.act.interview.org.test;

import com.aa.act.interview.org.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class OrganizationTest {

    private Organization org = new MyOrganization();

    private void hireMany() {
        org.hire(new Name("Doug", "Parker"), "CEO");
        org.hire(new Name("Robert", "Isom"), "President");
        org.hire(new Name("Gandalf", "Gray"), "Director Enterprise Architecture");
        org.hire(new Name("Head", "Geek"), "Director Customer Technology");
        org.hire(new Name("Jane", "Smith"), "VP Marketing");
        org.hire(new Name("Jim", "Jones"), "VP Sales");
        org.hire(new Name("Bean", "Counter"), "VP Finance");
        org.hire(new Name("Maya", "Liebman"), "CIO");
        org.hire(new Name("Danielle", "Hoover"), "VP Technology");
        org.hire(new Name("Scape", "Goat"), "VP Infrastructure");
        org.hire(new Name("Slick", "Willie"), "Salesperson");
    }

    private boolean searchOrg(Position root, Name name, String title) {
        Queue<Position> posQueue = new LinkedList<>();
        posQueue.add(root);

        while(!posQueue.isEmpty()) {
            Position currPos = posQueue.peek();
            if(title.equals(currPos.getTitle()) && currPos.isFilled()) {
                Name empName = currPos.getEmployee().get().getName();
                return name.getFirst().equals(empName.getFirst()) && name.getLast().equals(empName.getLast());
            }
            posQueue.addAll(posQueue.poll().getDirectReports());
        }

        return false;
    }

    @Test
    public void hireOneTest() {
        //Arrange + Act
        Name doug = new Name("Doug", "Parker");
        org.hire(doug, "CEO");

        //Assert
        Assertions.assertTrue(searchOrg(org.getRoot(), new Name("Doug", "Parker"), "CEO"));
    }



}
