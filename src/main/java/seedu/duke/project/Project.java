package seedu.duke.project;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import seedu.duke.sprint.Sprint;
import seedu.duke.sprint.SprintList;

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
    
    
    public Project() {
    }

    public Project(String title, String description, int projectDuration, int sprintLength) {
        this.title = title;
        this.description = description;
        this.projectDuration = projectDuration;
        this.sprintLength = sprintLength;
        backlog = new ProjectBacklog(this);
        members = new ProjectMembers();
        allSprints = new SprintList();
    }

    @Override
    public String toString() {
        StringBuilder projectInString = new StringBuilder();
        projectInString.append("\n============================ PROJECT ============================\n");
        projectInString.append(String.format("[Title: %s]\n", this.title));
        projectInString.append(String.format("[Description: %s]\n", this.description));
        if (!members.getAllMembers().isEmpty()) {
            projectInString.append(members.toString());
        } else {
            projectInString.append("[No members added]\n");
        }
        if (this.startDate != null) {
            projectInString.append(String.format("[Period: %s - %s] \n", this.startDate, this.endDate));
        } else {
            projectInString.append("[Project will start along with the first sprint]\n");
        }

        if (!this.backlog.backlogTasks.isEmpty()) {
            projectInString.append(this.backlog.toString());
        } else {
            projectInString.append("[Project backlog is empty]\n");
        }
        if (this.allSprints.size() != 0) {
            projectInString.append(allSprints.toString());
        } else {
            projectInString.append("[There are no Sprints]\n");
        }
        projectInString.append("=================================================================\n");
        return projectInString.toString();
    }

    //Getters
    public SprintList getAllSprints() {
        return allSprints;
    }

    public ProjectMembers getProjectMember() {
        return members;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public ProjectBacklog getBacklog() {
        return backlog;
    }

    public ProjectMembers getMembers() {
        return members;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ProjectBacklog getProjectBacklog() {
        return backlog;
    }

    public String getDescription() {
        return description;
    }
    // Call this function every time a new sprint object is instantiated.
    // sets the start date the first time.
    
    //Setters
    public void setStartDate() {
        setStartDate(LocalDate.now());
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public void setAllSprints(SprintList allSprints) {
        this.allSprints = allSprints;
    }

    public void setBacklog(ProjectBacklog backlog) {
        this.backlog = backlog;
    }

    public void setMembers(ProjectMembers members) {
        this.members = members;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProjectDuration(int projectDuration) {
        this.projectDuration = projectDuration;
    }

    public void setSprintLength(int sprintLength) {
        this.sprintLength = sprintLength;
    }

    public void displayProjectBacklog() {
        if (backlog.getNextId() == 0) {
            System.out.println("No tasks currently added to project backlog.");
        } else {
            System.out.println("Current tasks in your project backlog");
            for (int i = 0; i < backlog.getNextId(); i++) {
                System.out.println("\t" + (i + 1) + ". " + backlog.getTask(i).getTitle());
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
        final JsonObject jObj = new JsonObject();
        jObj.put("title", this.title);
        jObj.put("description", this.description);
        jObj.put("projectDuration", this.projectDuration);
        jObj.put("sprintLength", this.sprintLength);
        jObj.put("startDate", this.startDate == null ? null : this.startDate.toString());
        jObj.put("endDate", this.endDate == null ? null : this.endDate.toString());
        jObj.put("backlog", backlog);
        jObj.put("members", members);
        jObj.put("allSprints", allSprints);
        jObj.toJson(writer);
    }
}
