package rudok.gui.view;

import lombok.Getter;
import lombok.Setter;

import rudok.observer.ISubscriber;
import rudok.observer.ObserverEnum;
import rudok.repository.Workspace;

import javax.swing.*;

@Setter
@Getter
public class RightSideView extends JPanel implements ISubscriber {

    private Workspace workspace;
    private ProjectView focus;

    public RightSideView(Workspace workspace){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.workspace = workspace;

        workspace.addSubscriber(this);
    }

    @Override
    public void update(Object notification, ObserverEnum observerEnum) {
        if(observerEnum == ObserverEnum.DELETE){
            MainFrame.getInstance().getRightSide().removeAll();
            focus = null;
            updateUI();
        }else if(observerEnum == ObserverEnum.FOCUS){
            focus = (ProjectView) notification;
        }
    }

    public ProjectView getFocus() {
        return focus;
    }
}
