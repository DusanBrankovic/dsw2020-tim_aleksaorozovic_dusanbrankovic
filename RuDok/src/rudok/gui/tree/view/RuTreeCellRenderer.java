package rudok.gui.tree.view;

import rudok.gui.tree.model.RuTreeItem;
import rudok.repository.*;
import rudok.repository.slot.Slot;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class RuTreeCellRenderer extends DefaultTreeCellRenderer {

    public RuTreeCellRenderer(){

    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if(((RuTreeItem)value).getNodeModel() instanceof Workspace){
            URL imageURL = getClass().getResource("images/workspace.png");
            Icon icon = null;
            if(imageURL != null){
                icon = new ImageIcon(imageURL);
            }
            setIcon(icon);
        }else if(((RuTreeItem)value).getNodeModel() instanceof Project){
            URL imageURL = getClass().getResource("images/projekat.png");
            Icon icon = null;
            if(imageURL != null){
                icon = new ImageIcon(imageURL);
            }
            setIcon(icon);
        }else if(((RuTreeItem)value).getNodeModel() instanceof Document){
            URL imageURL = getClass().getResource("images/dokument.png");
            Icon icon = null;
            if(imageURL != null){
                icon = new ImageIcon(imageURL);
            }
            setIcon(icon);
        }else if(((RuTreeItem)value).getNodeModel() instanceof Page){
            URL imageURL = getClass().getResource("images/strana.png");
            Icon icon = null;
            if(imageURL != null){
                icon = new ImageIcon(imageURL);
            }
            setIcon(icon);
        }else if(((RuTreeItem)value).getNodeModel() instanceof Slot){
            URL imageURL = getClass().getResource("images/slot.png");
            Icon icon = null;
            if(imageURL != null){
                icon = new ImageIcon(imageURL);
            }
            setIcon(icon);
        }
        return this;
    }
}
