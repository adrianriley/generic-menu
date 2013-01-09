/*
 * 
 */
package uk.co.ariley.armenu.schema;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import uk.co.ariley.armenu.schema.types.MenuConfig;

/**
 *
 * @author ariley
 */
public class MenuConfigUnmarshaller {
    public MenuConfig unmarshall(final File file) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller();
        MenuConfig menuConfig = (MenuConfig) unmarshaller.unmarshal(file);
        
        return menuConfig;
    }
    
    public MenuConfig unmarshall(final InputStream stream) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller();
        MenuConfig menuConfig = (MenuConfig) unmarshaller.unmarshal(stream);
        
        return menuConfig;
    }


    private Unmarshaller createUnmarshaller() throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(MenuConfig.class.getPackage().getName());
        final Unmarshaller unmarshaller = jc.createUnmarshaller();
        return unmarshaller;
    }
}
