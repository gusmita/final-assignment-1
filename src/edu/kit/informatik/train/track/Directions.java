package edu.kit.informatik.train.track;

/**
 * An enum consists of all available directions
 * 
 * @author Gusmita
 * @version 1.0
 */
public enum Directions {
    
    /**
     * The right direction
     */
    RIGHT("1,0"), 
    
    /**
     * The left direction
     */
    LEFT("-1,0"), 
    
    /**
     * The up direction
     */
    UP("0,1"), 
    
    /**
     * The down direction
     */
    DOWN("0,-1");
    
    /**
     * String representation of the output
     */
    private String value;
    
    /**
     * @param value the String representation for output
     */
    Directions(String value) {
        this.value = value;
    }

    /**
     * Gets the String value of coach type
     * @return value
     */
    public String getValue() {
        return value;
    }

}
