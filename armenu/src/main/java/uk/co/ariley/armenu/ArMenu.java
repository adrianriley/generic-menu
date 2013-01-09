package uk.co.ariley.armenu;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.xml.bind.JAXBException;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Switch;

import uk.co.ariley.armenu.schema.MenuConfigUnmarshaller;
import uk.co.ariley.armenu.schema.types.MenuConfig;

/**
 * Hello world!
 *
 */
public class ArMenu {

    private MenuConfig menuConfig;

    private JFrame frame;

    private JMenuBar menuBar;

    private JMenu jMenu;

    private JPopupMenu popup;

    private JCheckBoxMenuItem sortByNameCheckbox;

    private ButtonGroup submenuPositionButtonGroup;

    private MouseListener popupListener;

    private Configuration configuration;


    public static void main(String[] args) {
        Configuration configuration = null;
        try {
            configuration = parse(args);
            if (configuration == null) {
                System.exit(1);
            }
        } catch (JSAPException ex) {
            Logger.getLogger(ArMenu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ArMenu app = new ArMenu(args[0]);
            if (configuration != null) {
                app.setConfiguration(configuration);
            }
            app.show();
        } catch (Exception ex) {
            Logger.getLogger(ArMenu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }


    private static Configuration parse(String[] args) throws JSAPException {
        JSAP jsap = new JSAP();

        Switch sortByNameSwitch = new Switch("sort-by-name").setShortFlag('s').setLongFlag(
                "sort-by-name");
        jsap.registerParameter(sortByNameSwitch);

        FlaggedOption submenuPositionOption = new FlaggedOption("submenu-position").setStringParser(
                JSAP.STRING_PARSER).setShortFlag('p').setLongFlag("submenu-position");
        jsap.registerParameter(submenuPositionOption);

        FlaggedOption configFileOption = new FlaggedOption("config-file").setStringParser(
                JSAP.STRING_PARSER)
                .setShortFlag('c').setLongFlag("config-file");
        jsap.registerParameter(configFileOption);

        final JSAPResult config = jsap.parse(args);

        if (!config.success()) {

            System.err.println();

            // print out specific error messages describing the problems
            // with the command line, THEN print usage
            for (java.util.Iterator errs = config.getErrorMessageIterator();
                    errs.hasNext();) {
                System.err.println("Error: " + errs.next());
            }

            System.err.println();
            System.err.println("Usage: java "
                    + ArMenu.class.getName());
            System.err.println("                "
                    + jsap.getUsage());
            
            return null;
        }

        Configuration configuration = new Configuration();

        if (config.contains("sort-by-name")) {
            configuration.setSortByName(config.getBoolean("sort-by-name"));
        }
        String submenuPos = config.getString("submenu-position");
        if (submenuPos != null) {
            configuration.setSubmenuPosition(SubmenuPosition.valueOf(submenuPos.toUpperCase()));
        }

        configuration.setConfigPathname(config.getString("config-file"));

        return configuration;
    }


    public ArMenu(String pathname) {
        this.configuration = new Configuration();
        configuration.setConfigPathname(pathname);
    }


    public void show() {
        init();

        if (load()) {
            frame.pack();
            frame.setVisible(true);
        } else {
            System.exit(1);
        }
    }


    public boolean reload() {
        return load();
    }


    private boolean load() {
        try {
            loadMenu();
            generate();
            return true;
        } catch (JAXBException ex) {
            Logger.getLogger(ArMenu.class.getName()).log(Level.SEVERE, null, ex);
            String message = ex.getMessage();
            if (message == null) {
                Throwable linkedException = ex.getLinkedException();
                if (linkedException != null) {
                    message = linkedException.getMessage();
                }
            }
            if (message == null) {
                Throwable linkedException = ex.getLinkedException();
                if (linkedException != null) {
                    message = linkedException.toString();
                } else {
                    message = ex.toString();
                }
            }
            JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    private void generate() {
        JMenuBar newMenuBar = generateMenuBar();

        frame.setJMenuBar(newMenuBar);
        menuBar = newMenuBar;

        menuBar.addMouseListener(popupListener);
        addPopupListener(menuBar);
    }


    private void init() {
        createFrame();
        createMenuBar();
        createPopupMenu();

        popupListener = new PopupListener();
        menuBar.addMouseListener(popupListener);
    }


    private void createFrame() throws HeadlessException, SecurityException {
        frame = new JFrame("Menu");
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void createMenuBar() {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
    }


    private void createPopupMenu() {
        popup = new JPopupMenu();

        Action sortByNameAction = new AbstractAction("Sort by name") {
            private static final long serialVersionUID = 1L;


            public void actionPerformed(ActionEvent e) {
                popup.setVisible(false);
                getConfiguration().setSortByName(sortByNameCheckbox.getState());
                generate();
            }
        };
        sortByNameCheckbox = new JCheckBoxMenuItem(sortByNameAction);
        sortByNameCheckbox.setState(getConfiguration().isSortByName());
        popup.add(sortByNameCheckbox);

        JMenu submenuPositionMenu = new JMenu("Submenu position");
        Action submenuPositionAction = new AbstractAction() {
            private static final long serialVersionUID = 1L;


            public void actionPerformed(ActionEvent e) {
                popup.setVisible(false);
                ButtonModel selection = submenuPositionButtonGroup.getSelection();
                SubmenuPosition position = SubmenuPosition.valueOf(selection.getActionCommand());
                getConfiguration().setSubmenuPosition(position);
                generate();
            }
        };
        
        submenuPositionButtonGroup = new ButtonGroup();
        final SubmenuPosition submenuPosition = getConfiguration().getSubmenuPosition();

        addSubmenuPositionRadioButtonMenuItem(submenuPositionMenu, submenuPositionAction,
                SubmenuPosition.NONE, submenuPosition);
        addSubmenuPositionRadioButtonMenuItem(submenuPositionMenu, submenuPositionAction,
                SubmenuPosition.TOP, submenuPosition);
        addSubmenuPositionRadioButtonMenuItem(submenuPositionMenu, submenuPositionAction,
                SubmenuPosition.BOTTOM, submenuPosition);
        
        
        popup.add(submenuPositionMenu);

        popup.addSeparator();

        Action reloadAction = new AbstractAction("Reload") {
            private static final long serialVersionUID = 1L;


            public void actionPerformed(ActionEvent e) {
                popup.setVisible(false);
                generate();
            }
        };
        popup.add(reloadAction);

        popup.addSeparator();

        Action closeAction = new AbstractAction("Close") {
            private static final long serialVersionUID = 1L;


            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        };
        popup.add(closeAction);
    }


    private void loadMenu() throws JAXBException {
        final MenuConfigUnmarshaller unmarshaller = new MenuConfigUnmarshaller();
        // @TODO: look for file in default locations, i.e. home, system, ...
        menuConfig = unmarshaller.unmarshall(new File(getConfiguration().getConfigPathname()));
    }


    private JMenuBar generateMenuBar() {
        final MenuGenerator generator = new MenuGenerator();
        return generator.generateMenuBar(menuConfig, configuration);
    }


    private void addSubmenuPositionRadioButtonMenuItem(final JMenu submenuPositionMenu, final Action submenuPositionAction, final SubmenuPosition submenuPosition, SubmenuPosition selectedPosition) {
        JRadioButtonMenuItem item = new JRadioButtonMenuItem(submenuPositionAction);
        item.setText(submenuPosition.getDescription());
        item.setActionCommand(submenuPosition.name());
        submenuPositionButtonGroup.add(item);
        submenuPositionMenu.add(item);
        
        if (submenuPosition.equals(selectedPosition)) {
            submenuPositionButtonGroup.setSelected(item.getModel(), true);
        }
    }


    private Configuration getConfiguration() {
        if (configuration == null) {
            configuration = new Configuration();
        }
        return configuration;
    }


    private void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }


    private void addPopupListener(JMenuBar menuBar) {
        menuBar.addMouseListener(popupListener);
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            addPopupListener(menuBar.getMenu(i));
        }
    }


    private void addPopupListener(JMenu menu) {
        menu.addMouseListener(popupListener);

        for (int i = 0; i < menu.getItemCount(); i++) {
            final JMenuItem item = menu.getItem(i);
            if (item instanceof JMenu) {
                addPopupListener((JMenu) item);
            }
        }
    }

    /**
     * MouseListener to show popup menu. The recommended method to do this these days is to use
     * {@link JComponent#setComponentPopupMenu(javax.swing.JPopupMenu) setComponentPopupMenu} but
     * that didn't work well for me. Couldn't make that work for the menu, only for the menubar, and
     * in any case the submenu position submenu did not appear.
     */
    class PopupListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }


        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                        e.getX(), e.getY());
            }
        }
    }
}
