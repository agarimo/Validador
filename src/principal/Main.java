package principal;

import entidades.Fase;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vista.Error;
import vista.Ventana;

public class Main {

    public static List<Fase> listFases;
    public static List<Fase> listFasesTestra;
    public static List<String> listArt;
    public static List<String> listNombre;
    public static Ventana ventana;
    public static File fichero;

    public static void main(String[] args) throws SQLException {
        listFases = new ArrayList();
        listFasesTestra = new ArrayList();
        listArt = new ArrayList();
        listNombre = new ArrayList();
        lookAndFeel();
        crearArchivos();
        
//        ejecucionPrueba();
        ventana = new Ventana();
    }

//    private static void ejecucionPrueba() {
//        String nifPrueba = "    ";
//        String lineaTest = "00000|09/06/2015|0905006Z|714|P|20| |ND|98    |270453259104|270453259104        |070215|052.1 RD 1428/0|FRANCO VAZQUEZ, JOSE             |1530FFP   |" + nifPrueba + "|27/TR-JEFATURA PROVINCIAL TRAFICO DE LUGO         |      |N.P|   | |      |27    |270453343802 MUSMAR , ALI X3905935Y LLEIDA 16/03/2015 8459GCG RD 1428/03 048.1 (1)                                                                                                                     |https: sede.dgt.gob.es/WEB_TTRA_CONSULTA/ServletVisualizacion?params=b40%2F90HdbbszJAt0Qedo6uRVnCAGVqVzSfSYVB88GiKWmlHmaRE33F2wbrR6QKlh%0D%0A%26subidioma%3Des&formato=PDF                                                                     |LLEIDA                                           ";
//
//        try {
//            Linea ln=new Linea(lineaTest);
//            System.out.println(ln);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private static void crearArchivos() {
        ControlDatos cd = new ControlDatos();
        Files.creaDirectorio(new File("verificado"));
        Files.creaDirectorio(new File("sinVerificar"));

        File file = new File("data.dat");
        if (!file.exists()) {
            try {
                file.createNewFile();
                cd.guardaDatos();
            } catch (IOException ex) {
                Error.ioe(ventana, "Error al crear el archivo");
            }
        } else {
            cargarDatos();
        }
    }

    public static void cargarDatos() {
        ControlDatos cd = new ControlDatos();
        cd.cargaDatos();
    }

    public static void guardarDatos() {
        ControlDatos cd = new ControlDatos();
        cd.guardaDatos();
    }

    public static void procesar() {
        Hilo a = new Hilo();
        a.start();
    }

    private static void lookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
