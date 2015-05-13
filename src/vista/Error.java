package vista;

import java.awt.Container;
import javax.swing.JOptionPane;

public class Error {

    public static void error(Container cont, String error) {
        JOptionPane.showMessageDialog(cont, error, "Error", 0);
    }

    public static void ioe(Container cont, String error) {
        JOptionPane.showMessageDialog(cont, error, "Error I/O", 0);
    }

    public static void sql(Container cont, String error) {
        JOptionPane.showMessageDialog(cont, error, "Error SQL", 0);
    }

    public static void classNotFound(Container cont, String error) {
        JOptionPane.showMessageDialog(cont, error, "Error I/O", 0);
    }
}
