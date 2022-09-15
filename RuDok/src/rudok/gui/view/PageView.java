package rudok.gui.view;

import lombok.Getter;
import lombok.Setter;
import rudok.gui.view.painters.SlotPainter;
import rudok.observer.ISubscriber;
import rudok.observer.ObserverEnum;
import rudok.repository.Page;
import rudok.repository.slot.Slot;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

@Getter
@Setter
public class PageView extends JPanel implements ISubscriber {

    private JLabel imeStrane;
    private Page page;

    private ArrayList<SlotPainter> slotPainters;

    private Point2D lastPosition = null;
    private Polygon selectionRectangle = null;

    private MouseController mouseController;

    public PageView(Page page){
        setBackground(Color.LIGHT_GRAY);
        setBorder(new LineBorder(Color.BLACK));
        setMaximumSize(new Dimension(800, 300));
        setPreferredSize(new Dimension(800,300));
        page.addSubscriber(this);

        page.getPageSelectionModel().addSubscriber(this);

        this.page = page;
        slotPainters = new ArrayList<>();

        imeStrane = new JLabel(page.getName());
        add(imeStrane, BorderLayout.CENTER);

        this.addMouseListener(new MouseController());
        this.addMouseMotionListener(new MouseController());
    }

    @Override
    public void update(Object notification, ObserverEnum obsEnum) {

        if(notification instanceof Page && obsEnum == ObserverEnum.RENAME) {
            imeStrane.setText(((Page) notification).getName());
        } else if(obsEnum == ObserverEnum.PAINT){
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

        for(SlotPainter slotPainter : slotPainters){
            slotPainter.paint(g2D, slotPainter.getSlot());
        }

        if(selectionRectangle != null) {
            float[] dash1 = {10.0f};
            BasicStroke dashed =
                    new BasicStroke(1.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 2.0f);
            g2D.setColor(Color.BLACK);
            g2D.setStroke(dashed);
            g2D.draw(selectionRectangle);
        }

    }

    public int getSlotAtPosition(Point point) {
        for(int i = page.getPageSlots().size() - 1 ; i >= 0 ; i--){
            Slot slot = page.getPageSlots().get(i);

            for(SlotPainter slotPainter : slotPainters){
                if(slot.equals(slotPainter.getSlot())){
                    if(slotPainter.elementAt(slot, point))
                        return i;
                }
            }
        }
        return -1;
    }

    private class MouseController extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            lastPosition = e.getPoint();
            MainFrame.getInstance().getRightSide().getFocus().getStateManager().getCurrentState().mousePressed(e);
        }

        public void mouseReleased(MouseEvent e) { MainFrame.getInstance().getRightSide().getFocus().getStateManager().getCurrentState().mouseReleased(e); }

        public void mouseDragged(MouseEvent e ) { MainFrame.getInstance().getRightSide().getFocus().getStateManager().getCurrentState().mouseDragged(e); }
    }

    public void setSelectionRec(int x, int y){
        Polygon rectangle = new Polygon();

        rectangle.addPoint(((int)lastPosition.getX()), (int) lastPosition.getY());
        rectangle.addPoint(x, (int) lastPosition.getY());
        rectangle.addPoint(x, y);
        rectangle.addPoint((int) lastPosition.getX(), y);

        selectionRectangle = rectangle;

        repaint();
    }

}
