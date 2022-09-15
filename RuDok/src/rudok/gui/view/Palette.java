package rudok.gui.view;

import javax.swing.*;
import java.awt.*;

public class Palette extends JToolBar {

    public Palette(){
        super(JToolBar.VERTICAL);
        setFloatable(false);
        setBackground(Color.LIGHT_GRAY);

        ButtonGroup group = new ButtonGroup();

        JToggleButton select = new JToggleButton(MainFrame.getInstance().getActionManager().getSelectAction());
        group.add(select);
        add(select);

        JToggleButton rectangle = new JToggleButton(MainFrame.getInstance().getActionManager().getRectangleAction());
        group.add(rectangle);
        add(rectangle);


        JToggleButton circle = new JToggleButton(MainFrame.getInstance().getActionManager().getCircleAction());
        group.add(circle);
        add(circle);

        JToggleButton triangle = new JToggleButton(MainFrame.getInstance().getActionManager().getTriangleAction());
        group.add(triangle);
        add(triangle);

        JToggleButton move = new JToggleButton(MainFrame.getInstance().getActionManager().getMoveAction());
        group.add(move);
        add(move);

        JToggleButton resize = new JToggleButton(MainFrame.getInstance().getActionManager().getResizeAction());
        group.add(resize);
        add(resize);

        JToggleButton rotate = new JToggleButton(MainFrame.getInstance().getActionManager().getRotateAction());
        group.add(rotate);
        add(rotate);

        JToggleButton delete = new JToggleButton(MainFrame.getInstance().getActionManager().getDeleteSlotAction());
        group.add(delete);
        add(delete);
    }

}
