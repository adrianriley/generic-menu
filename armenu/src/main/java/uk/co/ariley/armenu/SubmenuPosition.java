/*
 * 
 */
package uk.co.ariley.armenu;

/**
 *
 * @author ariley
 */
public enum SubmenuPosition {
    NONE("None"),
    TOP("Top"),
    BOTTOM("Bottom");
    
    private String description;


    private SubmenuPosition(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}
