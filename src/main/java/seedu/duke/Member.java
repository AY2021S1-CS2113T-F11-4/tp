package seedu.duke;

import seedu.duke.task.Task;
import java.util.ArrayList;

public class Member {
    public String userId;
    ArrayList<Task> allocatedTask;

    public Member(String userId) {
        this.userId = userId;
        allocatedTask = new ArrayList<>(100);

    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        /* Check if o is an instance of Member or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Member)) {
            return false;
        }
        Member v = (Member) o;
        // Compare the userId of members and return accordingly
        return userId.equals(v.userId);
    }

    public void addMemberTask(Task task) {
        allocatedTask.add(task);
    }

}
