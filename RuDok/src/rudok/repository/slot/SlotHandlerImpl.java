package rudok.repository.slot;

public class SlotHandlerImpl implements SlotHandler {

    @Override
    public void move(Slot slot, int dX, int dY) {

        slot.setPosX(slot.getPosX() + dX);
        slot.setPosY(slot.getPosY() + dY);

    }

    @Override
    public void resize(Slot slot, Slot selectedSlot, int x, int y) {
        int dW;

        if ((x > selectedSlot.getPosX() && y > selectedSlot.getPosY()) || (x > selectedSlot.getPosX() && y < selectedSlot.getPosY())) {
            dW = x - (selectedSlot.getPosX() + selectedSlot.getWidth() / 2);

            slot.setWidth(selectedSlot.getWidth() + dW);
            if(slot instanceof RectangleSlot) slot.setHeight((slot.getWidth() + dW) / 2);
            else slot.setHeight(slot.getWidth());

        } else {
            dW = selectedSlot.getPosX() - selectedSlot.getWidth() / 2 - x;

            slot.setWidth(selectedSlot.getWidth() + dW);
            if(slot instanceof RectangleSlot) slot.setHeight((slot.getWidth() + dW) / 2);
            else slot.setHeight(slot.getWidth());

        }
    }

    @Override
    public void rotate(Slot slot, Slot selectedSlot, int x, int y) {

        double angle1 = Math.atan2(selectedSlot.getPosY() - selectedSlot.getHeight(), selectedSlot.getPosX() - selectedSlot.getWidth());
        double angle2 = Math.atan2(selectedSlot.getPosY() - y, selectedSlot.getPosX() - x);

        double angle = angle1 - angle2;

        slot.setAngle(angle);
    }

}

