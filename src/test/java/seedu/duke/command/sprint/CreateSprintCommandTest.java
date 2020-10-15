package seedu.duke.command.sprint;

import org.junit.jupiter.api.Test;
import seedu.duke.parser.Parser;
import seedu.duke.project.Project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateSprintCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void createSprint_missingFields_returnErrorMessage() {
        setUpStreams();

        ArrayList<Project> projectList = new ArrayList<>();
        Parser parser = new Parser();
        Project proj = new Project(null, null, "90", "10");
        projectList.add(proj);

        String command = "sprint /create";
        parser.parser(command, projectList);
        String expectedOutput = "What is the goal for this sprint?" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());

        restoreStreams();
    }
}