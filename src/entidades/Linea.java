package entidades;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import principal.Main;

public final class Linea {

    String linea;
    String[] componentes;
    String fase;
    String tipoJuridico;
    String puntos;
    String articulo;
    String nif;
    String nombre;
    boolean testra = false;
    String cadenaCif = "ABCDEFGHJKLMNPQRSUVW";
    String cadenaNie = "XYZ";
    String patronDni = "[\\s]{1}[0-9]{6,8}[A-Z]{1}";
    String patronCif = "[\\s]{1}[" + this.cadenaCif + "]{1}[0-9]{8}";
    String patronNie = "[\\s]{1}[" + this.cadenaNie + "]{1}[0-9]{5,7}[A-Z]{1}";

    public Linea(String linea) throws SQLException {
        this.linea = linea;
        this.componentes = linea.split("\\|");
        limpiaComponentes();

        if (this.componentes[0].equals("00001")) {
            this.testra = true;
        }

        this.fase = this.componentes[3];
        this.tipoJuridico = this.componentes[4];
        this.articulo = this.componentes[12];
        this.nif = this.componentes[15].trim();
        this.puntos = this.componentes[18].trim();
        this.nombre = this.componentes[13];

        setPuntos();
        if (this.testra) {
            setNifTestra();
        } else {
            setNif();
            setFase();
        }
    }

    private void limpiaComponentes() {
        for (int i = 0; i < this.componentes.length; i++) {
            this.componentes[i] = this.componentes[i].replace("'", "´");
        }
    }

    private void setPuntos() {
        try {
            int punt = Integer.parseInt(this.puntos);

            if (punt < 1) {
                this.componentes[18] = "0  ";
            }
        } catch (Exception e) {
            this.componentes[18] = "N.P";
        }
    }

    private void setNif() {
        String aux = buscaNif(this.componentes[23]);

        if (aux.equals("NO*CONSTA ")) {
            if (this.nif.equals("")) {
                this.nif = "NO*CONSTA ";
                this.componentes[15] = this.nif;
            } else {
                switchNif();
                this.componentes[15] = this.nif;
            }
        } else {
            this.componentes[15] = this.nif;
        }
    }

    private void setNifTestra() {
        if (this.nif.equals("")) {
            this.nif = "NO*CONSTA ";
            this.componentes[15] = this.nif;
        } else {
            switchNif();
            this.componentes[15] = this.nif;
        }
    }

    private void switchNif() {
        if (this.cadenaCif.contains("" + this.nif.charAt(0))) {
            setCif(this.nif);
        } else if (this.cadenaNie.contains("" + this.nif.charAt(0))) {
            setNie(this.nif);
        } else {
            setDni(this.nif);
        }
    }

    private void setCif(String str) {
        str = str.trim();
        str = completaEspacios(str);
        if (!this.testra) {
            this.tipoJuridico = "E";
        }
        this.nif = str;
    }

    private void setDni(String str) {
        str = limpia(str);

        int dni = getNumero(str);
        if (dni == -1) {
            str = "NO*CONSTA ";
        } else {
            str = Integer.toString(dni) + calculaDni(dni);
            str = completaCeros(str, 9);
            str = completaEspacios(str);
        }
        if (!this.testra) {
            this.tipoJuridico = "P";
        }
        this.nif = str;
    }

    private void setNie(String str) {
        str = limpia(str);

        int dni = getNumero(str);
        if (dni == -1) {
            str = "NO*CONSTA ";
        } else {
            str = str.charAt(0) + completaCeros(Integer.toString(dni), 7) + calculaDni(dni);
            str = completaEspacios(str);
        }
        if (!this.testra) {
            this.tipoJuridico = "P";
        }
        this.nif = str;
    }

    private String buscaNif(String linea) {
        linea = limpiaConEspacios(linea);
        String str;
        String aux;
        if ((str = regex(linea, this.patronCif)) != null) {
            aux = str;
            setCif(aux);
        } else if ((str = regex(linea, this.patronNie)) != null) {
            aux = str;
            setNie(aux);
        } else if ((str = regex(linea, this.patronDni)) != null) {
            aux = str;
            setDni(aux);
        } else {
            if (getNombre()) {
                this.tipoJuridico = "E";
            } else {
                this.tipoJuridico = "P";
            }
            aux = "NO*CONSTA ";
        }

        return aux;
    }

    private String completaCeros(String str, int num) {
        while (str.length() < num) {
            str = "0" + str;
        }
        return str;
    }

    private String completaEspacios(String str) {
        while (str.length() < 10) {
            str = str + " ";
        }
        return str;
    }

    private char calculaDni(int dni) {
        String cadena = "TRWAGMYFPDXBNJZSQVHLCKE";
        char a = cadena.charAt(dni % 23);
        return a;
    }

    private String limpia(String str) {
        Pattern p = Pattern.compile("[^0-9A-Z]");
        Matcher m = p.matcher(str);

        if (m.find()) {
            str = m.replaceAll("");
        }
        return str.trim();
    }

    private String limpiaConEspacios(String str) {
        Pattern p = Pattern.compile("[^0-9A-ZáéíóúÁÉÍÓÚ\\s]");
        Matcher m = p.matcher(str);

        if (m.find()) {
            str = m.replaceAll("");
        }
        return str.trim();
    }

    private int getNumero(String str) {
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(str);

        if (m.find()) {
            str = m.replaceAll("");
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return -1;
    }

    private void setFase() {
        Fase aux = new Fase(this.fase);
        int i = Main.listFases.indexOf(aux);

        if (i != -1) {
            aux = (Fase) Main.listFases.get(i);
            editaFase(aux);
        }
    }

    private void editaFase(Fase aux) {
        String str;
        if (getTipoSancionado()) {
            if (getPuntos()) {
                str = aux.getEmpresaCon();
            } else {
                str = aux.getEmpresaSin();
            }
        } else {
            if (getPuntos()) {
                str = aux.getParticularCon();
            } else {
                str = aux.getParticularSin();
            }
        }
        if ((!this.testra)
                && (getArticulo())) {
            str = "717";
        }

        this.componentes[3] = str;
    }

    private boolean getTipoSancionado() {
        this.componentes[4] = this.tipoJuridico;

        return this.tipoJuridico.equalsIgnoreCase("E");
    }

    private boolean getPuntos() {
        try {
            this.puntos = this.puntos.trim();
            int punt = Integer.parseInt(this.puntos);

            return punt > 1;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean getArticulo() {
        boolean a = false;
        Iterator it = Main.listArt.iterator();

        while (it.hasNext()) {
            String aux = (String) it.next();
            if (this.articulo.toUpperCase().contains(aux)) {
                a = true;
            }
        }
        return a;
    }

    private boolean getNombre() {
        boolean a = false;
        Iterator it = Main.listNombre.iterator();

        while (it.hasNext()) {
            String aux = (String) it.next();

            if (this.nombre.contains(aux)) {
                a = true;
            }
        }
        return a;
    }

    public static String regex(String str, String patron) {
        String aux = null;

        Pattern pt = Pattern.compile(patron);
        Matcher mt = pt.matcher(str);

        if (mt.find()) {
            aux = mt.group();
        }
        return aux;
    }

    public static boolean buscaRegex(String str, String patron) {
        Pattern pt = Pattern.compile(patron);
        Matcher mt = pt.matcher(str);

        return mt.find();
    }

    private String recomponerLinea() {
        String str = this.componentes[0];

        for (int i = 1; i < this.componentes.length; i++) {
            str = str + "|" + this.componentes[i];
        }
        return str;
    }

    @Override
    public String toString() {
        return recomponerLinea();
    }
}
