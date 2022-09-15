package rudok.gui.view.state;

import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;
import rudok.gui.view.painters.SlotPainter;
import rudok.repository.slot.Slot;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class LassoState extends State {

    public LassoState(ProjectView p) {
        super(p);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        PageView pV = (PageView) e.getSource();

        pV.setSelectionRec(e.getX(), e.getY());

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        PageView pV = (PageView) e.getSource();

        super.getProjectView().startSelectState();

        for(Slot slot : pV.getPage().getPageSlots()) {
            Rectangle2D r = slot.getShape().getBounds2D();
            // Nekad se javlja NullPointerException koji ne remeti selekciju
            if (pV.getSelectionRectangle().intersects(r)) {

                for(SlotPainter slotPainter : pV.getSlotPainters()){
                    if(slot.equals(slotPainter.getSlot())){
                        slotPainter.setPaint(Color.YELLOW);
                        pV.getPage().getPageSelectionModel().addToSelectionList(slot);
                    }
                }
            }
        }
        pV.setSelectionRectangle(null);
        pV.repaint();
    }
}
