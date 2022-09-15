package rudok.gui.view;

import lombok.Getter;
import lombok.Setter;
import rudok.core.Repository;
import rudok.gui.controller.ActionManager;
import rudok.gui.error.MyError;
import rudok.gui.tree.RuTree;
import rudok.gui.tree.view.RuTreeImplementation;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame {

    private static MainFrame instance;
    private Repository documentRepository;
    private RuTree tree;
    private JTree workspaceTree;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private RightSideView rightSide;
    private Palette palette;

    private MainFrame(){
    }

    public void initialise(){
        actionManager = new ActionManager();
    }

    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialise();
        }

        return instance;
    }

    public void initialiseWorkspaceTree(){
        tree = new RuTreeImplementation();
        workspaceTree = tree.generateTree(documentRepository.getWorkspace());
        initialiseGUI();
    }

    private void initialiseGUI(){

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(2*screenWidth/3,2*screenHeight/3);
        setTitle("RuDok");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/RD.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        MyMenuBar menu = new MyMenuBar();
        this.setJMenuBar(menu);


        MyToolbar toolbar = new MyToolbar();
        add(toolbar, BorderLayout.NORTH);

        palette = new Palette();
        add(palette, BorderLayout.EAST);

        rightSide = new RightSideView(documentRepository.getWorkspace());

        JScrollPane scroll = new JScrollPane(workspaceTree);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,rightSide);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
    }

    public void showError(MyError e){
        JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitle(), JOptionPane.ERROR_MESSAGE);
    }

}
