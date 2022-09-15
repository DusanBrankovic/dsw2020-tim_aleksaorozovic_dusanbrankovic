package rudok.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public abstract class ApplicationFramework {

    protected Gui gui;
    protected Repository repository;
    protected ErrorHandler errorHandler;

    public abstract void run();

    public void initialise(Gui gui, Repository repository, ErrorHandler errorHandler){
        this.gui = gui;
        this.repository = repository;
        this.errorHandler = errorHandler;
        this.errorHandler.addSubscriber(gui);
    }
}
