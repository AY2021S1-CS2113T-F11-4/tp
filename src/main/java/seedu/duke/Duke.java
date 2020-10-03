package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

<<<<<<< HEAD
        System.out.println("Please enter the project details for instantiation");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        ArrayList<String> pdetails = parser(input);

        String projectDesc = pdetails.get(pdetails.indexOf(CommandExtract.DESCRIPTION) + 1);
        String projectTitle = pdetails.get(pdetails.indexOf(CommandExtract.TITLE) + 1);
        int projectDur = Integer.parseInt(pdetails.get(pdetails.indexOf(CommandExtract.DURATION) + 1));
        int projectSD = Integer.parseInt(pdetails.get(pdetails.indexOf(CommandExtract.SD) + 1));
=======
        String ADD = "/add";
        String DELETE = "/del";
        String TITLE = "-title";
        String DESCRIPTION = "-desc";
        String DURATION = "-duration";
        String SD = "-sd";
        String DISPLAY_MEMBERS = "/disp";
        String PRIORITY = "-priority";

        ArrayList<String> pDetails;

        System.out.println("Please enter the project details for instantiation");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        pDetails = parser(input);

        String projectDesc = pDetails.get(pDetails.indexOf(DESCRIPTION) + 1);
        String projectTitle = pDetails.get(pDetails.indexOf(TITLE) + 1);
        int projectDur = Integer.parseInt(pDetails.get(pDetails.indexOf(DURATION) + 1));
        int projectSD = Integer.parseInt(pDetails.get(pDetails.indexOf(SD) + 1));
>>>>>>> a16161dafe298a638622375d76bbb9552b33b5cd

        Project proj = new Project(projectTitle, projectDesc, projectDur, projectSD);
        input = in.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            try {
<<<<<<< HEAD
                pdetails = parser(input);
                String type = pdetails.get(0);
                String exec = pdetails.get(1);
=======
                pDetails = parser(input);
                String type = pDetails.get(0);
                String exec = pDetails.get(1);
>>>>>>> a16161dafe298a638622375d76bbb9552b33b5cd

                switch (type) {
                case "project":
                    if (exec.equals("/info")) {
                        System.out.println(proj);
                    } else if (exec.equals("/backlog")) {
                        proj.displayProjectBacklog();
                    }
                    break;
                case "member":
<<<<<<< HEAD
                    switch (exec) {
                    case CommandExtract.ADD:
                        proj.addMember(pdetails.subList(2, pdetails.size()));
                        break;
                    case CommandExtract.DELETE:
                        proj.removeMember(pdetails.subList(2, pdetails.size()));
                        break;
                    case CommandExtract.DISPLAY_MEMBERS:
                        proj.displayMembers();
                        break;
                    default:
                        System.out.println("Unknown format");
                    }
                    break;
                case "task":
                    if (exec.equals(CommandExtract.ADD)) {
                        String desc = String.join(" ",
                                pdetails.subList(pdetails.indexOf(CommandExtract.DESCRIPTION) + 1,
                                        pdetails.indexOf(CommandExtract.PRIORITY)));
                        String title = String.join(" ", pdetails.subList(pdetails.indexOf(CommandExtract.TITLE) + 1,
                                pdetails.indexOf(CommandExtract.DESCRIPTION)));
                        String priority = pdetails.get(pdetails.indexOf(CommandExtract.PRIORITY) + 1);
                        proj.addTask(new Task(title, desc, priority));
                    }
                    break;
                default:
                    System.out.println("Unknown command.");
=======
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
                        String desc = String.join(" ", pDetails.subList(pDetails.indexOf(DESCRIPTION) + 1, pDetails.indexOf(PRIORITY)));
                        String title = String.join(" ", pDetails.subList(pDetails.indexOf(TITLE) + 1, pDetails.indexOf(DESCRIPTION)));
                        String priority = pDetails.get(pDetails.indexOf(PRIORITY) + 1);
                        proj.addTask(new Task(title, desc, priority));
                    }
                    break;
>>>>>>> a16161dafe298a638622375d76bbb9552b33b5cd
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