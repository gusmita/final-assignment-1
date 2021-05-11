package edu.kit.informatik.train.model;

/**
 * Contains String value of coach types as stated in the assignment
 * 
 * @author Gusmita
 * @version 1.0
 */
public enum CoachTypes {
    /**
     * coach type passenger
     */
    PASSENGER("p"), 
    /**
     * coach type freight
     */
    FREIGHT("f"), 
    /**
     * coach type special
     */
    SPECIAL("s");
    
    /**
     * String representation of the output
     */
    private String content;
    
    /**
     * @param content the String representation for output
     */
    CoachTypes(String content) {
        this.content = content;
    }

    /**
     * @return String value of coach type
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Gets image representation of a coach. (Copyright 1993,1998,2014 Toyoda Masashi (mtoyoda@acm.org))
     * @param type coach type
     * @param row each row of image representation
     * @return each row of image representation of a coach
     */
    public String getCoachTypeImage(CoachTypes type, int row) {
        switch(type) {
            case FREIGHT:
                switch (row) {
                    case 1:
                        return "                    ";
                    case 2:
                        return "                    ";
                    case 3:
                        return "                    ";
                    case 4:
                        return "|                  |";
                    case 5:
                        return "|                  |";
                    case 6:
                        return "|                  |";
                    case 7:
                        return "|__________________|";
                    case 8:
                        return "   (O)        (O)   ";
                    default:
                        break;
                }
                break;
            case PASSENGER:
                switch (row) {
                    case 1:
                        return "                    ";
                    case 2:
                        return "                    ";
                    case 3:
                        return "____________________";
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
                break;
            case SPECIAL:
                switch (row) {
                    case 1:
                        return "                   ";
                    case 2:
                        return "               ____";
                    case 3:
                        return "/--------------|  |";
                    case 4:
                        return "\\--------------|  |";
                    case 5:
                        return "  | |          |  |";
                    case 6:
                        return " _|_|__________|  |";
                    case 7:
                        return "|_________________|";
                    case 8:
                        return "   (O)       (O)   ";
                    default:
                        break;
                }
                break;
            default:
                break;
        
        }
        return null;
    }

}
