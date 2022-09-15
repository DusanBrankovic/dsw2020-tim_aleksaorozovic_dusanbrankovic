package rudok.gui.controller;

import rudok.AppCore;
import rudok.gui.error.ErrorType;
import rudok.gui.view.MainFrame;
import rudok.gui.view.ProjectView;

import java.awt.event.ActionEvent;

public class RotateAction extends AbstractRudokAction{

    public RotateAction(){

        putValue(SMALL_ICON, loadIcon("images/Rotate.png"));
        putValue(SHORT_DESCRIPTION, "Rotate slots");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ProjectView pv = MainFrame.getInstance().getRightSide().getFocus();

        if(pv != null) {
            pv.startRotateState();
        }else{
            AppCore.getInstance().getErrorHandler().generateError(ErrorType.PROJECT_NOT_OPENED);
        }
    }
}
