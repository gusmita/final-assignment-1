package edu.kit.informatik.train.model;

/**
 * Encapsulates a coach
 * 
 * @author Gusmita
 * @version 1.0
 */
public class Coach {
    
    /**
     * Coach type either a passenger, freight or a special coach
     */
    private CoachTypes coachType;
    /**
     * Coach id from type Integer
     */
    private int coachID;
    
    /**
     * A constructor to instantiate a coach
     * @param coachType coach type
     * @param length length
     */
    public Coach(CoachTypes coachType, int length) {
        this.coachType = coachType;
    }

    /**
     * Gets a coach type
     * @return coach type
     */
    public CoachTypes getCoachType() {
        return coachType;
    }

    /**
     * Gets a coach id as String type.
     * A coach id must begin with "W" as stated in the assignment.
     * @return coach id preceded by "W"
     */
    public String getCoachID() {
        return "W" + coachID;
    }

    /**
     * Sets coach id
     * @param coachID id
     */
    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }
    
    /**
     * Gets the height of a coach type.
     * This height means number of rows the image representation of each type has.
     * @param type coach type
     * @return 0 if there are no such type
     */
    public int getCoachTypeHigh(CoachTypes type) {
        switch(type) {
            case FREIGHT:
                return 5;
            case PASSENGER:
                return 6;
            case SPECIAL:
                return 7;
            default:
                break;
        }
        return 0;
    }
    
    /**
     * Gets each row of image representation of a coach type.
     * @param type coach type
     * @param row row
     * @return the image representation
     */
    public String getImage(CoachTypes type, int row) {
        return type.getCoachTypeImage(type, row);
    }
    
    /**
     * @return String representation of a coach
     */
    public String toString() {
        return coachID + " " + coachType.getContent();
    }

}
