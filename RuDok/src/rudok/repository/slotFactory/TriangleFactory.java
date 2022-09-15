package rudok.repository.slotFactory;

import rudok.repository.node.RuNode;
import rudok.repository.slot.Slot;
import rudok.repository.slot.TriangleSlot;

public class TriangleFactory extends SlotFactory {

    @Override
    public Slot create(String name, RuNode parent) {

        return new TriangleSlot(name, parent);
    }
}
