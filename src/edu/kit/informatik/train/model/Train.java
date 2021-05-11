package edu.kit.informatik.train.model;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.train.InputException;
import edu.kit.informatik.train.track.Directions;

/**
 * Encapsulates a train
 * 
 * @author Gusmita
 * @version 1.0
 */
public class Train {
    
    /**
     * train id
     */
    private int trainID;
    /**
     * list of rolling stock consists in a train
     */
    private List<RollingStock> members;
    
    /**
     * train's direction
     */
    private Directions direction;
    /**
     * is true if a train is not placed on a track
     */
    private boolean available;
    /**
     * is true if a train has crashed, else false
     */
    private boolean crashed;
    /**
     * String to display if a train has crashed
     */
    private String crashOfTrains;
    /**
     * train's head (first position)
     */
    private RollingStock head;
    /**
     * train's tail (last position)
     */
    private RollingStock back;
    
    /**
     * A constructor to instantiate a train
     * @param trainID id
     */
    public Train(int trainID) {
        this.trainID = trainID;
        this.members = new ArrayList<>();
        this.available = true;
    }

    /**
     * Gets train id
     * @return train id
     */
    public int getTrainID() {
        return trainID;
    }
    
    /**
     * Gets train length
     * @return the length
     */
    public int getLength() {
        int count = 0;
        for (RollingStock rollingStock : members) {
            for (int i = 0; i < rollingStock.getLength(); i++) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Gets list of rolling stock
     * @return rolling stocks of a train
     */
    public List<RollingStock> getMembers() {
        return members;
    }
    
    /**
     * Gets train's direction
     * @return direction
     */
    public Directions getDirection() {
        return direction;
    }

    /**
     * Sets train's direction
     * @param direction direction
     */
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    /**
     * @return true if a train is not placed on a track, else false
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets availibility of a train
     * @param available true if a train is available, else false
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    /**
     * Gets boolean value whether a train has crashed or not
     * @return true if a train has crashed, else false
     */
    public boolean hasCrashed() {
        return crashed;
    }
    
    /**
     * Sets boolean value whether a train has crashed or not
     * @param crashed is true if a train has crashed, else false
     */
    public void setHasCrashed(boolean crashed) {
        this.crashed = crashed;
    }
    
    /**
     * @return String representation if a train has crashed
     */
    public String getCrashOfTrains() {
        return crashOfTrains;
    }
    
    /**
     * @param crashOfTrains String of train id involved in the crash
     */
    public void setCrashOfTrains(String crashOfTrains) {
        this.crashOfTrains = crashOfTrains;
    }
    
    /**
     * Gets train's head
     * @return train's head
     */
    public RollingStock getHead() {
        return head;
    }

    /**
     * Sets train's head
     * @param head the head
     */
    public void setHead(RollingStock head) {
        this.head = head;
    }
    
    /**
     * Gets train's back
     * @return train's back
     */
    public RollingStock getBack() {
        return back;
    }
    
    /**
     * Sets train's back
     * @param back train's back
     */
    public void setBack(RollingStock back) {
        this.back = back;
    }
    
    /**
     * Gets list of all train members
     * @return List of train members considering each rolling stock's length
     */
    public List<RollingStock> getTrainMembers() {
        List<RollingStock> trainMembers = new ArrayList<>();
        for (RollingStock rollingStock : members) {
            for (int i = 0; i < rollingStock.getRollingStocks().size(); i++) {
                trainMembers.add(rollingStock.getRollingStocks().get(i));
            }
        }
        return trainMembers;
    }
    
    /**
     * Check if train composition is valid
     * @throws InputException for invalid composition
     */
    public void checkValidTrain() throws InputException {
        if (!head.isEngine() && !head.isTrainSet() && !back.isEngine() && !back.isTrainSet()) {
            throw new InputException("train composition is not valid");
        }
    }
    
    /**
     * Sets train's head. If train steps forward (speed > 0), the head is current head (the first rolling stock
     * added to the train), if backward (speed < 0) the head is the tail (the last member).
     * @param speed speed
     * @return head
     */
    public RollingStock setHead(int speed) {
        if (speed > 0) {
            return head;
        } else {
            return back;
        }
    }
    
    /**
     * @return String representation of each train depends on each Rolling Stock's type
     */
    public String toString() {
        String result = Integer.toString(trainID);
        for (RollingStock member : members) {
            if (member.isEngine()) {
                result += " " + member.getEngine().getEngineID();
            }
            else if (member.isCoach()) {
                result += " " + member.getCoach().getCoachID();
            }
            else if (member.isTrainSet()) {
                result += " " + member.getTrainset().getTrainsetID();
            }
        }
        return result;
    }
    
}
