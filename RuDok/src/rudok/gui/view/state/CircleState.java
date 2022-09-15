package rudok.gui.view.state;

import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;
import rudok.gui.view.painters.CirclePainter;
import rudok.gui.view.painters.SlotPainter;
import rudok.repository.Page;
import rudok.repository.slot.CircleSlot;
import rudok.repository.slotFactory.CircleFactory;
import rudok.repository.slotFactory.SlotFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class CircleState extends State {

    private SlotFactory SF = new CircleFactory();

    public CircleState(ProjectView p) { super(p); }

    @Override
    public void mousePressed(MouseEvent e) {

        Point position = e.getPoint();
        PageView pV = (PageView) e.getSource();
        Page page = pV.getPage();

        boolean theresASlot = false;

        for(SlotPainter slotPainter : pV.getSlotPainters()){
            if(slotPainter.elementAt(slotPainter.getSlot(), e.getPoint())){
                theresASlot = true;
            }
        }

        if (e.getButton()==MouseEvent.BUTTON1 && !theresASlot){

            CircleSlot circle = (CircleSlot) SF.makeSlot("Krug " + page.getChildren().size(), page, position.x, position.y);
            CirclePainter circlePainter = new CirclePainter(circle);
            pV.getSlotPainters().add(circlePainter);

            circle.setShape(new Ellipse2D.Double(circle.getPosX() - circle.getWidth() / 2.0,
                    circle.getPosY() - circle.getHeight() / 2.0,
                    circle.getWidth(), circle.getHeight()));

            for(SlotPainter slotPainter : pV.getSlotPainters()){
                slotPainter.resetColor();
            }
            page.getPageSelectionModel().removeAllFromSelectedList();

            for(SlotPainter slotPainter : pV.getSlotPainters()){
                if(circle.equals(slotPainter.getSlot())){
                    slotPainter.setPaint(Color.YELLOW);
                }
            }
            page.getPageSelectionModel().addToSelectionList(circle);

            circle.addSubscriber(pV);
            page.addChild(circle);

        }

    }

}
