package edu.kit.informatik.train.operation.ui;

import edu.kit.informatik.train.InputException;

/**
 * Encapsulates all commands as stated in the assignment
 * 
 * @author Gusmita
 * @version 1.0
 */
public enum Command {
    /**
     * adds track
     */
    ADD_TRACK("add track " + InteractionStrings.POINT_PATTERN.toString() + " -> " 
                + InteractionStrings.POINT_PATTERN.toString()) {
        
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            
            output = Integer.toString(listCommands.addTrack(Integer.parseInt(params[2]
                    .substring(1, params[2].indexOf(","))), 
                    Integer.parseInt(params[2].substring(params[2].indexOf(",") + 1, params[2].indexOf(")"))), 
                    Integer.parseInt(params[4].substring(1, params[4].indexOf(","))), 
                    Integer.parseInt(params[4].substring(params[4].indexOf(",") + 1, params[4].indexOf(")")))));
        }
    },
    
    /**
     * adds switch
     */
    ADD_SWITCH("add switch " + InteractionStrings.POINT_PATTERN.toString() + " -> " 
                + InteractionStrings.POINT_PATTERN.toString() + "," + InteractionStrings.POINT_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            String[] endPoints = params[4].split(",");
            
            output = Integer.toString(listCommands.addSwitch(Integer.parseInt(params[2]
                    .substring(1, params[2].indexOf(","))), 
                    Integer.parseInt(params[2].substring(params[2].indexOf(",") + 1, params[2].indexOf(")"))), 
                    Integer.parseInt(endPoints[0].substring(1)), 
                    Integer.parseInt(endPoints[1].substring(0, endPoints[1].indexOf(")"))),
                    Integer.parseInt(endPoints[2].substring(1)), 
                    Integer.parseInt(endPoints[3].substring(0, endPoints[3].indexOf(")")))));
        }
    },
    
    /**
     * deletes track
     */
    DELETE_TRACK("delete track " + InteractionStrings.NUMBER_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            output = listCommands.deleteTrack(Integer.parseInt(params[3]));
        }
    },
    
    /**
     * prints list of tracks
     */
    LIST_TRACKS("list tracks") {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            output = listCommands.listTracks();
        }
    },
    
    /**
     * sets switch position
     */
    SET_SWITCH("set switch " + InteractionStrings.NUMBER_PATTERN.toString() + " position " 
                    + InteractionStrings.POINT_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            
            output = listCommands.setSwitch(Integer.parseInt(params[2]), Integer.parseInt(params[4]
                    .substring(1, params[4].indexOf(","))), 
                    Integer.parseInt(params[4].substring(params[4].indexOf(",") + 1, params[4].indexOf(")"))));
        }
    },
    
    /**
     * creates an engine
     */
    CREATE_ENGINE("create engine " + InteractionStrings.WORD_PATTERN.toString() 
                    + InteractionStrings.WHITESPACE_PATTERN.toString() 
                    + InteractionStrings.WORD_PATTERN.toString() + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.WORD_PATTERN.toString() + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.NUMBER_PATTERN.toString() + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.WORD_PATTERN.toString() + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.WORD_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            output = listCommands.createEngine(params[2], params[3], params[4], Integer.parseInt(params[5]), 
                    Boolean.valueOf(params[6]), Boolean.valueOf(params[7]));
        }
    },
    
    /**
     * prints list of engines
     */
    LIST_ENGINES("list engines") {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            output = listCommands.listEngines();
        }
    },
    
    /**
     * creates a coach
     */
    CREATE_COACH("create coach " + InteractionStrings.WORD_PATTERN.toString() 
                    + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.NUMBER_PATTERN.toString() + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.WORD_PATTERN.toString() + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.WORD_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            output = listCommands.createCoach(params[2], Integer.parseInt(params[3]), 
                    Boolean.valueOf(params[4]), Boolean.valueOf(params[5]));
        }
    },
    
    /**
     * prints list of coaches
     */
    LIST_COACH("list coaches") {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            output = listCommands.listCoaches();
        }
    },
    
    /**
     * creates a train-set
     */
    CREATE_TRAINSET("create train-set " + InteractionStrings.WORD_PATTERN.toString() 
                    + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.WORD_PATTERN.toString() + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.NUMBER_PATTERN.toString() + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.WORD_PATTERN.toString() + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.WORD_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            output = listCommands.createTrainSet(params[2], params[3], Integer.parseInt(params[4]), 
                    Boolean.valueOf(params[5]), Boolean.valueOf(params[6]));
        }
    },
    
    /**
     * prints list of train-sets
     */
    LIST_TRAINSETS("list train-sets") {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            output = listCommands.listTrainSets();
        }
    },
    
    /**
     * deletes rolling stock
     */
    DELETE_ROLLINGSTOCK("delete rolling stock " + InteractionStrings.ID_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            output = listCommands.deleteRollingStock(params[3]);
        }
    },
    
    /**
     * adds a train
     */
    ADD_TRAIN("add train " + InteractionStrings.NUMBER_PATTERN.toString() 
                    + InteractionStrings.WHITESPACE_PATTERN.toString()
                    + InteractionStrings.ID_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            output = listCommands.addTrain(Integer.parseInt(params[2]), params[3]);
        }
    },
    
    /**
     * deletes a train
     */
    DELETE_TRAIN("delete train " + InteractionStrings.NUMBER_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            output = listCommands.deleteTrain(Integer.parseInt(params[2]));
        }
    },
    
    /**
     * prints list of all trains
     */
    LIST_TRAINS("list trains") {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            output = listCommands.listTrains();
        }  
    },
    
    /**
     * prints train's representation
     */
    SHOW_TRAIN("show train " + InteractionStrings.NUMBER_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            output = listCommands.showTrain(Integer.parseInt(params[2]));
        }
    },
    
    /**
     * puts a train on a track
     */
    PUT_TRAIN("put train " + InteractionStrings.NUMBER_PATTERN.toString() + " at " 
                    + InteractionStrings.POINT_PATTERN.toString() + " in direction " 
                    + InteractionStrings.NUMBER_PATTERN.toString() + "," 
                    + InteractionStrings.NUMBER_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            String[] direction = params[7].split(",");
            output = listCommands.putTrain(Integer.parseInt(params[2]), 
                    Integer.parseInt(params[4].substring(1, params[4].indexOf(","))), 
                    Integer.parseInt(params[4].substring(params[4].indexOf(",") + 1, params[4].indexOf(")"))), 
                    Integer.parseInt(direction[0]), Integer.parseInt(direction[1]));
        }
    },
    
    /**
     * moves a train
     */
    STEP("step " + InteractionStrings.NUMBER_PATTERN.toString()) {
        @Override
        public void execute(final String parameters, final ListCommands listCommands) throws InputException {
            String[] params = parameters.split(" ");
            output = listCommands.step(Integer.parseInt(params[1]));
        }
    },
    
    /**
     * Finishes the interaction
     */
    EXIT("exit") {
        @Override
        public void execute(final String parameters,
                final ListCommands listCommands) {
            quit();
        }
    };
    
    /**
     * contains the output of the command
     */
    protected String output;
    
    /**
     * contains the state (not quitted yet)
     */
    private boolean isRunning;
    
    /**
     * contains commands input
     */
    private String pattern;
    
    /**
     * Constructs a new command
     * @param pattern commands pattern
     */
    Command(String pattern) {
        this.isRunning = true;
        this.pattern = pattern;
    }
    
    /**
     * Checks if the command matches any of the above described command
     * @param input user input
     * @param listCommands instance of ListCommands
     * @return the command that got executed
     * @throws InputException if there is no matching command
     */
    public static Command executeMatching(String input, 
            ListCommands listCommands) throws InputException {
        for (Command command : Command.values()) {
            if (input.matches(command.pattern)) {
                command.execute(input, listCommands);
                return command;
            }
        }
        throw new InputException("not a valid command!");
    }
    
    /**
     * Executes a command
     * @param parameters regex matcher 
     * @param listCommands instance of ListCommands
     * @throws InputException if the command contains sytactical or semantic errors
     */
    public abstract void execute(String parameters, ListCommands listCommands) 
            throws InputException;
    
    /**
     *Checks if the program is still running
     * @return true if it's still running
     */
    public boolean isRunning() {
        return isRunning;
    }
    
    /**
     * Returns the String passed by the last active command
     * @return string representations to the user
     */
    public String getOutput() {
        return output;
    }
    
    /**
     * Exits the program
     */
    protected void quit() {
        isRunning = false;
    }

}
