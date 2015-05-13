package entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import principal.Files;
import principal.Main;
import vista.Error;
import vista.Ventana;

public class Archivo
{
  String nombre;
  String path;
  File destino;

  public Archivo(File archivo, String numeroArchivo)
    throws SQLException, IOException
  {
    this.nombre = archivo.getName();
    this.path = archivo.getParent();
    preparaDestino();
    procesaArchivo(archivo, this.destino, numeroArchivo);
  }

  private void preparaDestino() throws IOException {
    String ruta = this.path.replaceAll("sinVerificar", "verificado");
    Files.creaDirectorio(new File(ruta));
    this.destino = new File(ruta, this.nombre);

    if (!this.destino.exists()) {
      this.destino.createNewFile();
    } else {
      this.destino.delete();
      preparaDestino();
    }
  }

  private void procesaArchivo(File archivo, File destino, String numeroArchivos) {
    FileReader fr = null;

    FileWriter fichero = null;

    Ventana.label.setText("Calculando líneas del archivo");
    int lineas = cuentaLineas(archivo);
    int indice = 0;

    Ventana.label.setText("Procesando archivo " + numeroArchivos);
    try
    {
      preparaDestino();
      fr = new FileReader(archivo);
      BufferedReader br = new BufferedReader(fr);
      fichero = new FileWriter(destino, true);
      PrintWriter pw = new PrintWriter(fichero);
      String linea;
      while ((linea = br.readLine()) != null) {
        Linea aux = new Linea(linea);
        pw.println(aux.toString());
        indice++;
        Main.ventana.setProgreso(indice, lineas);
      }
    }
    catch (Exception e) {
      Error.ioe(Main.ventana, e.getMessage());
    } finally {
      try {
        if (null != fr) {
          fr.close();
        }
        if (null != fichero)
          fichero.close();
      }
      catch (Exception e2) {
        Error.ioe(Main.ventana, e2.getMessage());
      }
    }
  }

  private int cuentaLineas(File archivo)
  {
    FileReader fr = null;
    int total = 0;
    try {
      fr = new FileReader(archivo);
      BufferedReader br = new BufferedReader(fr);
      String linea;
      while ((linea = br.readLine()) != null)
        total++;
    }
    catch (IOException ex) {
      Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        fr.close();
      } catch (IOException ex) {
        Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return total;
  }
}