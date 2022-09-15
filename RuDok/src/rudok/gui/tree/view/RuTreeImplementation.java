package rudok.gui.tree.view;

import lombok.Getter;
import lombok.Setter;
import rudok.gui.tree.RuTree;
import rudok.gui.tree.model.RuTreeItem;
import rudok.repository.*;
import rudok.repository.node.RuNode;

import javax.swing.*;
import javax.swing.text.Position;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

@Getter
@Setter
public class RuTreeImplementation implements RuTree {

    private RuTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public JTree generateTree(Workspace workspace) {
        RuTreeItem root = new RuTreeItem(workspace);
        treeModel = new DefaultTreeModel(root);
        treeView = new RuTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addProject(Workspace workspace, Project project) {
        if (treeView.getLastSelectedPathComponent() != null) {
            ((RuTreeItem) treeView.getLastSelectedPathComponent()).add(new RuTreeItem(project));

        } else{
            ((RuTreeItem)treeView.getModel().getRoot()).add(new RuTreeItem(project));
        }
        workspace.addChild(project);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void addDocument(Project project, Document document) {

        TreePath path = treeView.getNextMatch(project.getName(), 0, Position.Bias.Forward);
        RuTreeItem documentItem = new RuTreeItem(document);

        ((RuTreeItem) path.getLastPathComponent()).add(documentItem);

        project.addChild(document);
        treeView.expandPath(path);
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void addPage(Document document, Page page) {

        TreePath path = treeView.getNextMatch(document.getName(), 0, Position.Bias.Forward);
        RuTreeItem newPage = new RuTreeItem(page);

        ((RuTreeItem) path.getLastPathComponent()).add(newPage);

        document.addChild(page);
        treeView.expandPath(path);
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public RuNode getSelectedNode(){
        RuTreeItem item = (RuTreeItem) treeView.getLastSelectedPathComponent();
        if (item != null){
        return item.getNodeModel();
        }
        return ((RuTreeItem)treeModel.getRoot()).getNodeModel();
    }
    @Override
    public void deleteProject(Project project){
        if(project == null){
            return;
        }
        Workspace workspace = (Workspace)project.getParent();
        workspace.removeChild(project);

        SwingUtilities.updateComponentTreeUI(treeView);
    }

    public void deleteDocument(Document document){
        if(document == null){
            return;
        }
        Project project = (Project)document.getParent();
        project.removeChild(document);
        SwingUtilities.updateComponentTreeUI(treeView);
    }
    public void deletePage(Document document, Page page){
        if(page == null) {
            return;
        }

        document.removeChild(page);
        SwingUtilities.updateComponentTreeUI(treeView);
    }

}
