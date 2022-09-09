package com.aa.act.interview.org.test;

import com.aa.act.interview.org.MyOrganization;
import com.aa.act.interview.org.Name;
import com.aa.act.interview.org.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    public void hireTest() {
        //Arrange + Act
        org.hire(new Name("Doug", "Parker"), "CEO");

        //Assert

    }

}
