package edu.kit.informatik.train.operation.ui;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.train.InputException;

/**
 * Entry point of the program
 * 
 * @author Gusmita
 * @version 1.0
 */
public class Main {
    /**
     * The main method that is the entry point to the program.
     *
     * @param args An array of command line arguments.
     */
    public static void main(String[] args) {
        
        ListCommands listCommands = new ListCommands();
        Command command = null;
        
        do {
            try {
                command = Command
                        .executeMatching(Terminal.readLine(), listCommands);
                if (command.isRunning()) {
                    Terminal.printLine(command.getOutput());
                }
            } catch (InputException e) {
                Terminal.printError(e.getMessage());
            }
        } while (command == null || command.isRunning());
    }

}
