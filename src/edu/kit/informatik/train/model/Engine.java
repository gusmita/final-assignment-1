package edu.kit.informatik.train.model;

/**
 * Encapsulates an engine
 * 
 * @author Gusmita
 * @version 1.0
 */
public class Engine {
    
    /**
     * Engine types either an electrical, steam or diesel engine
     */
    private EngineTypes engineType;
    /**
     * Engine's series
     */
    private String series;
    /**
     * Engine's name
     */
    private String name;
    /**
     * Engine's id consists of series and name
     */
    private String engineID;
    
    /**
     * A constructor to instantiate an engine
     * @param engineType engine type
     * @param series engine's series
     * @param name engine's name
     */
    public Engine(EngineTypes engineType, String series, String name) {
        this.engineType = engineType;
        this.series = series;
        this.name = name;
    }
    
    /**
     * Gets engine type
     * @return engine type
     */
    public EngineTypes getEngineType() {
        return engineType;
    }

    /**
     * Gets series
     * @return series
     */
    public String getSeries() {
        return series;
    }

    /**
     * Gets the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the id
     * @return id
     */
    public String getEngineID() {
        return engineID;
    }

    /**
     * Sets the id
     * @param id id
     */
    public void setEngineID(String id) {
        this.engineID = id;
    }
    
    /**
     * Gets the height of engine type.
     * This height means number of rows the image representation of each type has.
     * @param type engine type
     * @return 0 if there are no such type
     */
    public int getEngineTypeHigh(EngineTypes type) {
        switch(type) {
            case DIESEL:
                return 6;
            case ELECTRICAL:
                return 8;
            case STEAM:
                return 6;
            default:
                break;
        }
        return 0;
    }
    
    /**
     * Gets each row of image representation of engine type.
     * @param type egine type
     * @param row row
     * @return the image representation
     */
    public String getImage(EngineTypes type, int row) {
        return type.getEngineTypeImage(type, row);
    }
    
    /**
     * @return String representation of an engine
     */
    public String toString() {
        return  engineType.getContent() + " " + series + " " + name;
    }

}
