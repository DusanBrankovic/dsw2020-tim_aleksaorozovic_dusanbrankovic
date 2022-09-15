package rudok.repository;

import lombok.Getter;
import lombok.Setter;
import rudok.observer.ISubscriber;
import rudok.observer.ObserverEnum;
import rudok.repository.node.RuNode;
import rudok.repository.node.RuNodeComposite;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Project extends RuNodeComposite implements Serializable {

    private transient List<ISubscriber> subscribers;
    private boolean changed;
    private File projectFile;

    public Project(String name, RuNode parent){
        super(name,parent);
        this.changed = true;

    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof Document){
            Document document = (Document) child;
            if(!this.getChildren().contains(document)){
                this.getChildren().add(document);
                this.notifySubscribers(document, ObserverEnum.ADD);
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child instanceof Document){
            Document document = (Document) child;
            if(this.getChildren().contains(document)){
                this.notifySubscribers(document, ObserverEnum.DELETE);
                this.getChildren().remove(document);
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
