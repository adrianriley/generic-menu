/*
 * 
 */
package uk.co.ariley.armenu;

import java.io.File;
import java.io.InputStream;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.xml.bind.JAXBException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.ariley.armenu.schema.MenuConfigUnmarshaller;
import uk.co.ariley.armenu.schema.types.Menu;
import uk.co.ariley.armenu.schema.types.MenuGroupType;
import static org.junit.Assert.*;

/**
 *
 * @author ariley
 */
public class MenuGeneratorTest {

    public MenuGeneratorTest() {
    }


    @Before
    public void setUp() {
    }


    @After
    public void tearDown() {
    }


    @Test
    public void testGenerate() throws JAXBException {
        final String pathname = "../armenu-schema/src/test/resources/sample/sample-menu.xml";
        final MenuConfigUnmarshaller unmarshaller = new MenuConfigUnmarshaller();
        final Menu menu = unmarshaller.unmarshall(new File(pathname));

        final MenuGenerator generator = new MenuGenerator();
        JMenu jMenu = generator.generateMenu(menu, "Start");

        assertNotNull("menu", jMenu);
        assertEquals("menu.itemCount", 2, jMenu.getItemCount());
        final JMenuItem menuItem0 = jMenu.getItem(0);
        assertEquals("menuItem0.class", JMenu.class, menuItem0.getClass());
        assertEquals("menuItem0.text", "First menu group", menuItem0.getText());
        assertEquals("menuItem0.itemCount", 2, ((JMenu) menuItem0).getItemCount());
        final JMenuItem item1 = ((JMenu) menuItem0).getItem(1);
        assertEquals("menuItem0.items[1].class", JMenuItem.class, item1.getClass());
        assertEquals("menuItem0.items[1].text", "Second menu item", item1.getText());
        assertEquals("menuItem0.items[1].action.class",
                ExecuteAction.class,
                item1.getAction().getClass());

        final JMenuItem menuItem1 = jMenu.getItem(1);
        assertEquals("menuItem1.text", "Second menu group", menuItem1.getText());
        assertEquals("menuItem1.itemCount", 4,
                ((JMenu) menuItem1).getItemCount());
        final JMenuItem item2 = ((JMenu) menuItem1).getItem(2);
        assertEquals("menuItem1.item2.class", JMenu.class, item2.getClass());
        assertEquals("menuItem1.item2.text", "Second menu group, first subgroup", item2.getText());
    }


    @Test
    public void testSortByName() throws JAXBException {
        InputStream stream = this.getClass().getResourceAsStream("/configuration-test-menu.xml");
        final MenuConfigUnmarshaller unmarshaller = new MenuConfigUnmarshaller();
        final Menu menu = unmarshaller.unmarshall(stream);

        final Configuration configuration = new Configuration();
        configuration.setSortByName(true);

        final MenuGenerator generator = new MenuGenerator();
        JMenu jMenu = generator.generateMenu(menu, "Start", configuration);
        testSimpleOrder(jMenu);
    }


    @Test
    public void testSubmenusToTop() throws JAXBException {
        InputStream stream = this.getClass().getResourceAsStream("/configuration-test-menu.xml");
        final MenuConfigUnmarshaller unmarshaller = new MenuConfigUnmarshaller();
        final Menu menu = unmarshaller.unmarshall(stream);
        final MenuGroupType menuGroupB = (MenuGroupType) menu.getItems().getMenuGroupOrMenuItem().get(1);

        final Configuration configuration = new Configuration();
        configuration.setSubmenuPosition(SubmenuPosition.TOP);

        final MenuGenerator generator = new MenuGenerator();
        JMenu jMenu = generator.generateMenu(menu, "Start", configuration);
        
        JMenu menuB = (JMenu) jMenu.getItem(1);
        assertEquals("Original order not preserved", menuGroupB.getName(), menuB.getText());
        assertEquals("Expected first item of second menu to be submenu", JMenu.class, menuB.getItem(0).getClass());
    }


    @Test
    public void testSubmenusToBottom() throws JAXBException {
        InputStream stream = this.getClass().getResourceAsStream("/configuration-test-menu.xml");
        final MenuConfigUnmarshaller unmarshaller = new MenuConfigUnmarshaller();
        final Menu menu = unmarshaller.unmarshall(stream);
        final MenuGroupType menuGroupB = (MenuGroupType) menu.getItems().getMenuGroupOrMenuItem().get(1);

        final Configuration configuration = new Configuration();
        configuration.setSubmenuPosition(SubmenuPosition.BOTTOM);

        final MenuGenerator generator = new MenuGenerator();
        JMenu jMenu = generator.generateMenu(menu, "Start", configuration);
        
        JMenu menuB = (JMenu) jMenu.getItem(1);
        assertEquals("Original order not preserved", menuGroupB.getName(), menuB.getText());
        assertEquals("Expected first item of second menu to be menu item", JMenuItem.class, menuB.getItem(0).getClass());
        assertEquals("Expected last item of second menu to be submenu", JMenu.class, menuB.getItem(menuB.getItemCount() - 1).getClass());
    }


    @Test
    public void testSortAndSubmenusToTop() throws JAXBException {
        InputStream stream = this.getClass().getResourceAsStream("/configuration-test-menu.xml");
        final MenuConfigUnmarshaller unmarshaller = new MenuConfigUnmarshaller();
        final Menu menu = unmarshaller.unmarshall(stream);
        final MenuGroupType menuGroupB = (MenuGroupType) menu.getItems().getMenuGroupOrMenuItem().get(1);

        final Configuration configuration = new Configuration();
        configuration.setSortByName(true);
        configuration.setSubmenuPosition(SubmenuPosition.TOP);

        final MenuGenerator generator = new MenuGenerator();
        JMenu jMenu = generator.generateMenu(menu, "Start", configuration);
        
        JMenu menuB = (JMenu) jMenu.getItem(0);
        assertEquals("Sort failed", menuGroupB.getName(), menuB.getText());
        assertEquals("Expected first item of second menu to be submenu", JMenu.class, menuB.getItem(0).getClass());
        testSimpleOrder(menuB, 1, jMenu.getItemCount() - 1);
    }


    private void testSimpleOrder(JMenu jMenu) {
        testSimpleOrder(jMenu, 0, jMenu.getItemCount() - 1);

        for (int i = 0; i < jMenu.getItemCount(); i++) {
            JMenuItem item = jMenu.getItem(i);
            if (item instanceof JMenu) {
                testSimpleOrder((JMenu) item);
            }
        }
    }


    private void testSimpleOrder(JMenu jMenu, int start, int end) {
        for (int i = start; i < end; i++) {
            JMenuItem itemA = jMenu.getItem(i);
            JMenuItem itemB = jMenu.getItem(i + 1);
            assertTrue("Bad sort: " + itemA.getText() + " v. " + itemB.getText(), itemA.getText().compareTo(itemB.getText()) <= 0);
        }
    }
}
