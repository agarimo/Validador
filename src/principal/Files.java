package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Files
{
  public static void creaDirectorio(File directorio)
  {
    if (!directorio.exists())
      directorio.mkdirs();
  }

  private static void copiaDirectorio(File origen, File destino)
  {
    if (origen.isDirectory()) {
      if (!destino.exists()) {
        destino.mkdir();
      }
      String[] archivos = origen.list();
        for (String archivo : archivos) {
            copiaDirectorio(new File(origen, archivo), new File(destino, archivo));
        }
    }
    else {
      copiaArchivo(origen, destino);
    }
  }

  public static void borraDirectorio(File directorio) {
    if (directorio.isDirectory()) {
      String[] archivos = directorio.list();
        for (String archivo : archivos) {
            borraDirectorio(new File(directorio, archivo));
        }
      directorio.delete();
    } else {
      directorio.delete();
    }
  }

  public static void moverDirectorio(File origen, File destino) {
    copiaDirectorio(origen, destino);
    borraDirectorio(origen);
  }

  public static boolean copiaArchivo(File origen, File destino)
  {
    try
    {
      InputStream in = new FileInputStream(origen);
      OutputStream out = new FileOutputStream(destino);

      byte[] buffer = new byte[1024];
      int lenght;
      while ((lenght = in.read(buffer)) > 0) {
        out.write(buffer, 0, lenght);
      }

      in.close();
      out.close();

      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }return false;
  }
}