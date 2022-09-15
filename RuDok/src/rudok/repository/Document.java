package rudok.repository;

import rudok.observer.ISubscriber;
import rudok.observer.ObserverEnum;
import rudok.repository.node.RuNode;
import rudok.repository.node.RuNodeComposite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Document extends RuNodeComposite implements Serializable {

    private transient List<ISubscriber> subscribers;

    public Document(String name, RuNode parent){
        super(name,parent);
    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof Page){
            Page page = (Page) child;
            if(!this.getChildren().contains(page)){
                this.getChildren().add(page);
                this.notifySubscribers(page, ObserverEnum.ADD);
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child instanceof Page){
            Page page = (Page) child;
            if(this.getChildren().contains(page)){
                this.notifySubscribers(page, ObserverEnum.DELETE);
                this.getChildren().remove(page);
            }
        }
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
