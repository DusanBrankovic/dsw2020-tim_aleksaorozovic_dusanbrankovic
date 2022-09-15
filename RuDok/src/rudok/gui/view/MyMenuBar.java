package rudok.gui.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {


    public MyMenuBar(){


        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getUndoAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getRedoAction());
        helpMenu.add(MainFrame.getInstance().getActionManager().getAboutAction());

        this.add(fileMenu);
        this.add(editMenu);
        this.add(helpMenu);

    }
}
