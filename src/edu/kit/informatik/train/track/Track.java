package edu.kit.informatik.train.track;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.train.InputException;

/**
 * Encapsulates a track
 * 
 * @author Gusmita
 * @version 1.0
 */
public class Track {

    /**
     * Start point
     */
    private Point start;
    /**
     * End point
     */
    private Point end;
    /**
     * Second end (if switch)
     */
    private Point secondEnd;
    /**
     * Final end (if switch has been set)
     */
    private Point finalEnd;
    /**
     * is true if track is switch, else false
     */
    private boolean turnout;
    /**
     * track's length
     */
    private int length;
    /**
     * track id
     */
    private int id;
    /**
     * Direction of a track. This direction will be needed to create list of points between start and end point.
     */
    private Directions direction;
    /**
     * List of points between start and end point.
     */
    private List<Point> points;
    
    /**
     * A constructor to instantiate a normal track
     * @param startX x-coordinate of start point
     * @param startY y-coordinate of start point
     * @param endX x-coordinate of end point
     * @param endY y-coordinate of end point
     * @throws InputException if the direction is not valid
     */
    public Track(int startX, int startY, int endX, int endY) throws InputException {
        this.start = new Point(startX, startY);
        this.end = new Point(endX, endY);
        this.points = new ArrayList<>();
        setDirection(new Point(startX, startY), new Point(endX, endY));
        setLength(start, end);
    }
    
    /**
     * A constructor to instantiate a switch
     * @param startX x-coordinate of start point
     * @param startY y-coordinate of start point
     * @param endX x-coordinate of end point
     * @param endY y-coordinate of end point
     * @param secondEndX x-coordinate of second end point
     * @param secondEndY y-coordinate of second end point
     */
    public Track(int startX, int startY, int endX, int endY, int secondEndX, int secondEndY) {
        this.start = new Point(startX, startY);
        this.end = new Point(endX, endY);
        this.secondEnd = new Point(secondEndX, secondEndY);
        this.finalEnd = null;
        this.turnout = true;
        this.points = new ArrayList<>();
    }
    
    /**
     * Gets start point
     * @return start point
     */
    public Point getStart() {
        return start;
    }
    
    /**
     * Sets start point
     * @param start start point
     */
    public void setStart(Point start) {
        this.start = start;
    }
    
    /**
     * Gets end point
     * @return end point
     */
    public Point getEnd() {
        return end;
    }
    
    /**
     * Sets end point
     * @param end end point
     */
    public void setEnd(Point end) {
        this.end = end;
    }
    
    /**
     * Gets second end point
     * @return second end point
     */
    public Point getSecondEnd() {
        return secondEnd;
    }
    
    /**
     * Sets second end point
     * @param secondEnd second end point
     */
    public void setSecondEnd(Point secondEnd) {
        this.secondEnd = secondEnd;
    }
    
    /**
     * Gets final end point
     * @return final end point
     */
    public Point getFinalEnd() {
        return finalEnd;
    }
    
    /**
     * Sets final end point
     * @param finalEnd final end point
     */
    public void setFinalEnd(Point finalEnd) {
        this.finalEnd = finalEnd;
    }
    
    /**
     * @return true if track is switch, else false
     */
    public boolean isTurnout() {
        return turnout;
    }
    
    /**
     * @param turnout true if track is switch, else false
     */
    public void setTurnout(boolean turnout) {
        this.turnout = turnout;
    }
    
    /**
     * Gets track id
     * @return track id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets track id
     * @param id track id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Sets direction of a track. This direction can be horizontal (left and right) and vertical (up and down).
     * @param position start point of a track
     * @param direction end point of a track
     * @throws InputException if direction is invalid
     */
    public void setDirection(Point position, Point direction) throws InputException {
        if ((position.getX() < direction.getX()) && (position.getY() == direction.getY())) {
            this.direction = Directions.RIGHT;
        }
        else if ((position.getX() > direction.getX()) && (position.getY() == direction.getY())) {
            this.direction = Directions.LEFT;
        }
        else if ((position.getX() == direction.getX()) && (position.getY() < direction.getY())) {
            this.direction = Directions.UP;
        }
        else if ((position.getX() == direction.getX()) && (position.getY() > direction.getY())) {
            this.direction = Directions.DOWN;
        }
        else {
            throw new InputException("creation not possible");
        }
    }
    
    /**
     * Gets direction of a track
     * @return the direction
     * @throws InputException if direction is invalid
     */
    public Directions getDirection() throws InputException {
        if (direction == null) {
            throw new InputException("only horizontal or vertical direction allowed");
        }
        return direction;
    }
    
    /**
     * Set track's length after the direction has been set
     * @param start start point
     * @param end end point
     */
    public void setLength(Point start, Point end) {
        switch(direction) {
            case DOWN:
                this.length = Math.max(start.getY(), end.getY()) - Math.min(start.getY(), end.getY());
                break;
            case LEFT:
                this.length = Math.max(start.getX(), end.getX()) - Math.min(start.getX(), end.getX());
                break;
            case RIGHT:
                this.length = Math.max(start.getX(), end.getX()) - Math.min(start.getX(), end.getX());
                break;
            case UP:
                this.length = Math.max(start.getY(), end.getY()) - Math.min(start.getY(), end.getY());
                break;
            default:
                break;
        }
    }
    
    /**
     * Gets track's length
     * @return the length
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Sets the points between start and end point based on track's direction
     * @param start start point
     * @param end end point
     * @throws InputException if direction is invalid
     */
    public void setPoints(Point start, Point end) throws InputException {
        switch(direction) {
            case DOWN:
                for (int i = 1; i < length; i++) {
                    this.points.add(new Point(start.getX(), start.getY() - i));
                }
                break;
            case LEFT:
                for (int i = 1; i < length; i++) {
                    this.points.add(new Point(start.getX() - i, start.getY()));
                }
                break;
            case RIGHT:
                for (int i = 1; i < length; i++) {
                    this.points.add(new Point(start.getX() + i, start.getY()));
                }
                break;
            case UP:
                for (int i = 1; i < length; i++) {
                    this.points.add(new Point(start.getX(), start.getY() + i));
                }
                break;
            default:
                break;
        }
        /*
         * After the points between start and end are added, sets each point's direction and its id
         */
        for (int i = 0; i < points.size(); i++) {
            points.get(i).setDirections(direction);
            points.get(i).setTrackID(id);
        }
    }
    
    /**
     * Gets the points between start and end
     * @return list of points
     */
    public List<Point> getPoints() {
        return points;
    }
    
    /**
     * @return String representation of normal and switch track
     */
    public String toString() {
        if (turnout == false) {
            return "t " + id + " " + start + " -> " + end + " " + length;
        } else {
            return length > 0 ? "w " + id + " " + start + " -> " + end + " " + secondEnd + " " + length
                : "s " + id + " " + start + " -> " + end + " " + secondEnd;
        }
    }

   
    
}
