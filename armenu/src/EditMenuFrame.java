/*
 * 
 */
package uk.co.ariley.armenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import uk.co.ariley.armenu.schema.types.AbstractMenuGroupType;
import uk.co.ariley.armenu.schema.types.AbstractMenuItemType;
import uk.co.ariley.armenu.schema.types.MenuBarType;
import uk.co.ariley.armenu.schema.types.MenuConfig;
import uk.co.ariley.armenu.schema.types.MenuGroupItemsType;
import uk.co.ariley.armenu.schema.types.MenuGroupType;

/**
 * @TODO: Move up, move down, cut, copy, paste. drag'n'drop
 * @author ariley
 */
public class EditMenuFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    private MenuConfig menuConfig;

    private boolean unsavedChanges = false;


    /**
     * Creates new form EditMenuFrame
     */
    public EditMenuFrame() {
        initComponents();
    }


    public void setMenuConfig(MenuConfig menuConfig) {
        this.menuConfig = menuConfig;
        initTree();
    }


    private void initTree() {
        TreeNode root = createNodes();
        DefaultTreeModel model = new DefaultTreeModel(root);
        menuTree.setModel(model);
        
        menuTree.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editMenuItem();
                }
            }
        });
    }
    
    
    private AbstractMenuGroupType extractMenu() {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) menuTree.getModel().getRoot();
        
        AbstractMenuGroupType newMenu;
        
        if (this.menuConfig.getMenuBar() != null) {
            newMenu = new MenuBarType();
        } else {
            newMenu = new MenuGroupType();
        }
        newMenu.setItems(new MenuGroupItemsType());
        
        extractMenu(root, newMenu.getItems());
        
        return newMenu;
    }
    
    private void extractMenu(DefaultMutableTreeNode root, MenuGroupItemsType items) {
        for (DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getFirstChild(); node.getNextSibling() != null; node = node.getNextSibling()) {
            AbstractMenuItemType item = (AbstractMenuItemType) node.getUserObject();
            items.getMenuGroupOrMenuItem().add(item);
            if (item instanceof MenuGroupType) {
                ((MenuGroupType)item).setItems(null);
                extractMenu(node, ((MenuGroupType)item).getItems());
            }
        }
    }


    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuTree = new javax.swing.JTree();
        buttonPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        closeMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        addItemMenuItem = new javax.swing.JMenuItem();
        addSubmenuMenuItem = new javax.swing.JMenuItem();
        editMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();

        setMinimumSize(new java.awt.Dimension(200, 240));

        mainPanel.setLayout(new java.awt.BorderLayout());

        menuTree.setCellRenderer(new MenuTreeCellRenderer());
        menuTree.setMaximumSize(null);
        menuTree.setMinimumSize(new java.awt.Dimension(200, 240));
        menuTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                menuTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(menuTree);

        mainPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(okButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(cancelButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        fileMenu.setText("File");

        loadMenuItem.setText("Load");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadMenuItem);

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setText("Save As...");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(jSeparator1);

        closeMenuItem.setText("Close");
        closeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(closeMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        addItemMenuItem.setText("Add Item");
        addItemMenuItem.setEnabled(false);
        addItemMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(addItemMenuItem);

        addSubmenuMenuItem.setText("Add Submenu");
        addSubmenuMenuItem.setEnabled(false);
        addSubmenuMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSubmenuMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(addSubmenuMenuItem);

        editMenuItem.setText("Edit");
        editMenuItem.setEnabled(false);
        editMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editMenuItem);

        deleteMenuItem.setText("Delete");
        deleteMenuItem.setEnabled(false);
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        maybeClose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okButtonActionPerformed

    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loadMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeMenuItemActionPerformed
        maybeClose();
    }//GEN-LAST:event_closeMenuItemActionPerformed

    private void addItemMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemMenuItemActionPerformed
        addMenuItem();
    }//GEN-LAST:event_addItemMenuItemActionPerformed

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
        deleteMenuItems();
    }//GEN-LAST:event_deleteMenuItemActionPerformed

    private void menuTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_menuTreeValueChanged
        int[] selectionRows = menuTree.getSelectionRows();
        addItemMenuItem.setEnabled(selectionRows.length == 1);
        addSubmenuMenuItem.setEnabled(selectionRows.length == 1);
        editMenuItem.setEnabled(selectionRows.length == 1);
        deleteMenuItem.setEnabled(selectionRows.length > 0);
    }//GEN-LAST:event_menuTreeValueChanged

    private void editMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMenuItemActionPerformed
        editMenuItem();
    }//GEN-LAST:event_editMenuItemActionPerformed

    private void addSubmenuMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSubmenuMenuItemActionPerformed
        addSubmenu();
    }//GEN-LAST:event_addSubmenuMenuItemActionPerformed


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
            java.util.logging.Logger.getLogger(EditMenuFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditMenuFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditMenuFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditMenuFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditMenuFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addItemMenuItem;
    private javax.swing.JMenuItem addSubmenuMenuItem;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JMenuItem closeMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem editMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTree menuTree;
    private javax.swing.JButton okButton;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables


    private TreeNode createNodes() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        if (menuConfig.getMenuBar() != null) {
            createNodes(root, menuConfig.getMenuBar());
        } else {
            createNodes(root, menuConfig.getMenu());
        }
        return root;
    }


    private void createNodes(DefaultMutableTreeNode root, AbstractMenuGroupType menuGroup) {
        DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(menuGroup);
        root.add(groupNode);
        final MenuGroupItemsType items = menuGroup.getItems();
        createNodes(groupNode, items);
    }


    private void createNodes(DefaultMutableTreeNode groupNode, final MenuGroupItemsType items) {
        for (Object o : items.getMenuGroupOrMenuItem()) {
            if (o instanceof MenuGroupType) {
                createNodes(groupNode, (MenuGroupType) o);
            } else {
                DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(o);
                groupNode.add(itemNode);
            }
        }
    }


    private void addMenuItem() {
        addMenuItem(false);
    }


    private void addSubmenu() {
        addMenuItem(true);
    }


    private void addMenuItem(boolean submenuItem) {
        if (menuTree.getSelectionCount() == 1) {
            final MenuItemPropertiesDialog dialog = new MenuItemPropertiesDialog(this, true);
            dialog.addPropertyChangeListener(new PropertyChangeListener() {


                public void propertyChange(PropertyChangeEvent evt) {
                    final TreePath selectionPath = menuTree.getSelectionModel().getSelectionPath();
                    final DefaultMutableTreeNode selectedNode =
                            (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
                    final AbstractMenuItemType selectedItem =
                            (AbstractMenuItemType) selectedNode.getUserObject();
                    final AbstractMenuItemType newValue = (AbstractMenuItemType) evt.getNewValue();

                    final DefaultMutableTreeNode newNode;
                    if (newValue instanceof MenuGroupType) {
                        newNode = new DefaultMutableTreeNode(newValue, true);
                    } else {
                        newNode = new DefaultMutableTreeNode(newValue, false);
                    }
                    final DefaultTreeModel model = (DefaultTreeModel) menuTree.getModel();

                    if (selectedItem instanceof MenuGroupType) {
                        // Add new item to end of list
                        model.insertNodeInto(newNode, selectedNode,
                                selectedNode.getChildCount());

                        ((MenuGroupType) selectedItem).getItems().getMenuGroupOrMenuItem().add(newValue);
                    } else {
                        // Add new item to parent before current item
                        final DefaultMutableTreeNode parentNode =
                                (DefaultMutableTreeNode) selectedNode.getParent();
                        model.insertNodeInto(newNode, parentNode,
                                parentNode.getIndex(selectedNode));

                        final MenuGroupType parentGroup = (MenuGroupType) parentNode.getUserObject();
                        final List<AbstractMenuItemType> items =
                                parentGroup.getItems().getMenuGroupOrMenuItem();
                        items.add(items.indexOf(selectedItem), newValue);
                    }
                }
            });
            
            if (submenuItem) {
                dialog.setNewSubmenu(submenuItem);
            }
            dialog.setVisible(true);
        }
    }


    private void editMenuItem() {
        if (menuTree.getSelectionCount() == 1) {
            final MenuItemPropertiesDialog dialog = new MenuItemPropertiesDialog(this, true);
            final DefaultMutableTreeNode selectedNode =
                    (DefaultMutableTreeNode) menuTree.getSelectionModel().getSelectionPath().getLastPathComponent();
            dialog.setValue((AbstractMenuItemType)(selectedNode.getUserObject()));
            dialog.addPropertyChangeListener(new PropertyChangeListener() {


                public void propertyChange(PropertyChangeEvent evt) {
                    final TreePath selectionPath = menuTree.getSelectionModel().getSelectionPath();
                    final DefaultMutableTreeNode selectedNode =
                            (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
                    selectedNode.setUserObject(evt.getNewValue());
                    menuTree.getModel().valueForPathChanged(selectionPath, evt.getNewValue());
                }
            });
            
            dialog.setVisible(true);
        }
    }
    
    
    private void deleteMenuItems() {
        final TreePath[] selectionPaths = menuTree.getSelectionPaths();
        if (selectionPaths.length == 0) {
               JOptionPane.showMessageDialog(this, "No items selected", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;            
        }
        
        for (TreePath path : selectionPaths) {
            if (path.getPathCount() <= 1) {
                JOptionPane.showMessageDialog(this, "Cannot delete root node", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        double[] limits = {1, 2};
        String[] part = {"this item", "these {0} items"};
        ChoiceFormat format = new ChoiceFormat(limits, part);
        Format[] formats = {format};
        MessageFormat pattern = new MessageFormat("Are you sure you want to delete {0}?");
        pattern.setFormats(formats);
        Object[] args = {Integer.valueOf(selectionPaths.length)};
        String message = pattern.format(args);
        
        if (JOptionPane.showConfirmDialog(this, message, "Conform deletion", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) {
            return;
        }

        final DefaultTreeModel model = (DefaultTreeModel) menuTree.getModel();
        for (TreePath path : selectionPaths) {
            final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

            model.removeNodeFromParent(node);

            final AbstractMenuItemType item = (AbstractMenuItemType) node.getUserObject();
            final TreePath parentPath = path.getParentPath();
            final Object parentUserObject =
                    ((DefaultMutableTreeNode) parentPath.getLastPathComponent()).getUserObject();
            if (parentUserObject instanceof MenuGroupType) {
                final MenuGroupType parentGroup =
                        (MenuGroupType) parentUserObject;
                parentGroup.getItems().getMenuGroupOrMenuItem().remove(item);
            }
        }
    }
    
    class MenuTreeCellRenderer extends DefaultTreeCellRenderer {

        private static final long serialVersionUID = 1L;


        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                boolean expanded, boolean leaf, int row, boolean hasFocus) {
            final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            final Object item = node.getUserObject();
            
            // Always display submenu as folder, even when empty
            if (item instanceof MenuGroupType) {
                leaf = false;
            }

            final Component component =
                    super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row,
                    hasFocus);

            if (item instanceof AbstractMenuItemType) {
                setText(((AbstractMenuItemType) item).getName());
            } else if (item != null) {
                setText(item.toString());
            }

            return component;
        }
    }


    private void maybeClose() {
        if (unsavedChanges) {
            int option = JOptionPane.showConfirmDialog(this,
                    "There are unsaved changes. Do you want to close the dialog and lose these changes?",
                    "Confirm", JOptionPane.YES_NO_OPTION);
            if (option != JOptionPane.YES_OPTION) {
                return;
            }
        }
        this.setVisible(false);
    }
}
