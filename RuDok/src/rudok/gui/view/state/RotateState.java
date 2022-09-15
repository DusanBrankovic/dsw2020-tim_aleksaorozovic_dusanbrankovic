package rudok.gui.view.state;

import rudok.AppCore;
import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;
import rudok.gui.view.painters.SlotPainter;
import rudok.repository.slot.Slot;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RotateState extends State {

    private Slot selectedSlot = null;

    public RotateState(ProjectView p) { super(p); }

    @Override
    public void mouseDragged(MouseEvent e) {

        PageView pV = (PageView) e.getSource();
        ArrayList<Slot> selectedSlots = pV.getPage().getPageSelectionModel().getSelectedSlots();

        for (Slot slot : selectedSlots) {
            for(SlotPainter slotPainter : pV.getSlotPainters()){
                if(slot.equals(slotPainter.getSlot())){
                    if(slotPainter.elementAt(slot, e.getPoint())){
                        selectedSlot = slot;
                    }
                }
            }
        }

        for(Slot slot : selectedSlots){
            if(selectedSlot != null) {
                AppCore.getInstance().getRepository().getSlotHandler().rotate(slot, selectedSlot, e.getX(), e.getY());
                pV.repaint();
            }
        }

    }
}
