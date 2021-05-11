package edu.kit.informatik.train.operation;

import java.util.List;
import java.util.StringJoiner;

import edu.kit.informatik.train.InputException;
import edu.kit.informatik.train.model.RollingStock;
import edu.kit.informatik.train.model.Train;
import edu.kit.informatik.train.model.TrainComposition;
import edu.kit.informatik.train.track.Directions;
import edu.kit.informatik.train.track.Point;
import edu.kit.informatik.train.track.Track;
import edu.kit.informatik.train.track.TrackComposition;

/**
 * A class contains all methods needed for train simulation
 * 
 * @author Gusmita
 * @version 1.0
 */
public class TrainOperation {
    
    /**
     * Current list of track from TrackComposition
     */
    private List<Track> tracks = TrackComposition.tracks;
    /**
     * Current list of trains from TrainComposition
     */
    private List<Train> trains = TrainComposition.trains;
    
    /**
     * A constructor with no parameters
     */
    public TrainOperation() {
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
    public String putTrain(int trainID, final int posX, final int posY, 
            final int directionX, final int directionY) throws InputException {
        Directions originalDirection;
        Train train = null;
        
        /*
         * throw exception if a train doesn't exist or has been placed on track
         */
        if (getTrain(trainID) == null) {
            throw new InputException("a train with this id doesn't exist");
        } else {
            train = getTrain(trainID);
            if (!train.isAvailable()) {
                throw new InputException("this train has been placed on a track");
            }
        }
        
        /*
         * check valid train composition
         */
        train.checkValidTrain();

        /*
         * Sets each rolling stock's position
         */
        if (validDirection(trainID, posX, posY, directionX, directionY)) {
            originalDirection = train.getDirection();
            
            if (getPointFromTracks(posX, posY) != null) {
                train.getHead().setPosition(getPointFromTracks(posX, posY));
            } else {
                throw new InputException("this point doesn't exist");
            }
            
            int setPosX = posX;
            int setPosY = posY;
            
            for (int i = 1; i < train.getTrainMembers().size(); i++) {
                RollingStock rollingStock = train.getTrainMembers().get(i);
                setTrainPosition(originalDirection, rollingStock, setPosX, setPosY);
                setPosX = rollingStock.getPosition().getX();
                setPosY = rollingStock.getPosition().getY();
            }
            /*
             * sets the train to its original direction for step method
             */
            getTrain(trainID).setDirection(originalDirection);
        }
        
        /*
         * Sets the train as unavailable after it has been placed on a track  
         */
        getTrain(trainID).setAvailable(false);
        return "OK";
    }
    
    /**
     * Moves the train
     * @param speed how many steps a train should move
     * @return OK if there is no train on tracks
     * @throws InputException if switches not set
     */
    public String step(int speed) throws InputException {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        throwIfSwitchesNotSet();
        /*
         * If step is negative, train moves backward
         */
        for (Train train : trains) {
            if (speed < 0) {
                train.setDirection(changeTrainDirection(train.getDirection()));
            }
        }
        /*
         * Moves train's head and set rolling stock's position
         */
        for (int i = 0; i < Math.abs(speed); i++) {
            for (Train train : trains) {
                if (!train.isAvailable()) {
                    RollingStock head = train.setHead(speed);
                    Point oldPos = head.getPosition();
                    moveTrain(speed, head, train);
                    if (train.getLength() > 1) {
                        if (speed > 0) {
                            for (int j = 1; j < train.getTrainMembers().size(); j++) {
                                Point temp = train.getTrainMembers().get(j).getPosition();
                                train.getTrainMembers().get(j).setPosition(oldPos);
                                oldPos = temp;
                            }
                        } else {
                            for (int j = train.getTrainMembers().size() - 2; j >= 0; j--) {
                                Point temp = train.getTrainMembers().get(j).getPosition();
                                train.getTrainMembers().get(j).setPosition(oldPos);
                                oldPos = temp;
                            }
                        }
                    }
                }
            }
            //checks if a crash occurs
            checkCrash();
        }
        /*
         * Change train's direction to its opposite direction if train stepped backwards and print the result
         */
        for (Train train : trains) {
            if (speed < 0) {
                train.setDirection(changeTrainDirection(train.getDirection()));
            }
            if (train.getHead().getPosition() != null) {
                if (!train.isAvailable()) {
                    result.add("Train " + train.getTrainID() + " at " + train.getHead().getPosition());
                } else {
                    if (train.getCrashOfTrains() != null) {
                        result.add("Crash of train " + train.getCrashOfTrains());
                    }
                }
            }
        }
        return result.length() > 0 ? result.toString() : "OK";
    }
    
    /**
     * Checks if a train crashes
     */
    private void checkCrash() {
        String crash = "";
        /*
         * Iterate through each train if it has the same position as the next train or if they are on the same track
         */
        for (Train train : trains) {
            if (train.getHead().getPosition() != null) {
                RollingStock head = train.getHead();
                RollingStock back = train.getBack();
                
                for (Train train2 : trains) {
                    if (!train2.equals(train) && train2.getHead().getPosition() != null && !train2.hasCrashed()) {
                        RollingStock head2 = train2.getHead();
                        RollingStock back2 = train2.getBack();
                        if (head.getPosition().equals(head2.getPosition()) 
                                || back.getPosition().equals(back2.getPosition())) {
                            train.setHasCrashed(true);
                            train2.setAvailable(true);
                            crash += train2.getTrainID() + ",";
                        }
                        else if (head.getPosition().getTrackID() != 0 && back.getPosition().getTrackID() != 0 
                                    && head2.getPosition().getTrackID() != 0 
                                    && back2.getPosition().getTrackID() != 0) {
                            if (head.getPosition().getTrackID() == head2.getPosition().getTrackID() 
                                    || back.getPosition().getTrackID() == back2.getPosition().getTrackID()
                                    || head.getPosition().getTrackID() == back2.getPosition().getTrackID()
                                    || back.getPosition().getTrackID() == head2.getPosition().getTrackID()) {
                                train.setHasCrashed(true);
                                train2.setAvailable(true);
                                crash += train2.getTrainID() + ",";
                            }
                        }
                    }
                }
                if (!train.isAvailable() && train.hasCrashed()) {
                    train.setAvailable(true);
                    train.setCrashOfTrains(train.getTrainID() + "," + crash.substring(0, crash.length() - 1));
                }
            } else {
                if (train.hasCrashed()) {
                    crash += train.getTrainID();
                }
            }
        }
    }
    
    /**
     * Sets each rolling stock's position
     * @param trainID train id
     * @param direction train direction
     * @param rollingStock rolling stock
     * @param posX x-coordinate
     * @param posY y-coordinate
     * @throws InputException throw exception if position point to set can't be found
     */
    private void setTrainPosition(Directions direction, RollingStock rollingStock, 
            final int posX, final int posY) throws InputException {
        switch (direction) {
            case DOWN:
                try {
                    setUp(rollingStock, posX, posY);
                } catch (InputException e) {
                    checkCornerCase(Directions.DOWN, rollingStock, posX, posY);
                }
                break;
            case LEFT:
                try {
                    setRight(rollingStock, posX, posY);
                } catch (InputException e) {
                    checkCornerCase(Directions.LEFT, rollingStock, posX, posY);
                }
                break;
            case RIGHT:
                try {
                    setLeft(rollingStock, posX, posY);
                } catch (InputException e) {
                    checkCornerCase(Directions.RIGHT, rollingStock, posX, posY);
                }
                break;
            case UP:
                try {
                    setDown(rollingStock, posX, posY);
                } catch (InputException e) {
                    checkCornerCase(Directions.UP, rollingStock, posX, posY);
                }
                break;
            default:
                break;
        }
    }
    
    /**
     * Moves train's head. This head depends on train's speed.
     * @param speed speed
     * @param head head
     * @param train train
     * @throws InputException 
     */
    private void moveTrain(int speed, RollingStock head, Train train) throws InputException {
        int posX = head.getPosition().getX();
        int posY = head.getPosition().getY();
        switch(train.getDirection()) {
            case DOWN:
                try {
                    setDown(head, posX, posY);
                } catch (InputException e) {
                    checkCornerCase(Directions.DOWN, head, posX, posY);
                }
                break;
            case LEFT:
                try {
                    setLeft(head, posX, posY);
                } catch (InputException e) {
                    checkCornerCase(Directions.LEFT, head, posX, posY);
                }
                break;
            case RIGHT:
                try {
                    setRight(head, posX, posY);
                } catch (InputException e) {
                    checkCornerCase(Directions.RIGHT, head, posX, posY);
                }
                break;
            case UP:
                try {
                    setUp(head, posX, posY);
                } catch (InputException e) {
                    checkCornerCase(Directions.UP, head, posX, posY);
                }
                break;
            default:
                break;
        }
    }
    
    /**
     * Check if a point on x-axis in positive direction exists
     * @param rollingStock rolling stock
     * @param posX x-coordinate
     * @param posY y-coordinate
     * @throws InputException if there is no valid point to be found
     */
    private void setRight(RollingStock rollingStock, int posX, int posY) throws InputException {
        if (getPointFromTracks(posX + 1, posY) != null) {
            rollingStock.setPosition(getPointFromTracks(posX + 1, posY));
        }
    }
    
    /**
     * Check if a point on x-axis in negative direction exists
     * @param rollingStock rolling stock
     * @param posX x-coordinate
     * @param posY y-coordinate
     * @throws InputException if there is no valid point to be found
     */
    private void setLeft(RollingStock rollingStock, int posX, int posY) throws InputException {
        if (getPointFromTracks(posX - 1, posY) != null) {
            rollingStock.setPosition(getPointFromTracks(posX - 1, posY));
        }
    }
    
    /**
     * Check if a point on y-axis in positive direction exists
     * @param rollingStock rolling stock
     * @param posX x-coordinate
     * @param posY y-coordinate
     * @throws InputException if there is no valid point to be found
     */
    private void setUp(RollingStock rollingStock, int posX, int posY) throws InputException {
        if (getPointFromTracks(posX, posY + 1) != null) {
            rollingStock.setPosition(getPointFromTracks(posX, posY + 1));
        }
    }
    
    /**
     * Check if a point on y-axis in negative direction exists
     * @param rollingStock rolling stock
     * @param posX x-coordinate
     * @param posY y-coordinate
     * @throws InputException if there is no valid point to be found
     */
    private void setDown(RollingStock rollingStock, int posX, int posY) throws InputException {
        if (getPointFromTracks(posX, posY - 1) != null) {
            rollingStock.setPosition(getPointFromTracks(posX, posY - 1));
        }
    }
    
    /**
     * Check train position is on the corner
     * @param direction direction
     * @param rollingStock rolling stock
     * @param posX x-coordinate
     * @param posY y-coordinate
     * @throws InputException if there is no valid point to be found
     */
    private void checkCornerCase(Directions direction, RollingStock rollingStock, int posX, int posY) 
                throws InputException {
        if (direction.equals(Directions.RIGHT) || direction.equals(Directions.LEFT)) {
            try {
                setUp(rollingStock, posX, posY);
            } catch (InputException exception1) {
                try {
                    setDown(rollingStock, posX, posY);
                } catch (InputException exception2) {
                    throw new InputException("no valid point found. Train is not on the track.");
                }
            }
        }
        else if (direction.equals(Directions.UP) || direction.equals(Directions.DOWN)) {
            try {
                setLeft(rollingStock, posX, posY);
            } catch (InputException exception1) {
                try {
                    setRight(rollingStock, posX, posY);
                } catch (InputException exception2) {
                    throw new InputException("no valid point found. Train is not on the track.");
                }
            }
        }
    }
    
    /**
     * Changes train direction to step backward
     * @param direction current direction of the train
     * @return null if there is no such direction
     */
    private Directions changeTrainDirection(Directions direction) {
        if (direction.equals(Directions.RIGHT)) {
            return Directions.LEFT;
        }
        else if (direction.equals(Directions.LEFT)) {
            return Directions.RIGHT;
        }
        else if (direction.equals(Directions.UP)) {
            return Directions.DOWN;
        }
        else if (direction.equals(Directions.DOWN)) {
            return Directions.UP;
        }
        return null;
    }
    
    /**
     * Gets point from track
     * @param x x-coordinate
     * @param y y-coordinate
     * @return null if there is no such point
     */
    private Point getPointFromTracks(int x, int y) throws InputException {
        for (Track track : tracks) {
            if (track.getStart().getX() == x && track.getStart().getY() == y) {
                return track.getStart();
            }
            else if (track.getEnd().getX() == x && track.getEnd().getY() == y) {
                return track.getEnd();
            }
            for (Point point : track.getPoints()) {
                if (point.getX() == x && point.getY() == y) {
                    return point;
                }
            }
        }
        throw new InputException("no valid point");
    }
    
    /**
     * Private method to check valid direction for given position also sets the train direction
     * @param trainID train id
     * @param posX x-coordinate
     * @param posY y-coordinate
     * @param directionX direction in x-axis
     * @param directionY direction in y-axis
     * @return true if direction is valid, else false
     * @throws InputException exception for invalid direction
     */
    private boolean validDirection(int trainID, int posX, int posY, int directionX, int directionY) 
            throws InputException {
        for (Directions direction : Directions.values()) {
            if (direction.getValue().equals(directionX + "," + directionY)) {
                getTrain(trainID).setDirection(direction);
                if (!getPointFromTracks(posX, posY).getAvailableDirections().contains(direction)) {
                    throw new InputException("not a valid direction");
                }
            }
        }
        return true;
    }
    
    /**
     * Private method to check if switches are set
     * @param point point
     * @return true if switches are set, else false
     * @throws InputException if switches are not set
     */
    private void throwIfSwitchesNotSet() throws InputException {
        for (Track track : tracks) {
            if (track.isTurnout()) {
                if (track.getFinalEnd() == null) {
                    throw new InputException("position of switches are not set");
                }
            }
        }
    }
    
    /**
     * Gets a train from list trains
     * @param trainID train id
     * @return the train or null if there is no train with given id
     */
    private Train getTrain(int trainID) {
        for (Train train : trains) {
            if (train.getTrainID() == trainID) {
                return train;
            }
        }
        return null;
    }
}
