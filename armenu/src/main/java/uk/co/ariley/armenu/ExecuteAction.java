/*
 * 
 */
package uk.co.ariley.armenu;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import uk.co.ariley.armenu.command.Command;
import uk.co.ariley.armenu.command.CommandException;

/**
 *
 * @author ariley
 */
public class ExecuteAction extends AbstractAction {

    private static final long serialVersionUID = -482633090149905412L;

    private Command command;


    public ExecuteAction(Command command, String name) {
        this(command, name, null);
    }


    public ExecuteAction(Command command, String name, Icon icon) {
        super(name, icon);
        this.command = command;
    }


    public void actionPerformed(ActionEvent e) {
        try {
            getLogger().log(Level.INFO, "Execute: {0}", command.toString());
            command.execute();
        } catch (CommandException ex) {
            getLogger().log(Level.SEVERE, command.toString(), ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), command.toString(), JOptionPane.ERROR_MESSAGE);
        }
    }


    private Logger getLogger() {
        return Logger.getLogger(ExecuteAction.class.getName());
    }
}
