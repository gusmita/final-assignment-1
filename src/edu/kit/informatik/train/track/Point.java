package edu.kit.informatik.train.track;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a coordinate point
 * 
 * @author Gusmita
 * @version 1.0
 */
public class Point {
    
    /**
     * point on horizontal axis
     */
    private int x;
    /**
     * point on vertical axis
     */
    private int y;
    /**
     * The directions each point has. If a point is on the corner it has 4 directions.
     * This will be useful for put train command in class TrainOperation.
     */
    private List<Directions> availableDirections;
    /**
     * A point that is not a connecting point has track id
     */
    private int trackID;
    
    /**
     * A constructor to instantiate a point
     * @param x point on horizontal axis
     * @param y point on vertical axis
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        availableDirections = new ArrayList<>();
    }

    /**
     * Gets the point on horizontal axis
     * @return Integer number of this point
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets the point on vertical axis
     * @return Integer number of this point
     */
    public int getY() {
        return y;
    }
    
    /**
     * Gets track id of a point
     * @return track id
     */
    public int getTrackID() {
        return trackID;
    }
    
    /**
     * Sets track id of a point
     * @param trackID track id
     */
    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }
    
    /**
     * Gets available directions of a point 
     * @return list of directions
     */
    public List<Directions> getAvailableDirections() {
        return availableDirections;
    }
    
    /**
     * Sets the available directions.
     * If a point is on horizontal axis available directions are left and right,
     * on vertical axis are up and down.
     * @param direction track direction
     */
    public void setDirections(Directions direction) {
        if (direction.equals(Directions.RIGHT) || direction.equals(Directions.LEFT)) {
            availableDirections.add(Directions.RIGHT);
            availableDirections.add(Directions.LEFT);
        }
        if (direction.equals(Directions.UP) || direction.equals(Directions.DOWN)) {
            availableDirections.add(Directions.UP);
            availableDirections.add(Directions.DOWN);
        }
    }
    
    /**
     * @return String representation of a coordinate
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }

}
