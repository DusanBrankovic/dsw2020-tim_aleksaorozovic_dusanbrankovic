package rudok.core;

import rudok.gui.error.ErrorType;
import rudok.observer.IPublisher;

public interface ErrorHandler extends IPublisher {
    void generateError(ErrorType errorType);
}
