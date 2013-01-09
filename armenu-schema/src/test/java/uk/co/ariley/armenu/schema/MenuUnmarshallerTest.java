/*
 * 
 */
package uk.co.ariley.armenu.schema;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.ariley.armenu.schema.types.AbstractMenuItemType;
import uk.co.ariley.armenu.schema.types.MenuBarType;
import uk.co.ariley.armenu.schema.types.MenuCommandType;
import uk.co.ariley.armenu.schema.types.MenuConfig;
import uk.co.ariley.armenu.schema.types.MenuGroupType;
import uk.co.ariley.armenu.schema.types.MenuItemType;

import static org.junit.Assert.*;

/**
 *
 * @author ariley
 */
public class MenuUnmarshallerTest {

    public MenuUnmarshallerTest() {
    }


    @Before
    public void setUp() {
    }


    @After
    public void tearDown() {
    }


    @Test
    public void testUnmarshallFile() throws JAXBException {
        final String pathname = "src/test/resources/sample/sample-menu.xml";
        final MenuConfigUnmarshaller unmarshaller = new MenuConfigUnmarshaller();
        final MenuConfig menu = unmarshaller.unmarshall(new File(pathname));
        testReturnedValue(menu);
    }


    @Test
    public void testUnmarshallStream() throws JAXBException {
        InputStream stream = this.getClass().getResourceAsStream("/sample/sample-menu.xml");
        final MenuConfigUnmarshaller unmarshaller = new MenuConfigUnmarshaller();
        final MenuConfig menu = unmarshaller.unmarshall(stream);
        testReturnedValue(menu);
    }


    private void testReturnedValue(final MenuConfig menuConfig) {
        assertNotNull("menu-config", menuConfig);
        assertNull("menu-config.menu", menuConfig.getMenu());
        assertNotNull("menu-config.menu", menuConfig.getMenuBar());
        MenuBarType menuBar = menuConfig.getMenuBar();
        final List<AbstractMenuItemType> menuBarItems = menuBar.getItems().getMenuGroupOrMenuItem();
        assertNotNull("menuBar.menuGroup", menuBarItems);
        assertEquals("menuBar.menuGroup.size", 1, menuBarItems.size());
        final MenuGroupType topMenuGroup = (MenuGroupType) menuBarItems.get(0);
        final List<AbstractMenuItemType> items = topMenuGroup.getItems().getMenuGroupOrMenuItem();
        assertNotNull("menu.menuGroup", items);
        assertEquals("menu.menuGroup.size", 2, items.size());
        final Object item0 = items.get(0);
        assertEquals("menu.items[0].class", MenuGroupType.class, item0.getClass());
        final MenuGroupType menuGroup0 = (MenuGroupType) item0;
        assertEquals("menu.menuGroup[0].name", "First menu group", menuGroup0.getName());
        assertNotNull("menu.menuGroup[0].items", menuGroup0.getItems());
        final List<AbstractMenuItemType> menuGroupOrMenuItem = menuGroup0.getItems().getMenuGroupOrMenuItem();
        assertEquals("menu.menuGroup[0].items.size", 2, menuGroupOrMenuItem.size());
        final Object item1 = menuGroupOrMenuItem.get(1);
        assertEquals("menu.menuGroup[0].items[1].class", MenuItemType.class, item1.getClass());
        assertNotNull("menu.menuGroup[0].items[1].command", ((MenuItemType)item1).getCommand());
        assertNotNull("menu.menuGroup[0].items[1].command.argument", ((MenuItemType)item1).getCommand().getArgument());
        
        assertEquals("menu.menuGroup[0].items[1].command.argument[0]",
                "C:/Program Files/a/foo-2.exe", ((MenuItemType)item1).getCommand().getArgument().get(0));
        assertEquals("menu.menuGroup[0].items[1].command.argument[1]",
                "arg with spaces", ((MenuItemType)item1).getCommand().getArgument().get(1));
        
        final MenuGroupType menuGroup1 = (MenuGroupType) items.get(1);
        assertEquals("menu.menuGroup[1].name", "Second menu group", menuGroup1.getName());
        assertEquals("menu.menuGroup[1].items.size", 4,
                menuGroup1.getItems().getMenuGroupOrMenuItem().size());
        final Object item2 = menuGroup1.getItems().getMenuGroupOrMenuItem().get(2);
        assertEquals("menu.menuGroup[1].items[2].class", MenuGroupType.class, item2.getClass());
        
        assertNotNull("menu-config.commands", menuConfig.getCommands());
        assertNotNull("menu-config.commands.command", menuConfig.getCommands().getCommand());
        assertEquals("menu-config.commands.command.size", 1, menuConfig.getCommands().getCommand().size());
        
        MenuCommandType command = menuConfig.getCommands().getCommand().get(0);
        assertEquals("menu-config.commands.command.ref[0]", "Execute", command.getId());
    }
}
