package rudok.gui.view.painters;

import com.sun.nio.sctp.PeerAddressChangeNotification;
import lombok.Getter;
import lombok.Setter;
import rudok.repository.slot.Slot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

@Getter
@Setter
public class RectanglePainter extends SlotPainter {

    public RectanglePainter(Slot slot) {
        super(slot);
        paint = new Color(0,139,139);
    }

    @Override
    public void paint(Graphics2D g, Slot slot) {

        AffineTransform old = g.getTransform();
        g.rotate(-slot.getAngle(), slot.getPosX(), slot.getPosY());

        g.setPaint(paint);
        g.setStroke(new BasicStroke(4f));

        g.draw(constructShape(slot));

        g.setPaint(new Color(239, 246, 238));
        g.fill(constructShape(slot));

        g.setPaint(Color.BLACK);
        g.drawString(slot.getName(), slot.getPosX() - slot.getWidth() / 2 + 10, slot.getPosY() - slot.getHeight() / 2 + 10);

        g.setTransform(old);
    }

    @Override
    public boolean elementAt(Slot slot, Point position) {
        return slot.getShape().contains(position);
    }

    @Override
    public void resetColor() {
        setPaint(new Color(0,139,139));
    }

    public Shape constructShape(Slot slot){

        GeneralPath shape = new GeneralPath();

        shape.moveTo(slot.getPosX() - slot.getWidth() / 2.0, slot.getPosY() - slot.getHeight() / 2.0);
        shape.lineTo(slot.getPosX() + slot.getWidth() / 2.0, slot.getPosY() - slot.getHeight() / 2.0);
        shape.lineTo(slot.getPosX() + slot.getWidth() / 2.0, slot.getPosY() + slot.getHeight() / 2.0);
        shape.lineTo(slot.getPosX() - slot.getWidth() / 2.0, slot.getPosY() + slot.getHeight() / 2.0);
        shape.closePath();

        slot.setShape(shape);

        return shape;
    }
}
