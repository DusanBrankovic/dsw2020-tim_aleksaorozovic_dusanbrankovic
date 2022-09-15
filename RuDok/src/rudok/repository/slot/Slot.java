package rudok.repository.slot;

import lombok.Getter;
import lombok.Setter;
import rudok.observer.ISubscriber;
import rudok.observer.ObserverEnum;
import rudok.repository.node.RuNode;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Slot extends RuNode implements Serializable {
    private transient List<ISubscriber> subscribers;

    private Shape shape;

    private int width;
    private int height;

    private int posX;
    private int posY;

    private double angle;

    public Slot(String name, RuNode parent) {
        super(name, parent);
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

    public void setWidth(int width) {
        this.width = width;
        notifySubscribers(this, ObserverEnum.PAINT);
    }

    public void setHeight(int height) {
        this.height = height;
        notifySubscribers(this, ObserverEnum.PAINT);
    }

    public void setPosX(int posX) {
        this.posX = posX;
        notifySubscribers(this, ObserverEnum.PAINT);
    }

    public void setPosY(int posY) {
        this.posY = posY;
        notifySubscribers(this, ObserverEnum.PAINT);
    }

}
