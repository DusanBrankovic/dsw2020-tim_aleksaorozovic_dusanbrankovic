package rudok.gui.controller;

import rudok.AppCore;
import rudok.gui.error.ErrorType;
import rudok.gui.view.MainFrame;
import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;

import java.awt.event.ActionEvent;

public class DeleteSlotAction extends AbstractRudokAction {

    public DeleteSlotAction(){

        putValue(SMALL_ICON, loadIcon("images/DeleteSlot.png"));
        putValue(SHORT_DESCRIPTION, "Delete slot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ProjectView pV = MainFrame.getInstance().getRightSide().getFocus();

        if(pV != null) {
            pV.startDeleteState();
        }else{
            AppCore.getInstance().getErrorHandler().generateError(ErrorType.PROJECT_NOT_OPENED);
        }
    }
}
