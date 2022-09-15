package rudok.gui.controller;

import rudok.gui.view.MainFrame;
import rudok.repository.*;
import rudok.repository.node.RuNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class NewProjectAction extends AbstractRudokAction{

    public NewProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/New.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int label = new Random().nextInt(100);
        RuNode selected = MainFrame.getInstance().getTree().getSelectedNode();

        if(selected instanceof Workspace){
            Project p = new Project("Project " + label, selected);
            MainFrame.getInstance().getTree().addProject((Workspace) selected, p);
        }else if(selected instanceof Project){
            Document d = new Document("Document " + label, selected);
            MainFrame.getInstance().getTree().addDocument((Project) selected, d);
        }else if(selected instanceof Document){
            Page p = new Page("Page " + label, selected);
            MainFrame.getInstance().getTree().addPage((Document) selected, p);
        }

    }
}
