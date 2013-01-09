/*
 * 
 */
package uk.co.ariley.armenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import uk.co.ariley.armenu.command.Command;
import uk.co.ariley.armenu.schema.types.AbstractMenuItemType;
import uk.co.ariley.armenu.schema.types.CommandType;
import uk.co.ariley.armenu.schema.types.MenuBarType;
import uk.co.ariley.armenu.schema.types.MenuCommandType;
import uk.co.ariley.armenu.schema.types.MenuConfig;
import uk.co.ariley.armenu.schema.types.MenuGroupType;
import uk.co.ariley.armenu.schema.types.MenuItemType;

/**
 *
 * @author ariley
 */
class MenuGenerator {
    
    private Map<String, Class<Command>> commandClassMap;

    JMenuBar generateMenuBar(final MenuConfig menuConfig) throws MenuGenerationException {

        return generateMenuBar(menuConfig, null);
    }

    JMenuBar generateMenuBar(final MenuConfig menuConfig, Configuration configuration) throws MenuGenerationException  {
        initCommandClassMap(menuConfig);
        
        MenuBarType menuBar = menuConfig.getMenuBar();
        if (menuBar == null) {
            throw new IllegalArgumentException("Not a menuBar configuration");
        }
        
        JMenuBar jMenuBar = new JMenuBar();
        for (Object item : menuBar.getItems().getMenuGroupOrMenuItem()) {
            if (item instanceof MenuGroupType) {
                JMenu subMenu = generateMenu((MenuGroupType) item);
                jMenuBar.add(subMenu);
            } else {
                throw new IllegalArgumentException("MenuBar can only contain MenuGroups");
            }
        }
        
        for (int i = 0; i < jMenuBar.getMenuCount(); i++) {
            configure(jMenuBar.getMenu(i), configuration);
        }

        return jMenuBar;
    }

    JMenu generateMenu(final MenuConfig menuConfig) throws MenuGenerationException {

        return generateMenu(menuConfig, null);
    }

    JMenu generateMenu(final MenuConfig menuConfig, Configuration configuration) throws MenuGenerationException {
        initCommandClassMap(menuConfig);

        MenuGroupType menu = menuConfig.getMenu();
        if (menu == null) {
            throw new IllegalArgumentException("Not a menu configuration");
        }
        final JMenu rootMenu = generateMenu(menu);
        
        configure(rootMenu, configuration);

        return rootMenu;
    }
    
    
    private void initCommandClassMap(MenuConfig menuConfig) throws MenuGenerationException {
        commandClassMap = new HashMap<>();
        
        for (MenuCommandType command : menuConfig.getCommands().getCommand()) {
            try {
                Class<Command> clazz =
                        (Class<Command>) getClass().getClassLoader().loadClass(command.getClazz());
            commandClassMap.put(command.getId(), clazz);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuGenerator.class.getName()).log(Level.SEVERE, null, ex);
                throw new MenuGenerationException(command.getId(), ex);
            }
        }
    }


    private JMenu generateMenu(final MenuGroupType menuGroupType) throws MenuGenerationException {
        JMenu menu = createMenu(menuGroupType);
        final List<AbstractMenuItemType> items = menuGroupType.getItems().getMenuGroupOrMenuItem();
        return generateMenu(items, menu);
    }


    private JMenu generateMenu(final List<AbstractMenuItemType> items, final JMenu menu) throws MenuGenerationException {
        for (Object item : items) {
            if (item instanceof MenuGroupType) {
                JMenu subMenu = generateMenu((MenuGroupType) item);
                menu.add(subMenu);
            } else {
                MenuItemType menuItemType = (MenuItemType) item;
                JMenuItem jMenuItem = createMenuItem(menuItemType);
                menu.add(jMenuItem);
            }
        }
        return menu;
    }


    private JMenuItem createMenuItem(MenuItemType menuItemType) throws MenuGenerationException {
        final String iconPath = menuItemType.getIcon();
        try {
            Command command = createCommand(menuItemType);
                
        
        final ExecuteAction action;
        if (iconPath == null) {
            action = new ExecuteAction(command, menuItemType.getName());
        } else {
            action = new ExecuteAction(command, menuItemType.getName(),
                    new ImageIcon(iconPath));
        }
        JMenuItem jMenuItem = new JMenuItem(action);
        setToolTipText(jMenuItem, menuItemType.getDescription());
        return jMenuItem;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MenuGenerator.class.getName()).log(Level.SEVERE, null, ex);
            throw new MenuGenerationException(menuItemType.getName(), ex);
        }
    }


    private JMenu createMenu(final MenuGroupType menuGroupType) {
        JMenu menu = new JMenu(menuGroupType.getName());
        setToolTipText(menu, menuGroupType.getDescription());
        if (menuGroupType.getIcon() != null) {
            menu.setIcon(new ImageIcon(menuGroupType.getIcon()));
        }
        return menu;
    }


    private void setToolTipText(final JMenuItem menu, final String description) {
        if (description != null) {
            menu.setToolTipText(description);
        }
    }


    void configure(JMenu menu, Configuration configuration) {
        if (configuration == null) {
            return;
        }
        
        if (configuration.isSortByName()) {
            sortByName(menu);
        }
        
        switch (configuration.getSubmenuPosition()) {
            case TOP: {
                // Run through the menu items in order, moving submenus to the top and preserving their order
                int pos = 0;
                for (int i = 0; i < menu.getItemCount(); i++) {
                    JMenuItem item = menu.getItem(i);
                    if (item instanceof JMenu) {
                        menu.remove(item);
                        menu.insert(item, pos);
                        pos++;
                    }
                }
                break;
            }
                
            case BOTTOM: {
                // Run through the menu items in reverse order, moving submenus to the bottom and preserving order
                int pos = menu.getItemCount() - 1;
                for (int i = menu.getItemCount() - 1; i >= 0; i--) {
                    JMenuItem item = menu.getItem(i);
                    if (item instanceof JMenu) {
                        menu.remove(item);
                        menu.insert(item, pos);
                        pos--;
                    }
                }
                break;
            }
        }
        
        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem item = menu.getItem(i);
            if (item instanceof JMenu) {
                configure((JMenu)item, configuration);
            }
        }
    }


    private void sortByName(JMenu menu) {
        List<JMenuItem> items = new ArrayList<>(menu.getItemCount());
        for (int i = 0; i < menu.getItemCount(); i++) {
            items.add(menu.getItem(i));
        }
        
        Collections.sort(items, new Comparator<JMenuItem>() {


            @Override
            public int compare(JMenuItem o1, JMenuItem o2) {
                return o1.getText().compareTo(o2.getText());
            }
            
        });
        
        menu.removeAll();
        
        for (JMenuItem item : items) {
            menu.add(item);
            
            if (item instanceof JMenu) {
                sortByName((JMenu)item);
            }
        }
    }


    private Command createCommand(MenuItemType menuItemType) throws InstantiationException, IllegalAccessException {
        CommandType command = menuItemType.getCommand();
        
        Class<Command> commandClass = commandClassMap.get(command.getRef());
        Command instance = commandClass.newInstance();
        
        for (String argument : command.getArgument()) {
            instance.addArgument(argument);
        }
        
        return instance;
    }
}
