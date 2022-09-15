package rudok.gui.controller;

import rudok.AppCore;
import rudok.gui.view.DocumentView;
import rudok.gui.view.MainFrame;
import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;
import rudok.repository.Document;
import rudok.repository.Page;
import rudok.repository.Project;
import rudok.repository.node.RuNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class OpenProjectAction extends AbstractRudokAction{

    public OpenProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/Open.png"));
        putValue(NAME, "Open Project");
        putValue(SHORT_DESCRIPTION, "Open project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new ProjectFileFilter());

        if(jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            try {
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
                Project project = null;
                ProjectView projectView = null;

                try {
                    project = (Project) is.readObject();
                    projectView = new ProjectView(project);
                } catch (ClassNotFoundException e1){
                    e1.printStackTrace();
                }

                MainFrame.getInstance().getTree().addProject(MainFrame.getInstance().getDocumentRepository().getWorkspace(), project);

                for(RuNode nodeDocument : project.getChildren()){
                    Document document = (Document) nodeDocument;
                    DocumentView documentView = new DocumentView(document, projectView);

                    MainFrame.getInstance().getTree().addDocument(project, document);

                    for(RuNode nodePage : document.getChildren()){
                        Page page = (Page) nodePage;
                        PageView pageView = new PageView(page);

                        MainFrame.getInstance().getTree().addPage(document, page);
                    }
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}