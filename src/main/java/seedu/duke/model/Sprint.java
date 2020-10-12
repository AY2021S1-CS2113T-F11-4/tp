package seedu.duke.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class Sprint {


    private String goal;
    private LocalDate startDate;
    private LocalDate endDate;
    private Hashtable<Integer, ArrayList<String>> sprintTasks;
    //private ArrayList<Integer> sprintTasks;

    public Sprint(String goal) {
        this(goal, null, null);
    }

    public Sprint(String goal, LocalDate startDate) {
        this(goal, startDate, null);
    }

    public Sprint(String goal, LocalDate startDate, LocalDate endDate) {
        setGoal(goal);
        setStartDate(startDate);
        setEndDate(endDate);
        sprintTasks = new Hashtable<>();
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
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

    public void addSprintTask(int taskId) {
        this.sprintTasks.put(taskId, new ArrayList<>());
    }

    public void removeSprintTask(int taskId) {
        this.sprintTasks.remove(taskId);
    }

    public void allocateSprintTask(int taskId, ArrayList<String> users) {
        this.sprintTasks.put(taskId, users);
    }

    public Hashtable<Integer, ArrayList<String>> getAllSprintTask() {
        return this.sprintTasks;
    }
}