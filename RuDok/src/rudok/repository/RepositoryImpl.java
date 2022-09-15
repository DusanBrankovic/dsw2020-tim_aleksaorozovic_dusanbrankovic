package rudok.repository;

import lombok.Getter;
import lombok.Setter;
import rudok.core.Repository;
import rudok.repository.slot.SlotHandler;
import rudok.repository.slot.SlotHandlerImpl;

@Getter
@Setter
public class RepositoryImpl implements Repository {

    private Workspace root;
    private SlotHandler slotHandler;

    public RepositoryImpl(){

        root = new Workspace("Workspace");
        slotHandler = new SlotHandlerImpl();
    }

    @Override
    public Workspace getWorkspace() {
        return root;
    }

    @Override
    public SlotHandler getSlotHandler(){ return slotHandler; }

}
