/*
 * 
 */
package uk.co.ariley.armenu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import uk.co.ariley.armenu.schema.types.AbstractMenuItemType;
import uk.co.ariley.armenu.schema.types.CommandType;
import uk.co.ariley.armenu.schema.types.MenuCommandType;
import uk.co.ariley.armenu.schema.types.MenuConfig;
import uk.co.ariley.armenu.schema.types.MenuGroupType;
import uk.co.ariley.armenu.schema.types.MenuItemType;

/**
 *
 * @author ariley
 */
public class MenuItemPropertiesDialog extends javax.swing.JDialog {

    private AbstractMenuItemType oldValue;

    private ArgumentTableModel argumentTableModel = new ArgumentTableModel(new ArrayList<String>());

    private ComboBoxModel commandComboBoxModel = new DefaultComboBoxModel();

    /**
     * When adding a new item, whether it is a menu item or a group
     */
    private boolean newMenuGroup;


    /**
     * Creates new form MenuItemPropertiesDialog
     */
    public MenuItemPropertiesDialog(final java.awt.Frame parent, final MenuConfig menuConfig, final boolean modal) {
        super(parent, modal);
        initComponents();
        setMenuConfig(menuConfig);
    }


    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        propertiesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        descriptionTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        iconTextField = new javax.swing.JTextField();
        iconBrowseButton = new javax.swing.JButton();
        commandLabel = new javax.swing.JLabel();
        commandComboBox = new javax.swing.JComboBox();
        argumentLabel = new javax.swing.JLabel();
        argumentScrollPane = new javax.swing.JScrollPane();
        argumentTable = new javax.swing.JTable();
        argumentButtonPanel = new javax.swing.JPanel();
        addArgumentButton = new javax.swing.JButton();
        deleteArgumentButton = new javax.swing.JButton();
        moveArgumentUpButton = new javax.swing.JButton();
        moveArgumentDownButton = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Properties");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        mainPanel.setLayout(new java.awt.BorderLayout());

        propertiesPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(jLabel1, gridBagConstraints);

        nameTextField.setColumns(24);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(nameTextField, gridBagConstraints);

        jLabel2.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(jLabel2, gridBagConstraints);

        descriptionTextField.setColumns(24);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(descriptionTextField, gridBagConstraints);

        jLabel3.setText("Icon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(jLabel3, gridBagConstraints);

        iconTextField.setColumns(24);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(iconTextField, gridBagConstraints);

        iconBrowseButton.setText("Browse...");
        iconBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iconBrowseButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(iconBrowseButton, gridBagConstraints);

        commandLabel.setText("Command");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(commandLabel, gridBagConstraints);

        commandComboBox.setModel(commandComboBoxModel);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(commandComboBox, gridBagConstraints);

        argumentLabel.setText("Arguments");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(argumentLabel, gridBagConstraints);

        argumentScrollPane.setPreferredSize(new java.awt.Dimension(80, 20));

        argumentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        argumentTable.setTableHeader(null);
        argumentScrollPane.setViewportView(argumentTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        propertiesPanel.add(argumentScrollPane, gridBagConstraints);

        argumentButtonPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 1));

