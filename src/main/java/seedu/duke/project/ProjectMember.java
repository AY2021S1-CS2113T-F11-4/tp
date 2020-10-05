package seedu.duke.project;

import seedu.duke.Member;

import java.util.ArrayList;
import java.util.List;

public class ProjectMember {

    ArrayList<Member> memberList;

    public ProjectMember() {
        memberList = new ArrayList<>(100);
    }

    public int size() {
        return memberList.size();
    }

    public Member getMember(int id) {
        return memberList.get(id);
    }

    public void addMember(List<String> userId) {
        Member m;
        for (String s : userId) {
            if (memberList.contains(new Member(s))) {
                System.out.println("The user associated with " + s + "is already added to the project");
            } else {
                m = new Member(s);
                memberList.add(m);
                System.out.println("The user associated with " + s + "has been added");
            }
        }
    }

    //add comparator for removing object
    public void removeMember(List<String> userId) {
        for (String s : userId) {
            if (memberList.contains(new Member(s))) {
                memberList.remove(new Member(s));
                System.out.println("The user associated with " + s + "has been removed from the project");
            } else {
                System.out.println("This member is not associated with this project: " + new Member(s).userId);
            }
        }
    }

}
