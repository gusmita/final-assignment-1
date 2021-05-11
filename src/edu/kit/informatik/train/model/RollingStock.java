package edu.kit.informatik.train.model;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.train.track.Point;

/**
 * Encapsulates a rolling stock
 * 
 * @author Gusmita
 * @version 1.0
 */
public class RollingStock {
    
    /**
     * train id
     */
    private int trainID;
    /**
     * an object engine of type Engine
     */
    private Engine engine;
    /**
     * an object coach of type Coach
     */
    private Coach coach;
    /**
     * an object train-set of type train-set
     */
    private TrainSet trainset;
    /**
     * rolling stock's length
     */
    private int length;
    /**
     * list of identic rolling stock which its size is rolling stock's length
     */
    private List<RollingStock> rollingStocks;
    /**
     * front coupling
     */
    private boolean couplingFront;
    /**
     * back coupling
     */
    private boolean couplingBack;
    
    /**
     * is true if rolling stock is an engine
     */
    private boolean isEngine;
    /**
     * is true if rolling stock is a coach
     */
    private boolean isCoach;
    /**
     * is true if rolling stock is a train-set
     */
    private boolean isTrainSet;
    /**
     * position of the rolling stock
     */
    private Point position;
    
    /**
     * Instantiate a rolling stock from type engine
     * @param engine an object engine
     * @param length the length
     * @param couplingFront true if it has a coupling on the front, else false
     * @param couplingBack true if it has a coupling on the back, else false
     */
    public RollingStock(Engine engine, int length, boolean couplingFront, boolean couplingBack) {
        this.engine = engine;
        this.length = length;
        this.couplingFront = couplingFront;
        this.couplingBack = couplingBack;
        this.isEngine = true;
        this.rollingStocks = new ArrayList<>();
    }
    
    /**
     * Instantiate a rolling stock from type coach
     * @param coach an object coach
     * @param length the length
     * @param couplingFront true if it has a coupling on the front, else false
     * @param couplingBack true if it has a coupling on the back, else false
     */
    public RollingStock(Coach coach, int length, boolean couplingFront, boolean couplingBack) {
        this.coach = coach;
        this.length = length;
        this.couplingFront = couplingFront;
        this.couplingBack = couplingBack;
        this.isCoach = true;
        this.rollingStocks = new ArrayList<>();
    }
    
    /**
     * Instantiate a rolling stock from type train-set
     * @param trainset an object train-set
     * @param length the length
     * @param couplingFront true if it has a coupling on the front, else false
     * @param couplingBack true if it has a coupling on the back, else false
     */
    public RollingStock(TrainSet trainset, int length, boolean couplingFront, boolean couplingBack) {
        this.trainset = trainset;
        this.length = length;
        this.couplingFront = couplingFront;
        this.couplingBack = couplingBack;
        this.isTrainSet = true;
        this.rollingStocks = new ArrayList<>();
    }
    
    /**
     * Gets the rolling stock of type engine
     * @return engine
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Gets the rolling stock of type coach
     * @return coach
     */
    public Coach getCoach() {
        return coach;
    }

    /**
     * Gets the rolling stock of type train-set
     * @return train-set
     */
    public TrainSet getTrainset() {
        return trainset;
    }

    /**
     * @return true if the rolling stock is from type engine, else false
     */
    public boolean isEngine() {
        return isEngine;
    }

    /**
     * @return true if the rolling stock is from type coach, else false
     */
    public boolean isCoach() {
        return isCoach;
    }

    /**
     * @return true if the rolling stock is from type train-set, else false
     */
    public boolean isTrainSet() {
        return isTrainSet;
    }

    /**
     * Gets the length
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     * @return true if the rolling stock has a coupling on the front, else false
     */
    public boolean hasCouplingFront() {
        return couplingFront;
    }

    /**
     * @return true if the rolling stock has a coupling on the back, else false
     */
    public boolean hasCouplingBack() {
        return couplingBack;
    }
    
    /**
     * Gets train id. If a rolling stock doesn't have a train id yet,
     * it returns "none".
     * @return train id from type String
     */
    public String getTrainID() {
        if (trainID == 0) {
            return "none";
        } 
        return Integer.toString(trainID);
    }

    /**
     * Sets the train id
     * @param trainID train id
     */
    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }
    
    /**
     * Gets rolling stock's position
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Sets rolling stock's position
     * @param position position point
     */
    public void setPosition(Point position) {
        this.position = position;
    }
    
    /**
     * @return list of rolling identical object. The size is rolling stock's length.
     */
    public List<RollingStock> getRollingStocks() {
        return rollingStocks;
    }

    /**
     * @return A String representation of a rolling stock from type engine, coach and train-set
     */
    public String toString() {
        /**
         * String representation of an engine
         */
        if (isEngine()) {
            return getTrainID() + " " + engine + " " + length + " " + couplingFront + " " + couplingBack;
        }
        /**
         * String representation of a coach
         */
        else if (isCoach()) {
            return coach.getCoachID().substring(1) + " " + getTrainID() + " " + coach.getCoachType().getContent() + " " 
                    + length + " " + couplingFront + " " + couplingBack;
        }
        /**
         * String representation of a train-set
         */
        else if (isTrainSet()) {
            return getTrainID() + " " + trainset + " " + length + " " + couplingFront + " " + couplingBack;
        }
        return null;
    }

}
