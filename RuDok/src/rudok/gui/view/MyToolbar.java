package rudok.gui.view;

import javax.swing.*;
import java.awt.*;

public class MyToolbar extends JToolBar {

    public MyToolbar(){
        super(SwingConstants.HORIZONTAL);
        setFloatable(false);
        setBackground(Color.LIGHT_GRAY);

        add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        add(MainFrame.getInstance().getActionManager().getOpenTabAction());
        add(MainFrame.getInstance().getActionManager().getDeleteProjectAction());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
    }

}
