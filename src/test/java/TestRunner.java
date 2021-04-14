import edu.purdue.cs.percolator.AutoGrader;
import edu.purdue.cs.percolator.StyleChecker;
import edu.purdue.cs.bridge.ioExample.HelloTest;

/**
 * {@link TestRunner} is used to run the test cases
 * inside of the Gradescope autograder.
 */
public class TestRunner {

    public static void main(String[] args) {
        System.setProperty("student.package.name", "");
        System.setProperty("solution.package.name", "edu.purdue.cs.bridge.ioExample.solution");

        // Add your test suites here
        Class<?>[] testSuites = {HelloTest.class};
        StyleChecker checker = StyleChecker.lint(
            "/autograder/submission",
            "/autograder/source/checkstyle_policy.xml"
        );
        AutoGrader.grade(testSuites)
            .onGradescope()
            .withMaxScore(100.0)
            .withStyleChecker(checker)
            .run();
    }

}
