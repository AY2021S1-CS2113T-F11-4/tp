package seedu.duke.command.sprint;

import seedu.duke.common.Messages;
import seedu.duke.model.Project;
import seedu.duke.model.Sprint;
import seedu.duke.model.SprintList;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.Hashtable;

public class CreateSprintCommand extends SprintCommand {
    SprintList allSprint;

    /**
     * Creates a new DELETE command with arguments.
     */
    public CreateSprintCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    /**
     * Abstract method that execute the command.
     *
     * @param ui UI that handles user interaction
     * @return Boolean - True if Bye command is executed
     */
    public boolean execute(Project proj, Ui ui) {
        if (validateParams()) {
            allSprint = proj.getAllSprints();
            if (allSprint.size() == 0) {
                createFirstSprint(proj, ui);
            } else {
                createSubsequentSprint(proj, ui);
            }
        }
        return false;
    }

    private void createFirstSprint(Project proj, Ui ui) {

        LocalDate sprintStart = LocalDate.now();
        if (!parameters.get("start").isEmpty()) {
            sprintStart = DateTimeParser.parseDate(parameters.get("start"));
        }
        LocalDate sprintEnd = sprintStart.plusDays(proj.getSprintLength() - 1);
        String sprintGoal = parameters.get("goal");
        allSprint.addSprint(sprintGoal, sprintStart, sprintEnd);

        LocalDate projEndDate = sprintStart.plusDays(proj.getProjectDuration() - 1);
        proj.setStartDate(sprintStart);
        proj.setEndDate(projEndDate);

        ui.showToUserLn("Project will start along with the newly created sprint");
        System.out.println("Project period: " + sprintStart + " to " + projEndDate);
        printCreatedSprint(ui);

    }

    private void createSubsequentSprint(Project proj, Ui ui) {

        String sprintGoal = parameters.get("goal");
        Sprint prevSprint = allSprint.getSprint(allSprint.size() - 1);
        LocalDate sprintStart = prevSprint.getEndDate().plusDays(1);
        if (DateTimeParser.diff(proj.getEndDate(),sprintStart) >= 0) {
            ui.showToUserLn("\nAll sprints are already created.");
            return;
        }
        LocalDate sprintEnd = sprintStart.plusDays(proj.getSprintLength() - 1);
        allSprint.addSprint(sprintGoal, sprintStart, sprintEnd);
        if (!parameters.get("start").isEmpty()) {
            ui.showToUserLn(Messages.MESSAGE_CREATE_SUB_SPRINT);
        }
        printCreatedSprint(ui);
    }

    private boolean validateParams() {
        return !parameters.get("goal").isEmpty();
    }

    private void printCreatedSprint(Ui ui) {
        Sprint created = allSprint.getSprint(allSprint.size() - 1);
        ui.showToUserLn("\n--- New Sprint ---");
        ui.showToUserLn("Goal: " + created.getGoal());
        ui.showToUserLn("Start Date: " + created.getStartDate());
        ui.showToUserLn("End Date: " + created.getEndDate());
    }
}
