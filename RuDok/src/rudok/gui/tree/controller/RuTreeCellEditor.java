package rudok.gui.tree.controller;

import rudok.AppCore;
import rudok.gui.error.ErrorType;
import rudok.gui.tree.model.RuTreeItem;
import rudok.repository.Workspace;
import rudok.repository.node.RuNode;
import rudok.repository.node.RuNodeComposite;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class RuTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;

    public RuTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1){
        super(arg0,arg1);
    }

    @Override
    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean isSelected, boolean expanded, boolean leaf, int row) {
        clickedOn = arg1;
        JTextField edit = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;

    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if(event instanceof MouseEvent) {
            return ((MouseEvent) event).getClickCount() == 3;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(clickedOn instanceof RuTreeItem)) {
            return;
        }

        if (e.getActionCommand().isEmpty()) {
            AppCore.getInstance().getErrorHandler().generateError(ErrorType.NAME_CANNOT_BE_EMPTY);
        } else {
            if (sameName(((RuTreeItem) clickedOn).getNodeModel(), e.getActionCommand())) {
                AppCore.getInstance().getErrorHandler().generateError(ErrorType.SAME_NAME);
            } else {
                ((RuTreeItem) clickedOn).setName(e.getActionCommand());
            }


        }
    }

    boolean sameName(RuNode clickedOn, String name){
        if(!(clickedOn instanceof Workspace)){
            for(RuNode node : ((RuNodeComposite)clickedOn.getParent()).getChildren()){
                if(node.getName().equals(name) && !node.equals(clickedOn)){
                    return true;
                }
            }
        }
        return false;
    }

}
