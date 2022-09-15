package rudok.gui.tree.controller;

import rudok.gui.tree.model.RuTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class RuTreeSelectionListener implements TreeSelectionListener {


    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        RuTreeItem treeItemSelected = (RuTreeItem)path.getLastPathComponent();

        System.out.println("Selektovan cvor: "+ treeItemSelected.getName());
        System.out.println("getPath: " + e.getPath());
    }
}
