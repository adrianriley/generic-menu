/*
 * 
 */
package uk.co.ariley.armenu;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.xml.bind.JAXBException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import uk.co.ariley.armenu.schema.MenuConfigUnmarshaller;
import uk.co.ariley.armenu.schema.types.Menu;
import static org.junit.Assert.*;

/**
 *
 * @author ariley
 */
public class EditMenuFrameTest {

    public EditMenuFrameTest() {
    }


    @Before
    public void setUp() {
    }


    @After
    public void tearDown() {
    }


//    @Ignore("because it creates a frame and waits for the user to close it")
    @Test
    public void testLoad() throws JAXBException {
        final EditMenuFrame editMenuFrame = new EditMenuFrame();
        editMenuFrame.setMenu(loadMenu());
        editMenuFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                synchronized (EditMenuFrameTest.this) {
                    EditMenuFrameTest.this.notifyAll();
                }
            }
        });
        editMenuFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                synchronized (EditMenuFrameTest.this) {
                    EditMenuFrameTest.this.notifyAll();
                }
            }
        });

        editMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editMenuFrame.setVisible(true);

        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException ex) {
        }
    }


    private Menu loadMenu() throws JAXBException {
        InputStream stream = EditMenuFrame.class.getResourceAsStream("/configuration-test-menu.xml");
        final MenuConfigUnmarshaller unmarshaller = new MenuConfigUnmarshaller();
        final Menu menu = unmarshaller.unmarshall(stream);
        return menu;
    }
}
