package seedu.duke.project;

import seedu.duke.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {

    public static ProjectBacklog backlog;
    public static ProjectMember member;
    String title;
    String description;
    int projectDuration;
    int sprintLength;
    LocalDate startDate = null;
    LocalDate endDate = null;

    public Project(String title, String description, int projectDuration, int sprintLength) {
        this.title = title;
        this.description = description;
        this.projectDuration = projectDuration;
        this.sprintLength = sprintLength;
        backlog = new ProjectBacklog();
        member = new ProjectMember();
    }

    public String toString() {
        return "Project title: " + title + "\nProject description " + description;
    }

    public int getProjectDuration() {
        return projectDuration;
    }

    public int getSprintLength() {
        return sprintLength;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }



    public void displayProjectBacklog() {
        if (backlog.size() == 0) {
            System.out.println("No tasks currently added to project backlog.");
        } else {
            System.out.println("Current tasks in your project backlog");
            for (int i = 0; i < backlog.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + backlog.getTask(i).title);
            }
        }
    }

    // Call this function every time a new sprint object is instantiated.
    // sets the start date the first time.
    public void setStartDate() {
        if (startDate != null) {
            startDate = LocalDate.now();
            endDate = startDate.plusDays(projectDuration);
        }
    }
}
