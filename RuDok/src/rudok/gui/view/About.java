package rudok.gui.view;

import javax.swing.*;
import java.awt.*;


public class About extends JDialog {
    public About(Icon icon1, Icon icon2) {

        setSize(350,220);
        setLocationRelativeTo(null);
        setTitle("About");
        setVisible(true);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(0,2));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        JLabel imePrezimeIndeksAlekse = new JLabel("Aleksa Orozović 96/20 RN");
        JLabel imePrezimeIndeksDusana = new JLabel("Dušan Branković 38/19 RN");
        JLabel slikaAlekse = new JLabel(icon1);
        JLabel slikaDusana = new JLabel(icon2);

        panel1.add(slikaAlekse);
        panel1.add(imePrezimeIndeksAlekse);

        panel2.add(slikaDusana);
        panel2.add(imePrezimeIndeksDusana);

        grid.add(panel1);
        grid.add(panel2);

        add(grid);

        validate();
    }
}