        addArgumentButton.setText("+");
        addArgumentButton.setToolTipText("Add argument");
        addArgumentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addArgumentButtonActionPerformed(evt);
            }
        });
        argumentButtonPanel.add(addArgumentButton);

        deleteArgumentButton.setText("X");
        deleteArgumentButton.setToolTipText("Delete selected argiment");
        deleteArgumentButton.setEnabled(false);
        deleteArgumentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteArgumentButtonActionPerformed(evt);
            }
        });
        argumentButtonPanel.add(deleteArgumentButton);

        moveArgumentUpButton.setText("^");
        moveArgumentUpButton.setToolTipText("Move selected argiment up");
        moveArgumentUpButton.setEnabled(false);
        moveArgumentUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveArgumentUpButtonActionPerformed(evt);
            }
        });
        argumentButtonPanel.add(moveArgumentUpButton);

        moveArgumentDownButton.setText("v");
        moveArgumentDownButton.setToolTipText("Move selected argiment down");
        moveArgumentDownButton.setEnabled(false);
        moveArgumentDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveArgumentDownButtonActionPerformed(evt);
            }
        });
        argumentButtonPanel.add(moveArgumentDownButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        propertiesPanel.add(argumentButtonPanel, gridBagConstraints);

        mainPanel.add(propertiesPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.BorderLayout());

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jPanel1.add(okButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel1.add(cancelButton);

        buttonPanel.add(jPanel1, java.awt.BorderLayout.SOUTH);
        buttonPanel.add(jSeparator2, java.awt.BorderLayout.NORTH);

        mainPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iconBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iconBrowseButtonActionPerformed
        JFileChooser iconFileChooser;
        String iconPath = iconTextField.getText();
        if (iconPath != null && iconPath.trim().length() > 0) {
            File iconFile = new File(iconPath);
            iconFileChooser = new JFileChooser(iconFile.getParent());
            iconFileChooser.setSelectedFile(iconFile);
        } else {
            iconFileChooser = new JFileChooser();
        }
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "gif", "jpg", "jpeg",
                "png");
        iconFileChooser.setFileFilter(filter);
        int returnVal = iconFileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            iconTextField.setText(iconFileChooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_iconBrowseButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // Validation
        boolean ok = true;
        String name = nameTextField.getText();
        if (name == null || name.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Name is required", "Error",
                    JOptionPane.ERROR_MESSAGE);
            ok = false;
        }
        if (commandComboBox.isVisible()) {
            String command = (String) commandComboBox.getSelectedItem();
            if (command == null || command.trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Command is required", "Error",
                        JOptionPane.ERROR_MESSAGE);
                ok = false;
            }
        }
        if (ok) {
            this.setVisible(false);

            AbstractMenuItemType newValue = getValue();
            PropertyChangeEvent event = new PropertyChangeEvent(this, "value", oldValue, newValue);
            for (PropertyChangeListener listener : getPropertyChangeListeners()) {
                listener.propertyChange(event);
            }
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void addArgumentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addArgumentButtonActionPerformed
        argumentTableModel.setValueAt(null, argumentTableModel.getRowCount(), 0);
    }//GEN-LAST:event_addArgumentButtonActionPerformed

    private void moveArgumentUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveArgumentUpButtonActionPerformed
        final int selectedRow = argumentTable.getSelectedRow();
        argumentTableModel.moveUp(selectedRow);
        argumentTable.getSelectionModel().setSelectionInterval(selectedRow - 1, selectedRow - 1);
    }//GEN-LAST:event_moveArgumentUpButtonActionPerformed

    private void moveArgumentDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveArgumentDownButtonActionPerformed
        final int selectedRow = argumentTable.getSelectedRow();
        argumentTableModel.moveDown(selectedRow);
        argumentTable.getSelectionModel().setSelectionInterval(selectedRow + 1, selectedRow + 1);
    }//GEN-LAST:event_moveArgumentDownButtonActionPerformed

    private void deleteArgumentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteArgumentButtonActionPerformed
        argumentTableModel.delete(argumentTable.getSelectedRow());
        argumentTable.getSelectionModel().clearSelection();
    }//GEN-LAST:event_deleteArgumentButtonActionPerformed


    private void setMenuConfig(MenuConfig menuConfig) {
        if (menuConfig == null) {
            commandComboBoxModel = new DefaultComboBoxModel();
        } else {
            List<String> commandList = new ArrayList<>(menuConfig.getCommands().getCommand().size());
            for (MenuCommandType command : menuConfig.getCommands().getCommand()) {
                commandList.add(command.getId());
            }
            String[] commands = commandList.toArray(new String[commandList.size()]);
            commandComboBoxModel = new DefaultComboBoxModel(commands);
        }

        commandComboBox.setModel(commandComboBoxModel);
    }


    public AbstractMenuItemType getValue() {
        AbstractMenuItemType menuItem;
        if (commandComboBox.isVisible()) {
            MenuItemType menuItemType = new MenuItemType();
            CommandType command = new CommandType();
            command.setRef((String) commandComboBoxModel.getSelectedItem());
            for (int row = 0; row < argumentTableModel.getRowCount(); row++) {
                final String value = (String) argumentTableModel.getValueAt(row, 0);
                if (value != null && value.length() > 0) {
                    command.getArgument().add(value);
                }
            }
            menuItemType.setCommand(command);
            menuItem = menuItemType;
        } else {
            menuItem = new MenuGroupType();
        }
        menuItem.setName(nameTextField.getText());
        menuItem.setDescription(descriptionTextField.getText());
        menuItem.setIcon(iconTextField.getText());

        return menuItem;
    }


    public void setValue(AbstractMenuItemType menuItem) {
        oldValue = menuItem;
        if (menuItem != null) {
            nameTextField.setText(menuItem.getName());
            descriptionTextField.setText(menuItem.getDescription());
            iconTextField.setText(menuItem.getIcon());
            if (menuItem instanceof MenuItemType) {
                CommandType command = ((MenuItemType) menuItem).getCommand();
                commandComboBox.setSelectedItem(command.getRef());
                List<String> arguments = new ArrayList<>(command.getArgument());
                argumentTableModel = new ArgumentTableModel(arguments);
                argumentTable.setModel(argumentTableModel);
                argumentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        deleteArgumentButton.setEnabled(argumentTable.getSelectedRowCount() > 0);
                        moveArgumentDownButton.setEnabled(argumentTable.getSelectedRow()
                                < argumentTableModel.getRowCount() - 1);
                        moveArgumentUpButton.setEnabled(argumentTable.getSelectedRow() > 0);
                    }
                });
            } else {
                hideCommandComponents();
            }
        }
    }


    public boolean isNewMenuGroup() {
        return newMenuGroup;
    }


    public void setNewSubmenu(boolean newMenuGroup) {
        this.newMenuGroup = newMenuGroup;
        if (newMenuGroup) {
            hideCommandComponents();
        }
    }


    private String[] parseCommand(String command) {
        final List<String> tokens = new ArrayList<String>();

        boolean inQuotedString = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);

            switch (c) {
                case '\\': {
                    ++i;
                    if (i < command.length()) {
                        sb.append(command.charAt(i));
                    }
                    break;
                }

                case '"': {
                    inQuotedString = !inQuotedString;
                    break;
                }

                case ' ': {
                    if (inQuotedString) {
                        sb.append(c);
                    } else {
                        tokens.add(sb.toString());
                        sb = new StringBuilder();
                    }
                    break;
                }

                default: {
                    sb.append(c);
                    break;
                }
            }

        }

        if (sb.length() > 0) {
            tokens.add(sb.toString());
        }


        return tokens.toArray(new String[tokens.size()]);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuItemPropertiesDialog.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuItemPropertiesDialog.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuItemPropertiesDialog.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuItemPropertiesDialog.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuItemPropertiesDialog dialog =
                        new MenuItemPropertiesDialog(new javax.swing.JFrame(), null, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addArgumentButton;
    private javax.swing.JPanel argumentButtonPanel;
    private javax.swing.JLabel argumentLabel;
    private javax.swing.JScrollPane argumentScrollPane;
    private javax.swing.JTable argumentTable;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox commandComboBox;
    private javax.swing.JLabel commandLabel;
    private javax.swing.JButton deleteArgumentButton;
    private javax.swing.JTextField descriptionTextField;
    private javax.swing.JButton iconBrowseButton;
    private javax.swing.JTextField iconTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton moveArgumentDownButton;
    private javax.swing.JButton moveArgumentUpButton;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel propertiesPanel;
    // End of variables declaration//GEN-END:variables


    private void hideCommandComponents() {
        commandLabel.getParent().remove(commandLabel);
        commandComboBox.getParent().remove(commandComboBox);
        argumentLabel.getParent().remove(argumentLabel);
        argumentScrollPane.getParent().remove(argumentScrollPane);
        argumentButtonPanel.getParent().remove(argumentButtonPanel);
        this.pack();
    }

    private class ArgumentTableModel extends AbstractTableModel {

        private List<String> arguments;


        public ArgumentTableModel(List<String> arguments) {
            this.arguments = arguments;
        }


        @Override
        public int getRowCount() {
            return arguments.size();
        }


        @Override
        public int getColumnCount() {
            return 1;
        }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return arguments.get(rowIndex);
        }


        @Override
        public String getColumnName(int column) {
            return null;
        }


        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }


        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            String value = aValue == null ? null : String.valueOf(aValue);
            if (rowIndex < arguments.size()) {
                arguments.set(rowIndex, value);
                fireTableCellUpdated(rowIndex, columnIndex);
            } else {
                arguments.add(value);
                fireTableRowsInserted(rowIndex, columnIndex);
            }
        }


        public void moveUp(final int row) {
            if (row > 0) {
                String value = arguments.remove(row);
                arguments.add(row - 1, value);
                fireTableCellUpdated(row - 1, 0);
                fireTableCellUpdated(row, 0);
            }
        }


        public void moveDown(final int row) {
            moveUp(row + 1);
        }
        
        
        public void delete(final int row) {
            arguments.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }
}
