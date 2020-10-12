package seedu.duke.command.sprint;

import seedu.duke.model.Project;
import seedu.duke.model.Sprint;
import seedu.duke.model.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;

public class ViewSprintCommand extends SprintCommand {
    SprintList allSprint;

    public ViewSprintCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    /**
     * Abstract method that execute the command.
     *
     * @param ui UI that handles user interaction
     * @return Boolean - True if Bye command is executed
     */
    public boolean execute(Project proj, Ui ui) {

        allSprint = proj.getAllSprints();
        if (allSprint.updateCurrentSprint()) {
            int currentSprintNo = allSprint.getCurrentSprintIndex();
            Sprint currentSprint = allSprint.getSprint(currentSprintNo);
            ui.showToUserLn("------ Current Sprint ------");
            ui.showToUserLn("Sprint number: " + (currentSprintNo + 1));
            ui.showToUserLn("Sprint Goal: " + currentSprint.getGoal());
            ui.showToUserLn("Sprint period: " + currentSprint.getStartDate() + " to " + currentSprint.getEndDate());
            ui.showToUserLn("Days left: " + currentSprint.getEndDate().compareTo(LocalDate.now()));
            printSprintTask(proj, currentSprint, ui);

        } else {
            checkReason(proj,ui);
        }

        return false;
    }

    private void printSprintTask(Project proj, Sprint sprint, Ui ui) {
        Hashtable<Integer, ArrayList<String>> sprintTasks = sprint.getAllSprintTask();
        if (sprintTasks.size() == 0) {
            System.out.println("No task allocated to current sprint.");
            return;
        }
        ArrayList<String> users = new ArrayList<>();
        Set<Integer> keys = sprintTasks.keySet();

        System.out.println("Sprint Tasks: " + keys.size());
        for (int key: keys) {
            proj.getProjectBacklog().viewTask(Integer.toString(key), ui);
            users = sprintTasks.get(key);
            if (users.size() == 0) {
                ui.showToUserLn("No allocation.");
            } else {
                ui.showToUserLn("Allocated to:" + Arrays.toString(users.toArray()));
            }
        }
    }

    private void checkReason(Project proj, Ui ui) {
        if (allSprint.size() == 0) {
            ui.showToUserLn("You have yet to create your sprint.");
            return;
        }

        Sprint latestSprint = allSprint.getSprint(allSprint.size() - 1);
        if (DateTimeParser.diff(LocalDate.now(), proj.getEndDate()) == 0) {
            ui.showToUserLn("Project already ended on " + proj.getEndDate());
            return;
        } else if (DateTimeParser.diff(LocalDate.now(), proj.getStartDate()) > 0) {
            ui.showToUserLn("Project will start on " + proj.getStartDate());
            return;
        }

        if (DateTimeParser.diff(latestSprint.getEndDate(), LocalDate.now()) >= 0) {
            ui.showToUserLn("Latest sprint ended on " + latestSprint.getEndDate());
            ui.showToUserLn("Please create new sprint.");
            return;
        }

        Sprint current = allSprint.getSprint(0);
        if (DateTimeParser.diff(LocalDate.now(), current.getStartDate()) < 0) {
            ui.showToUserLn("First sprint will start on " + current.getStartDate());
        }
    }
}
