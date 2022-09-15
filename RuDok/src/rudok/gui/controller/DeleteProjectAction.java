package rudok.gui.controller;

import rudok.AppCore;
import rudok.gui.error.ErrorType;
import rudok.gui.view.MainFrame;
import rudok.repository.*;
import rudok.repository.node.RuNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteProjectAction extends AbstractRudokAction{
    public DeleteProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/Delete.png"));
        putValue(NAME, "Delete Project");
        putValue(SHORT_DESCRIPTION, "Delete Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuNode selected = MainFrame.getInstance().getTree().getSelectedNode();
        if(selected instanceof  Workspace){
            AppCore.getInstance().getErrorHandler().generateError(ErrorType.WS_CANNOT_BE_DELETED);
        }else if(selected instanceof Project){
            MainFrame.getInstance().getTree().deleteProject((Project) selected);
        }else if(selected instanceof Document){
            MainFrame.getInstance().getTree().deleteDocument((Document) selected);
        }else if(selected instanceof Page){
            MainFrame.getInstance().getTree().deletePage((Document) selected.getParent(), (Page) selected);
        }

    }
}
