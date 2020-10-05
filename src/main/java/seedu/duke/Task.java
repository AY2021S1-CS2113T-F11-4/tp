package seedu.duke;

public class Task {

    public String title;
    String description;
    String priority;
    boolean isDone;

    public Task(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return title + " " + priority + " " + isDone;
    }
}
