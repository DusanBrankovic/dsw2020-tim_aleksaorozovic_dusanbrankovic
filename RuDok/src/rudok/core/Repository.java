package rudok.core;

import rudok.repository.Workspace;
import rudok.repository.slot.SlotHandler;

public interface Repository {

    Workspace getWorkspace();
    SlotHandler getSlotHandler();

}
