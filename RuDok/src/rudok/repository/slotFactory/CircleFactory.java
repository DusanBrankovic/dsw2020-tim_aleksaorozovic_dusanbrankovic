package rudok.repository.slotFactory;

import rudok.repository.node.RuNode;
import rudok.repository.slot.CircleSlot;
import rudok.repository.slot.Slot;

public class CircleFactory extends SlotFactory {

    @Override
    public Slot create(String name, RuNode parent) {

        return new CircleSlot(name, parent);
    }
}
