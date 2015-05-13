package vista;

import entidades.Fase;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import principal.ControlDatos;

public class Fases extends JDialog {

    int modo;
    int tipo;
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JTextField textEC;
    private JTextField textES;
    private JTextField textId;
    private JTextField textPC;
    private JTextField textPS;

    public Fases(Frame parent, boolean modal, int tipo) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.modo = 0;
        this.tipo = tipo;
        setTitulo();
    }

    public Fases(Frame parent, boolean modal, Fase fase, int tipo) {
        this(parent, modal, tipo);
        this.modo = 1;
        setTitulo();
        setDatos(fase);
    }

    private void setTitulo() {
        switch (this.modo) {
            case 0:
                if (this.tipo == 0) {
                    setTitle("Nueva Fase");
                } else {
                    setTitle("Nueva Fase TESTRA");
                }
                break;
            case 1:
                if (this.tipo == 0) {
                    setTitle("Editar Fase");
                    this.textId.setEditable(false);
                } else {
                    setTitle("Editar Fase TESTRA");
                    this.textId.setEditable(false);
                }
                break;
        }
    }

    private void setDatos(Fase fase) {
        this.textId.setText(fase.getId());
        this.textEC.setText(fase.getEmpresaCon());
        this.textES.setText(fase.getEmpresaSin());
        this.textPC.setText(fase.getParticularCon());
        this.textPS.setText(fase.getParticularSin());
    }

    private void guardarNuevo() {
        String id = this.textId.getText();
        String EC = this.textEC.getText();
        String ES = this.textES.getText();
        String PC = this.textPC.getText();
        String PS = this.textPS.getText();

        Fase fase = new Fase(id, EC, ES, PC, PS);
        ControlDatos cd = new ControlDatos(" ");
        if (this.tipo == 0) {
            cd.nuevaFase(fase);
            cd.actualizaLista();
        } else {
            cd.nuevaFaseTestra(fase);
            cd.actualizaLista();
        }
    }

    private void guardarEditado() {
        String id = this.textId.getText();
        String EC = this.textEC.getText();
        String ES = this.textES.getText();
        String PC = this.textPC.getText();
        String PS = this.textPS.getText();

        Fase fase = new Fase(id, EC, ES, PC, PS);
        ControlDatos cd = new ControlDatos(" ");
        if (this.tipo == 0) {
            cd.modificaFase(fase);
            cd.actualizaLista();
        } else {
            cd.modificaFaseTestra(fase);
            cd.actualizaLista();
        }
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.textId = new JTextField();
        this.textEC = new JTextField();
        this.textES = new JTextField();
        this.textPC = new JTextField();
        this.textPS = new JTextField();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();

        setDefaultCloseOperation(2);

        this.jLabel1.setText("Id fase: ");

        this.jLabel2.setText("Empresa CON puntos");

        this.jLabel3.setText("Empresa SIN puntos");

        this.jLabel4.setText("Particular CON puntos");

        this.jLabel5.setText("Particular SIN puntos");

        this.textId.setHorizontalAlignment(0);

        this.textEC.setHorizontalAlignment(0);

        this.textES.setHorizontalAlignment(0);

        this.textPC.setHorizontalAlignment(0);

        this.textPS.setHorizontalAlignment(0);

        this.jButton1.setIcon(new ImageIcon(getClass().getResource("/ico/aceptar32.png")));
        this.jButton1.setToolTipText("Aceptar");
        this.jButton1.addActionListener((ActionEvent evt) -> {
            Fases.this.jButton1ActionPerformed(evt);
        });
        this.jButton2.setIcon(new ImageIcon(getClass().getResource("/ico/cancel32.png")));
        this.jButton2.setToolTipText("Cancelar");
        this.jButton2.addActionListener((ActionEvent evt) -> {
            Fases.this.jButton2ActionPerformed(evt);
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.textId)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jButton2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton1)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel5).addGap(18, 18, 18).addComponent(this.textPS, -2, 100, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel2).addGap(18, 18, 18).addComponent(this.textEC, -2, 100, -2)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.jLabel3, -2, 111, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.textES, -2, 100, -2).addComponent(this.textPC, -2, 100, -2)))).addGap(0, 0, 32767))).addContainerGap()));

        layout.linkSize(0, new Component[]{this.jLabel2, this.jLabel3, this.jLabel4, this.jLabel5});

        layout.linkSize(0, new Component[]{this.textEC, this.textES, this.textPC, this.textPS});

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.textId, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.textEC, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.textES, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.textPC, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.textPS, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap(-1, 32767)));

        layout.linkSize(1, new Component[]{this.jLabel2, this.jLabel3, this.jLabel4, this.jLabel5});

        layout.linkSize(1, new Component[]{this.textEC, this.textES, this.textPC, this.textPS});

        pack();
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        dispose();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        switch (this.modo) {
            case 0:
                guardarNuevo();
                dispose();
                break;
            case 1:
                guardarEditado();
                dispose();
        }
    }
}
