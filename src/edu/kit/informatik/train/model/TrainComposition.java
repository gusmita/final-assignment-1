package edu.kit.informatik.train.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

import edu.kit.informatik.train.InputException;

/**
 * A class consists of methods to compose a train
 * 
 * @author Gusmita
 * @version 1.0
 */
public class TrainComposition {
    
    /**
     * List of trains
     */
    public static List<Train> trains;
    /**
     * counts coach that's already created
     */
    private static int coachIdCounter = 1;
    /**
     * List of rolling stock
     */
    public List<RollingStock> stocks;
    
    /**
     * A constructor to instantiate a new object of type TrainComposition
     */
    public TrainComposition() {
        trains = new ArrayList<>();
        stocks = new ArrayList<>();
    }
    
    /**
     * Creates an engine
     * @param engineType engine type
     * @param series engine series
     * @param name the name
     * @param length the length
     * @param couplingFront true if an engine has coupling on the front, else false
     * @param couplingBack true if an engine has coupling on the back, else false
     * @return engine's id of type String
     * @throws InputException if engine type is not from enum values, id already exists or coupling invalid
     */
    public String createEngine(String engineType, String series, 
            String name, int length, boolean couplingFront, boolean couplingBack) throws InputException {
        EngineTypes type = null;
        for (EngineTypes curr : EngineTypes.values()) {
            if (curr.name().toLowerCase().equals(engineType)) {
                type = curr;
            }
        }
        if (type == null) {
            throw new InputException("this type of engine doesn't exist");
        }
        
        Engine engine = new Engine(type, series, name);
        String id = series + "-" + name;
        
        if (!checkCoupling(couplingFront, couplingBack)) {
            throw new InputException("material should have minimum one coupling");
        }
        
        if (getStock(id) == null) {
            RollingStock newRollingStock = new RollingStock(engine, length, couplingFront, couplingBack);
            stocks.add(newRollingStock);
            
            for (int i = 0; i < length; i++) {
                newRollingStock.getRollingStocks().add(new RollingStock(engine, length, couplingFront, couplingBack));
            }
        } else {
            throw new InputException("this id already exists");
        }
        
        engine.setEngineID(id);
        return engine.getEngineID();
    }
    
    /**
     * @return list of engines
     */
    public String listEngines() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        List<String> id = new ArrayList<>();

