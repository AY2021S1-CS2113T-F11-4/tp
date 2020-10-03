package seedu.duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
    static ArrayList<Task> projectBacklog = new ArrayList<>(100);
    ArrayList<Member> member = new ArrayList<>(100);
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

    public void addMember(List<String> userId) {
        Member m;
        for (String s : userId) {
            if (member.contains(new Member(s))) {
                System.out.println("The user associated with " + s + "is already added to the project");
            } else {
                m = new Member(s);
                member.add(m);
            }
        }
    }

    //add comparator for removing object
    public void removeMember(List<String> userId) {
        for (String s : userId) {
            if (member.contains(new Member(s))) {
                member.remove(new Member(s));
            } else {
                System.out.println("This member is not associated with this project: " + new Member(s).userId);
            }
        }
    }

    public void displayMembers() {
        if (member.size() == 0) {
            System.out.println("Currently no members added to the project.");
        } else {
            System.out.println("Here are the members added to you project:");
            for (int i = 0; i < member.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + member.get(i).getUserId());
            }
        }
    }

    public void addTask(Task task) {
        projectBacklog.add(task);
    }

    public void displayProjectBacklog() {
        if (projectBacklog.size() == 0) {
            System.out.println("No tasks currently added to project backlog.");
        } else {
            System.out.println("Current tasks in your project backlog");
            for (int i = 0; i < projectBacklog.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + projectBacklog.get(i).title);
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
