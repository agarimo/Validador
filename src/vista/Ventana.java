package vista;

import entidades.Fase;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import principal.ControlDatos;
import principal.Main;

public class Ventana extends JFrame {

    private int tipoFase = 0;
    private int tipoArticulo = 0;
    private ButtonGroup buttonGroup1;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JComboBox jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel6;
    private JLabel jLabel8;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem5;
    private JMenuItem jMenuItem6;
    private JMenuItem jMenuItem7;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JPopupMenu.Separator jSeparator1;
    private JPopupMenu.Separator jSeparator2;
    public static JLabel label;
    private JLabel labelArticulos;
    private JLabel labelFases;
    private JLabel labelTituloArticulos;
    private JList listaArticulos;
    private JList listaFases;
    private JPanel panelArticulos;
    private JPanel panelFases;
    private JPanel panelInicio;
    private JPanel paneles;
    public static JProgressBar progreso;
    private JTextField textArticulo;
    private JTextField textEC;
    private JTextField textES;
    private JTextField textPC;
    private JTextField textPS;
    public static JTextField textRuta;

    public Ventana() {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void fileChooser() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(1);
        int i = fc.showOpenDialog(this);

        if (i == 0) {
            Main.fichero = fc.getSelectedFile();
            try {
                textRuta.setText(Main.fichero.getCanonicalPath());
                progreso.setString("PREPARADO PARA COMENZAR");
            } catch (IOException ex) {
                Error.ioe(this, ex.getMessage());
            }
        } else if (i != 1) {
            Error.error(this, "Error en JChooser");
        }
    }

