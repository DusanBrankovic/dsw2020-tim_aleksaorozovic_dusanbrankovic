package rudok.gui.view.painters;

import rudok.repository.slot.Slot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class CirclePainter extends SlotPainter {

    public CirclePainter(Slot slot) {
        super(slot);
        paint = new Color(216, 71, 39);
    }

    @Override
    public void paint(Graphics2D g, Slot slot) {

        AffineTransform old = g.getTransform();
        g.rotate(-slot.getAngle(), slot.getPosX(), slot.getPosY());

        g.setPaint(paint);
        g.setStroke(new BasicStroke(4f));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.draw(constructShape(slot));

        g.setPaint(new Color(239, 246, 238));
        g.fill(constructShape(slot));

        g.setPaint(Color.BLACK);
        g.drawString(slot.getName(), (float) (slot.getPosX() - slot.getWidth() / 8), (float) (slot.getPosY() - slot.getWidth() / 10));

        g.setTransform(old);
    }

    @Override
    public boolean elementAt(Slot slot, Point position) { return slot.getShape().contains(position); }

    @Override
    public void resetColor() {
        setPaint(new Color(216, 71, 39));
    }

    @Override
    public Shape constructShape(Slot slot) {

        Ellipse2D.Double shape = new Ellipse2D.Double();
        shape.width = slot.getWidth();
        shape.height = slot.getHeight();
        shape.x = slot.getPosX() - shape.width / 2;
        shape.y = slot.getPosY() - shape.height / 2;

        slot.setShape(shape);

        return shape;
    }
}
