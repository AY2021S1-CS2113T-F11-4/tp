package seedu.duke.command.sprint;

import seedu.duke.model.Project;
import seedu.duke.model.Sprint;
import seedu.duke.model.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.TextUi;

import java.time.LocalDate;
import java.util.Hashtable;

public class AddSprintTaskCommand extends SprintCommand {
    SprintList allSprint;

    public AddSprintTaskCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    public boolean execute(Project proj, TextUi ui) {
        allSprint = proj.getAllSprints();
        if (allSprint.updateCurrentSprint()) {
            int currentSprintNo = allSprint.getCurrentSprintIndex();
            Sprint currentSprint = allSprint.getSprint(currentSprintNo);

            ui.showToUser("Tasks added: ");
            for (int i = 0; i < parameters.size(); i++) {
                String taskId = parameters.get(Integer.toString(i));
                currentSprint.addSprintTask(Integer.parseInt(taskId));
                proj.getProjectBacklog().viewTask(taskId, ui);

            }
        } else {
            checkReason(proj,ui);
        }
        return false;
    }

    public void checkReason(Project proj, TextUi ui) {
        if (allSprint.size() == 0) {
            ui.showToUser("You have yet to create your sprint.");
            return;
        }

        Sprint latestSprint = allSprint.getSprint(allSprint.size() - 1);
        if (DateTimeParser.diff(LocalDate.now(), proj.getEndDate()) == 0) {
            ui.showToUser("Project already ended on " + proj.getEndDate());
            return;
        } else if (DateTimeParser.diff(LocalDate.now(), proj.getStartDate()) > 0) {
            ui.showToUser("Project will start on " + proj.getStartDate());
            return;
        }

        if (DateTimeParser.diff(latestSprint.getEndDate(), LocalDate.now()) >= 0) {
            ui.showToUser("Latest sprint ended on " + latestSprint.getEndDate());
            ui.showToUser("Please create new sprint.");
            return;
        }

        Sprint current = allSprint.getSprint(0);
        if (DateTimeParser.diff(LocalDate.now(), current.getStartDate()) < 0) {
            ui.showToUser("First sprint will start on " + current.getStartDate());
        }
    }

}
