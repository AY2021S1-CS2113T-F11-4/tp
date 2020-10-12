package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;
import seedu.duke.task.Task;
import seedu.duke.ui.old.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.TASK_ID;


public class TaskCommand {
    public void addTaskCommand(Hashtable<String, String> tasks, Ui ui, ArrayList<Project> projectList)
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

            //ui.printTaskAdded(proj.backlog.getTask(proj.backlog.size() - 1));

        } catch (IndexOutOfBoundsException e) {
            //ui.printError("There are no projects! Please create a project first.");
        }

    }

    public void deleteTaskCommand(ArrayList<String> taskId, ArrayList<Project> projectList) {

        try {
            Project proj = projectList.get(0);
            Collections.sort(taskId);
            int offset = 1;
            for (String id : taskId) {
                try {
                    int backlogId = Integer.parseInt(id) - offset;
                    if (backlogId < proj.backlog.size()) {
                        //ui.printTaskRemoved(proj.backlog.getTask(backlogId));
                        proj.backlog.backlogTasks.remove(backlogId);
                        offset++;
                    } else {
                        //Ui.displayInvalidId();
                    }
                } catch (NumberFormatException e) {
                   // Ui.printError("Task ID entered is not an integer!");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            //Ui.printError("There are no projects! Please create a project first.");
        }
    }

    public void viewTaskCommand(ArrayList<String> taskId, ArrayList<Project> projectList) {

        try {
            Project proj = projectList.get(0);
            for (String id : taskId) {
                Task task;
                try {
                    int backlogId = Integer.parseInt(id) - 1;
                    if (backlogId < proj.backlog.backlogTasks.size()) {
                        task = proj.backlog.getTask(backlogId);
                        //ui.displayTask(task);
                    } else {
                        //Ui.displayInvalidId();
                    }
                } catch (NumberFormatException e) {
                    //Ui.printError("Task ID entered is not an integer!");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            //Ui.printError("There are no projects! Please create a project first.");
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
                //ui.printPriorityChanged(proj.backlog.getTask(id));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task ID entered is out of bounds!");
            } catch (IllegalArgumentException e) {
                throw new DukeException("Priority entered is invalid!");
            }
        } catch (IndexOutOfBoundsException e) {
            //Ui.printError("There are no projects! Please create a project first.");
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
                        //Ui.displayTaskDone(proj.backlog.getTask(backlogId), backlogId + 1);
                    } else {
                        //Ui.displayInvalidId();
                    }
                } catch (NumberFormatException e) {
                    //Ui.printError("Task ID is not an integer!");
                } catch (IndexOutOfBoundsException e) {
                    //Ui.printError("Task ID is out of bounds!");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            //Ui.printError("There are no projects! Please create a project first.");
        }
    }
}

