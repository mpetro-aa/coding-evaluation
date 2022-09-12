package com.aa.act.interview.org.test;

import com.aa.act.interview.org.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class OrganizationTest {

    private Organization org = new MyOrganization();

    /**
     * Resets the organization after each test to ensure clean testing and no hires from one test appear in another.
     */
    @AfterEach
    public void resetOrg() {
        org = new MyOrganization();
    }

    // ---- Tests ----

    /**
     * This test ensures that the hire method returns an optional describing the proper return value as well as ensures
     * that the employee exists within the organization.
     */
    @Test
    public void hireOneTest() {
        //Arrange
        Name doug = new Name("Doug", "Parker");
        String title = "CEO";

        //Act
        Optional<Position> pos = org.hire(doug, title);

        //Assert
        Assertions.assertTrue(pos.isPresent()
                && pos.get().getEmployee().get().getName().getFirst().equals(doug.getFirst())
                && pos.get().getEmployee().get().getName().getLast().equals(doug.getLast())
                && pos.get().getTitle().equals(title)
                && org.isHired(doug));
    }

    /**
     * This test ensures that many people can be hired by an organization, that all return values from the hire method
     * are on target, and that the people exist within the organization after being hired.
     */
    @Test
    public void hireManyTest() {
        //Arrange
        Name doug = new Name("Doug", "Parker");
        Name mike = new Name("Mike", "Harrison");
        Name jake = new Name("Jake", "Berk");
        Name tom = new Name("Tom", "Barth");
        Name anthony = new Name("Anthony", "Davis");

        //Act
        Optional<Position> p1 = org.hire(doug, "CEO");
        Optional<Position> p2 = org.hire(mike, "VP Marketing");
        Optional<Position> p3 = org.hire(jake, "VP Sales");
        Optional<Position> p4 = org.hire(tom, "VP Finance");
        Optional<Position> p5 = org.hire(anthony, "CIO");

        //Assert
        boolean allPresent = p1.isPresent() && p2.isPresent() && p3.isPresent() && p4.isPresent() && p5.isPresent();
        boolean allHired = org.isHired(doug) && org.isHired(mike) && org.isHired(jake) && org.isHired(tom) && org.isHired(anthony);

        Assertions.assertTrue(allPresent && allHired);
    }

    /**
     * This test ensures the hire method does not override a position that is already filled and returns an optional
     * describing the already filled position. It additionally ensures that the original employee remains within the
     * organization while the new employee that was not hired does not.
     */
    @Test
    public void hireWhenAlreadyFilledTest() {
        //Arrange
        Name jake = new Name("Jake", "Gall");
        Name david = new Name("David", "harrison");
        String title = "CEO";

        //Act
        org.hire(david, title);
        Optional<Position> hire = org.hire(jake, title);

        //Assert
        Assertions.assertTrue(hire.isPresent()
                        && hire.get().getEmployee().get().getName().getFirst().equals(david.getFirst())
                        && hire.get().getEmployee().get().getName().getLast().equals(david.getLast())
                        && org.isHired(david) && !org.isHired(jake));
    }

    /**
     * This test ensures that hiring for a non-existent position returns an empty optional and does not place the person
     * in the organization.
     */
    @Test
    public void hireForNonExistentPositionTest() {
        //Arrange
        Name doug = new Name("Doug", "Parker");

        //Act
        Optional<Position> hire = org.hire(doug, "CFO");

        //Assert
        Assertions.assertTrue(hire.isEmpty() && !org.isHired(doug));
    }

    /**
     * Ensures the organization cannot hire an employee given a null name
     */
    @Test
    public void hireNameIsNullTest() {
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
