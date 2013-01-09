/*
 * 
 */
package uk.co.ariley.armenu;

/**
 *
 * @author ariley
 */
public class Configuration {
    private boolean sortByName;
    private SubmenuPosition submenuPosition = SubmenuPosition.NONE;
    private String configPathname;


    public boolean isSortByName() {
        return sortByName;
    }


    public void setSortByName(boolean sortByName) {
        this.sortByName = sortByName;
    }


    public SubmenuPosition getSubmenuPosition() {
        return submenuPosition;
    }


    public void setSubmenuPosition(SubmenuPosition submenuPosition) {
        this.submenuPosition = submenuPosition;
    }


    public String getConfigPathname() {
        return configPathname;
    }


    public void setConfigPathname(String configPathname) {
        this.configPathname = configPathname;
    }
    
}
