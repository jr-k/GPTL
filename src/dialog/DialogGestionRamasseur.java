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
import util.EnsembleEquipeRamasseurs;
import util.EnsembleRamasseurs;
import util.EquipeRamasseurs;
import util.JXPanel;
import util.Ramasseur;
import util.SQL;

public class DialogGestionRamasseur
extends JDialog {
    private JPanel container = null;
    private JDialog dialog;
    private JLabel logo_bt;
    public static boolean init = true;
    private JTextField prenomField;
    private JTextField nomField;
    private JComboBox nationalitecombo;
    private JComboBox equipecombo;
    private JLabel prenomlab;
    private JLabel nomlab;
    private JLabel nationalitelab;
    private JLabel equipelab;
    JTable tableau;
    JScrollPane jsp;
    JButton ajouter;
    JButton modifier;
    JButton confirmer;
    JButton supprimer;
    JButton cancel;
    CalendrierMain win;
    String idmodifier;

    public DialogGestionRamasseur(JFrame parent, String name, boolean modal, CalendrierMain win) {
        super(parent, name, modal);
        this.dialog = this;
        this.logo_bt = new JLabel(new ImageIcon(this.getClass().getResource("/imgs/ramasseurtennisc.png")));
        this.prenomField = new JTextField(9);
        this.nomField = new JTextField(9);
        this.nationalitecombo = new JComboBox();
        this.equipecombo = new JComboBox();
        this.prenomlab = new JLabel("Pr\u00e9nom : ");
        this.nomlab = new JLabel("Nom :     ");
        this.nationalitelab = new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(0) + ".png")));
        this.equipelab = new JLabel("Equipe : ");
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
        JXPanel ramasseurpane = new JXPanel(new BorderLayout(), 480, 230, BorderFactory.createEtchedBorder());
        this.tableau = new JTable(new TableModel(new Object[0][], new String[]{"Id", "Pr\u00e9nom", "Nom", "Equipe", " ", "Pays"}));
        CalendrierMain.ramasseurs.toTables(this.tableau);
        this.tableau.setAutoCreateRowSorter(true);
        this.tableau.setRowHeight(24);
        this.tableau.getColumn("Id").setMaxWidth(25);
        this.tableau.getColumn("Id").setMinWidth(25);
        this.tableau.getColumn(" ").setMinWidth(25);
        this.tableau.getColumn(" ").setMaxWidth(25);
        this.tableau.setSelectionMode(0);
        this.tableau.setDefaultRenderer(JLabel.class, new TableComponent());
        this.jsp = new JScrollPane(this.tableau);
        this.jsp.setPreferredSize(new Dimension(290, 200));
        ramasseurpane.add((JComponent)this.jsp, "West");
        JLabel title = new JLabel("<html><b>Ajouter un nouveau ramasseur</b><html>");
        title.setHorizontalAlignment(0);
        JXPanel prenompane = new JXPanel(170, 35, Color.white, BorderFactory.createEtchedBorder()).add((JComponent)this.prenomlab, this.prenomField);
        JXPanel nompane = new JXPanel(170, 35, Color.white, BorderFactory.createEtchedBorder()).add((JComponent)this.nomlab, this.nomField);
        JXPanel nationalitepane = new JXPanel(170, 35, Color.white, BorderFactory.createEtchedBorder()).add((JComponent)this.nationalitelab, this.nationalitecombo);
        int i = 0;
        while (i < Main.nationalite.size()) {
            this.nationalitecombo.addItem(Main.nationalite.get(i++));
        }
        this.nationalitecombo.addActionListener(new PaysListener());
        JXPanel equipepane = new JXPanel(170, 35, Color.white, BorderFactory.createEtchedBorder()).add((JComponent)this.equipelab, this.equipecombo);
        EnsembleEquipeRamasseurs.chargeInComboBox(this.equipecombo);
        this.equipecombo.setPreferredSize(new Dimension(100, 20));
        JXPanel nouveauRamasseur = new JXPanel(180, 220).add(title, prenompane, nompane, equipepane, nationalitepane);
        ramasseurpane.add((JComponent)nouveauRamasseur, "East");
        JXPanel leftopt = new JXPanel(290, 37, BorderFactory.createEtchedBorder()).add((JComponent)this.supprimer, this.modifier);
        JXPanel rightopt = new JXPanel(170, 37, BorderFactory.createEtchedBorder()).add((JComponent)this.ajouter, this.confirmer);
        JXPanel options = new JXPanel(new FlowLayout()).add(leftopt, new JLabel(""), rightopt);
        ramasseurpane.add((JComponent)options, "South");
        this.ajouter.addActionListener(new AjouteListener());
        this.supprimer.addActionListener(new SupprimeListener());
        this.modifier.addActionListener(new ModifieListener());
        this.confirmer.addActionListener(new ConfirmeListener());
        JXPanel contentJoueursDialog = new JXPanel(Color.white, BorderFactory.createEtchedBorder()).add((JComponent)new JXPanel(200, 11, Color.white), ramasseurpane);
        content.add((JComponent)contentJoueursDialog, "Center");
        JXPanel control = new JXPanel().add(this.cancel);
        this.cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogGestionRamasseur.this.win.refreshPlanningFromBD();
                DialogGestionRamasseur.this.setVisible(false);
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
        this.equipecombo.setSelectedIndex(0);
    }

    class PaysListener
    implements ActionListener {
        PaysListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int select = ((JComboBox)e.getSource()).getSelectedIndex();
            DialogGestionRamasseur.this.nationalitelab.setIcon(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(select) + ".png")));
        }
    }

    class ConfirmeListener
    implements ActionListener {
        ConfirmeListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String prenom = ("'" + DialogGestionRamasseur.this.prenomField.getText() + "',").toLowerCase();
            String nom = ("'" + DialogGestionRamasseur.this.nomField.getText() + "',").toLowerCase();
            String idnat = String.valueOf(Main.getPaysIndex((String)DialogGestionRamasseur.this.nationalitecombo.getSelectedItem())) + ",";
            String ideq = "" + ((EquipeRamasseurs)DialogGestionRamasseur.this.equipecombo.getSelectedItem()).getNumero() + "";
            if (DialogGestionRamasseur.this.nomField.getText().length() > 0 && DialogGestionRamasseur.this.prenomField.getText().length() > 0) {
                int resultat = SQL.update("update ramasseurs set prenom=" + prenom + "nom=" + nom + "pays=" + idnat + "equipe=" + ideq + " where id=" + DialogGestionRamasseur.this.idmodifier);
                EnsembleRamasseurs.chargeIn();
                ((TableModel)DialogGestionRamasseur.this.tableau.getModel()).deleteAll();
                CalendrierMain.ramasseurs.toTables(DialogGestionRamasseur.this.tableau);
            } else {
                JOptionPane info = new JOptionPane();
                JOptionPane.showMessageDialog(null, "<html>Vous n'avez pas renseign\u00e9 tous les champs : <b>Op\u00e9ration annul\u00e9e</b></html>");
            }
            DialogGestionRamasseur.this.resetAjouteForm();
            DialogGestionRamasseur.this.supprimer.setEnabled(true);
            DialogGestionRamasseur.this.modifier.setEnabled(true);
            DialogGestionRamasseur.this.ajouter.setEnabled(true);
            DialogGestionRamasseur.this.confirmer.setEnabled(false);
            EnsembleEquipeRamasseurs.chargeInComboBox(DialogGestionRamasseur.this.equipecombo);
        }
    }

    public class ModifieListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = DialogGestionRamasseur.this.tableau.getSelectedRow();
            Object[] infos = ((TableModel)DialogGestionRamasseur.this.tableau.getModel()).getInfoAt(selected);
            String id = String.valueOf(infos[0]);
            String prenom = String.valueOf(infos[1]);
            String nom = String.valueOf(infos[2]);
            String eq = String.valueOf(infos[3]).toLowerCase();
            String pays = String.valueOf(infos[5]);
            EnsembleEquipeRamasseurs.chargeInComboBox(DialogGestionRamasseur.this.equipecombo);
            Ramasseur r = EnsembleRamasseurs.getRamasseurById(Integer.parseInt(id));
            EquipeRamasseurs equipeR = EnsembleEquipeRamasseurs.getEquipeRamasseursById(r.getEquipe());
            DialogGestionRamasseur.this.equipecombo.addItem(equipeR);
            DialogGestionRamasseur.this.idmodifier = id;
            DialogGestionRamasseur.this.prenomField.setText(prenom);
            DialogGestionRamasseur.this.nomField.setText(nom);
            DialogGestionRamasseur.this.equipecombo.setSelectedItem(equipeR);
            DialogGestionRamasseur.this.nationalitecombo.setSelectedIndex(Main.getPaysIndex(pays));
            DialogGestionRamasseur.this.supprimer.setEnabled(false);
            DialogGestionRamasseur.this.modifier.setEnabled(false);
            DialogGestionRamasseur.this.ajouter.setEnabled(false);
            DialogGestionRamasseur.this.confirmer.setEnabled(true);
        }
    }

    public class SupprimeListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (DialogGestionRamasseur.this.tableau.getSelectedRows().length > 0) {
                JOptionPane confirm = new JOptionPane();
                if (JOptionPane.showConfirmDialog(null, "\u00cates-vous s\u00fbr de vouloir supprimer ces informations ?", "Supprimer", 0) == 0) {
                    int[] selected = DialogGestionRamasseur.this.tableau.getSelectedRows();
                    for (int i = 0; i < selected.length; ++i) {
                        Object[] infos = ((TableModel)DialogGestionRamasseur.this.tableau.getModel()).getInfoAt(selected[i]);
                        int id = Integer.parseInt(infos[0] + "");
                        Ramasseur r = EnsembleRamasseurs.getRamasseurById(id);
                        ResultSet resultats = SQL.query("select * from smatchs where ramasseurs1=" + r.getEquipe() + " or ramasseurs2=" + r.getEquipe(), 1004, 1007);
                        ResultSet resultatd = SQL.query("select * from dmatchs where ramasseurs1=" + r.getEquipe() + " or ramasseurs2=" + r.getEquipe(), 1004, 1007);
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
                            JOptionPane.showMessageDialog(null, "Ce ramasseur est affect\u00e9 \u00e0 un match.\nVeuillez changer l'\u00e9quipe de ramasseurs du match concern\u00e9 avant de supprimer ce ramasseur.");
                            break;
                        }
                        int resultat = SQL.update("delete from ramasseurs where id=" + infos[0]);
                        ((TableModel)DialogGestionRamasseur.this.tableau.getModel()).removeRow(selected[i]);
                        int j = i;
                        while (j < selected.length) {
                            int[] arrn = selected;
                            int n = j++;
                            arrn[n] = arrn[n] - 1;
                        }
                    }
                    EnsembleRamasseurs.chargeIn();
                    EnsembleEquipeRamasseurs.chargeInComboBox(DialogGestionRamasseur.this.equipecombo);
                }
            }
        }
    }

    public class AjouteListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String maxid = "" + EnsembleRamasseurs.getMaxId() + ",";
            String prenom = ("'" + DialogGestionRamasseur.this.prenomField.getText() + "',").toLowerCase();
            String nom = ("'" + DialogGestionRamasseur.this.nomField.getText() + "',").toLowerCase();
            String idnat = String.valueOf(Main.getPaysIndex((String)DialogGestionRamasseur.this.nationalitecombo.getSelectedItem())) + ",";
            String ideq = "" + ((EquipeRamasseurs)DialogGestionRamasseur.this.equipecombo.getSelectedItem()).getNumero() + "";
            ResultSet resultatMax = SQL.query("select * from ramasseurs where equipe=" + ideq, 1004, 1007);
            int res = 0;
            try {
                resultatMax.last();
                res = resultatMax.getRow();
            }
            catch (Exception els) {
                // empty catch block
            }
            if (res >= 6) {
                JOptionPane info = new JOptionPane();
                JOptionPane.showMessageDialog(null, "<html>L'\u00e9quipe <b>" + ideq + "</b> poss\u00e8de d\u00e9j\u00e0 6 ramasseurs (maximum)</html>");
            } else {
                if (DialogGestionRamasseur.this.nomField.getText().length() > 0 && DialogGestionRamasseur.this.prenomField.getText().length() > 0) {
                    int resultat = SQL.update("insert into ramasseurs values(" + maxid + prenom + nom + idnat + ideq + ")");
                    EnsembleRamasseurs.chargeIn();
                    ((TableModel)DialogGestionRamasseur.this.tableau.getModel()).deleteAll();
                    CalendrierMain.ramasseurs.toTables(DialogGestionRamasseur.this.tableau);
                    JViewport jv = DialogGestionRamasseur.this.jsp.getViewport();
                    jv.setViewPosition(new Point(0, 100000));
                } else {
                    JOptionPane info = new JOptionPane();
                    JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs pour ajouter un ramasseur");
                }
                DialogGestionRamasseur.this.resetAjouteForm();
            }
            EnsembleEquipeRamasseurs.chargeInComboBox(DialogGestionRamasseur.this.equipecombo);
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

