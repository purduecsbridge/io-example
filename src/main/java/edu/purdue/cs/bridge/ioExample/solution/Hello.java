package edu.purdue.cs.bridge.ioExample.solution;

import java.util.Scanner;

/**
 * Purdue CS Bridge – Lab Example
 * Hello.java
 * Says hello!
 *
 * @author Andrew Davis, drew@cs.purdue.edu
 * @version 1.1, 03/31/2020
 */
public class Hello {

    /**
     * Prints hello to the given name.
     * @param args the name to greet
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();
        System.out.println("Hello, " + name + "!");
    }

}
