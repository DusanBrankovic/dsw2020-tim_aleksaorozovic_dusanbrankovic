package rudok.gui.controller;

import rudok.gui.view.MainFrame;
import rudok.repository.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class SaveProjectAction extends AbstractRudokAction {

    public SaveProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/Save.png"));
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new ProjectFileFilter());

        Project project = (Project) MainFrame.getInstance().getTree().getSelectedNode();
        File projectFile = project.getProjectFile();

        if(!project.isChanged()) {
            return;
        }

        if(project.getProjectFile() == null){
            if(jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
                projectFile = jfc.getSelectedFile();
                System.out.println(projectFile.getName());
            }else{
                return;
            }
        }

        ObjectOutputStream os;

        try {
            os = new ObjectOutputStream(new FileOutputStream(projectFile));
            os.writeObject(project);
            System.out.println(project);
            project.setProjectFile(projectFile);
            project.setChanged(false);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }

    }
}