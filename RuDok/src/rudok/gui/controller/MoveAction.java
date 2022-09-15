package rudok.gui.controller;

import rudok.AppCore;
import rudok.gui.error.ErrorType;
import rudok.gui.view.MainFrame;
import rudok.gui.view.ProjectView;

import java.awt.event.ActionEvent;

public class MoveAction extends AbstractRudokAction{
    public MoveAction(){

        putValue(SMALL_ICON, loadIcon("images/Move.png"));
        putValue(SHORT_DESCRIPTION, "Move slots");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ProjectView pv = MainFrame.getInstance().getRightSide().getFocus();

        if(pv != null) {
            pv.startMoveState();
        }else{
            AppCore.getInstance().getErrorHandler().generateError(ErrorType.PROJECT_NOT_OPENED);
        }
    }
}
