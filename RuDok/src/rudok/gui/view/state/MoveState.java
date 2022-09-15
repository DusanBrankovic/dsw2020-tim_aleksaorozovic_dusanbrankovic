package rudok.gui.view.state;

import rudok.AppCore;
import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;
import rudok.gui.view.painters.SlotPainter;
import rudok.repository.slot.Slot;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MoveState extends State {

    private Slot selectedSlot = null;

    public MoveState(ProjectView p) { super(p); }

    @Override
    public void mouseDragged(MouseEvent e) {

        PageView pV = (PageView) e.getSource();
        ArrayList<Slot> selectedSlots = pV.getPage().getPageSelectionModel().getSelectedSlots();
        int dX = 0;
        int dY = 0;

        for (Slot slot : selectedSlots) {
            for(SlotPainter slotPainter : pV.getSlotPainters()){
                if(slot.equals(slotPainter.getSlot())){
                    if(slotPainter.elementAt(slot, e.getPoint())){
                        selectedSlot = slot;

                        dX = e.getX() - slot.getPosX();
                        dY = e.getY() - slot.getPosY();
                    }
                }
            }
        }

        for(Slot slot : selectedSlots){
            AppCore.getInstance().getRepository().getSlotHandler().move(slot, dX, dY);
            pV.repaint();
        }

    }

}
