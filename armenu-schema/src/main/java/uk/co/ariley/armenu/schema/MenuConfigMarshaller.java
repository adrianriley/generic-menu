/*
 * 
 */
package uk.co.ariley.armenu.schema;

import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import uk.co.ariley.armenu.schema.types.MenuConfig;

/**
 *
 * @author ariley
 */
public class MenuConfigMarshaller {

    private boolean formatted;


    public MenuConfigMarshaller() {
    }


    public MenuConfigMarshaller(boolean formatted) {
        this.formatted = formatted;
    }


    public boolean isFormatted() {
        return formatted;
    }


    public void setFormatted(boolean formatted) {
        this.formatted = formatted;
    }


    public void marshall(final MenuConfig menu, final Writer writer) throws JAXBException {

        final JAXBContext jc = JAXBContext.newInstance(MenuConfig.class.getPackage().getName());
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatted);
        marshaller.marshal(menu, writer);
    }
}
