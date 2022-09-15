package rudok.gui.controller;

import rudok.AppCore;
import rudok.gui.error.ErrorType;
import rudok.gui.view.MainFrame;
import rudok.gui.view.ProjectView;
import rudok.repository.Project;
import rudok.repository.node.RuNode;

import java.awt.event.ActionEvent;

public class OpenTabAction extends AbstractRudokAction {

    public OpenTabAction(){

        putValue(SMALL_ICON, loadIcon("images/OpenProj.png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open tabs");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        RuNode selected = MainFrame.getInstance().getTree().getSelectedNode();

        if(selected instanceof Project) {
            ProjectView pv = new ProjectView((Project) selected);
            MainFrame.getInstance().getRightSide().removeAll();
            MainFrame.getInstance().getRightSide().add(pv);
            MainFrame.getInstance().getRightSide().setFocus(pv);
            MainFrame.getInstance().getRightSide().updateUI();
        }else{
            AppCore.getInstance().getErrorHandler().generateError(ErrorType.NOT_PROJECT);
        }

    }
}
