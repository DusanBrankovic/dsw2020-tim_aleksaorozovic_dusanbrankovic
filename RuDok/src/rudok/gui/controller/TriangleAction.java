package rudok.gui.controller;

import rudok.AppCore;
import rudok.gui.error.ErrorType;
import rudok.gui.view.MainFrame;
import rudok.gui.view.ProjectView;

import java.awt.event.ActionEvent;

public class TriangleAction extends AbstractRudokAction {

    public TriangleAction(){

        putValue(SMALL_ICON, loadIcon("images/Triangle.png"));
        putValue(SHORT_DESCRIPTION, "Draw triangle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ProjectView pv = MainFrame.getInstance().getRightSide().getFocus();

        if(pv != null) {
            pv.startTriangleState();
        }else{
            AppCore.getInstance().getErrorHandler().generateError(ErrorType.PROJECT_NOT_OPENED);
        }
    }
}
