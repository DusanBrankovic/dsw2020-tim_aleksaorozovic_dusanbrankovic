package rudok.gui.controller;

import rudok.gui.view.About;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutAction extends AbstractRudokAction{

    public AboutAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/About.png"));
        putValue(NAME, "About");
        putValue(SHORT_DESCRIPTION, "About");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        About about = new About(loadIcon("../view/images/SlikaAlekse.jpg"),loadIcon("../view/images/SlikaDusana.jpg"));
    }
}
