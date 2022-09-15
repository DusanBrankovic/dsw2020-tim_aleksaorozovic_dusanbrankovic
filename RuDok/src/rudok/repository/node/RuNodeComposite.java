package rudok.repository.node;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public abstract class RuNodeComposite extends RuNode implements Serializable {
    private List<RuNode> children;

    public RuNodeComposite(String name, RuNode parent){
        super(name,parent);
        this.children = new ArrayList<>();
    }

    public abstract void addChild(RuNode child);
    public abstract void removeChild(RuNode child);

    public RuNode getChildByName(String name){
        for(RuNode child : this.getChildren()){
            if(name.equals(child.getName())){
                return child;
            }
        }

        return null;
    }
}
