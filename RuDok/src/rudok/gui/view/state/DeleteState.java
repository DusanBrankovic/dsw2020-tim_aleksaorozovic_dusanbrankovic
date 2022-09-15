package rudok.gui.view.state;

import rudok.AppCore;
import rudok.gui.error.ErrorType;
import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;
import rudok.gui.view.painters.SlotPainter;
import rudok.repository.slot.Slot;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DeleteState extends State {

    public DeleteState(ProjectView p) { super(p); }

    @Override
    public void mousePressed(MouseEvent e) {

        PageView pV = (PageView) e.getSource();
        ArrayList<Slot> selectedSlots = pV.getPage().getPageSelectionModel().getSelectedSlots();

        if(!selectedSlots.isEmpty()) {

            for(Slot slot : selectedSlots){
                pV.getPage().removeChild(slot);
                pV.getPage().getPageSlots().remove(slot);

                SlotPainter slotPainter1 = null;
                for(SlotPainter slotPainter : pV.getSlotPainters()){
                    if(slot.equals(slotPainter.getSlot())){
                        slotPainter1 = slotPainter;
                    }
                }

                pV.getSlotPainters().remove(slotPainter1);
            }
            pV.getPage().getPageSelectionModel().removeAllFromSelectedList();

        }else {
            AppCore.getInstance().getErrorHandler().generateError(ErrorType.SLOT_NOT_SELECTED);
        }
    }
}
