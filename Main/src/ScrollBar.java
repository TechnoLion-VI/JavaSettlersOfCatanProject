import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ScrollBar extends JPanel {
    JLabel jLabelV;
    public ScrollBar() {
        super(true);
        jLabelV  = new JLabel();
        setLayout(new BorderLayout());
        JScrollBar verticalBar = new JScrollBar( JScrollBar.VERTICAL, 60, 60, 0, 150); //place holder
        verticalBar.addAdjustmentListener(new VerticalAdjustmentListener());
        add(verticalBar, BorderLayout.EAST);
        add(jLabelV, BorderLayout.CENTER);
    }
    class VerticalAdjustmentListener implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
            jLabelV.setText("Vertical scrolled value " + e.getValue());
            repaint();
        }
    }
    public static void main(String s[]) {
        JFrame jFrame = new JFrame("scroll bar");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(new ScrollBar());
        jFrame.setSize(500, 150);
        jFrame.setVisible(true);
    }
}