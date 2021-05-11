package edu.kit.informatik.train.track;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import edu.kit.informatik.train.InputException;

/**
 * This class has all methods needed to compose a track
 * 
 * @author Gusmita
 * @version 1.0
 */
public class TrackComposition {
    
    /**
     * List of tracks
     */
    public static List<Track> tracks;
    /**
     * Counts track that has been created, sets the first added track its id to 1
     */
    private static int trackIdCounter = 1;
    /**
     * List of available points to choose as a start, end or second end of a track.
     * This is also needed to check if a track is connected or not.
     */
    private List<Point> availablePoints;
    
    /**
     * A constructor to instantiate a track
     */
    public TrackComposition() {
        tracks = new ArrayList<>();
        availablePoints = new ArrayList<>();
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
        Track track = new Track(startX, startY, endX, endY);
        
        if (checkConnectedTrack(track) == false) {
            throw new InputException("track is not connected");
        }
        
        track.getStart().setDirections(track.getDirection());
        track.getEnd().setDirections(track.getDirection());
        track.setPoints(track.getStart(), track.getEnd());
        
        checkIntersection(track);
        
        tracks.add(track);
        track.setId(trackIdCounter++);
        
        return track.getId();
    }
    
    /**
     * Deletes a track
     * @param trackID track id
     * @return OK for successfully deleted track
     * @throws InputException if a track with given id doesn't exist
     */
    public String deleteTrack(int trackID) throws InputException {
        if (getTrack(trackID) != null) {
            tracks.remove(getTrack(trackID));
            return "OK";
        }
        throw new InputException("a track with this ID doesn't exist");
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
    //TODO check intersection --> list points empty
    public int addSwitch(int startX, int startY, int endX, int endY, int secondEndX, int secondEndY) 
            throws InputException {
        Track turnout = new Track(startX, startY, endX, endY, secondEndX, secondEndY);
        
        if (checkConnectedTrack(turnout) == false) {
            throw new InputException("track is not connected");
        }
            
        tracks.add(turnout);
        turnout.setId(trackIdCounter++);
        return turnout.getId();
    }
    
    /**
     * @return list of tracks
     */
    public String listTracks() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
       
        for (Track track : tracks) {
            result.add(track.toString());
        }
        
        return result.length() > 0 ? result.toString() : "No track exists";
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
        Track track = getTrack(trackID);
        
        if (track.isTurnout()) {
            if (track.getEnd().getX() == posX && track.getEnd().getY() == posY) {
                track.setFinalEnd(track.getEnd());
            }
            else if (track.getSecondEnd().getX() == posX && track.getSecondEnd().getY() == posY) {
                track.setFinalEnd(track.getSecondEnd());
                
            }
            else {
                throw new InputException("the given point is not the end of the track");
            }
        } else {
            throw new InputException("this track is not from type switch");
        }
        
        track.setDirection(track.getStart(), track.getFinalEnd());
        track.setLength(track.getStart(), track.getFinalEnd());
        track.setPoints(track.getStart(), track.getFinalEnd());
        
        track.getStart().setDirections(track.getDirection());
        track.getEnd().setDirections(track.getDirection());
        
        return "OK";
    }
    
    /**
     * Private method to get a track from list of tracks
     * @param trackID track id
     * @return the track
     * @throws InputException if a track with given id doesn't exist
     */
    private Track getTrack(int trackID) throws InputException {
        for (Track track : tracks) {
            if (track.getId() == trackID) {
                return track;
            }
        }
        throw new InputException("a track with this id doesn't exist");
    }
    
