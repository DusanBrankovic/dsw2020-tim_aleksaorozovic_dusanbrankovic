package rudok.repository;

import lombok.Getter;
import lombok.Setter;

import rudok.observer.ISubscriber;
import rudok.observer.ObserverEnum;
import rudok.repository.node.RuNode;
import rudok.repository.node.RuNodeComposite;
import rudok.repository.slot.Slot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Page extends RuNodeComposite implements Serializable {

    private transient List<ISubscriber> subscribers;

    protected transient ArrayList<Slot> pageSlots = new ArrayList<>();
    private transient PageSelectionModel pageSelectionModel;

    public Page(String name, RuNode parent){
        super(name, parent);
    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof Slot){
            Slot slot = (Slot) child;
            if(!this.getChildren().contains(slot)){
                this.getChildren().add(slot);
                pageSlots.add(slot);
                notifySubscribers(slot, ObserverEnum.PAINT);
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child instanceof Slot){
            Slot slot = (Slot) child;

            pageSlots.remove(slot);
            this.getChildren().remove(slot);

            notifySubscribers(slot, ObserverEnum.PAINT);
        }
    }

    public PageSelectionModel getPageSelectionModel() {
        if(pageSelectionModel == null)
            pageSelectionModel = new PageSelectionModel();
        return pageSelectionModel;
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
