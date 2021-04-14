package edu.purdue.cs.bridge.ioExample;

import edu.purdue.cs.percolator.TestCase;
import edu.purdue.cs.percolator.util.DebugUtilities;
import edu.purdue.cs.percolator.util.SetupUtilities;
import edu.purdue.cs.percolator.util.StringUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

/**
 * Purdue CS Bridge – Lab Example
 * HelloTest.java
 * Test cases for the Hello class.
 *
 * @version 07/20/2020
 */
public class HelloTest {

    private static Method studentMainMethod;
    private static Method solutionMainMethod;
    private static ByteArrayOutputStream actualOut;
    private static ByteArrayOutputStream expectedOut;
    private final static InputStream ORIGINAL_IN = System.in;
    private final static PrintStream ORIGINAL_OUT = System.out;

    /**
     * Sets up the classes/methods for the test cases.
     */
    @BeforeClass
    public static void setup() {
        String testPackage = SetupUtilities.getPackageToTest();
        String solutionPackage = System.getProperty("solution.package.name", "solution") + ".";

        Class<?> studentHello = null;

        try {
            studentHello = Class.forName(testPackage + "Hello");
        } catch (ClassNotFoundException e) {
            Assert.fail("Make sure you submitted the Hello.java file.");
        }

        try {
            studentMainMethod = studentHello.getDeclaredMethod("main", String[].class);
            studentMainMethod.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            Assert.fail("Make sure you do not change any of the skeleton code in Hello.java");
        }

        try {
            Class<?> solutionHello = Class.forName(solutionPackage + "Hello");
            solutionMainMethod = solutionHello.getDeclaredMethod("main", String[].class);
            studentMainMethod.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            DebugUtilities.failWithoutCause("initializer");
        }

        actualOut = new ByteArrayOutputStream();
        expectedOut = new ByteArrayOutputStream();
    }

    /**
     * Runs a test on the Hello program with the given input.
     * @param input the test input
     * @param message the assertion message to display on failure
     */
    private static void testHello(String input, String message) {
        final String testInput = input + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        System.setOut(new PrintStream(actualOut));

        try {
            studentMainMethod.invoke(null, (Object) null);
        } catch (ReflectiveOperationException e) {
            DebugUtilities.failWithStackTrace(e.getCause());
        }

        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        System.setOut(new PrintStream(expectedOut));

        try {
            solutionMainMethod.invoke(null, (Object) null);
        } catch (ReflectiveOperationException e) {
            DebugUtilities.failWithoutCause("`testHello`");
        }

        System.setIn(ORIGINAL_IN);
        System.setOut(ORIGINAL_OUT);

        String actual = actualOut.toString();
        String expected = expectedOut.toString();

        actualOut.reset();
        expectedOut.reset();

        Assert.assertTrue(message, StringUtilities.fuzzyEquals(expected, actual));
    }

    /**
     * Tests the {@code Hello} program.
     *
     * Input:
     *     "Alice"
     */
    @Test(timeout = 1000)
    @TestCase(name = "Hello: Test correctness 1", points = 25.0)
    public void testHelloOne() {
        testHello("Alice", "Make sure you've correctly implemented the `hello` method.");
    }

    /**
     * Tests the {@code Hello} program.
     *
     * Input:
     *     "Bob"
     */
    @Test(timeout = 1000)
    @TestCase(name = "Hello: Test correctness 2", points = 25.0)
    public void testHelloTwo() {
        testHello("Bob", "Make sure you've correctly implemented the `hello` method.");
    }

    /**
     * Tests the {@code Hello} program with spaces and special characters.
     *
     * Input:
     *     "fdsa 32 !$$"
     */
    @Test(timeout = 1000)
    @TestCase(name = "Hello: Test special characters", points = 25.0)
    public void testHelloSpecialCharacters() {
        testHello("fdsa 32 !$$", "Make sure you've checked for edge cases.");
    }

    /**
     * Tests the {@code Hello} program with an empty string.
     *
     * Input:
     *     ""
     */
    @Test(timeout = 1000)
    @TestCase(name = "Hello: Test empty name", points = 25.0)
    public void testHelloEmptyInput() {
        testHello("", "Make sure you've checked for edge cases.");
    }

}