        for (RollingStock rollingStock : stocks) {
            if (rollingStock.isEngine()) {
                id.add(rollingStock.getEngine().getEngineID());
            }
        }
        Collections.sort(id);
        for (String engineID : id) {
            result.add(getStock(engineID).toString());
        }
        return result.length() > 0 ? result.toString() : "No engine exists";
    }
    
    /**
     * Creates a coach
     * @param coachType coach type
     * @param length the length
     * @param couplingFront true if an engine has coupling on the front, else false
     * @param couplingBack true if an engine has coupling on the back, else false
     * @return coach id
     * @throws InputException if coupling invalid
     */
    public String createCoach(String coachType, int length, 
            boolean couplingFront, boolean couplingBack) throws InputException {
        
        if (!checkCoupling(couplingFront, couplingBack)) {
            throw new InputException("material should have minimum one coupling");
        }
        
        CoachTypes type = null;
        
        for (CoachTypes curr : CoachTypes.values()) {
            if (curr.name().toLowerCase().equals(coachType)) {
                type = curr;
            }
        }
        
        if (type == null) {
            throw new InputException("this type of coach doesn't exist");
        }
        
        Coach coach = new Coach(type, length);
        RollingStock newRollingStock = new RollingStock(coach, length, couplingFront, couplingBack);
        stocks.add(newRollingStock);
        
        for (int i = 0; i < length; i++) {
            newRollingStock.getRollingStocks().add(new RollingStock(coach, length, couplingFront, couplingBack));
        }
        
        coach.setCoachID(coachIdCounter++);
        return coach.getCoachID().substring(1);
    }
    
    /**
     * @return list of coaches
     */
    public String listCoaches() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (RollingStock rollingStock : stocks) {
            if (rollingStock.isCoach()) {
                result.add(rollingStock.toString());
            }
        }
        return result.length() > 0 ? result.toString() : "No coach exists";
    }
    
    /**
     * Creates a train-set
     * @param series train-set series
     * @param name the name
     * @param length the length
     * @param couplingFront true if an engine has coupling on the front, else false
     * @param couplingBack true if an engine has coupling on the back, else false
     * @return train-set's id of type String
     * @throws InputException if id already exists or coupling invalid
     */
    public String createTrainSet(String series, String name, int length, 
            boolean couplingFront, boolean couplingBack) throws InputException {
        TrainSet trainset = new TrainSet(series, name);
        String id = series + "-" + name;
        
        if (!checkCoupling(couplingFront, couplingBack)) {
            throw new InputException("material should have minimum one coupling");
        }
        if (stocks.isEmpty() || getStock(id) == null) {
            RollingStock newRollingStock = new RollingStock(trainset, length, couplingFront, couplingBack);
            stocks.add(newRollingStock);
            for (int i = 0; i < length; i++) {
                newRollingStock.getRollingStocks().add(new RollingStock(trainset, length, couplingFront, couplingBack));
            }
        }
        trainset.setTrainsetID(id);
        return trainset.getTrainsetID();
    }
    
    /**
     * @return list of train-sets
     */
    public String listTrainSets() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        List<String> id = new ArrayList<>();
        
        for (RollingStock rollingStock : stocks) {
            if (rollingStock.isTrainSet()) {
                id.add(rollingStock.getTrainset().getTrainsetID());
            }
        }
        Collections.sort(id);
        for (String trainsetID : id) {
            result.add(getStock(trainsetID).getTrainset().toString());
        }
        return result.length() > 0 ? result.toString() : "No train-set exists";
    }
    
    /**
     * Deletes a rolling stock
     * @param stockID id
     * @return OK if input valid
     * @throws InputException if id doesn't exist
     */
    public String deleteRollingStock(String stockID) throws InputException {
        if (getStock(stockID) != null) {
            stocks.remove(getStock(stockID));
            return "OK";
        }
        throw new InputException("rolling stock with ID " + stockID + " not found");
    }
    
    /**
     * Adds rolling stock to a train
     * @param trainID train id beginning from 1
     * @param stockID stock id
     * @return String representation of successfully added train
     * @throws InputException if train id is invalid, train composition is invalid
     */
    public String addTrain(int trainID, String stockID) throws InputException {
        String output = "";
        
        //check valid trainID
        checkTrainID(trainID);
        
        if (trains.isEmpty() || getTrain(trainID) == null) {
            trains.add(new Train(trainID));
        }
            
        Train train = getTrain(trainID);
        RollingStock rollingStock = getStock(stockID);
        
        if (!train.isAvailable()) {
            throw new InputException("can't add rolling stock. Train is already on a track");
        }
        
        /*
         * check material availability. If available, the rolling stock will be added to a train
         */
        if (rollingStock != null && rollingStock.getTrainID().equals("none")) {
            train.getMembers().add(rollingStock);
            rollingStock.setTrainID(trainID);
        } else {
            throw new InputException("a stock with this id is not available");
        }
        
        //check coupling compatibility
        if (checkCouplingCompatibility(trainID, stockID) || train.getMembers().size() == 1) {
            if (rollingStock.isEngine()) {
                output += rollingStock.getEngine().getEngineType().name().toLowerCase() + " engine " 
                        + stockID +  " added to train " + trainID;
            }
            else if (rollingStock.isCoach()) {
                output += rollingStock.getCoach().getCoachType().name().toLowerCase() + " coach " 
                        + stockID +  " added to train " + trainID;
            }
            else if (rollingStock.isTrainSet()) {
                if (train.getMembers().size() > 1) {
                    //check if train members are all train-sets with the same series
                    if (!checkTrainSetComposition(trainID, stockID)) {
                        throw new InputException("train-sets can only be composed with train-sets of the same series");
                    }
                }
                output += "train-set " + stockID + " added to train " + trainID;
            }
        } else {
            throw new InputException("coupling isn't compatible");
        }
        
        train.setHead(train.getTrainMembers().get(0));
        train.setBack(train.getTrainMembers().get(train.getTrainMembers().size() - 1));
        return output;
    }
    
    /**
     * Deletes a train
     * @param trainID train id
     * @return OK for successfully deleted train
     * @throws InputException if a train with given id doesn't exist
     */
    public String deleteTrain(int trainID) throws InputException {
        if (getTrain(trainID) != null) {
            for (RollingStock rollingStock : getTrain(trainID).getMembers()) {
                rollingStock.setTrainID(0);
            }
            trains.remove(getTrain(trainID));
            return "OK";
        }
        throw new InputException("a train with this id doesn't exist");
    }
    
    /**
     * @return list of all trains
     */
    public String listTrains() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (Train train : trains) {
            result.add(train.toString());
        }
        return result.length() > 0 ? result.toString() : "No train exists";
    }
    
    /**
     * Shows the representation of a train
     * @param trainID train id
     * @return image representation of a train
     */
    public String showTrain(int trainID) {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        
        //a list to store rolling stock's height
        List<Integer> heights = new ArrayList<>();
        
        for (RollingStock member : getTrain(trainID).getMembers()) {
            if (member.isEngine()) {
                heights.add(member.getEngine().getEngineTypeHigh(member.getEngine().getEngineType()));
            }
            else if (member.isCoach()) {
                heights.add(member.getCoach().getCoachTypeHigh(member.getCoach().getCoachType()));
            }
            else if (member.isTrainSet()) {
                heights.add(member.getTrainset().getHeight());
            }
        }
        
        /*
         * get the highest rolling stock. This will be needed to print the representation so that
         * it is fully shown on the console and make sure no parts are cut
         */
        int maxHeight = Collections.max(heights);
        
        /*
         * the highest rolling stock's representation has 8 rows. We print each row of representation
         * beginning from the first row. This explains the number 9.
         */
        for (int row = 9 - maxHeight; row < 9; row++) {
            String line = "";
            for (RollingStock member : getTrain(trainID).getMembers()) {
                if (member.isEngine()) {
                    line += member.getEngine().getImage(member.getEngine().getEngineType(), row) + " ";
                }
                else if (member.isCoach()) {
                    line += member.getCoach().getImage(member.getCoach().getCoachType(), row) + " ";
                }
                else if (member.isTrainSet()) {
                    line += member.getTrainset().getTrainSetImage(row) + " ";
                }
            }
            result.add(line);
        }
        return result.length() > 0 ? result.toString().substring(0, result.length() - 1) : null;
    }
    
    /**
     * Private method to get the Rolling Stock from list of rolling stock 
     * @param id id
     * @return the rolling stock if exists, else null
     */
    private RollingStock getStock(String id) {
        for (RollingStock rollingStock : stocks) {
            if (rollingStock.isEngine() && rollingStock.getEngine().getEngineID().equals(id)) {
                return rollingStock;
            }
            else if (rollingStock.isCoach() && rollingStock.getCoach().getCoachID().equals(id)) {
                return rollingStock;
            }
            else if (rollingStock.isTrainSet() && rollingStock.getTrainset().getTrainsetID().equals(id)) {
                return rollingStock;
            }
        }
        return null;
    }
    
    /**
     * Private method to check if a rolling stock has a coupling on the front or back 
     * @param hasFront true if it has, else false
     * @param hasBack true if it has, else false
     * @return true if a rolling stock has at least one coupling
     * @throws InputException if a rolling stock has no coupling
     */
    private boolean checkCoupling(boolean hasFront, boolean hasBack) throws InputException {
        if (hasFront == true || hasBack == true) {
            return true;
        }
        return false;
    }
    
    /**
     * Private method to check if a given train id is valid
     * @param trainID id
     * @return true if valid
     * @throws InputException if chosen id isn't the next available id
     */
    private boolean checkTrainID(int trainID) throws InputException {
        if (trainID > 0 && trainID <= trains.size() + 1) {
            return true;
        }
        throw new InputException("please insert the next available ID");
    }
    
    /**
     * Private method to check coupling compatibility of the rolling stock that will be added to a train
     * to its last rolling stock
     * @param trainID train id
     * @param stockID stock id
     * @return true if coupling match, else false
     */
    private boolean checkCouplingCompatibility(int trainID, String stockID) {
        if (getTrain(trainID).getMembers().size() > 1) {
            RollingStock lastMember = getTrain(trainID).getMembers().get(getTrain(trainID).getMembers().size() - 2);
            RollingStock newMember = getTrain(trainID).getMembers().get(getTrain(trainID).getMembers().size() - 1);
            if (lastMember.hasCouplingBack() && newMember.hasCouplingFront()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Private method to check if a train-set is composed with the same series of train-sets
     * @param trainID train id
     * @param stockID stock id
     * @return true if trainset composition is valid, else false
     */
    private boolean checkTrainSetComposition(int trainID, String stockID) {
        RollingStock lastMember = getTrain(trainID).getMembers().get(getTrain(trainID).getMembers().size() - 2);
        RollingStock newMember = getTrain(trainID).getMembers().get(getTrain(trainID).getMembers().size() - 1);
        
        if ((lastMember.isTrainSet() && newMember.isTrainSet()) 
                && (newMember.getTrainset().getSeries().equals(lastMember.getTrainset().getSeries()))) {
            return true;
        }
        return false;
    }
    
    /**
     * Private method to get a train from list of trains
     * @param trainID train if
     * @return the train with this id if exists, else null
     */
    public Train getTrain(int trainID) {
        for (Train train : trains) {
            if (train.getTrainID() == trainID) {
                return train;
            }
        }
        return null;
    }

}
