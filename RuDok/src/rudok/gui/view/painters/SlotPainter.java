package rudok.gui.view.painters;

import lombok.Getter;
import lombok.Setter;
import rudok.repository.slot.Slot;

import java.awt.*;

@Getter
@Setter
public abstract class SlotPainter {

    protected Paint paint;
    protected Slot slot;

    public SlotPainter(Slot slot) {
        this.slot = slot;
    }

    public abstract void paint(Graphics2D g, Slot slot);

    public abstract boolean elementAt(Slot slot, Point position);

    public abstract void resetColor();

    public abstract Shape constructShape(Slot slot);
}
