package rudok.gui.tree.view;

import rudok.gui.tree.controller.RuTreeCellEditor;
import rudok.gui.tree.controller.RuTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class RuTreeView extends JTree {

    public RuTreeView(DefaultTreeModel defaultTreeModel){
        setModel(defaultTreeModel);
        RuTreeCellRenderer ruTreeCellRenderer = new RuTreeCellRenderer();
        addTreeSelectionListener(new RuTreeSelectionListener());
        setCellEditor(new RuTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);
        setExpandsSelectedPaths(true);
    }

}
