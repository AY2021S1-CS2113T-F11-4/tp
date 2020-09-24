package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        String ADD = "/add";
        String DELETE = "/del";
        String TITLE = "-title";
        String DESCRIPTION = "-desc";
        String DURATION = "-duration";
        String SD = "-sd";
        String DISPLAY_MEMBERS = "/disp";

        ArrayList<String> pDetails;

        System.out.println("Please enter the project details for instantiation");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        pDetails = parser(input);
        System.out.println(pDetails);
        String projectDesc = pDetails.get(pDetails.indexOf(DESCRIPTION) + 1);
        String projectTitle = pDetails.get(pDetails.indexOf(TITLE) + 1);
        int projectDur = Integer.parseInt(pDetails.get(pDetails.indexOf(DURATION) + 1));
        int projectSD = Integer.parseInt(pDetails.get(pDetails.indexOf(SD) + 1));

        Project proj = new Project(projectTitle, projectDesc, projectDur, projectSD);
        input = in.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            try {
                pDetails = parser(input);
                String type = pDetails.get(0);
                String exec = pDetails.get(1);

                switch (type) {
                case "project":
                    if (exec.equals("/info")) {
                        System.out.println(proj);
                    }
                    break;
                case "member":
                    if (exec.equals(ADD)) {
                        proj.addMember(pDetails.subList(2, pDetails.size()));
                    } else if (exec.equals(DELETE)) {
                        proj.removeMember(pDetails.subList(2, pDetails.size()));
                    } else if (exec.equals(DISPLAY_MEMBERS)) {
                        proj.displayMembers();
                    }
                    break;
                case "task":
                    if (exec.equals(ADD)) {


                    }
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            input = in.nextLine();
        }
        System.out.println("Code exited");
    }

    public static ArrayList<String> parser(String input) {
        String[] projectDetails = input.split("\\s+");
        return new ArrayList<>(Arrays.asList(projectDetails));
    }
}
