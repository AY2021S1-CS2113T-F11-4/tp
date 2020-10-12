package seedu.duke.command.member;


import seedu.duke.Member;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class MemberCommand {
    public void addMemberCommand(ArrayList<String> userId, ArrayList<Project> projectList) {
        Member m;
        try {
            Project proj = projectList.get(0);
            for (String s : userId) {
                if (proj.members.containMember(new Member(s))) {
                    Ui.showToUserLn(s + " is already associated to the project.");
                } else {
                    m = new Member(s);
                    proj.members.addMember(m);
                    Ui.showToUserLn(s + " has been added to the project.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
             Ui.showError("Required index of project not found. Please create before proceeding.");
        }
    }

    public void deleteMemberCommand(ArrayList<String> userId, ArrayList<Project> projectList) {
        Project proj;
        try {
            proj = projectList.get(0);
            for (String s : userId) {
                if (proj.members.containMember(new Member(s))) {
                    proj.members.removeMember(new Member(s));
                    Ui.showToUserLn(s + " has been removed from the project.");
                } else {
                    Ui.showToUserLn(s + " is not associated with the project.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("Required index of project not found. Please create before proceeding.");
        }
    }

}
