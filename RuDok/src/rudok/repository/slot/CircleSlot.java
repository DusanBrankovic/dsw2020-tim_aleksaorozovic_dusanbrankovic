package rudok.repository.slot;

import rudok.repository.node.RuNode;

public class CircleSlot extends Slot {

    public CircleSlot(String name, RuNode parent) {
        super(name, parent);
        setHeight(75);
        setWidth(75);
        setAngle(0);
    }
}
