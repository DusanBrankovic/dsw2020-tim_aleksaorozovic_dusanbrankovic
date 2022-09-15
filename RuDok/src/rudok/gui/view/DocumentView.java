package rudok.gui.view;


import lombok.Getter;
import lombok.Setter;
import rudok.observer.ISubscriber;
import rudok.repository.Document;
import rudok.observer.ObserverEnum;
import rudok.repository.Page;
import rudok.repository.node.RuNode;

import javax.swing.*;
import java.util.ArrayList;

@Getter
@Setter
public class DocumentView extends JPanel implements ISubscriber {

    private String nazivDokumenta;
    private ProjectView projView;
    private Document document;
    private ArrayList<PageView> pageViews;

    public DocumentView(Document document, ProjectView projView){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.document = document;
        this.nazivDokumenta = document.getName();
        this.projView = projView;

        pageViews = new ArrayList<>();
        document.addSubscriber(this);

        loadPages();
    }

    public void loadPages(){
        for(RuNode node : document.getChildren()){
            PageView pageView = new PageView((Page)node);
            pageViews.add(pageView);
            add(pageView);
        }
    }

    @Override
    public void update(Object notification, ObserverEnum obsEnum) {
        if(obsEnum == ObserverEnum.RENAME) {
            for (int i = 0; i < projView.getTabbedPane().getTabCount(); i++) {
                JScrollPane scroll = (JScrollPane) projView.getTabbedPane().getComponentAt(i);
                DocumentView docView = (DocumentView) scroll.getViewport().getView();

                if (docView.getNazivDokumenta().equals(nazivDokumenta))
                    projView.getTabbedPane().setTitleAt(i, ((Document) notification).getName());

            }
        }else if(obsEnum == ObserverEnum.ADD){
            PageView pv = new PageView((Page)notification);
            pageViews.add(pv);
            add(pv);
            updateUI();
        }else if(obsEnum == ObserverEnum.DELETE){

            for (PageView pageView : pageViews) {
                if (pageView.getPage().equals(notification)) {
                    pageViews.remove(pageView);
                    remove(pageView);
                    updateUI();
                    return;
                }
            }
        }
    }
}
