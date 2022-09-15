package rudok.gui.view;

import lombok.Getter;
import lombok.Setter;
import rudok.gui.view.state.StateManager;
import rudok.observer.ISubscriber;
import rudok.repository.Document;
import rudok.observer.ObserverEnum;
import rudok.repository.Project;
import rudok.repository.node.RuNode;

import javax.swing.*;

@Getter
@Setter
public class ProjectView extends JPanel implements ISubscriber {

    private RuTabbedPane tabbedPane;
    private JLabel nazivProjekta;
    private Project project;

    private StateManager stateManager = new StateManager(this);

    public ProjectView(Project project){
        this.project = project;
        project.addSubscriber(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        tabbedPane = new RuTabbedPane();
        nazivProjekta = new JLabel(project.getName());

        add(nazivProjekta);
        loadTabs();
    }

    public void loadTabs(){
        for(RuNode node : project.getChildren()){
            DocumentView dV = new DocumentView((Document)node, this);

            JScrollPane scroll = new JScrollPane(dV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            tabbedPane.addTab(dV.getDocument().getName(), scroll);
        }

        add(tabbedPane);
    }

    @Override
    public void update(Object notification, ObserverEnum obsEnum) {
        if(obsEnum == ObserverEnum.RENAME)
            nazivProjekta.setText(((Project)notification).getName());
        else if(obsEnum == ObserverEnum.ADD){
            DocumentView dv = new DocumentView((Document)notification, this);

            JScrollPane scroll = new JScrollPane(dv, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            tabbedPane.addTab(dv.getDocument().getName(), scroll);
        }else if(obsEnum == ObserverEnum.DELETE){

            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                String tabTitle = tabbedPane.getTitleAt(i);
                if (tabTitle.equals(((Document)notification).getName())) {
                    tabbedPane.remove(i);
                    updateUI();
                    return;
                }
            }
        }

    }

    public void startRectangleState() { stateManager.setRectangleState();}
    public void startCircleState() { stateManager.setCircleState();}
    public void startTriangleState() { stateManager.setTriangleState();}
    public void startSelectState() { stateManager.setSelectState();}
    public void startResizeState() { stateManager.setResizeState();}
    public void startRotateState() { stateManager.setRotateState();}
    public void startMoveState() { stateManager.setMoveState();}
    public void startDeleteState() {stateManager.setDeleteState();}
    public void startLassoState() {stateManager.setLassoState();}

}
