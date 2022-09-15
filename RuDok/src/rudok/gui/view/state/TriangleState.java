package rudok.gui.view.state;

import rudok.gui.view.PageView;
import rudok.gui.view.ProjectView;
import rudok.gui.view.painters.SlotPainter;
import rudok.gui.view.painters.TrianglePainter;
import rudok.repository.Page;
import rudok.repository.slot.TriangleSlot;
import rudok.repository.slotFactory.SlotFactory;
import rudok.repository.slotFactory.TriangleFactory;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TriangleState extends State {

    private SlotFactory SF = new TriangleFactory();

    public TriangleState(ProjectView p) { super(p); }

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

            TriangleSlot triangle = (TriangleSlot) SF.makeSlot("Trougao " + page.getChildren().size(), page, position.x, position.y);
            TrianglePainter trianglePainter = new TrianglePainter(triangle);
            pV.getSlotPainters().add(trianglePainter);

            Polygon triangleShape = new Polygon();

            triangleShape.addPoint(triangle.getPosX(), triangle.getPosY() - triangle.getHeight() / 2);
            triangleShape.addPoint(triangle.getPosX() - triangle.getWidth() / 2, triangle.getPosY() + triangle.getHeight() / 2);
            triangleShape.addPoint(triangle.getPosX() + triangle.getWidth() / 2, triangle.getPosY() + triangle.getHeight() / 2);

            triangle.setShape(triangleShape);

            for(SlotPainter slotPainter : pV.getSlotPainters()){
                slotPainter.resetColor();
            }
            page.getPageSelectionModel().removeAllFromSelectedList();

            for(SlotPainter slotPainter : pV.getSlotPainters()){
                if(triangle.equals(slotPainter.getSlot())){
                    slotPainter.setPaint(Color.YELLOW);
                }
            }
            page.getPageSelectionModel().addToSelectionList(triangle);

            triangle.addSubscriber(pV);
            page.addChild(triangle);

        }
    }
}
