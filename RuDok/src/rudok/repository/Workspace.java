package rudok.repository;

import rudok.observer.ISubscriber;
import rudok.observer.ObserverEnum;
import rudok.repository.node.RuNode;
import rudok.repository.node.RuNodeComposite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Workspace extends RuNodeComposite implements Serializable {
    private transient List<ISubscriber> subscribers;

    public Workspace(String name){
        super(name,null);
    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof Project){
            Project project = (Project) child;
            if(!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child instanceof Project){
            Project project = (Project) child;
            if(this.getChildren().contains(project)){
                this.notifySubscribers(project, ObserverEnum.DELETE);
                this.getChildren().remove(project);
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
