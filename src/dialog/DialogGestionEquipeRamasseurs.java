/*
 * Decompiled with CFR 0_110.
 */
package dialog;

import calendrier.CalendrierMain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import util.EnsembleEquipeRamasseurs;
import util.JXPanel;
import util.SQL;

public class DialogGestionEquipeRamasseurs
extends JDialog {
    private JPanel container = null;
    private JDialog dialog;
    private JLabel logo_bt;
    public static boolean init = true;
    private JTextField labelField;
    private JLabel labelLab;
    JTable tableau;
    JScrollPane jsp;
    JButton ajouter;
    JButton modifier;
    JButton confirmer;
    JButton supprimer;
    JButton cancel;
    CalendrierMain win;
    String idmodifier;

    public DialogGestionEquipeRamasseurs(JFrame parent, String name, boolean modal, CalendrierMain win) {
        super(parent, name, modal);
        this.dialog = this;
        this.logo_bt = new JLabel(new ImageIcon(this.getClass().getResource("/imgs/equiperamasseurtennisc.png")));
        this.labelField = new JTextField(9);
        this.labelLab = new JLabel("Label : ");
        this.tableau = null;
        this.jsp = null;
        this.ajouter = new JButton("Ajouter");
        this.modifier = new JButton("Modifier");
        this.confirmer = new JButton("Confirmer");
        this.supprimer = new JButton("Supprimer");
        this.cancel = new JButton("Quitter");
        this.win = null;
        this.idmodifier = "";
        this.win = win;
        this.setSize(490, 460);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(1);
        this.initDialog();
        this.confirmer.setEnabled(false);
    }

    public void initDialog() {
        JXPanel panIcon = new JXPanel(new BorderLayout(), Color.white).add((JComponent)this.logo_bt, "North");
        JXPanel content = new JXPanel(new BorderLayout(), Color.white).add((JComponent)panIcon, "North");
        JXPanel equipepane = new JXPanel(new BorderLayout(), 480, 220, BorderFactory.createEtchedBorder());
        this.tableau = new JTable(new TableModel(new Object[0][], new String[]{"Id", "Equipe"}));
        CalendrierMain.equiperamasseurs.toTables(this.tableau);
        this.tableau.setAutoCreateRowSorter(true);
        this.tableau.setSelectionMode(0);
        this.tableau.setRowHeight(24);
        this.tableau.getColumn("Id").setMaxWidth(25);
        this.tableau.getColumn("Id").setMinWidth(25);
        this.tableau.setDefaultRenderer(JLabel.class, new TableComponent());
        this.jsp = new JScrollPane(this.tableau);
        this.jsp.setPreferredSize(new Dimension(290, 200));
        equipepane.add((JComponent)this.jsp, "West");
        JLabel title = new JLabel("<html><b>Ajouter une nouvelle \u00e9quipe</b><html>");
        title.setHorizontalAlignment(0);
        JXPanel labelpane = new JXPanel(170, 35, Color.white, BorderFactory.createEtchedBorder()).add((JComponent)this.labelLab, this.labelField);
        JXPanel nouvelEquipe = new JXPanel(180, 220).add(title, new JXPanel(200, 41), labelpane, new JXPanel(200, 21));
        equipepane.add((JComponent)nouvelEquipe, "East");
        JXPanel leftopt = new JXPanel(290, 37, BorderFactory.createEtchedBorder()).add((JComponent)this.supprimer, this.modifier);
        JXPanel rightopt = new JXPanel(170, 37, BorderFactory.createEtchedBorder()).add((JComponent)this.ajouter, this.confirmer);
        JXPanel options = new JXPanel(new FlowLayout()).add(leftopt, new JLabel(""), rightopt);
        equipepane.add((JComponent)options, "South");
        this.ajouter.addActionListener(new AjouteListener());
        this.supprimer.addActionListener(new SupprimeListener());
        this.modifier.addActionListener(new ModifieListener());
        this.confirmer.addActionListener(new ConfirmeListener());
        JXPanel contentJoueursDialog = new JXPanel(Color.white, BorderFactory.createEtchedBorder()).add((JComponent)new JXPanel(200, 11, Color.white), equipepane);
        content.add((JComponent)contentJoueursDialog, "Center");
        JXPanel control = new JXPanel().add(this.cancel);
        this.cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogGestionEquipeRamasseurs.this.win.refreshPlanningFromBD();
                DialogGestionEquipeRamasseurs.this.setVisible(false);
            }
        });
        this.getContentPane().add((Component)content, "Center");
        this.getContentPane().add((Component)control, "South");
    }

    public void showGestionDialog() {
        this.setVisible(true);
    }

    public static String up1(String value) {
        if (value == null) {
            return null;
        }
        if (value.length() == 0) {
            return value;
        }
        StringBuilder result = new StringBuilder(value);
        result.replace(0, 1, result.substring(0, 1).toUpperCase());
        return result.toString();
    }

    public void resetAjouteForm() {
        this.labelField.setText("");
    }

    class ConfirmeListener
    implements ActionListener {
        ConfirmeListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String label = ("'" + DialogGestionEquipeRamasseurs.this.labelField.getText() + "'").toLowerCase();
            if (DialogGestionEquipeRamasseurs.this.labelField.getText().length() > 0) {
                int resultat = SQL.update("update equiperamasseur set label=" + label + " where id=" + DialogGestionEquipeRamasseurs.this.idmodifier);
                EnsembleEquipeRamasseurs.chargeIn();
                ((TableModel)DialogGestionEquipeRamasseurs.this.tableau.getModel()).deleteAll();
                CalendrierMain.equiperamasseurs.toTables(DialogGestionEquipeRamasseurs.this.tableau);
            } else {
                JOptionPane info = new JOptionPane();
                JOptionPane.showMessageDialog(null, "<html>Vous n'avez pas renseign\u00e9 le nom de l'\u00e9quipe : <b>Op\u00e9ration annul\u00e9e</b></html>");
            }
            DialogGestionEquipeRamasseurs.this.resetAjouteForm();
            DialogGestionEquipeRamasseurs.this.supprimer.setEnabled(true);
            DialogGestionEquipeRamasseurs.this.modifier.setEnabled(true);
            DialogGestionEquipeRamasseurs.this.ajouter.setEnabled(true);
            DialogGestionEquipeRamasseurs.this.confirmer.setEnabled(false);
        }
    }

    public class ModifieListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = DialogGestionEquipeRamasseurs.this.tableau.getSelectedRow();
            Object[] infos = ((TableModel)DialogGestionEquipeRamasseurs.this.tableau.getModel()).getInfoAt(selected);
            String id = String.valueOf(infos[0]);
            String label = String.valueOf(infos[1]);
            DialogGestionEquipeRamasseurs.this.idmodifier = id;
            DialogGestionEquipeRamasseurs.this.labelField.setText(label);
            DialogGestionEquipeRamasseurs.this.supprimer.setEnabled(false);
            DialogGestionEquipeRamasseurs.this.modifier.setEnabled(false);
            DialogGestionEquipeRamasseurs.this.ajouter.setEnabled(false);
            DialogGestionEquipeRamasseurs.this.confirmer.setEnabled(true);
        }
    }

    public class SupprimeListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (DialogGestionEquipeRamasseurs.this.tableau.getSelectedRows().length > 0) {
                JOptionPane confirm = new JOptionPane();
                if (JOptionPane.showConfirmDialog(null, "\u00cates-vous s\u00fbr de vouloir supprimer ces informations ?", "Supprimer", 0) == 0) {
                    int[] selected = DialogGestionEquipeRamasseurs.this.tableau.getSelectedRows();
                    for (int i = 0; i < selected.length; ++i) {
                        Object[] infos = ((TableModel)DialogGestionEquipeRamasseurs.this.tableau.getModel()).getInfoAt(selected[i]);
                        ResultSet resultats = SQL.query("select * from ramasseurs where equipe=" + infos[0], 1004, 1007);
                        int res = 0;
                        try {
                            resultats.last();
                            res = resultats.getRow();
                        }
                        catch (Exception es) {
                            // empty catch block
                        }
                        if (res > 0) {
                            JOptionPane info = new JOptionPane();
                            JOptionPane.showMessageDialog(null, "Il reste des ramasseurs affect\u00e9s \u00e0 cette \u00e9quipe.\nVeuillez changer l'\u00e9quipe de ces ramasseurs avant de supprimer cette \u00e9quipe.");
                            break;
                        }
                        int resultat = SQL.update("delete from equiperamasseur where id=" + infos[0]);
                        ((TableModel)DialogGestionEquipeRamasseurs.this.tableau.getModel()).removeRow(selected[i]);
                        int j = i;
                        while (j < selected.length) {
                            int[] arrn = selected;
                            int n = j++;
                            arrn[n] = arrn[n] - 1;
                        }
                    }
                    EnsembleEquipeRamasseurs.chargeIn();
                }
            }
        }
    }

    public class AjouteListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String maxid = "" + EnsembleEquipeRamasseurs.getMaxId() + ",";
            String label = ("'" + DialogGestionEquipeRamasseurs.this.labelField.getText() + "'").toLowerCase();
            if (DialogGestionEquipeRamasseurs.this.labelField.getText().length() > 0) {
                int resultat = SQL.update("insert into equiperamasseur values(" + maxid + label + ")");
                EnsembleEquipeRamasseurs.chargeIn();
                ((TableModel)DialogGestionEquipeRamasseurs.this.tableau.getModel()).deleteAll();
                CalendrierMain.equiperamasseurs.toTables(DialogGestionEquipeRamasseurs.this.tableau);
                JViewport jv = DialogGestionEquipeRamasseurs.this.jsp.getViewport();
                jv.setViewPosition(new Point(0, 100000));
            } else {
                JOptionPane info = new JOptionPane();
                JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs pour ajouter une nouvelle \u00e9quipe");
            }
            DialogGestionEquipeRamasseurs.this.resetAjouteForm();
        }
    }

    public class TableComponent
    extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (JLabel)value;
        }
    }

    public class TableModel
    extends AbstractTableModel {
        private Object[][] data;
        private String[] title;

        public Object[] getInfoAt(int i) {
            return this.data[i];
        }

        public TableModel(Object[][] data, String[] title) {
            this.data = data;
            this.title = title;
        }

        public void deleteAll() {
            this.data = new Object[0][];
        }

        @Override
        public String getColumnName(int col) {
            return this.title[col];
        }

        @Override
        public int getColumnCount() {
            return this.title.length;
        }

        @Override
        public int getRowCount() {
            return this.data.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            return this.data[row][col];
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            if (!this.getColumnName(col).equals("Age") && !this.getColumnName(col).equals("Suppression")) {
                this.data[row][col] = value;
            }
        }

        public Class getColumnClass(int col) {
            return this.data[0][col].getClass();
        }

        public void removeRow(int position) {
            int indice = 0;
            int indice2 = 0;
            int nbRow = this.getRowCount() - 1;
            int nbCol = this.getColumnCount();
            Object[][] temp = new Object[nbRow][nbCol];
            for (Object[] value : this.data) {
                if (indice != position) {
                    temp[indice2++] = value;
                }
                ++indice;
            }
            this.data = temp;
            temp = null;
            this.fireTableDataChanged();
        }

        public void addRow(Object[] data) {
            int indice = 0;
            int nbRow = this.getRowCount();
            int nbCol = this.getColumnCount();
            Object[][] temp = this.data;
            this.data = new Object[nbRow + 1][nbCol];
            for (Object[] value : temp) {
                this.data[indice++] = value;
            }
            this.data[indice] = data;
            temp = null;
            this.fireTableDataChanged();
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }

}

