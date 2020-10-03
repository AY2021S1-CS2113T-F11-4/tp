package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        System.out.println("Please enter the project details for instantiation");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        ArrayList<String> pdetails = parser(input);

        String projectDesc = pdetails.get(pdetails.indexOf(CommandExtract.DESCRIPTION) + 1);
        String projectTitle = pdetails.get(pdetails.indexOf(CommandExtract.TITLE) + 1);
        int projectDur = Integer.parseInt(pdetails.get(pdetails.indexOf(CommandExtract.DURATION) + 1));
        int projectSD = Integer.parseInt(pdetails.get(pdetails.indexOf(CommandExtract.SD) + 1));

        Project proj = new Project(projectTitle, projectDesc, projectDur, projectSD);
        input = in.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            try {
                pdetails = parser(input);
                String type = pdetails.get(0);
                String exec = pdetails.get(1);

                switch (type) {
                case "project":
                    if (exec.equals("/info")) {
                        System.out.println(proj);
                    } else if (exec.equals("/backlog")) {
                        proj.displayProjectBacklog();
                    }
                    break;
                case "member":
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