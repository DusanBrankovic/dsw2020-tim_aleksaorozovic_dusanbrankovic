package rudok;

import rudok.core.ApplicationFramework;
import rudok.core.ErrorHandler;
import rudok.core.Gui;
import rudok.core.Repository;
import rudok.gui.SwingGui;
import rudok.gui.error.ErrorHandlerImpl;
import rudok.repository.RepositoryImpl;

public class AppCore extends ApplicationFramework {

    private static AppCore instance;

    private AppCore(){

    }

    public static AppCore getInstance(){
        if(instance == null){
            instance = new AppCore();
        }
        return instance;
    }

    @Override
    public void run() {
        this.gui.start();
    }

    public static void main(String[] args){
        Repository repository = new RepositoryImpl();
        ErrorHandler errorHandler = new ErrorHandlerImpl();
        Gui gui = new SwingGui(repository);
        ApplicationFramework appCore = AppCore.getInstance();
        appCore.initialise(gui,repository, errorHandler);
        appCore.run();
    }
}
