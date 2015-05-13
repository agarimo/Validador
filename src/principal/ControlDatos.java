package principal;

import entidades.Fase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import vista.Error;

public final class ControlDatos {

    List<Fase> fases;
    List<Fase> fasesTestra;
    List<String> art;
    List<String> nombre;

    public ControlDatos() {
        this.fases = new ArrayList();
        this.fasesTestra = new ArrayList();
        this.art = new ArrayList();
        this.nombre = new ArrayList();
    }

    public ControlDatos(String carga) {
        this.fases = Main.listFases;
        this.fasesTestra = Main.listFasesTestra;
        this.art = Main.listArt;
        this.nombre = Main.listNombre;
    }

    public void actualizaLista() {
        Collections.sort(this.fases);
        Collections.sort(this.fasesTestra);
        Collections.sort(this.art);
        Collections.sort(this.nombre);
        Main.listFases = this.fases;
        Main.listFasesTestra = this.fasesTestra;
        Main.listArt = this.art;
        Main.listNombre = this.nombre;
    }

    public void cargaDatos() {
        if (cargar()) {
            Main.listFases = this.fases;
            Main.listFasesTestra = this.fasesTestra;
            Main.listArt = this.art;
            Main.listNombre = this.nombre;
        } else {
            Main.listFases = new ArrayList();
            Main.listFasesTestra = new ArrayList();
            Main.listArt = new ArrayList();
            Main.listNombre = new ArrayList();
        }
    }

    public void guardaDatos() {
        this.fases = Main.listFases;
        this.fasesTestra = Main.listFasesTestra;
        this.art = Main.listArt;
        this.nombre = Main.listNombre;
        guardar();
    }

    public void nuevaFase(Fase aux) {
        if (this.fases.isEmpty()) {
            this.fases.add(aux);
        } else if (!this.fases.contains(aux)) {
            this.fases.add(aux);
        }
    }

    public void borraFase(Fase aux) {
        if (this.fases.contains(aux)) {
            this.fases.remove(aux);
        }
    }

    public void modificaFase(Fase aux) {
        int indice = this.fases.indexOf(aux);
        if (indice != -1) {
            this.fases.set(indice, aux);
        }
    }

    public void nuevaFaseTestra(Fase aux) {
        if (this.fasesTestra.isEmpty()) {
            this.fasesTestra.add(aux);
        } else if (!this.fasesTestra.contains(aux)) {
            this.fasesTestra.add(aux);
        }
    }

    public void borraFaseTestra(Fase aux) {
        if (this.fasesTestra.contains(aux)) {
            this.fasesTestra.remove(aux);
        }
    }

    public void modificaFaseTestra(Fase aux) {
        int indice = this.fasesTestra.indexOf(aux);
        if (indice != -1) {
            this.fasesTestra.set(indice, aux);
        }
    }

    public void nuevoArt(String aux) {
        if (this.art.isEmpty()) {
            this.art.add(aux);
        } else if (!this.art.contains(aux)) {
            this.art.add(aux);
        }
    }

    public void borraArt(String aux) {
        if (this.art.contains(aux)) {
            this.art.remove(aux);
        }
    }

    public void nuevoNombre(String aux) {
        if (this.nombre.isEmpty()) {
            this.nombre.add(aux);
        } else if (!this.nombre.contains(aux)) {
            this.nombre.add(aux);
        }
    }

    public void borraNombre(String aux) {
        if (this.nombre.contains(aux)) {
            this.nombre.remove(aux);
        }
    }

    public boolean guardar() {
        try {
            ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(new File("data.dat")));

            flujoSalida.writeObject(this.fases);
            flujoSalida.writeObject(this.fasesTestra);
            flujoSalida.writeObject(this.art);
            flujoSalida.writeObject(this.nombre);
            System.out.println("Datos guardados correctamente");
            return true;
        } catch (IOException ioe) {
            Error.ioe(Main.ventana, "Error al guardar los datos");
        }
        return false;
    }

    public boolean cargar() {
        ObjectInputStream flujoEntrada = null;
        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream(new File("data.dat")));
            this.fases = ((List) flujoEntrada.readObject());
            this.fasesTestra = ((List) flujoEntrada.readObject());
            this.art = ((List) flujoEntrada.readObject());
            this.nombre = ((List) flujoEntrada.readObject());
            System.out.println("Datos cargados correctamente.");
            return true;
        } catch (ClassNotFoundException ex) {
            Error.classNotFound(Main.ventana, "Error al cargar los datos.");
            return false;
        } catch (IOException ex) {
            Error.ioe(Main.ventana, "Error al cargar los datos.");
            return false;
        } finally {
            try {
                flujoEntrada.close();
            } catch (IOException ex) {
                Error.ioe(Main.ventana, "Error al cerrar el Stream.");
                return false;
            }
        }
    }
}
