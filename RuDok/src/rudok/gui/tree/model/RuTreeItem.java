package rudok.gui.tree.model;

import lombok.Getter;
import rudok.repository.node.RuNode;
import rudok.repository.node.RuNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Iterator;

@Getter
public class RuTreeItem extends DefaultMutableTreeNode {

    private String name;
    private RuNode nodeModel;

    public RuTreeItem(RuNode nodeModel){
        this.nodeModel = nodeModel;
        this.name = nodeModel.getName();
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return findChildByIndex(childIndex);
    }

    @Override
    public int getChildCount() {
        if(nodeModel instanceof RuNodeComposite){
            return ((RuNodeComposite) nodeModel).getChildren().size();
        }
        return 0;
    }

    @Override
    public int getIndex(TreeNode node) {
        return findIndexByChild((RuTreeItem)node);
    }

    @Override
    public boolean getAllowsChildren() {
        return nodeModel instanceof RuNodeComposite;
    }

    @Override
    public boolean isLeaf() {
        return !(nodeModel instanceof RuNodeComposite);
    }

    private TreeNode findChildByIndex(int childIndex){

        if(nodeModel instanceof RuNodeComposite){
            RuTreeItem toLookFor = new RuTreeItem(((RuNodeComposite) nodeModel).getChildren().get(childIndex));

            Iterator<TreeNode> childrenIterator = children.iterator();
            TreeNode current;

            while(childrenIterator.hasNext()){
                current = childrenIterator.next();
                if(current.equals(toLookFor)){
                    return current;
                }
            }
        }

        return null;
    }

    private int findIndexByChild(RuTreeItem node){

        if(this.nodeModel instanceof RuNodeComposite){
            return ((RuNodeComposite) this.nodeModel).getChildren().indexOf(node.getNodeModel());
        }

        return -1;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RuTreeItem){
            RuTreeItem otherObj = (RuTreeItem) obj;
            return this.nodeModel.equals(otherObj.nodeModel);
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public RuNode getNodeModel() {
        return nodeModel;
    }

    public void setName(String name) {
        this.name = name;
        this.nodeModel.setName(name);
    }
}
