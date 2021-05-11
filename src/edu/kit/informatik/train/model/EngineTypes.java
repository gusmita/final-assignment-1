package edu.kit.informatik.train.model;

/**
 * Contains String value of engine types as stated in the assignment
 * 
 * @author Gusmita
 */
public enum EngineTypes {
    
    /**
     * engine type electrical
     */
    ELECTRICAL("e"), 
    
    /**
     * engine type steam
     */
    STEAM("s"), 
    
    /**
     * engine type diesel
     */
    DIESEL("d");
    
    /**
     * String representation of the output
     */
    private String content;
    
    /**
     * @param content the String representation for output
     */
    EngineTypes(String content) {
        this.content = content;
    }

    /**
     * Gets the String value of engine type
     * @return String content
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Gets image representation of an engine. (Copyright 1993,1998,2014 Toyoda Masashi (mtoyoda@acm.org))
     * @param type engine type
     * @param row each row of image representation
     * @return null if there is no such type
     */
    public String getEngineTypeImage(EngineTypes type, int row) {
        switch (type) {
            case DIESEL:
                switch (row) {
                    case 1:
                        return "                      ";
                    case 2:
                        return "                      ";
                    case 3:
                        return "  _____________|____  ";
                    case 4:
                        return " /_| ____________ |_\\ ";
                    case 5:
                        return "/   |____________|   \\";
                    case 6:
                        return "\\                    /";
                    case 7:
                        return " \\__________________/ ";
                    case 8:
                        return "  (O)(O)      (O)(O)  ";
                    default:
                        break;
                }
                break;
            case ELECTRICAL:
                switch (row) {
                    case 1:
                        return "               ___    ";
                    case 2:
                        return "                 \\    ";
                    case 3:
                        return "  _______________/__  ";
                    case 4:
                        return " /_| ____________ |_\\ ";
                    case 5:
                        return "/   |____________|   \\";
                    case 6:
                        return "\\                    /";
                    case 7:
                        return " \\__________________/ ";
                    case 8:
                        return "  (O)(O)      (O)(O)  ";
                    default:
                        break;
                }
                break;
            case STEAM:
                switch (row) {
                    case 1:
                        return "                    ";
                    case 2:
                        return "                    ";
                    case 3:
                        return "     ++      +------";
                    case 4:
                        return "     ||      |+-+ | ";
                    case 5:
                        return "   /---------|| | | ";
                    case 6:
                        return "  + ========  +-+ | ";
                    case 7:
                        return " _|--/~\\------/~\\-+ ";
                    case 8:
                        return "//// \\_/      \\_/   ";
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
