
package freakland2d;
import freakland2d.gui.*;
import javax.swing.JFrame;
/**
 *
 * @author Sebastian-PC
 */
public class FreakLand2D {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Stage stage = new Stage();
        frame.setSize(800, 500);
        frame.add(stage);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setFocusable(true);
    }
    
}
