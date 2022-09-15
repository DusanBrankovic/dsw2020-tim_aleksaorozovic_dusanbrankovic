package rudok.gui.view.state;

import lombok.Getter;
import lombok.Setter;
import rudok.gui.view.ProjectView;

import java.awt.event.MouseEvent;

@Getter
@Setter
public class State {

    private ProjectView projectView;

    public State(ProjectView projectView) {
        this.projectView = projectView;
    }

    public void mousePressed(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}
