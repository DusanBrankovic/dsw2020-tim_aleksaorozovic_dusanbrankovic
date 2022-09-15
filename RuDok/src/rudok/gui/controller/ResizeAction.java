package rudok.gui.controller;

import rudok.AppCore;
import rudok.gui.error.ErrorType;
import rudok.gui.view.MainFrame;
import rudok.gui.view.ProjectView;

import java.awt.event.ActionEvent;

public class ResizeAction extends AbstractRudokAction{

    public ResizeAction(){

        putValue(SMALL_ICON, loadIcon("images/Resize.png"));
        putValue(SHORT_DESCRIPTION, "Resize slots");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ProjectView pv = MainFrame.getInstance().getRightSide().getFocus();

        if(pv != null) {
            pv.startResizeState();
        }else{
            AppCore.getInstance().getErrorHandler().generateError(ErrorType.PROJECT_NOT_OPENED);
        }
    }

}
