/*
 * Decompiled with CFR 0_110.
 */
package dialog;

import calendrier.CalendrierMain;
import calendrier.Main;
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
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import util.EnsembleJoueurs;
import util.JXPanel;
import util.SQL;

public class DialogGestionJoueur
extends JDialog {
    private JPanel container = null;
    private JDialog dialog;
    private JLabel logo_bt;
    public static boolean init = true;
    private JTextField prenomField;
    private JTextField nomField;
    private JComboBox nationalitecombo;
    private JLabel prenomlab;
    private JLabel nomlab;
    private JLabel nationalitelab;
    JTable tableau;
    JScrollPane jsp;
    JButton ajouter;
    JButton modifier;
    JButton confirmer;
    JButton supprimer;
    JButton cancel;
    CalendrierMain win;
    String idmodifier;

    public DialogGestionJoueur(JFrame parent, String name, boolean modal, CalendrierMain win) {
        super(parent, name, modal);
        this.dialog = this;
        this.logo_bt = new JLabel(new ImageIcon(this.getClass().getResource("/imgs/joueurtennisc.png")));
        this.prenomField = new JTextField(9);
        this.nomField = new JTextField(9);
        this.nationalitecombo = new JComboBox();
        this.prenomlab = new JLabel("Pr\u00e9nom : ");
        this.nomlab = new JLabel("Nom :     ");
        this.nationalitelab = new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(0) + ".png")));
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
        JXPanel joueurspane = new JXPanel(new BorderLayout(), 480, 220, BorderFactory.createEtchedBorder());
        this.tableau = new JTable(new TableModel(new Object[0][], new String[]{"Id", "Pr\u00e9nom", "Nom", " ", "Pays"}));
        CalendrierMain.joueurs.toTables(this.tableau);
        this.tableau.setAutoCreateRowSorter(true);
        this.tableau.setSelectionMode(0);
        this.tableau.setRowHeight(24);
        this.tableau.getColumn("Id").setMaxWidth(25);
        this.tableau.getColumn("Id").setMinWidth(25);
        this.tableau.getColumn(" ").setMinWidth(25);
        this.tableau.getColumn(" ").setMaxWidth(25);
        this.tableau.setDefaultRenderer(JLabel.class, new TableComponent());
        this.jsp = new JScrollPane(this.tableau);
        this.jsp.setPreferredSize(new Dimension(290, 200));
        joueurspane.add((JComponent)this.jsp, "West");
        JLabel title = new JLabel("<html><b>Ajouter un nouveau joueur</b><html>");
        title.setHorizontalAlignment(0);
        JXPanel prenompane = new JXPanel(170, 35, Color.white, BorderFactory.createEtchedBorder()).add((JComponent)this.prenomlab, this.prenomField);
        JXPanel nompane = new JXPanel(170, 35, Color.white, BorderFactory.createEtchedBorder()).add((JComponent)this.nomlab, this.nomField);
        JXPanel nationalitepane = new JXPanel(170, 35, Color.white, BorderFactory.createEtchedBorder()).add((JComponent)this.nationalitelab, this.nationalitecombo);
        int i = 0;
        while (i < Main.nationalite.size()) {
            this.nationalitecombo.addItem(Main.nationalite.get(i++));
        }
        this.nationalitecombo.addActionListener(new PaysListener());
        JXPanel nouveauJoueur = new JXPanel(180, 220).add(title, new JXPanel(200, 21), prenompane, nompane, nationalitepane);
        joueurspane.add((JComponent)nouveauJoueur, "East");
        JXPanel leftopt = new JXPanel(290, 37, BorderFactory.createEtchedBorder()).add((JComponent)this.supprimer, this.modifier);
        JXPanel rightopt = new JXPanel(170, 37, BorderFactory.createEtchedBorder()).add((JComponent)this.ajouter, this.confirmer);
        JXPanel options = new JXPanel(new FlowLayout()).add(leftopt, new JLabel(""), rightopt);
        joueurspane.add((JComponent)options, "South");
        this.ajouter.addActionListener(new AjouteListener());
        this.supprimer.addActionListener(new SupprimeListener());
        this.modifier.addActionListener(new ModifieListener());
        this.confirmer.addActionListener(new ConfirmeListener());
        JXPanel contentJoueursDialog = new JXPanel(Color.white, BorderFactory.createEtchedBorder()).add((JComponent)new JXPanel(200, 11, Color.white), joueurspane);
        content.add((JComponent)contentJoueursDialog, "Center");
        JXPanel control = new JXPanel().add(this.cancel);
        this.cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogGestionJoueur.this.win.refreshPlanningFromBD();
                DialogGestionJoueur.this.setVisible(false);
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
        this.nomField.setText("");
        this.prenomField.setText("");
        this.nationalitecombo.setSelectedIndex(0);
    }

    class PaysListener
    implements ActionListener {
        PaysListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int select = ((JComboBox)e.getSource()).getSelectedIndex();
            DialogGestionJoueur.this.nationalitelab.setIcon(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(select) + ".png")));
        }
    }

    class ConfirmeListener
    implements ActionListener {
        ConfirmeListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String prenom = ("'" + DialogGestionJoueur.this.prenomField.getText() + "',").toLowerCase();
            String nom = ("'" + DialogGestionJoueur.this.nomField.getText() + "',").toLowerCase();
            String idnat = String.valueOf(Main.getPaysIndex((String)DialogGestionJoueur.this.nationalitecombo.getSelectedItem()));
            if (DialogGestionJoueur.this.nomField.getText().length() > 0 && DialogGestionJoueur.this.prenomField.getText().length() > 0) {
                int resultat = SQL.update("update joueurs set prenom=" + prenom + "nom=" + nom + "pays=" + idnat + " where id=" + DialogGestionJoueur.this.idmodifier);
                EnsembleJoueurs.chargeIn();
                ((TableModel)DialogGestionJoueur.this.tableau.getModel()).deleteAll();
                CalendrierMain.joueurs.toTables(DialogGestionJoueur.this.tableau);
            } else {
                JOptionPane info = new JOptionPane();
                JOptionPane.showMessageDialog(null, "<html>Vous n'avez pas renseign\u00e9 tous les champs : <b>Op\u00e9ration annul\u00e9e</b></html>");
            }
            DialogGestionJoueur.this.resetAjouteForm();
            DialogGestionJoueur.this.supprimer.setEnabled(true);
            DialogGestionJoueur.this.modifier.setEnabled(true);
            DialogGestionJoueur.this.ajouter.setEnabled(true);
            DialogGestionJoueur.this.confirmer.setEnabled(false);
        }
    }

    public class ModifieListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = DialogGestionJoueur.this.tableau.getSelectedRow();
            Object[] infos = ((TableModel)DialogGestionJoueur.this.tableau.getModel()).getInfoAt(selected);
            String id = String.valueOf(infos[0]);
            String prenom = String.valueOf(infos[1]);
            String nom = String.valueOf(infos[2]);
            String pays = String.valueOf(infos[4]);
            DialogGestionJoueur.this.idmodifier = id;
            DialogGestionJoueur.this.prenomField.setText(prenom);
            DialogGestionJoueur.this.nomField.setText(nom);
            DialogGestionJoueur.this.nationalitecombo.setSelectedIndex(Main.getPaysIndex(pays));
            DialogGestionJoueur.this.supprimer.setEnabled(false);
            DialogGestionJoueur.this.modifier.setEnabled(false);
            DialogGestionJoueur.this.ajouter.setEnabled(false);
            DialogGestionJoueur.this.confirmer.setEnabled(true);
        }
    }

    public class SupprimeListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (DialogGestionJoueur.this.tableau.getSelectedRows().length > 0) {
                JOptionPane confirm = new JOptionPane();
                if (JOptionPane.showConfirmDialog(null, "\u00cates-vous s\u00fbr de vouloir supprimer ces informations ?", "Supprimer", 0) == 0) {
                    int[] selected = DialogGestionJoueur.this.tableau.getSelectedRows();
                    for (int i = 0; i < selected.length; ++i) {
                        Object[] infos = ((TableModel)DialogGestionJoueur.this.tableau.getModel()).getInfoAt(selected[i]);
                        ResultSet resultats = SQL.query("select * from smatchs where joueur1=" + infos[0] + " or joueur2=" + infos[0], 1004, 1007);
                        ResultSet resultatd = SQL.query("select * from dmatchs where joueur1=" + infos[0] + " or joueur2=" + infos[0] + " or joueur3=" + infos[0] + " or joueur4=" + infos[0], 1004, 1007);
                        int res = 0;
                        int red = 0;
                        try {
                            resultats.last();
                            resultatd.last();
                            res = resultats.getRow();
                            red = resultatd.getRow();
                        }
                        catch (Exception es) {
                            // empty catch block
                        }
                        if (res > 0 || red > 0) {
                            JOptionPane info = new JOptionPane();
                            JOptionPane.showMessageDialog(null, "<html>Ce joueur a un match r\u00e9serv\u00e9 dans le planning : <b>Op\u00e9ration annul\u00e9e</b></html>");
                            break;
                        }
                        int resultat = SQL.update("delete from joueurs where id=" + infos[0]);
                        ((TableModel)DialogGestionJoueur.this.tableau.getModel()).removeRow(selected[i]);
                        int j = i;
                        while (j < selected.length) {
                            int[] arrn = selected;
                            int n = j++;
                            arrn[n] = arrn[n] - 1;
                        }
                    }
                    EnsembleJoueurs.chargeIn();
                }
            }
        }
    }

    public class AjouteListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String maxid = "" + EnsembleJoueurs.getMaxId() + ",";
            String prenom = ("'" + DialogGestionJoueur.this.prenomField.getText() + "',").toLowerCase();
            String nom = ("'" + DialogGestionJoueur.this.nomField.getText() + "',").toLowerCase();
            String idnat = String.valueOf(Main.getPaysIndex((String)DialogGestionJoueur.this.nationalitecombo.getSelectedItem()));
            if (DialogGestionJoueur.this.nomField.getText().length() > 0 && DialogGestionJoueur.this.prenomField.getText().length() > 0) {
                int resultat = SQL.update("insert into joueurs values(" + maxid + prenom + nom + idnat + ")");
                EnsembleJoueurs.chargeIn();
                ((TableModel)DialogGestionJoueur.this.tableau.getModel()).deleteAll();
                CalendrierMain.joueurs.toTables(DialogGestionJoueur.this.tableau);
                JViewport jv = DialogGestionJoueur.this.jsp.getViewport();
                jv.setViewPosition(new Point(0, 100000));
            } else {
                JOptionPane info = new JOptionPane();
                JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs pour ajouter un joueur");
            }
            DialogGestionJoueur.this.resetAjouteForm();
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

