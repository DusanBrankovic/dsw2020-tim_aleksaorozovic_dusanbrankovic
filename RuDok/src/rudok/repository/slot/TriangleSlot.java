package rudok.repository.slot;

import rudok.repository.node.RuNode;

public class TriangleSlot extends Slot {

    public TriangleSlot(String name, RuNode parent) {
        super(name, parent);
        setWidth(75);
        setHeight(75);
        setAngle(0);
    }

}
