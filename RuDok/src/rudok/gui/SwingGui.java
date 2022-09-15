package rudok.gui;

import rudok.core.Gui;
import rudok.core.Repository;
import rudok.gui.error.MyError;
import rudok.gui.view.MainFrame;
import rudok.observer.ObserverEnum;

public class SwingGui implements Gui {
    private MainFrame instance;
    private Repository documentRepository;

    public SwingGui(Repository documentRepository){
        this.documentRepository = documentRepository;

    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setDocumentRepository(documentRepository);
        instance.initialiseWorkspaceTree();
        instance.setVisible(true);
    }

    @Override
    public void update(Object notification, ObserverEnum observerEnum) {
        if(observerEnum == ObserverEnum.ERROR){
            MainFrame.getInstance().showError((MyError)notification);
        }
    }
}