    /**
     * Check if a new track is connected before adding it
     * @param newTrack new track
     * @return true if connected, else false
     */
    private boolean checkConnectedTrack(Track newTrack) {
        /*
         * List of points to be removed from available points.
         * The point that has to be removed is a point which is already connected to a track.
         * As stated in the assignment a point can only be connected to one other track.
         */
        List<Point> pointToBeRemoved = new ArrayList<>();
        
        /*
         * If there is no track, add both start, end and second end (if switch) to available points
         */
        if (tracks.isEmpty()) {
            availablePoints.add(newTrack.getStart());
            availablePoints.add(newTrack.getEnd());
            if (newTrack.isTurnout()) {
                availablePoints.add(newTrack.getSecondEnd());
            }
            return true;
        }
        
        /*
         * If a track already exists, check if the new track that will be added has at least one point
         * which has the same coordinate as one of the available points. If so, this point will be added
         * to pointToBeRemoved because it's already connected.
         */
        for (Point point : availablePoints) {
            if (point.toString().equals(newTrack.getStart().toString())) {
                newTrack.setStart(point);
                pointToBeRemoved.add(point);
                checkValidPoint(availablePoints, pointToBeRemoved, newTrack);
            }
            else if (point.toString().equals(newTrack.getEnd().toString())) {
                pointToBeRemoved.add(point);
                newTrack.setEnd(point);
                checkValidPoint(availablePoints, pointToBeRemoved, newTrack);
            }
            else if (newTrack.isTurnout()) {
                if (point.toString().equals(newTrack.getSecondEnd().toString())) {
                    pointToBeRemoved.add(newTrack.getSecondEnd());
                    newTrack.setSecondEnd(point);
                    checkValidPoint(availablePoints, pointToBeRemoved, newTrack);
                }
            }
        }
        
        /*
         * if there is no point to be removed, meaning the new track has no point which has the same
         * coordinate as available points, the track is not connected and it returns false
         */
        if (pointToBeRemoved.isEmpty()) {
            return false;
        }
        
        /*
         * adds start/end/second end point of a new track which has different coordinate as available points
         * to available points
         */
        if (!pointToBeRemoved.contains(newTrack.getStart())) {
            availablePoints.add(newTrack.getStart());
        }
        if (!pointToBeRemoved.contains(newTrack.getEnd())) {
            availablePoints.add(newTrack.getEnd());
        }
        if (newTrack.isTurnout()) {
            if (!pointToBeRemoved.contains(newTrack.getSecondEnd())) {
                availablePoints.add(newTrack.getSecondEnd());
            }
        }
        
        availablePoints.removeAll(pointToBeRemoved);
        return true;
    }
    
    /**
     * Private method to check if the new track has at least one point 
     * which has the same coordinate as one of the available points.
     * @param availablePoints list of all available points
     * @param pointToBeRemoved point(s) to be removed
     * @param newTrack new track
     */
    private void checkValidPoint(List<Point> availablePoints, List<Point> pointToBeRemoved, Track newTrack) {
        for (Point point : availablePoints) {
            if (point.toString().equals(newTrack.getEnd().toString())) {
                newTrack.setEnd(point);
                pointToBeRemoved.add(point);
            }
            if (newTrack.isTurnout()) {
                if (point.toString().equals(newTrack.getSecondEnd().toString())) {
                    newTrack.setSecondEnd(point);
                    pointToBeRemoved.add(point);
                }
            }
        }
    }
    
    /**
     * Checks if new track intersects another track
     * @param newTrack new track
     * @throws InputException if a track intersects another track
     */
    private void checkIntersection(Track newTrack) throws InputException {
        
        for (Track track : tracks) {
            for (Point point : track.getPoints()) {
                if (point.toString().equals(newTrack.getStart().toString()) 
                        || point.toString().equals(newTrack.getEnd().toString())) {
                    throw new InputException("intersection is not allowed");
                }
                if (newTrack.isTurnout()) {
                    if (point.toString().equals(newTrack.getSecondEnd().toString())) {
                        throw new InputException("intersection is not allowed");
                    }
                }
                for (Point point2 : newTrack.getPoints()) {
                    if (point2.toString().equals(point.toString())) {
                        throw new InputException("intersection is not allowed");
                    }
                }
            }
        }
    }
    
}
