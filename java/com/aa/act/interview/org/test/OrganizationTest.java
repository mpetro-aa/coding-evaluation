package com.aa.act.interview.org.test;

import com.aa.act.interview.org.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OrganizationTest {

    private Organization org = new MyOrganization();

    /**
     * A utility method used to search within an organization given its root.
     * @param root root of the organization to be searched
     * @param name name of the employee to search for
     * @param title title of the position the given employee should have
     * @return
     */
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

    // ---- Tests ----

    /**
     * Hires only a single employee and ensures their existance in the organization
     */
    @Test
    public void hireOneTest() {
        //Arrange
        Name doug = new Name("Doug", "Parker");

        //Act
        org.hire(doug, "CEO");

        //Assert
        Assertions.assertTrue(searchOrg(org.getRoot(), new Name("Doug", "Parker"), "CEO"));
    }

    /**
     * Hires many employees and ensures that all employees were successfully hired in their proper positions
     */
    // ---- Tests ----
    @Test
    public void hireManyTest() {
        //Arrange
        List<Name> nameList = new ArrayList<>();
        nameList.add(new Name("Doug", "Parker"));
        nameList.add(new Name("Robert", "Isom"));
        nameList.add(new Name("Gandalf", "Gray"));
        nameList.add(new Name("Head", "Geek"));
        nameList.add(new Name("Jane", "Smith"));
        nameList.add(new Name("Jim", "Jones"));
        nameList.add(new Name("Bean", "Counter"));
        nameList.add(new Name("Maya", "Liebman"));
        nameList.add(new Name("Danielle", "Hoover"));
        nameList.add(new Name("Scape", "Goat"));
        nameList.add(new Name("Slick", "Willie"));

        List<String> posList = new ArrayList<>();
        posList.add("CEO");
        posList.add("President");
        posList.add("Director Enterprise Architecture");
        posList.add("Director Customer Technology");
        posList.add("VP Marketing");
        posList.add("VP Sales");
        posList.add("VP Finance");
        posList.add("CIO");
        posList.add("VP Technology");
        posList.add("VP Infrastructure");
        posList.add("Salesperson");

        //Act
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

        //Assert
        AtomicInteger index = new AtomicInteger();
        nameList.forEach(name -> Assertions.assertTrue(searchOrg(org.getRoot(), name, posList.get(index.getAndIncrement()))));
    }

    /**
     * Ensures the organization cannot hire an employee given a null name
     */
    @Test
    public void hireOneNullTest() {
        //Arrange
        Name noName = null;

        //Act
        Exception exc = Assertions.assertThrows(IllegalArgumentException.class, () -> org.hire(noName, "CEO"));

        //Assert
        Assertions.assertEquals("name cannot be null", exc.getMessage());
    }

    /**
     * Ensures organization cannot hire employee given a null title
     */
    @Test
    public void hireTitleIsNullTest() {
        //Arrange
        Name doug = new Name("Doug", "Parker");

        //Act
        Exception exc = Assertions.assertThrows(IllegalArgumentException.class, () -> org.hire(doug, null));

        //Assert
        Assertions.assertEquals("title cannot be null or empty", exc.getMessage());
    }

    /**
     * Ensures organization cannot hire an employee given an empty string
     */
    @Test
    public void hireTitleIsEmptyTest() {
        //Arrange
        Name doug = new Name("Doug", "Parker");

        //Act
        Exception exc = Assertions.assertThrows(IllegalArgumentException.class, () -> org.hire(doug, null));

        //Assert
        Assertions.assertEquals("title cannot be null or empty", exc.getMessage());
    }

}
