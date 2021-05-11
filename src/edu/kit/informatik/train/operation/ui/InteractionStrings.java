package edu.kit.informatik.train.operation.ui;

/**
 * Patterns of valid input
 * 
 * @author Gusmita
 */
public enum InteractionStrings {
    
    /**
     * Pattern of valid point
     */
    POINT_PATTERN("\\([-]?\\d+\\,[-]?\\d+\\)"),
    /**
     * Pattern of valid words
     */
    WORD_PATTERN("\\w+"),
    /**
     * Pattern of valid id
     */
    ID_PATTERN("\\w+(-\\w+)?"),
    /**
     * Pattern of a whitespace
     */
    WHITESPACE_PATTERN("\\s"),
    /**
     * Pattern of valid numbers
     */
    NUMBER_PATTERN("[-]?\\d+");
    
    /**
     * String representation of an output
     */
    private String content;
    
    /**
     * 
     * @param content String representation of an output
     */
    InteractionStrings(final String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

}
