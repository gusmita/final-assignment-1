package edu.kit.informatik.train.operation.ui;

import edu.kit.informatik.train.InputException;
import edu.kit.informatik.train.model.TrainComposition;
import edu.kit.informatik.train.operation.TrainOperation;
import edu.kit.informatik.train.track.TrackComposition;

/**
 * A class that provides all methods of how a train simulation should work
 * 
 * @author Gusmita
 * @version 1.0
 */
public class ListCommands {
    /**
     * An object of type TrackComposition to access all methods to create the tracks
     */
    private TrackComposition trackComposition;
    /**
     * An object of type TrainComposition to access all methods to compose the trains
     */
    private TrainComposition trainComposition;
    /**
     * An object of type TrackComposition to access all methods to move the train
     */
    private TrainOperation trainOperation;
    
    /**
     * A constructor to instantiate a new object of type ListCommands
     */
    public ListCommands() {
        trackComposition = new TrackComposition();
        trainComposition = new TrainComposition();
        trainOperation = new TrainOperation();
    }
    
    /**
     * Adds a track
     * @param startX x-coordinate of start point
     * @param startY y-coordinate of start point
     * @param endX x-coordinate of end point
     * @param endY y-coordinate of end point
     * @return track's id if a track is successfully added
     * @throws InputException if direction is invalid, track is not connected or 
     * there is intersection between two tracks
     */
    public int addTrack(int startX, int startY, int endX, int endY) throws InputException {
        return trackComposition.addTrack(startX, startY, endX, endY);
    }
    
    /**
     * Deletes a track
     * @param trackID track id
     * @return OK for successfully deleted track
     * @throws InputException if a track with given id doesn't exist
     */
    public String deleteTrack(int trackID) throws InputException {
        return trackComposition.deleteTrack(trackID);
    }
    
    /**
     * Adds a switch
     * @param startX x-coordinate of start point
     * @param startY y-coordinate of start point
     * @param endX x-coordinate of end point
     * @param endY y-coordinate of end point
     * @param secondEndX x-coordinate of second end point
     * @param secondEndY y-coordinate of second end point
     * @return track id
     * @throws InputException if direction is invalid, track is not connected or 
     * there is intersection between two tracks 
     */
    public int addSwitch(int startX, int startY, int endX, int endY, int secondEndX, int secondEndY) 
            throws InputException {
        return trackComposition.addSwitch(startX, startY, endX, endY, secondEndX, secondEndY);
    }
    
    /**
     * @return list of tracks
     */
    public String listTracks() {
        return trackComposition.listTracks();
    }
    
    /**
     * Sets the final end of a switch
     * @param trackID track id
     * @param posX x-coordinate of final end point
     * @param posY y-coordinate of final end point
     * @return OK if final end successfully set
     * @throws InputException if given position is not the end or second end of switch
     */
    public String setSwitch(int trackID, int posX, int posY) throws InputException {
        return trackComposition.setSwitch(trackID, posX, posY);
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
        return trainComposition.createEngine(engineType, series, name, length, couplingFront, couplingBack);
    }
    
    /**
     * @return list of engines
     */
    public String listEngines() {
        return trainComposition.listEngines();
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
        return trainComposition.createCoach(coachType, length, couplingFront, couplingBack);
    }
    
    /**
     * @return list of coaches
     */
    public String listCoaches() {
        return trainComposition.listCoaches();
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
        return trainComposition.createTrainSet(series, name, length, couplingFront, couplingBack);
    }
    
    /**
     * @return list of train-sets
     */
    public String listTrainSets() {
        return trainComposition.listTrainSets();
    }
    
    /**
     * Deletes a rolling stock
     * @param stockID id
     * @return OK if input valid
     * @throws InputException if id doesn't exist
     */
    public String deleteRollingStock(String stockID) throws InputException {
        return trainComposition.deleteRollingStock(stockID);
    }
    
    /**
     * Adds rolling stock to a train
     * @param trainID train id beginning from 1
     * @param stockID stock id
     * @return String representation of successfully added train
     * @throws InputException if train id is invalid, train composition is invalid
     */
    public String addTrain(int trainID, String stockID) throws InputException {
        return trainComposition.addTrain(trainID, stockID);
    }
    
    /**
     * Deletes a train
     * @param trainID train id
     * @return OK for successfullt deleted train
     * @throws InputException if a train with given id doesn't exist
     */
    public String deleteTrain(int trainID) throws InputException {
        return trainComposition.deleteTrain(trainID);
    }
    
    /**
     * @return list of all trains
     */
    public String listTrains() {
        return trainComposition.listTrains();
    }
    
    /**
     * Shows the representation of a train
     * @param trainID train id
     * @return image representation of a train
     */
    public String showTrain(int trainID) {
        return trainComposition.showTrain(trainID);
    }
    
    /**
     * Puts a train on a track
     * @param trainID train id
     * @param posX x-coordinate
     * @param posY y-coordinate
     * @param directionX direction in x-axis
     * @param directionY direction in y-axis
     * @return OK if a train is successfully put on a track
     * @throws InputException if train is not available
     */
    public String putTrain(int trainID, int posX, int posY, int directionX, int directionY) throws InputException {
        return trainOperation.putTrain(trainID, posX, posY, directionX, directionY);
    }
    
    /**
     * Moves the train
     * @param speed how many steps a train should move
     * @return OK if there is no train on tracks
     * @throws InputException if switches not set
     */
    public String step(int speed) throws InputException {
        return trainOperation.step(speed);
    }

}
