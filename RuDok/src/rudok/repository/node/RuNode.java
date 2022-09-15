package rudok.repository.node;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rudok.observer.IPublisher;
import rudok.observer.ObserverEnum;

import java.io.Serializable;

@Getter
@Setter
public abstract class RuNode implements IPublisher, Serializable {

    private String name;
    @ToString.Exclude
    private RuNode parent;

    public RuNode(){}
    public RuNode(String name, RuNode parent){
        this.name = name;
        this.parent = parent;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifySubscribers(this, ObserverEnum.RENAME);
    }

    public RuNode getParent() {
        return parent;
    }

    public void setParent(RuNode parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RuNode){
            RuNode otherObj = (RuNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }

    @Override
    public String toString() { return name; }
}
