package rudok.repository.slot;

public interface SlotHandler {

    void move(Slot slot, int dX, int dY);
    void resize(Slot slot, Slot selectedSlot, int x, int y);
    void rotate(Slot slot, Slot selectedSlot, int x, int y);
}