package rudok.gui.view.state;

import rudok.AppCore;
import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;
import rudok.gui.view.painters.SlotPainter;
import rudok.repository.slot.Slot;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ResizeState extends State {

    private Slot selectedSlot = null;
    private boolean flag = false;

    public ResizeState(ProjectView p) { super(p); }

    @Override
    public void mouseDragged(MouseEvent e) {

        PageView pV = (PageView) e.getSource();
        ArrayList<Slot> selectedSlots = pV.getPage().getPageSelectionModel().getSelectedSlots();

        for (Slot slot : selectedSlots) {
            for(SlotPainter slotPainter : pV.getSlotPainters()){
                if(slot.equals(slotPainter.getSlot())){
                    if(slotPainter.elementAt(slot, e.getPoint())){
                        selectedSlot = slot;

                        flag = true;
                    }
                }
            }
        }

        for(Slot slot : selectedSlots){
            if(selectedSlot != null) {
                AppCore.getInstance().getRepository().getSlotHandler().resize(slot, selectedSlot, e.getX(), e.getY());
                pV.repaint();
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        flag = false;
    }
}
