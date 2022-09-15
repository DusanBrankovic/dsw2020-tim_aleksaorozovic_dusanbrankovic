package rudok.gui.view.state;

import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;
import rudok.gui.view.painters.SlotPainter;
import rudok.repository.slot.Slot;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectState extends State {

    private int slotInMotion = -1;

    public SelectState(ProjectView p) { super(p); }

    @Override
    public void mousePressed(MouseEvent e) {

        PageView pageView = (PageView) e.getSource();

        if(!e.isControlDown()){
            for(SlotPainter slotPainter : pageView.getSlotPainters()){
                slotPainter.resetColor();
            }
            pageView.getPage().getPageSelectionModel().removeAllFromSelectedList();
        }

        if(e.getButton() == MouseEvent.BUTTON1){

            slotInMotion = pageView.getSlotAtPosition(e.getPoint());

            if(slotInMotion != -1){

                Slot slot = pageView.getPage().getPageSlots().get(slotInMotion);
                System.out.println(slot);
                if(pageView.getPage().getPageSelectionModel().isSlotSelected(slot)){
                    for(SlotPainter slotPainter : pageView.getSlotPainters()){
                        if(slot.equals(slotPainter.getSlot())) {
                            slotPainter.resetColor();
                            pageView.getPage().getPageSelectionModel().removeFromSelectedList(slot);
                        }
                    }
                }else{
                    for(SlotPainter slotPainter : pageView.getSlotPainters()){
                        if(slot.equals(slotPainter.getSlot())) {
                            slotPainter.setPaint(Color.YELLOW);
                            pageView.getPage().getPageSelectionModel().addToSelectionList(slot);
                            System.out.println(pageView.getPage().getPageSelectionModel().getSelectedSlots());
                            System.out.println(pageView.getSlotPainters());
                            System.out.println("-------");
                        }
                    }
                }
            }

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!e.isControlDown())
            super.getProjectView().startLassoState();
    }
}
