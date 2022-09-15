package rudok.repository.slotFactory;

import rudok.repository.node.RuNode;
import rudok.repository.slot.Slot;

public abstract class SlotFactory {

    public Slot makeSlot(String name, RuNode parent, int x, int y){
        Slot slot;
        slot = create(name, parent);

        slot.setPosX(x);
        slot.setPosY(y);

        return slot;
    }

    public abstract Slot create(String name, RuNode parent);
}