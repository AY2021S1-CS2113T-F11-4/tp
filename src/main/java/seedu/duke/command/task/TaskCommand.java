package seedu.duke.command.task;

import seedu.duke.common.Messages;
import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.TASK_ID;


public class TaskCommand {
    public void addTaskCommand(Hashtable<String, String> tasks, ArrayList<Project> projectList)
            throws DukeException {

        String title;
        String description;
        String priority;

        if (tasks.get(TITLE) != null) {
            title = tasks.get(TITLE);
        } else {
            throw new DukeException("Please enter a title!");
        }
        if (tasks.get(DESCRIPTION) != null) {
            description = tasks.get(DESCRIPTION);
        } else {
            throw new DukeException("Please enter a description!");
        }
        if (tasks.get(PRIORITY) != null) {
            priority = tasks.get(PRIORITY);
        } else {
            throw new DukeException("Please enter a priority!");
        }
        try {
            Project proj = projectList.get(0);
            Task task = new Task(title, description, priority);
            proj.backlog.addTask(task);

            Ui.showToUserLn(task.getTitle() + " has been added.");

        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }

    }

    public void deleteTaskCommand(ArrayList<String> taskId, ArrayList<Project> projectList) {

        try {
            Project proj = projectList.get(0);
            Collections.sort(taskId);
            int offset = 1;
            Task task;
            for (String id : taskId) {
                try {
                    int backlogId = Integer.parseInt(id) - offset;
                    if (backlogId < proj.backlog.size()) {
                        task = proj.backlog.getTask(backlogId);
                        Ui.showToUserLn("The corresponding task " + task.getTitle() + "has been removed.");
                        proj.backlog.removeTask(backlogId);
                        offset++;
                    } else {
                        Ui.showError(Messages.MESSAGE_INVALID_ID);
                    }
                } catch (NumberFormatException e) {
                    Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }
    }

    public void viewTaskCommand(ArrayList<String> taskId, ArrayList<Project> projectList) {

        try {
            Project proj = projectList.get(0);
            Ui.showToUserLn("The details of the tasks are as follows: ");
            for (String id : taskId) {
                Task task;
                try {
                    int backlogId = Integer.parseInt(id) - 1;
                    if (backlogId < proj.backlog.backlogTasks.size()) {
                        task = proj.backlog.getTask(backlogId);
                        Ui.showToUserLn("\t Title: " + task.getTitle());
                    } else {
                        Ui.showError(Messages.MESSAGE_INVALID_ID);
                    }
                } catch (NumberFormatException e) {
                    Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }
    }

    public void changeTaskPriorityCommand(Hashtable<String, String> tasks, ArrayList<Project> projectList)
            throws DukeException {

        Task task;
        int id;
        String priority;
        if (tasks.get(TASK_ID) != null) {
            id = Integer.parseInt(tasks.get(TASK_ID)) - 1;
        } else {
            throw new DukeException("Task ID entered is invalid!");
        }
        if (tasks.get(PRIORITY) != null) {
            priority = tasks.get(PRIORITY).trim();
        } else {
            throw new DukeException("Please enter a priority!");
        }
        try {
            Project proj = projectList.get(0);
            try {
                task = proj.backlog.getTask(id);
                task.setPriority(priority);
                Ui.showToUserLn("The task " + task.getTitle() + "has its priority changed to:");
                Ui.showToUserLn("\t" + task.getPriority());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task ID entered is out of bounds!");
            } catch (IllegalArgumentException e) {
                throw new DukeException("Priority entered is invalid!");
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }
    }

    public void doneTaskCommand(ArrayList<String> taskId, ArrayList<Project> projectList) {

        try {
            Project proj = projectList.get(0);
            for (String id : taskId) {
                Task task;
                try {
                    int backlogId = Integer.parseInt(id) - 1;
                    if (backlogId < proj.backlog.backlogTasks.size()) {
                        task = proj.backlog.getTask(backlogId);
                        task.setAsDone();
                        Ui.showToUserLn(task.getTitle() + "has been marked as done.");
                    } else {
                        Ui.showError(Messages.MESSAGE_INVALID_ID);
                    }
                } catch (NumberFormatException e) {
                    Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("There are no projects! Please create a project first.");
        }
    }
}

