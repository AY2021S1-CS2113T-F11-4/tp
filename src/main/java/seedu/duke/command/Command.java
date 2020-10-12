package seedu.duke.command;

import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public abstract class Command {


    protected String command;
    protected String action;
    protected Hashtable<String, String> parameters = new Hashtable<>();

    /**
     * Creates a new abstract command.
     *
     * @param command e.g. Project/Member/Sprint
     * @param action e.g. /add /del /edit
     * @param parameters Parameters in Hashtable
     */
    public Command(String command, String action, Hashtable<String, String> parameters) {
        setCommand(command);
        setAction(action);
        setParameters(parameters);
    }

    //Overloading constructor
    public Command(String action, Hashtable<String, String> parameters) {
        this(null, action, parameters);
    }

    //Overloading constructor
    public Command(Hashtable<String, String> parameters) {
        this(null, null, parameters);
    }

    /**
     * Abstract method that execute the command.
     *
     * @param ui UI that handles user interaction
     * @return Boolean - True if Bye command is executed
     */
    public abstract boolean execute(Project proj, Ui ui);

    /**
     * Getters and Setters.
     */
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Hashtable<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Hashtable<String, String> parameters) {
        this.parameters = parameters;
    }
}
