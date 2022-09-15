package rudok.repository.slot;

import lombok.Getter;
import lombok.Setter;
import rudok.repository.node.RuNode;

@Getter
@Setter
public class RectangleSlot extends Slot {

    public RectangleSlot(String name, RuNode parent) {
        super(name, parent);
        setWidth(100);
        setHeight(50);
        setAngle(0);
    }
}
