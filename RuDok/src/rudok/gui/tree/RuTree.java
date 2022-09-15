package rudok.gui.tree;

import rudok.repository.*;
import rudok.repository.node.RuNode;

import javax.swing.*;

public interface RuTree {
    JTree generateTree(Workspace workspace);

    void addProject(Workspace workspace, Project project);
    void addDocument(Project project, Document document);
    void addPage(Document document, Page page);

    void deleteProject(Project project);
    void deleteDocument(Document document);
    void deletePage(Document document, Page page);

    RuNode getSelectedNode();
}
