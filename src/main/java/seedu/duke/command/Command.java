package seedu.duke.command;

import seedu.duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Command {


    protected Hashtable<String, String> parameters;

    /**
     * Creates a new abstract command.
     */
    public Command(Hashtable<String, String> parameters) {
        setParameters(parameters);
    }

    /**
     * Abstract method that execute the command.
     */
    public abstract void execute() throws DukeException;

    /**
     * Getters and Setters.
     */
    public void setParameters(Hashtable<String, String> parameters) {
        this.parameters = parameters;
    }
}