package rudok.gui.controller;

import rudok.AppCore;
import rudok.gui.error.ErrorType;
import rudok.gui.view.MainFrame;
import rudok.gui.view.ProjectView;

import java.awt.event.ActionEvent;

public class CircleAction extends AbstractRudokAction {

    public CircleAction(){

        putValue(SMALL_ICON, loadIcon("images/Circle.png"));
        putValue(SHORT_DESCRIPTION, "Draw circle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ProjectView pv = MainFrame.getInstance().getRightSide().getFocus();

        if(pv != null) {
            pv.startCircleState();
        }else{
            AppCore.getInstance().getErrorHandler().generateError(ErrorType.PROJECT_NOT_OPENED);
        }
    }
}
