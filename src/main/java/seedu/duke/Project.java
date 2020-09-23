package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class Project {
    static ArrayList<Member> member = new ArrayList<>(100);
    String title;
    String description;
    int projectDuration;
    int sprintLength;

    public Project(String title, String description, int projectDuration, int sprintLength) {
        this.title = title;
        this.description = description;
        this.projectDuration = projectDuration;
        this.sprintLength = sprintLength;
    }

    public String toString() {
        return title + " " + description;
    }

    public void addMember(List<String> userId) {
        Member m;
        for (String s : userId) {
            m = new Member(s);
            member.add(m);
        }
    }
    //add comparator for removing object
    public void removeMember(List<String> userId) {
        for (String s : userId) {
            member.remove(new Member(s));
        }
    }

    public void displayMembers() {
        for (Member value : member) {
            System.out.println(value.userId);
        }
    }

}
