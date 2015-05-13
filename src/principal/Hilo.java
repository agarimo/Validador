package principal;

import entidades.Archivo;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import vista.Error;
import vista.Ventana;

public class Hilo extends Thread
{
  @Override
  public void run()
  {
    File carpeta = new File("sinVerificar", Main.fichero.getName());
    Ventana.progreso.setString("0%");
    Ventana.label.setText("Preparando archivo");
    Files.moverDirectorio(Main.fichero, carpeta);
    try {
      procesaCarpeta(carpeta);
    } catch (SQLException | IOException ex) {
      Error.error(Main.ventana, ex.getMessage());
    }
    Ventana.textRuta.setText("");
    Ventana.progreso.setString("PROCESO TERMINADO");
    Ventana.label.setText("...");
  }

  public static void procesaCarpeta(File file) throws SQLException, IOException
  {
    if (file.isDirectory()) {
      File destino = new File("verificado", file.getName());
      Files.creaDirectorio(destino);
      String[] archivos = file.list();
      Archivo archivo;
      
      for (int i = 0; i < archivos.length; i++) {
        int indice = i + 1;
        String numArch = indice + " de " + archivos.length;
        File aux = new File(file, archivos[i]);
        archivo = new Archivo(aux, numArch);
      }
      Files.borraDirectorio(file);
    }
  }
}