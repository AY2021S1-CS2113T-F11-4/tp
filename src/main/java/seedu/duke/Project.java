package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class Project {
    String title;
    String description;
    int projectDuration;
    int sprintLength;
    ArrayList<Member> member = new ArrayList<>(100);

    public Project(String title, String description, int projectDuration, int sprintLength){
        this.title = title;
        this.description = description;
        this.projectDuration = projectDuration;
        this.sprintLength = sprintLength;
    }

    public String toString(){
        return title + " " + description;
    }

    public void addMember(List<String> userId){
        Member m;
        for (String s : userId) {
            m = new Member(s);
            member.add(m);
            System.out.println(member.size());
        }

    }

    public void removeMember(ArrayList<String> userId){
        for (int i=0; i<member.size(); i++){
            member.remove(new Member(userId.get(i)));
        }
    }
    public void displayMembers() {
        System.out.println(member.size());
        for (int i=0; i < member.size(); i++) {
            System.out.println(member.get(i).userId);
        }
    }

}
