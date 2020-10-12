package seedu.duke.model;

import seedu.duke.parser.DateTimeParser;

import java.time.LocalDate;
import java.util.ArrayList;

public class SprintList {
    private ArrayList<Sprint> sprintList;
    private int currentSprintIndex;

    public SprintList() {
        sprintList = new ArrayList<>();
        currentSprintIndex = -1;
    }

    public int size() {
        return sprintList.size();
    }

    public Sprint getSprint(int index) {
        return sprintList.get(index);
    }

    public void addSprint(String goal, LocalDate start, LocalDate end) {
        sprintList.add(new Sprint(goal, start, end));
    }

    public int getCurrentSprintIndex() {
        return currentSprintIndex;
    }

    public void setCurrentSprintIndex(int currentSprintIndex) {
        this.currentSprintIndex = currentSprintIndex;
    }

    public boolean updateCurrentSprint() {
        for (int i = 0; i < this.size(); i++) {
            Sprint current = this.getSprint(i);
            if (DateTimeParser.diff(LocalDate.now(), current.getEndDate()) >= 0
                    && DateTimeParser.diff(current.getStartDate(), LocalDate.now()) >= 0) {
                this.setCurrentSprintIndex(i);
                return true;

            }
        }
        return false;
    }
}