    private static void abrirCarpeta() {
        try {
            File a = new File(".");
            String ruta = a.getCanonicalPath() + "\\verificado";
            Runtime r = Runtime.getRuntime();
            Process p = null;
            p = r.exec("explorer.exe " + ruta);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniciarFases() {
        DefaultListModel modelo = new DefaultListModel();
        Iterator it;
        if (this.tipoFase == 0) {
            this.labelFases.setText("FASES");
            it = Main.listFases.iterator();
        } else {
            this.labelFases.setText("FASES TESTRA");
            it = Main.listFasesTestra.iterator();
        }

        while (it.hasNext()) {
            Fase fase = (Fase) it.next();
            modelo.addElement(fase);
        }
        this.listaFases.setModel(modelo);

        this.textEC.setText("");
        this.textES.setText("");
        this.textPC.setText("");
        this.textPS.setText("");
    }

    private void mostrarFase(Fase fase) {
        this.textEC.setText(fase.getEmpresaCon());
        this.textES.setText(fase.getEmpresaSin());
        this.textPC.setText(fase.getParticularCon());
        this.textPS.setText(fase.getParticularSin());
    }

    private void iniciarArticulos() {
        this.labelArticulos.setText("ARTICULOS");
        this.labelTituloArticulos.setText("Nuevo Artículo");
        this.tipoArticulo = 0;
        DefaultListModel modelo = new DefaultListModel();
        Iterator it = Main.listArt.iterator();

        while (it.hasNext()) {
            String aux = (String) it.next();
            modelo.addElement(aux);
        }
        this.listaArticulos.setModel(modelo);
    }

    private void iniciarNombres() {
        this.labelArticulos.setText("NOMBRES");
        this.labelTituloArticulos.setText("Nuevo Nombre");
        this.tipoArticulo = 1;
        DefaultListModel modelo = new DefaultListModel();
        Iterator it = Main.listNombre.iterator();

        while (it.hasNext()) {
            String aux = (String) it.next();
            modelo.addElement(aux);
        }
        this.listaArticulos.setModel(modelo);
    }

    public void setProgreso(int indice, int total) {
        int prog = indice * 100 / total;
        progreso.setValue(prog);
        progreso.setString(prog + "%");
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("ico/run32.png"));

        return retValue;
    }

    private void initComponents() {
        this.jMenuItem2 = new JMenuItem();
        this.buttonGroup1 = new ButtonGroup();
        this.jComboBox1 = new JComboBox();
        this.paneles = new JPanel();
        this.panelInicio = new JPanel();
        this.jPanel3 = new JPanel();
        progreso = new JProgressBar();
        label = new JLabel();
        this.jButton5 = new JButton();
        this.jButton6 = new JButton();
        this.jLabel8 = new JLabel();
        this.jLabel6 = new JLabel();
        textRuta = new JTextField();
        this.jButton8 = new JButton();
        this.panelArticulos = new JPanel();
        this.jScrollPane2 = new JScrollPane();
        this.listaArticulos = new JList();
        this.labelTituloArticulos = new JLabel();
        this.textArticulo = new JTextField();
        this.jButton4 = new JButton();
        this.labelArticulos = new JLabel();
        this.jButton7 = new JButton();
        this.panelFases = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.listaFases = new JList();
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.textEC = new JTextField();
        this.textES = new JTextField();
        this.jPanel2 = new JPanel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.textPC = new JTextField();
        this.textPS = new JTextField();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.labelFases = new JLabel();
        this.jMenuBar1 = new JMenuBar();
        this.jMenu1 = new JMenu();
        this.jMenuItem1 = new JMenuItem();
        this.jMenu2 = new JMenu();
        this.jMenuItem4 = new JMenuItem();
        this.jMenuItem6 = new JMenuItem();
        this.jMenuItem5 = new JMenuItem();
        this.jMenuItem7 = new JMenuItem();
        this.jSeparator2 = new JPopupMenu.Separator();
        this.jMenuItem3 = new JMenuItem();
        this.jSeparator1 = new JPopupMenu.Separator();

        this.jMenuItem2.setText("jMenuItem2");

        this.jComboBox1.setModel(new DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        setDefaultCloseOperation(0);
        setTitle("Procesado de Fases");
        setIconImage(getIconImage());
        setResizable(false);

        this.paneles.setLayout(new CardLayout());

        this.jPanel3.setBorder(BorderFactory.createTitledBorder("Progreso"));

        progreso.setStringPainted(true);

        label.setHorizontalAlignment(0);
        label.setText("...");

        GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(progreso, -1, 394, 32767).addComponent(label, -1, -1, 32767)).addContainerGap()));

        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(progreso, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(label)));

        this.jButton5.setIcon(new ImageIcon(getClass().getResource("/ico/run32.png")));
        this.jButton5.setToolTipText("Procesar");
        this.jButton5.addActionListener((ActionEvent evt) -> {
            Ventana.this.jButton5ActionPerformed(evt);
        });
        this.jButton6.setIcon(new ImageIcon(getClass().getResource("/ico/open32.png")));
        this.jButton6.setToolTipText("Abrir carpeta contenedora");
        this.jButton6.addActionListener((ActionEvent evt) -> {
            Ventana.this.jButton6ActionPerformed(evt);
        });
        this.jLabel8.setFont(new Font("Tahoma", 0, 18));
        this.jLabel8.setHorizontalAlignment(0);
        this.jLabel8.setText("Procesado de archivos .BB0");

        this.jLabel6.setText("Carpeta a procesar: ");

        textRuta.setEditable(false);

        this.jButton8.setText("...");
        this.jButton8.addActionListener((ActionEvent evt) -> {
            Ventana.this.jButton8ActionPerformed(evt);
        });
        GroupLayout panelInicioLayout = new GroupLayout(this.panelInicio);
        this.panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(panelInicioLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelInicioLayout.createSequentialGroup().addContainerGap().addGroup(panelInicioLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelInicioLayout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jButton6, -2, 55, -2).addGap(18, 18, 18).addComponent(this.jButton5, -2, 75, -2)).addComponent(this.jPanel3, -1, -1, 32767).addComponent(this.jLabel8, -1, 426, 32767).addGroup(panelInicioLayout.createSequentialGroup().addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(textRuta).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton8, -2, 23, -2))).addContainerGap()));

        panelInicioLayout.linkSize(0, new Component[]{this.jButton5, this.jButton6});

        panelInicioLayout.setVerticalGroup(panelInicioLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelInicioLayout.createSequentialGroup().addContainerGap().addComponent(this.jLabel8).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(panelInicioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel6).addComponent(textRuta, -2, -1, -2).addComponent(this.jButton8)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel3, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(panelInicioLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton5).addComponent(this.jButton6)).addContainerGap(14, 32767)));

        panelInicioLayout.linkSize(1, new Component[]{this.jButton5, this.jButton6});

        this.paneles.add(this.panelInicio, "inicio");

        this.listaArticulos.setSelectionMode(0);
        this.jScrollPane2.setViewportView(this.listaArticulos);

        this.labelTituloArticulos.setText("Nuevo artículo: ");

        this.textArticulo.setHorizontalAlignment(0);

        this.jButton4.setIcon(new ImageIcon(getClass().getResource("/ico/add32.png")));
        this.jButton4.setToolTipText("Agregar artículo");
        this.jButton4.addActionListener((ActionEvent evt) -> {
            Ventana.this.jButton4ActionPerformed(evt);
        });
        this.labelArticulos.setFont(new Font("Tahoma", 0, 18));
        this.labelArticulos.setHorizontalAlignment(0);
        this.labelArticulos.setText("ARTÍCULOS");

        this.jButton7.setIcon(new ImageIcon(getClass().getResource("/ico/borrar32.png")));
        this.jButton7.setToolTipText("Borrar");
        this.jButton7.addActionListener((ActionEvent evt) -> {
            Ventana.this.jButton7ActionPerformed(evt);
        });
        GroupLayout panelArticulosLayout = new GroupLayout(this.panelArticulos);
        this.panelArticulos.setLayout(panelArticulosLayout);
        panelArticulosLayout.setHorizontalGroup(panelArticulosLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelArticulosLayout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane2, -2, 81, -2).addGap(18, 18, 18).addGroup(panelArticulosLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, panelArticulosLayout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jButton7).addGap(18, 18, 18).addComponent(this.jButton4)).addComponent(this.labelArticulos, -1, 327, 32767).addGroup(panelArticulosLayout.createSequentialGroup().addComponent(this.labelTituloArticulos).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.textArticulo))).addContainerGap()));

        panelArticulosLayout.setVerticalGroup(panelArticulosLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelArticulosLayout.createSequentialGroup().addContainerGap().addGroup(panelArticulosLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelArticulosLayout.createSequentialGroup().addComponent(this.labelArticulos).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(panelArticulosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelTituloArticulos).addComponent(this.textArticulo, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(panelArticulosLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jButton7).addComponent(this.jButton4))).addComponent(this.jScrollPane2, -1, 176, 32767)).addContainerGap()));

        this.paneles.add(this.panelArticulos, "articulos");

        this.listaFases.setBackground(new Color(240, 240, 240));
        this.listaFases.setSelectionMode(0);
        this.listaFases.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Ventana.this.listaFasesMouseClicked(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.listaFases);

        this.jPanel1.setBorder(BorderFactory.createTitledBorder("Empresas"));

        this.jLabel1.setText("Con puntos: ");

        this.jLabel2.setText("Sin puntos: ");

        this.textEC.setEditable(false);
        this.textEC.setHorizontalAlignment(0);

        this.textES.setEditable(false);
        this.textES.setHorizontalAlignment(0);

        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.textEC)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.textES, -1, 79, 32767))).addContainerGap()));

        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.textEC, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.textES, -2, -1, -2)).addContainerGap(-1, 32767)));

        this.jPanel2.setBorder(BorderFactory.createTitledBorder("Particulares"));

        this.jLabel3.setText("Con puntos: ");

        this.jLabel4.setText("Sin puntos: ");

        this.textPC.setEditable(false);
        this.textPC.setHorizontalAlignment(0);

        this.textPS.setEditable(false);
        this.textPS.setHorizontalAlignment(0);

        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addComponent(this.jLabel4)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.textPS, -1, 69, 32767).addComponent(this.textPC)).addContainerGap()));

        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.textPC, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.textPS, -2, -1, -2)).addContainerGap(-1, 32767)));

        this.jButton1.setIcon(new ImageIcon(getClass().getResource("/ico/add32.png")));
        this.jButton1.setToolTipText("Nueva Fase");
        this.jButton1.addActionListener((ActionEvent evt) -> {
            Ventana.this.jButton1ActionPerformed(evt);
        });
        this.jButton2.setIcon(new ImageIcon(getClass().getResource("/ico/edit32.png")));
        this.jButton2.setToolTipText("Editar Fase");
        this.jButton2.addActionListener((ActionEvent evt) -> {
            Ventana.this.jButton2ActionPerformed(evt);
        });
        this.jButton3.setIcon(new ImageIcon(getClass().getResource("/ico/borrar32.png")));
        this.jButton3.setToolTipText("Borrar Fase");
        this.jButton3.addActionListener((ActionEvent evt) -> {
            Ventana.this.jButton3ActionPerformed(evt);
        });
        this.labelFases.setFont(new Font("Tahoma", 1, 14));
        this.labelFases.setHorizontalAlignment(0);
        this.labelFases.setText("....");

        GroupLayout panelFasesLayout = new GroupLayout(this.panelFases);
        this.panelFases.setLayout(panelFasesLayout);
        panelFasesLayout.setHorizontalGroup(panelFasesLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelFasesLayout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -2, 47, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(panelFasesLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, panelFasesLayout.createSequentialGroup().addComponent(this.jPanel1, -1, -1, 32767).addGap(18, 18, 18).addComponent(this.jPanel2, -2, -1, -2)).addGroup(GroupLayout.Alignment.TRAILING, panelFasesLayout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jButton3).addGap(18, 18, 18).addComponent(this.jButton2).addGap(18, 18, 18).addComponent(this.jButton1)).addComponent(this.labelFases, -1, -1, 32767)).addContainerGap()));

        panelFasesLayout.linkSize(0, new Component[]{this.jButton1, this.jButton2, this.jButton3});

        panelFasesLayout.setVerticalGroup(panelFasesLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelFasesLayout.createSequentialGroup().addContainerGap().addGroup(panelFasesLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelFasesLayout.createSequentialGroup().addComponent(this.labelFases).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(panelFasesLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jPanel2, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jPanel1, GroupLayout.Alignment.LEADING, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, 32767).addGroup(panelFasesLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton1, GroupLayout.Alignment.TRAILING).addComponent(this.jButton2, GroupLayout.Alignment.TRAILING).addComponent(this.jButton3, GroupLayout.Alignment.TRAILING))).addComponent(this.jScrollPane1)).addContainerGap()));

        panelFasesLayout.linkSize(1, new Component[]{this.jButton1, this.jButton2, this.jButton3});

        panelFasesLayout.linkSize(1, new Component[]{this.jPanel1, this.jPanel2});

        this.paneles.add(this.panelFases, "fases");

        this.jMenu1.setText("Menú");

        this.jMenuItem1.setIcon(new ImageIcon(getClass().getResource("/ico/home16.png")));
        this.jMenuItem1.setText("Inicio");
        this.jMenuItem1.addActionListener((ActionEvent evt) -> {
            Ventana.this.jMenuItem1ActionPerformed(evt);
        });
        this.jMenu1.add(this.jMenuItem1);

        this.jMenu2.setIcon(new ImageIcon(getClass().getResource("/ico/config16.png")));
        this.jMenu2.setText("Configuración");

        this.jMenuItem4.setText("Fases");
        this.jMenuItem4.addActionListener((ActionEvent evt) -> {
            Ventana.this.jMenuItem4ActionPerformed(evt);
        });
        this.jMenu2.add(this.jMenuItem4);

        this.jMenuItem6.setText("Fases Testra");
        this.jMenuItem6.addActionListener((ActionEvent evt) -> {
            Ventana.this.jMenuItem6ActionPerformed(evt);
        });
        this.jMenu2.add(this.jMenuItem6);

        this.jMenuItem5.setText("Artículos");
        this.jMenuItem5.addActionListener((ActionEvent evt) -> {
            Ventana.this.jMenuItem5ActionPerformed(evt);
        });
        this.jMenu2.add(this.jMenuItem5);

        this.jMenuItem7.setText("Nombres");
        this.jMenuItem7.addActionListener((ActionEvent evt) -> {
            Ventana.this.jMenuItem7ActionPerformed(evt);
        });
        this.jMenu2.add(this.jMenuItem7);

        this.jMenu1.add(this.jMenu2);
        this.jMenu1.add(this.jSeparator2);

        this.jMenuItem3.setIcon(new ImageIcon(getClass().getResource("/ico/salir16.png")));
        this.jMenuItem3.setText("Salir");
        this.jMenuItem3.addActionListener((ActionEvent evt) -> {
            Ventana.this.jMenuItem3ActionPerformed(evt);
        });
        this.jMenu1.add(this.jMenuItem3);
        this.jMenu1.add(this.jSeparator1);

        this.jMenuBar1.add(this.jMenu1);

        setJMenuBar(this.jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.paneles, -1, -1, 32767));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.paneles, -1, -1, 32767));

        pack();
    }

    private void jMenuItem1ActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) this.paneles.getLayout();
        cl.show(this.paneles, "inicio");
    }

    private void jMenuItem4ActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) this.paneles.getLayout();
        cl.show(this.paneles, "fases");
        this.tipoFase = 0;
        iniciarFases();
    }

    private void jMenuItem5ActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) this.paneles.getLayout();
        cl.show(this.paneles, "articulos");
        iniciarArticulos();
    }

    private void jMenuItem3ActionPerformed(ActionEvent evt) {
        Main.guardarDatos();
        System.exit(0);
    }

    private void jButton8ActionPerformed(ActionEvent evt) {
        progreso.setValue(0);
        progreso.setString("0%");
        fileChooser();
    }

    private void jButton6ActionPerformed(ActionEvent evt) {
        abrirCarpeta();
    }

    private void jButton5ActionPerformed(ActionEvent evt) {
        if (Main.fichero == null) {
            Error.error(this, "Debes seleccionar un archivo");
        } else {
            Main.procesar();
        }
    }

    private void listaFasesMouseClicked(MouseEvent evt) {
        mostrarFase((Fase) this.listaFases.getSelectedValue());
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        Fases aux = new Fases(this, true, this.tipoFase);
        aux.setVisible(true);
        aux.setLocationRelativeTo(null);
        iniciarFases();
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        Fase fase = (Fase) this.listaFases.getSelectedValue();
        if (fase != null) {
            Fases aux = new Fases(this, true, fase, this.tipoFase);
            aux.setVisible(true);
            aux.setLocationRelativeTo(null);
            iniciarFases();
        } else {
            Error.error(this, "Debes seleccionar una fase");
        }
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        String aux = this.textArticulo.getText();
        if ((aux != null) && (!aux.equals(""))) {
            ControlDatos cd = new ControlDatos(" ");
            if (this.tipoArticulo == 0) {
                cd.nuevoArt(aux);
                cd.actualizaLista();
                iniciarArticulos();
            } else if (this.tipoArticulo == 1) {
                cd.nuevoNombre(aux);
                cd.actualizaLista();
                iniciarNombres();
            }
            this.textArticulo.setText("");
        } else {
            Error.error(this, "El campo nuevo artículo no puede estar vacío");
        }
    }

    private void jButton7ActionPerformed(ActionEvent evt) {
        String aux = (String) this.listaArticulos.getSelectedValue();
        if (aux != null) {
            ControlDatos cd = new ControlDatos(" ");
            if (this.tipoArticulo == 0) {
                cd.borraArt(aux);
                cd.actualizaLista();
                iniciarArticulos();
            } else if (this.tipoArticulo == 1) {
                cd.borraNombre(aux);
                cd.actualizaLista();
                iniciarNombres();
            }
        } else {
            Error.error(this, "Debes seleccionar un artículo");
        }
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        Fase fase = (Fase) this.listaFases.getSelectedValue();
        if (fase != null) {
            ControlDatos cd = new ControlDatos(" ");
            if (this.tipoFase == 0) {
                cd.borraFase(fase);
            } else {
                cd.borraFaseTestra(fase);
            }
            cd.actualizaLista();
            iniciarFases();
        } else {
            Error.error(this, "Debes seleccionar una fase");
        }
    }

    private void jMenuItem6ActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) this.paneles.getLayout();
        cl.show(this.paneles, "fases");
        this.tipoFase = 1;
        iniciarFases();
    }

    private void jMenuItem7ActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) this.paneles.getLayout();
        cl.show(this.paneles, "articulos");
        iniciarNombres();
    }
}
