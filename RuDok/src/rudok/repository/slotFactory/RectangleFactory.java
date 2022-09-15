package rudok.repository.slotFactory;

import rudok.repository.node.RuNode;
import rudok.repository.slot.RectangleSlot;
import rudok.repository.slot.Slot;

public class RectangleFactory extends SlotFactory{

    @Override
    public Slot create(String name, RuNode parent) {

        return new RectangleSlot(name, parent);
    }
}
