package rudok.gui.view.state;

import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;
import rudok.gui.view.painters.RectanglePainter;
import rudok.gui.view.painters.SlotPainter;
import rudok.repository.Page;
import rudok.repository.slot.RectangleSlot;
import rudok.repository.slotFactory.RectangleFactory;
import rudok.repository.slotFactory.SlotFactory;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RectangleState extends State {

    private SlotFactory SF = new RectangleFactory();

    public RectangleState(ProjectView p) { super(p); }

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

            RectangleSlot rectangle = (RectangleSlot) SF.makeSlot("Pavougaonik " + page.getChildren().size(), page, position.x, position.y);
            RectanglePainter rectanglePainter = new RectanglePainter(rectangle);
            pV.getSlotPainters().add(rectanglePainter);

            Polygon rectangleShape = new Polygon();

            rectangleShape.addPoint(rectangle.getPosX() - rectangle.getWidth() / 2, rectangle.getPosY() - rectangle.getHeight() / 2);
            rectangleShape.addPoint(rectangle.getPosX() + rectangle.getWidth() / 2, rectangle.getPosY() - rectangle.getHeight() / 2);
            rectangleShape.addPoint(rectangle.getPosX() + rectangle.getWidth() / 2, rectangle.getPosY() + rectangle.getHeight() / 2);
            rectangleShape.addPoint(rectangle.getPosX() - rectangle.getWidth() / 2, rectangle.getPosY() + rectangle.getHeight() / 2);

            rectangle.setShape(rectangleShape);

            for(SlotPainter slotPainter : pV.getSlotPainters()){
                slotPainter.resetColor();
            }
            page.getPageSelectionModel().removeAllFromSelectedList();

            for(SlotPainter slotPainter : pV.getSlotPainters()){
                if(rectangle.equals(slotPainter.getSlot())){
                    slotPainter.setPaint(Color.YELLOW);
                }
            }
            page.getPageSelectionModel().addToSelectionList(rectangle);

            rectangle.addSubscriber(pV);
            page.addChild(rectangle);

        }

    }
}
