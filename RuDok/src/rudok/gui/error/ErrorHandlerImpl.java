package rudok.gui.error;

import rudok.core.ErrorHandler;
import rudok.observer.ISubscriber;
import rudok.observer.ObserverEnum;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandlerImpl implements ErrorHandler {

    private List<ISubscriber> subscribers;

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification, ObserverEnum obsEnum) {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(ISubscriber listener : subscribers) {
            listener.update(notification, obsEnum);
        }
    }

    @Override
    public void generateError(ErrorType errorType) {
        if(errorType == ErrorType.WS_CANNOT_BE_DELETED){
            notifySubscribers(new MyError("Ne mozete obrisati workspace", "Greska prilikom brisanja"), ObserverEnum.ERROR);
        }else if(errorType == ErrorType.NAME_CANNOT_BE_EMPTY){
            notifySubscribers(new MyError("Naziv ne moze biti prazan string", "Greska prilikom preimenovanja"), ObserverEnum.ERROR);
        }else if(errorType == ErrorType.SAME_NAME){
            notifySubscribers(new MyError("Naziv ne moze biti isti kao drugi naziv", "Greska prilikom preimenovanja"), ObserverEnum.ERROR);
        }else if(errorType == ErrorType.NOT_PROJECT){
            notifySubscribers(new MyError("Mozete otvoriti samo projekat", "Greska pri otvaranju projekta"), ObserverEnum.ERROR);
        }else if(errorType == ErrorType.PROJECT_NOT_OPENED){
            notifySubscribers(new MyError("Mozete menjati stanje samo ako je projekat otvoren", "Greska pri menjanju stanja"), ObserverEnum.ERROR);
        }else if(errorType == ErrorType.SLOT_NOT_SELECTED){
            notifySubscribers(new MyError("Morate selektovati slot koji zelite da obrisete", "Greska pri brisanju slota"), ObserverEnum.ERROR);
        }
    }
}
