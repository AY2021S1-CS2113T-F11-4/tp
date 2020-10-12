package seedu.duke.command.member;

import seedu.duke.model.Member;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class MemberCommand {
    public void addMemberCommand(ArrayList<String> userId, ArrayList<Project> projectList) {
        Member m;
        try {
            Project proj = projectList.get(0);
            for (String s : userId) {
                if (proj.members.memberList.contains(new Member(s))) {
                    Ui.showToUser(s + "is already added to the project.");
                } else {
                    m = new Member(s);
                    proj.members.memberList.add(m);
                    Ui.showToUser(s + "has been successfully added.");
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
                if (proj.members.memberList.contains(new Member(s))) {
                    proj.members.memberList.remove(new Member(s));
                    Ui.showToUser(s + " has successfully been removed.");
                } else {
                    Ui.showToUser(s + " is not associated with this project.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("Required index of project not found. Please create before proceeding.");
        }
    }

}
