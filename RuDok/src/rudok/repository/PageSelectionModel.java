package rudok.repository;

import lombok.Getter;
import lombok.Setter;
import rudok.observer.IPublisher;
import rudok.observer.ISubscriber;
import rudok.observer.ObserverEnum;
import rudok.repository.slot.Slot;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageSelectionModel extends DefaultSingleSelectionModel implements IPublisher {

    private ArrayList<Slot> selectedSlots = new ArrayList<>();

    private List<ISubscriber> subscribers;

    public void addToSelectionList(Slot slot){
        selectedSlots.add(slot);
        notifySubscribers(this, ObserverEnum.PAINT);
    }

    public void addToSelectionList(ArrayList<Slot> slots){
        selectedSlots.addAll(slots);
        notifySubscribers(this, ObserverEnum.PAINT);
    }

    public int getSelectedListSize(){ return selectedSlots.size(); }

    public void removeFromSelectedList(Slot slot){
        selectedSlots.remove(slot);
        notifySubscribers(this, ObserverEnum.PAINT);
    }

    public void removeAllFromSelectedList(){
        selectedSlots.clear();
        notifySubscribers(this, ObserverEnum.PAINT);
    }

    public boolean isSlotSelected(Slot slot){
        return selectedSlots.contains(slot);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification, ObserverEnum obsEnum) {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(ISubscriber listener : subscribers) {
            listener.update(notification, obsEnum);

        }
    }
}
