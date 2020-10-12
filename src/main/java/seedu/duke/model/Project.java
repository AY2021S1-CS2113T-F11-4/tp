package seedu.duke.model;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;

public class Project implements Jsonable {

    protected SprintList allSprints;
    protected ProjectBacklog backlog;
    protected ProjectMembers members;
    protected String title;
    protected String description;
    protected int projectDuration;
    protected int sprintLength;

    protected LocalDate startDate = null;
    protected LocalDate endDate = null;


    public ProjectMembers getMembers() {
        return members;
    }

    public Project(String title, String description, String projectDuration, String sprintLength) {
        this.title = title;
        this.description = description;
        this.projectDuration = Integer.parseInt(projectDuration);
        this.sprintLength = Integer.parseInt(sprintLength);
        backlog = new ProjectBacklog();
        members = new ProjectMembers();
        allSprints = new SprintList();
    }

    public String toString() {
        return "Project title: " + title + "\nProject description " + description;
    }

    public SprintList getAllSprints() {
        return allSprints;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ProjectBacklog getProjectBacklog() {
        return backlog;
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

    @Override
    public String toJson() {
        final StringWriter writeable = new StringWriter();
        try {
            this.toJson(writeable);
        } catch (IOException e) {
            System.out.println("[Error] Cannot convert this project to JSON");
            e.printStackTrace();
        }
        return writeable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("duration", this.projectDuration);
        json.put("sprint_length", this.sprintLength);
        json.put("start_date", this.startDate == null ? null : this.startDate.toString());
        json.put("end_date", this.endDate == null ? null : this.startDate.toString());
        //TODO Make backlog and members parsable
        json.put("backlog", new JsonArray());
        json.put("members", new JsonArray());
        json.toJson(writer);
    }

}