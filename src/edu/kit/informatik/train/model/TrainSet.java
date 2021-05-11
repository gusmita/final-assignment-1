package edu.kit.informatik.train.model;

/**
 * Encapsulates a train-set
 * 
 * @author Gusmita
 * @version 1.0
 */
public class TrainSet {
    
    /**
     * series of the train-set
     */
    private String series;
    /**
     * name of the train-set
     */
    private String name;
    /**
     * train-set's id
     */
    private String trainsetID;
    
    /**
     * A constructor to instantiate a train-set
     * @param series train-set's series
     * @param name the name
     */
    public TrainSet(String series, String name) {
        this.series = series;
        this.name = name;
    }
    
    /**
     * Gets train-set's series
     * @return series
     */
    public String getSeries() {
        return series;
    }
    
    /**
     * Gets train-set's name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets train-set id
     * @return the id
     */
    public String getTrainsetID() {
        return trainsetID;
    }
    
    /**
     * Sets train-set id consists of series and name
     * @param id id
     */
    public void setTrainsetID(String id) {
        this.trainsetID = id;
    }
    
    /**
     * Gets the height of train-set.
     * This height means number of rows the image representation of train-set has.
     * @return the height
     */
    public int getHeight() {
        return 8;
    }
    
    /**
     * Gets image representation of a train-set (Copyright 1993,1998,2014 Toyoda Masashi (mtoyoda@acm.org))
     * @param row each row of image representation
     * @return null if there is no String to display
     */
    public String getTrainSetImage(int row) {
        switch (row) {
            case 1:
                return "         ++         ";
            case 2:
                return "         ||         ";
            case 3:
                return "_________||_________";
            case 4:
                return "|  ___ ___ ___ ___ |";
            case 5:
                return "|  |_| |_| |_| |_| |";
            case 6:
                return "|__________________|";
            case 7:
                return "|__________________|";
            case 8:
                return "   (O)        (O)   ";
            default:
                break;
        }
        return null;
    }

    /**
     * @return String representastion of a train-set
     */
    public String toString() {
        return series + " " + name + " ";
    }
}
