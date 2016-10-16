/*
 * Decompiled with CFR 0_110.
 */
package dialog;

import calendrier.CalendrierInfos;
import calendrier.CalendrierMain;
import calendrier.Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import util.Arbitre;
import util.EnsembleArbitres;
import util.EnsembleEquipeRamasseurs;
import util.EnsembleJoueurs;
import util.EquipeRamasseurs;
import util.JXPanel;
import util.Joueur;
import util.SQL;

public class DialogGestionMatchD
extends JDialog {
    private JPanel container = null;
    private JDialog dialog;
    private JLabel logo_bt;
    public static boolean init = true;
    private JLabel j1labF;
    private JLabel j2labF;
    private JLabel j3labF;
    private JLabel j4labF;
    private JLabel eq1labF;
    private JLabel eq2labF;
    private JComboBox arbitrechaisecombo;
    private JComboBox arbitrefiletcombo;
    private JLabel j1lab;
    private JLabel j2lab;
    private JLabel j3lab;
    private JLabel j4lab;
    private JLabel eq1lab;
    private JLabel eq2lab;
    private JLabel arbitrechaiselab;
    private JLabel arbitrefiletlab;
    JTable tableau;
    JTable tableau2;
    JTable tableau3;
    JTable tableau4;
    JScrollPane jsp;
    JScrollPane jsp2;
    JScrollPane jsp3;
    JScrollPane jsp4;
    JButton recommencer;
    JButton eqrecommencer;
    JButton joueur1;
    JButton joueur2;
    JButton joueur3;
    JButton joueur4;
    JButton ramasseur1;
    JButton ramasseur2;
    JButton eqremove1;
    JButton eqremove2;
    JButton remove1;
    JButton remove2;
    JButton remove3;
    JButton remove4;
    JButton valider;
    JButton suivant;
    JButton precedent;
    CalendrierMain win;
    JXPanel matchpane;
    JXPanel matchpane2;
    JXPanel matchpane3;
    CalendrierInfos iargs;
    JXPanel contentPaneDialog;
    JButton arbitreSelect;
    JButton arbitreDeselect;
    public int currentPage;
    Vector<JXPanel> pages;
    int j1id;
    int j2id;
    int j3id;
    int j4id;
    String j1prenom;
    String j1nom;
    String j1pays;
    String j2prenom;
    String j2nom;
    String j2pays;
    String j3prenom;
    String j3nom;
    String j3pays;
    String j4prenom;
    String j4nom;
    String j4pays;
    JLabel j1icon;
    JLabel j2icon;
    JLabel j3icon;
    JLabel j4icon;
    int eq1id;
    int eq2id;
    String eq1equipe;
    String eq2equipe;

    public DialogGestionMatchD(JFrame parent, String name, boolean modal, CalendrierMain win, CalendrierInfos iargs) {
        super(parent, name, modal);
        this.dialog = this;
        this.logo_bt = new JLabel(new ImageIcon(this.getClass().getResource("/imgs/matchdoubletennisc.png")));
        this.j1labF = new JLabel();
        this.j2labF = new JLabel();
        this.j3labF = new JLabel();
        this.j4labF = new JLabel();
        this.eq1labF = new JLabel();
        this.eq2labF = new JLabel();
        this.arbitrechaisecombo = new JComboBox();
        this.arbitrefiletcombo = new JComboBox();
        this.j1lab = new JLabel("");
        this.j2lab = new JLabel("");
        this.j3lab = new JLabel("");
        this.j4lab = new JLabel("");
        this.eq1lab = new JLabel("");
        this.eq2lab = new JLabel("");
        this.arbitrechaiselab = new JLabel("Arbitre de chaise : ");
        this.arbitrefiletlab = new JLabel("Arbitre de filet : ");
        this.tableau = null;
        this.tableau2 = null;
        this.tableau3 = null;
        this.tableau4 = null;
        this.jsp = null;
        this.jsp2 = null;
        this.jsp3 = null;
        this.jsp4 = null;
        this.recommencer = new JButton("Recommencer");
        this.eqrecommencer = new JButton("Recommencer");
        this.joueur1 = new JButton("J 1");
        this.joueur2 = new JButton("J 2");
        this.joueur3 = new JButton("J 3");
        this.joueur4 = new JButton("J 4");
        this.ramasseur1 = new JButton("Equipe 1");
        this.ramasseur2 = new JButton("Equipe 2");
        this.eqremove1 = new JButton(new ImageIcon(this.getClass().getResource("/imgs/removeicon.png")));
        this.eqremove2 = new JButton(new ImageIcon(this.getClass().getResource("/imgs/removeicon.png")));
        this.remove1 = new JButton(new ImageIcon(this.getClass().getResource("/imgs/removeicon.png")));
        this.remove2 = new JButton(new ImageIcon(this.getClass().getResource("/imgs/removeicon.png")));
        this.remove3 = new JButton(new ImageIcon(this.getClass().getResource("/imgs/removeicon.png")));
        this.remove4 = new JButton(new ImageIcon(this.getClass().getResource("/imgs/removeicon.png")));
        this.valider = new JButton("Valider");
        this.suivant = new JButton("Suivant");
        this.precedent = new JButton("Pr\u00e9c\u00e9dent");
        this.win = null;
        this.matchpane = null;
        this.matchpane2 = null;
        this.matchpane3 = null;
        this.iargs = null;
        this.contentPaneDialog = null;
        this.arbitreSelect = new JButton("S\u00e9lectionner");
        this.arbitreDeselect = new JButton("D\u00e9selectionner");
        this.currentPage = 0;
        this.pages = new Vector();
        this.win = win;
        this.setSize(550, 580);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(1);
        this.iargs = iargs;
        this.initDialog();
        this.initDialog2();
        this.initDialog3();
        this.pages.add(this.matchpane);
        this.pages.add(this.matchpane2);
        this.pages.add(this.matchpane3);
        this.remplirSiModification();
        this.precedent.doClick();
    }

    public void remplirSiModification() {
        ListSelectionModel selectionModel = this.tableau.getSelectionModel();
        ListSelectionModel selectionModel2 = this.tableau2.getSelectionModel();
        ListSelectionModel selectionModel4 = this.tableau4.getSelectionModel();
        if (!this.iargs.getInit()) {
            int id;
            int k;
            int j;
            int i;
            for (k = 0; k < this.iargs.getJoueurs().length; ++k) {
                for (i = 0; i < this.tableau.getRowCount(); ++i) {
                    id = Integer.parseInt(((TableModel)this.tableau.getModel()).getInfoAt(i)[0] + "");
                    for (j = 0; j < this.iargs.getJoueurs().length; ++j) {
                        if (this.iargs.getJoueurs()[j].getNumero() != id) continue;
                        selectionModel.setSelectionInterval(i, i);
                        if (j == 0) {
                            this.joueur1.doClick();
                            continue;
                        }
                        if (j == 1) {
                            this.joueur2.doClick();
                            continue;
                        }
                        if (j == 2) {
                            this.joueur3.doClick();
                            continue;
                        }
                        if (j != 3) continue;
                        this.joueur4.doClick();
                    }
                }
            }
            this.arbitrechaisecombo.setSelectedItem(this.iargs.getArbitreChaise());
            for (k = 0; k < this.iargs.getArbitres().length; ++k) {
                for (i = 0; i < this.tableau2.getRowCount(); ++i) {
                    id = Integer.parseInt(((TableModel)this.tableau2.getModel()).getInfoAt(i)[0] + "");
                    for (j = 0; j < this.iargs.getArbitres().length; ++j) {
                        if (this.iargs.getArbitres()[j].getNumero() != id) continue;
                        selectionModel2.setSelectionInterval(i, i);
                        this.arbitreSelect.doClick();
                    }
                }
            }
            this.arbitrefiletcombo.setSelectedItem(this.iargs.getArbitres()[0]);
            for (k = 0; k < this.iargs.getEquipeRamasseurs().length; ++k) {
                for (i = 0; i < this.tableau4.getRowCount(); ++i) {
                    id = Integer.parseInt(((TableModel)this.tableau4.getModel()).getInfoAt(i)[0] + "");
                    for (j = 0; j < this.iargs.getEquipeRamasseurs().length; ++j) {
                        if (this.iargs.getEquipeRamasseurs()[j].getNumero() != id) continue;
                        selectionModel4.setSelectionInterval(i, i);
                        if (j == 0) {
                            this.ramasseur1.doClick();
                            continue;
                        }
                        if (j != 1) continue;
                        this.ramasseur2.doClick();
                    }
                }
            }
        }
    }

    public void initDialog3() {
        this.matchpane3 = new JXPanel(new BorderLayout(), 540, 220, BorderFactory.createEtchedBorder());
        this.contentPaneDialog.add(this.matchpane3);
        this.matchpane3.setVisible(false);
        JLabel gauche = new JLabel("<html><b>Equipes de ramasseurs disponibles</b></html>");
        JLabel droite = new JLabel("<html><b>2 Equipes de ramasseurs li\u00e9es au match</b></html>");
        JXPanel leftopt = new JXPanel(260, 30).add(gauche);
        JXPanel rightopt = new JXPanel(260, 30).add(droite);
        JXPanel options = new JXPanel(new FlowLayout()).add(leftopt, new JLabel(""), rightopt);
        this.matchpane3.add((JComponent)options, "North");
        this.tableau4 = new JTable(new TableModel(new Object[0][], new String[]{"Id", "Equipe"}));
        CalendrierMain.equiperamasseurs.toTablesMatchD(this.tableau4, this.iargs);
        this.tableau4.setAutoCreateRowSorter(true);
        this.tableau4.setRowHeight(24);
        this.tableau4.getColumn("Id").setMaxWidth(25);
        this.tableau4.getColumn("Id").setMinWidth(25);
        this.tableau4.setSelectionMode(0);
        this.jsp4 = new JScrollPane(this.tableau4);
        this.jsp4.setPreferredSize(new Dimension(260, 100));
        this.matchpane3.add((JComponent)this.jsp4, "West");
        JXPanel eq1pane = new JXPanel(new BorderLayout(), 230, 35, Color.white, BorderFactory.createEtchedBorder());
        eq1pane.add((JComponent)this.eq1lab, "West");
        eq1pane.add((JComponent)this.eq1labF, "Center");
        eq1pane.add((JComponent)this.eqremove1, "East");
        JXPanel eq2pane = new JXPanel(new BorderLayout(), 230, 35, Color.white, BorderFactory.createEtchedBorder());
        eq2pane.add((JComponent)this.eq2lab, "West");
        eq2pane.add((JComponent)this.eq2labF, "Center");
        eq2pane.add((JComponent)this.eqremove2, "East");
        this.eqremove1.setVisible(false);
        this.eqremove2.setVisible(false);
        this.eqremove1.addActionListener(new EqRemove1Listener());
        this.eqremove2.addActionListener(new EqRemove2Listener());
        this.eqremove1.setPreferredSize(new Dimension(32, 25));
        this.eqremove2.setPreferredSize(new Dimension(32, 25));
        JXPanel nouveaumatch4 = new JXPanel(260, 140).add(new JXPanel(200, 21), eq1pane, eq2pane);
        nouveaumatch4.setBorder(BorderFactory.createEtchedBorder());
        this.matchpane3.add((JComponent)nouveaumatch4, "East");
        JXPanel leftopt4 = new JXPanel(260, 37, BorderFactory.createEtchedBorder()).add((JComponent)this.ramasseur1, this.ramasseur2);
        JXPanel rightopt4 = new JXPanel(260, 37, BorderFactory.createEtchedBorder()).add(this.eqrecommencer);
        JXPanel optionsss = new JXPanel(new FlowLayout()).add(leftopt4, new JLabel(""), rightopt4);
        this.eqrecommencer.addActionListener(new EResetListener());
        this.ramasseur1.addActionListener(new E1Listener());
        this.ramasseur2.addActionListener(new E2Listener());
        this.matchpane3.add((JComponent)optionsss, "South");
    }

    public void initDialog2() {
        this.matchpane2 = new JXPanel(new BorderLayout(), 540, 270, BorderFactory.createEtchedBorder());
        this.matchpane2.setVisible(false);
        this.contentPaneDialog.add(this.matchpane2);
        JLabel gauche = new JLabel("<html><b>Arbitres disponibles</b></html>");
        JLabel droite = new JLabel("<html><b>9 Arbitres li\u00e9s au match</b></html>");
        JXPanel leftopt = new JXPanel(260, 30).add(gauche);
        JXPanel rightopt = new JXPanel(260, 30).add(droite);
        JXPanel options = new JXPanel(new FlowLayout()).add(leftopt, new JLabel(""), rightopt);
        this.matchpane2.add((JComponent)options, "North");
        this.tableau2 = new JTable(new TableModel(new Object[0][], new String[]{"Id", "Pr\u00e9nom", "Nom", "Cat", " ", "Pays"}));
        CalendrierMain.arbitres.toTablesMatchD(this.tableau2, this.iargs);
        this.tableau2.setAutoCreateRowSorter(true);
        this.tableau2.setRowHeight(24);
        this.tableau2.getColumn("Id").setMaxWidth(25);
        this.tableau2.getColumn("Id").setMinWidth(25);
        this.tableau2.getColumn(" ").setMinWidth(25);
        this.tableau2.getColumn(" ").setMaxWidth(25);
        this.tableau2.setSelectionMode(1);
        this.tableau2.setDefaultRenderer(JLabel.class, new TableComponent());
        this.jsp2 = new JScrollPane(this.tableau2);
        this.jsp2.setPreferredSize(new Dimension(260, 200));
        this.matchpane2.add((JComponent)this.jsp2, "West");
        this.tableau3 = new JTable(new TableModel(new Object[0][], new String[]{"Id", "Pr\u00e9nom", "Nom", "Cat", " ", "Pays"}));
        this.tableau3.setAutoCreateRowSorter(true);
        this.tableau3.setRowHeight(24);
        this.tableau3.setSelectionMode(1);
        this.tableau3.getColumn("Id").setMaxWidth(25);
        this.tableau3.getColumn("Id").setMinWidth(25);
        this.tableau3.getColumn(" ").setMinWidth(25);
        this.tableau3.getColumn(" ").setMaxWidth(25);
        this.tableau3.setDefaultRenderer(JLabel.class, new TableComponent());
        this.jsp3 = new JScrollPane(this.tableau3);
        this.jsp3.setPreferredSize(new Dimension(260, 200));
        this.matchpane2.add((JComponent)this.jsp3, "East");
        this.arbitreSelect.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (DialogGestionMatchD.this.tableau3.getRowCount() < 9 && DialogGestionMatchD.this.tableau2.getSelectedRows().length > 0) {
                    int[] selected = DialogGestionMatchD.this.tableau2.getSelectedRows();
                    int max = selected.length;
                    if (selected.length + DialogGestionMatchD.this.tableau3.getRowCount() > 9) {
                        int depassement = selected.length + DialogGestionMatchD.this.tableau3.getRowCount() - 9;
                        max = selected.length - depassement;
                    }
                    for (int i = 0; i < max; ++i) {
                        Object[] infos = ((TableModel)DialogGestionMatchD.this.tableau2.getModel()).getInfoAt(selected[i]);
                        int id = Integer.parseInt(infos[0] + "");
                        String prenom = String.valueOf(infos[1]);
                        String nom = String.valueOf(infos[2]);
                        String cat = String.valueOf(infos[3]).toUpperCase();
                        String pays = String.valueOf(infos[5]);
                        JLabel icon = new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(Main.getPaysIndex(pays)) + ".png")));
                        ((TableModel)DialogGestionMatchD.this.tableau3.getModel()).addRow(new Object[]{id, prenom, nom, cat, icon, pays});
                        if (DialogGestionMatchD.this.tableau2.getRowCount() == 1) {
                            ((TableModel)DialogGestionMatchD.this.tableau2.getModel()).setDataNull();
                        } else {
                            ((TableModel)DialogGestionMatchD.this.tableau2.getModel()).removeRow(selected[i]);
                        }
                        int j = i;
                        while (j < selected.length) {
                            int[] arrn = selected;
                            int n = j++;
                            arrn[n] = arrn[n] - 1;
                        }
                        if (DialogGestionMatchD.this.tableau3.getRowCount() != 9) continue;
                        DialogGestionMatchD.this.arbitreSelect.setEnabled(false);
                        DialogGestionMatchD.this.arbitrefiletcombo.setEnabled(true);
                        DialogGestionMatchD.this.chargeArbitreFilet(DialogGestionMatchD.this.arbitrefiletcombo);
                    }
                }
            }
        });
        this.arbitreDeselect.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (DialogGestionMatchD.this.tableau3.getRowCount() == 9) {
                    DialogGestionMatchD.this.arbitreSelect.setEnabled(true);
                    DialogGestionMatchD.this.arbitrefiletcombo.setEnabled(false);
                }
                if (DialogGestionMatchD.this.tableau3.getSelectedRows().length > 0) {
                    int[] selected = DialogGestionMatchD.this.tableau3.getSelectedRows();
                    for (int i = 0; i < selected.length; ++i) {
                        Object[] infos = ((TableModel)DialogGestionMatchD.this.tableau3.getModel()).getInfoAt(selected[i]);
                        int id = Integer.parseInt(infos[0] + "");
                        String prenom = String.valueOf(infos[1]);
                        String nom = String.valueOf(infos[2]);
                        String cat = String.valueOf(infos[3]).toUpperCase();
                        String pays = String.valueOf(infos[5]);
                        JLabel icon = new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(Main.getPaysIndex(pays)) + ".png")));
                        ((TableModel)DialogGestionMatchD.this.tableau2.getModel()).addRow(new Object[]{id, prenom, nom, cat, icon, pays});
                        if (DialogGestionMatchD.this.tableau3.getRowCount() == 1) {
                            ((TableModel)DialogGestionMatchD.this.tableau3.getModel()).setDataNull();
                        } else {
                            ((TableModel)DialogGestionMatchD.this.tableau3.getModel()).removeRow(selected[i]);
                        }
                        int j = i;
                        while (j < selected.length) {
                            int[] arrn = selected;
                            int n = j++;
                            arrn[n] = arrn[n] - 1;
                        }
                    }
                }
            }
        });
        JXPanel leftopts = new JXPanel(260, 37, BorderFactory.createEtchedBorder()).add(this.arbitreSelect);
        JXPanel rightopts = new JXPanel(260, 37, BorderFactory.createEtchedBorder()).add(this.arbitreDeselect);
        JXPanel optionss = new JXPanel(new FlowLayout()).add(leftopts, new JLabel(""), rightopts);
        this.arbitrefiletlab.setFont(new Font("Arial", 1, 12));
        this.arbitrefiletcombo.setPreferredSize(new Dimension(100, 20));
        JXPanel arbitrefiletpane = new JXPanel(200, 35, Color.white, BorderFactory.createEtchedBorder()).add((JComponent)this.arbitrefiletlab, this.arbitrefiletcombo);
        EnsembleArbitres.chargeInComboBox(this.arbitrefiletcombo);
        this.arbitrefiletcombo.setEnabled(false);
        JPanel south = new JPanel(new BorderLayout());
        south.add((Component)optionss, "North");
        south.add((Component)arbitrefiletpane, "South");
        this.matchpane2.add((JComponent)south, "South");
    }

    public void chargeArbitreFilet(JComboBox combo) {
        combo.removeAllItems();
        for (int i = 0; i < this.tableau3.getRowCount(); ++i) {
            Object[] infos = ((TableModel)this.tableau3.getModel()).getInfoAt(i);
            int id = Integer.parseInt(infos[0] + "");
            Arbitre a = CalendrierMain.arbitres.getArbitreById(id);
            combo.addItem(a);
        }
    }

    public void initDialog() {
        JXPanel panIcon = new JXPanel(new BorderLayout(), Color.white).add((JComponent)this.logo_bt, "North");
        JXPanel content = new JXPanel(new BorderLayout(), Color.white).add((JComponent)panIcon, "North");
        this.matchpane = new JXPanel(new BorderLayout(), 540, 320, BorderFactory.createEtchedBorder());
        this.tableau = new JTable(new TableModel(new Object[0][], new String[]{"Id", "Pr\u00e9nom", "Nom", " ", "Pays"}));
        CalendrierMain.joueurs.toTablesMatchD(this.tableau, this.iargs);
        this.tableau.setAutoCreateRowSorter(true);
        this.tableau.setRowHeight(24);
        this.tableau.setSelectionMode(0);
        this.tableau.getColumn("Id").setMaxWidth(25);
        this.tableau.getColumn("Id").setMinWidth(25);
        this.tableau.getColumn(" ").setMinWidth(25);
        this.tableau.getColumn(" ").setMaxWidth(25);
        this.tableau.setDefaultRenderer(JLabel.class, new TableComponent());
        this.jsp = new JScrollPane(this.tableau);
        this.jsp.setPreferredSize(new Dimension(290, 200));
        this.matchpane.add((JComponent)this.jsp, "West");
        this.tableau = new JTable(new TableModel(new Object[0][], new String[]{"Id", "Pr\u00e9nom", "Nom", " ", "Pays"}));
        CalendrierMain.joueurs.toTablesMatchD(this.tableau, this.iargs);
        this.tableau.setAutoCreateRowSorter(true);
        this.tableau.setRowHeight(24);
        this.tableau.setSelectionMode(0);
        this.tableau.getColumn("Id").setMaxWidth(25);
        this.tableau.getColumn("Id").setMinWidth(25);
        this.tableau.getColumn(" ").setMinWidth(25);
        this.tableau.getColumn(" ").setMaxWidth(25);
        this.tableau.setDefaultRenderer(JLabel.class, new TableComponent());
        this.jsp = new JScrollPane(this.tableau);
        this.jsp.setPreferredSize(new Dimension(290, 200));
        this.matchpane.add((JComponent)this.jsp, "West");
        JLabel title = new JLabel("<html><b>Gestion joueurs et arbitre de chaise</b><html>");
        title.setHorizontalAlignment(0);
        JXPanel j1pane = new JXPanel(new BorderLayout(), 230, 35, Color.white, BorderFactory.createEtchedBorder());
        j1pane.add((JComponent)this.j1lab, "West");
        j1pane.add((JComponent)this.j1labF, "Center");
        j1pane.add((JComponent)this.remove1, "East");
        JXPanel j2pane = new JXPanel(new BorderLayout(), 230, 35, Color.white, BorderFactory.createEtchedBorder());
        j2pane.add((JComponent)this.j2lab, "West");
        j2pane.add((JComponent)this.j2labF, "Center");
        j2pane.add((JComponent)this.remove2, "East");
        JXPanel j3pane = new JXPanel(new BorderLayout(), 230, 35, Color.white, BorderFactory.createEtchedBorder());
        j3pane.add((JComponent)this.j3lab, "West");
        j3pane.add((JComponent)this.j3labF, "Center");
        j3pane.add((JComponent)this.remove3, "East");
        JXPanel j4pane = new JXPanel(new BorderLayout(), 230, 35, Color.white, BorderFactory.createEtchedBorder());
        j4pane.add((JComponent)this.j4lab, "West");
        j4pane.add((JComponent)this.j4labF, "Center");
        j4pane.add((JComponent)this.remove4, "East");
        this.arbitrechaiselab.setFont(new Font("Arial", 0, 9));
        this.arbitrechaisecombo.setPreferredSize(new Dimension(120, 20));
        JXPanel arbitrechaisepane = new JXPanel(230, 35, Color.white, BorderFactory.createEtchedBorder()).add((JComponent)this.arbitrechaiselab, this.arbitrechaisecombo);
        EnsembleArbitres.chargeInComboBox(this.arbitrechaisecombo);
        this.arbitrechaisecombo.setEnabled(false);
        this.remove1.setVisible(false);
        this.remove2.setVisible(false);
        this.remove3.setVisible(false);
        this.remove4.setVisible(false);
        this.remove1.addActionListener(new Remove1Listener());
        this.remove2.addActionListener(new Remove2Listener());
        this.remove3.addActionListener(new Remove3Listener());
        this.remove4.addActionListener(new Remove4Listener());
        this.remove1.setPreferredSize(new Dimension(32, 25));
        this.remove2.setPreferredSize(new Dimension(32, 25));
        this.remove3.setPreferredSize(new Dimension(32, 25));
        this.remove4.setPreferredSize(new Dimension(32, 25));
        JXPanel nouveaumatch = new JXPanel(240, 220).add(title, new JXPanel(200, 21), j1pane, j2pane, j3pane, j4pane, arbitrechaisepane);
        this.matchpane.add((JComponent)nouveaumatch, "East");
        JXPanel leftopt = new JXPanel(290, 37, BorderFactory.createEtchedBorder()).add(this.joueur1, this.joueur2, this.joueur3, this.joueur4);
        JXPanel rightopt = new JXPanel(230, 37, BorderFactory.createEtchedBorder()).add(this.recommencer);
        JXPanel options = new JXPanel(new FlowLayout()).add(leftopt, new JLabel(""), rightopt);
        this.matchpane.add((JComponent)options, "South");
        this.recommencer.addActionListener(new ResetListener());
        this.joueur1.addActionListener(new J1Listener());
        this.joueur2.addActionListener(new J2Listener());
        this.joueur3.addActionListener(new J3Listener());
        this.joueur4.addActionListener(new J4Listener());
        this.contentPaneDialog = new JXPanel(Color.white, BorderFactory.createEtchedBorder()).add((JComponent)new JXPanel(200, 11, Color.white), this.matchpane);
        content.add((JComponent)this.contentPaneDialog, "Center");
        JXPanel control = new JXPanel().add(this.precedent, this.valider, this.suivant);
        this.precedent.setEnabled(false);
        this.valider.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String log = "ok";
                if (DialogGestionMatchD.this.ramasseur1.isEnabled() || DialogGestionMatchD.this.ramasseur2.isEnabled()) {
                    log = "Veuillez choisir 2 \u00e9quipes de ramasseurs";
                }
                if (DialogGestionMatchD.this.tableau3.getRowCount() < 9) {
                    log = "Veuillez choisir 9 arbitres de cat\u00e9gorie JAT2";
                }
                if (DialogGestionMatchD.this.arbitrechaisecombo.getSelectedItem() == null) {
                    log = "Aucun arbitre de chaise n'est disponible";
                }
                if (!DialogGestionMatchD.this.arbitrechaisecombo.isEnabled()) {
                    log = "Veuillez choisir 4 joueurs";
                }
                if (!log.equals("ok")) {
                    JOptionPane jop = new JOptionPane();
                    JOptionPane.showMessageDialog(null, log, "Erreur lors de l'ajout d'un match", 0);
                } else {
                    String idm = "" + DialogGestionMatchD.this.iargs.getId() + ",";
                    String j1n = "" + DialogGestionMatchD.this.j1id + ",";
                    String j2n = "" + DialogGestionMatchD.this.j2id + ",";
                    String j3n = "" + DialogGestionMatchD.this.j3id + ",";
                    String j4n = "" + DialogGestionMatchD.this.j4id + ",";
                    String creneaun = "'" + DialogGestionMatchD.this.iargs.getHeureString() + "',";
                    String courtn = "" + DialogGestionMatchD.this.iargs.getCourt() + ",";
                    String finalen = DialogGestionMatchD.this.iargs.getFinale() ? "1," : "0,";
                    String idarbitrechaisen = "" + ((Arbitre)DialogGestionMatchD.this.arbitrechaisecombo.getSelectedItem()).getNumero() + ",";
                    String journ = "" + DialogGestionMatchD.this.iargs.getJour() + ",";
                    String ramasseurn1 = "" + DialogGestionMatchD.this.eq1id + ",";
                    String ramasseurn2 = "" + DialogGestionMatchD.this.eq2id + ",";
                    Arbitre filet = (Arbitre)DialogGestionMatchD.this.arbitrefiletcombo.getSelectedItem();
                    String arbitresn = "" + filet.getNumero() + ":";
                    for (int i = 0; i < 9; ++i) {
                        Object[] infos = ((TableModel)DialogGestionMatchD.this.tableau3.getModel()).getInfoAt(i);
                        int id = Integer.parseInt(infos[0] + "");
                        if (id == filet.getNumero()) continue;
                        arbitresn = arbitresn + id + ":";
                    }
                    String req = "";
                    req = DialogGestionMatchD.this.iargs.getInit() ? "insert into dmatchs values(" + idm + j1n + j2n + j3n + j4n + creneaun + courtn + finalen + journ + idarbitrechaisen + ramasseurn1 + ramasseurn2 + "'" + arbitresn + "')" : "update dmatchs set joueur1=" + j1n + " joueur2=" + j2n + " joueur3=" + j3n + "joueur4=" + j4n + "creneau=" + creneaun + "court=" + courtn + " finale=" + finalen + " arbitrechaise=" + idarbitrechaisen + "" + "jour=" + journ + " ramasseurs1=" + ramasseurn1 + " ramasseurs2=" + ramasseurn2 + " arbitres='" + arbitresn + "' where id=" + DialogGestionMatchD.this.iargs.getId();
                    SQL.update(req);
                    DialogGestionMatchD.this.win.refreshPlanningFromBD();
                    DialogGestionMatchD.this.setVisible(false);
                }
            }
        });
        this.suivant.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (DialogGestionMatchD.this.currentPage <= DialogGestionMatchD.this.pages.size() - 2) {
                    ++DialogGestionMatchD.this.currentPage;
                    for (int i = 0; i < DialogGestionMatchD.this.pages.size(); ++i) {
                        if (i == DialogGestionMatchD.this.currentPage) {
                            DialogGestionMatchD.this.pages.get(i).setVisible(true);
                            continue;
                        }
                        DialogGestionMatchD.this.pages.get(i).setVisible(false);
                    }
                }
                if (DialogGestionMatchD.this.currentPage > DialogGestionMatchD.this.pages.size() - 2) {
                    DialogGestionMatchD.this.suivant.setEnabled(false);
                    DialogGestionMatchD.this.precedent.setEnabled(true);
                } else {
                    DialogGestionMatchD.this.suivant.setEnabled(true);
                    DialogGestionMatchD.this.precedent.setEnabled(true);
                }
            }
        });
        this.precedent.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (DialogGestionMatchD.this.currentPage > 0) {
                    --DialogGestionMatchD.this.currentPage;
                    for (int i = 0; i < DialogGestionMatchD.this.pages.size(); ++i) {
                        if (i == DialogGestionMatchD.this.currentPage) {
                            DialogGestionMatchD.this.pages.get(i).setVisible(true);
                            continue;
                        }
                        DialogGestionMatchD.this.pages.get(i).setVisible(false);
                    }
                }
                if (DialogGestionMatchD.this.currentPage > 0) {
                    DialogGestionMatchD.this.suivant.setEnabled(true);
                    DialogGestionMatchD.this.precedent.setEnabled(true);
                } else {
                    DialogGestionMatchD.this.suivant.setEnabled(true);
                    DialogGestionMatchD.this.precedent.setEnabled(false);
                }
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

    public void chargeArbitreChaise() {
        Vector<String> paysJoueurs = new Vector<String>();
        paysJoueurs.add(Main.nationaliteCode.get(Main.getPaysIndex(this.j1pays)));
        paysJoueurs.add(Main.nationaliteCode.get(Main.getPaysIndex(this.j2pays)));
        paysJoueurs.add(Main.nationaliteCode.get(Main.getPaysIndex(this.j3pays)));
        paysJoueurs.add(Main.nationaliteCode.get(Main.getPaysIndex(this.j4pays)));
        EnsembleArbitres.chargeInComboBoxWithout(this.arbitrechaisecombo, paysJoueurs, this.iargs);
    }

    public class E2Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = DialogGestionMatchD.this.tableau4.getSelectedRow();
            Object[] infos = ((TableModel)DialogGestionMatchD.this.tableau4.getModel()).getInfoAt(selected);
            DialogGestionMatchD.this.eq2equipe = String.valueOf(infos[1]);
            DialogGestionMatchD.this.eq2id = Integer.parseInt(infos[0] + "");
            DialogGestionMatchD.this.eq2lab.setText("  Equipe 2 :  ");
            DialogGestionMatchD.this.eq2labF.setText(DialogGestionMatchD.this.eq2equipe.toUpperCase());
            DialogGestionMatchD.this.eqremove2.setVisible(true);
            DialogGestionMatchD.this.ramasseur2.setEnabled(false);
            if (DialogGestionMatchD.this.tableau4.getRowCount() == 1) {
                ((TableModel)DialogGestionMatchD.this.tableau4.getModel()).deleteAll();
                DialogGestionMatchD.this.precedent.doClick();
                DialogGestionMatchD.this.suivant.doClick();
            } else {
                ((TableModel)DialogGestionMatchD.this.tableau4.getModel()).removeRow(selected);
            }
        }
    }

    public class E1Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = DialogGestionMatchD.this.tableau4.getSelectedRow();
            Object[] infos = ((TableModel)DialogGestionMatchD.this.tableau4.getModel()).getInfoAt(selected);
            DialogGestionMatchD.this.eq1equipe = String.valueOf(infos[1]);
            DialogGestionMatchD.this.eq1id = Integer.parseInt(infos[0] + "");
            DialogGestionMatchD.this.eq1lab.setText("  Equipe 1 :  ");
            DialogGestionMatchD.this.eq1labF.setText(DialogGestionMatchD.this.eq1equipe.toUpperCase());
            DialogGestionMatchD.this.eqremove1.setVisible(true);
            DialogGestionMatchD.this.ramasseur1.setEnabled(false);
            if (DialogGestionMatchD.this.tableau4.getRowCount() == 1) {
                ((TableModel)DialogGestionMatchD.this.tableau4.getModel()).deleteAll();
                DialogGestionMatchD.this.precedent.doClick();
                DialogGestionMatchD.this.suivant.doClick();
            } else {
                ((TableModel)DialogGestionMatchD.this.tableau4.getModel()).removeRow(selected);
            }
        }
    }

    public class EqRemove2Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((TableModel)DialogGestionMatchD.this.tableau4.getModel()).addRow(new Object[]{DialogGestionMatchD.this.eq2id, DialogGestionMatchD.this.eq2equipe});
            DialogGestionMatchD.this.eq2lab.setText("");
            DialogGestionMatchD.this.eq2labF.setText("");
            DialogGestionMatchD.this.eqremove2.setVisible(false);
            DialogGestionMatchD.this.ramasseur2.setEnabled(true);
        }
    }

    public class EqRemove1Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((TableModel)DialogGestionMatchD.this.tableau4.getModel()).addRow(new Object[]{DialogGestionMatchD.this.eq1id, DialogGestionMatchD.this.eq1equipe});
            DialogGestionMatchD.this.eq1lab.setText("");
            DialogGestionMatchD.this.eq1labF.setText("");
            DialogGestionMatchD.this.eqremove1.setVisible(false);
            DialogGestionMatchD.this.ramasseur1.setEnabled(true);
        }
    }

    class ConfirmeListener
    implements ActionListener {
        ConfirmeListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    public class Remove4Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((TableModel)DialogGestionMatchD.this.tableau.getModel()).addRow(new Object[]{DialogGestionMatchD.this.j4id, DialogGestionMatchD.this.j4prenom, DialogGestionMatchD.this.j4nom, DialogGestionMatchD.this.j4icon, DialogGestionMatchD.this.j4pays});
            DialogGestionMatchD.this.j4lab.setText("");
            DialogGestionMatchD.this.j4labF.setIcon(null);
            DialogGestionMatchD.this.j4labF.setText("");
            DialogGestionMatchD.this.remove4.setVisible(false);
            DialogGestionMatchD.this.joueur4.setEnabled(true);
            DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(false);
        }
    }

    public class Remove3Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((TableModel)DialogGestionMatchD.this.tableau.getModel()).addRow(new Object[]{DialogGestionMatchD.this.j3id, DialogGestionMatchD.this.j3prenom, DialogGestionMatchD.this.j3nom, DialogGestionMatchD.this.j3icon, DialogGestionMatchD.this.j3pays});
            DialogGestionMatchD.this.j3lab.setText("");
            DialogGestionMatchD.this.j3labF.setIcon(null);
            DialogGestionMatchD.this.j3labF.setText("");
            DialogGestionMatchD.this.remove3.setVisible(false);
            DialogGestionMatchD.this.joueur3.setEnabled(true);
            DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(false);
        }
    }

    public class Remove2Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((TableModel)DialogGestionMatchD.this.tableau.getModel()).addRow(new Object[]{DialogGestionMatchD.this.j2id, DialogGestionMatchD.this.j2prenom, DialogGestionMatchD.this.j2nom, DialogGestionMatchD.this.j2icon, DialogGestionMatchD.this.j2pays});
            DialogGestionMatchD.this.j2lab.setText("");
            DialogGestionMatchD.this.j2labF.setIcon(null);
            DialogGestionMatchD.this.j2labF.setText("");
            DialogGestionMatchD.this.remove2.setVisible(false);
            DialogGestionMatchD.this.joueur2.setEnabled(true);
            DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(false);
        }
    }

    public class Remove1Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((TableModel)DialogGestionMatchD.this.tableau.getModel()).addRow(new Object[]{DialogGestionMatchD.this.j1id, DialogGestionMatchD.this.j1prenom, DialogGestionMatchD.this.j1nom, DialogGestionMatchD.this.j1icon, DialogGestionMatchD.this.j1pays});
            DialogGestionMatchD.this.j1lab.setText("");
            DialogGestionMatchD.this.j1labF.setIcon(null);
            DialogGestionMatchD.this.j1labF.setText("");
            DialogGestionMatchD.this.remove1.setVisible(false);
            DialogGestionMatchD.this.joueur1.setEnabled(true);
            DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(false);
        }
    }

    public class J4Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = DialogGestionMatchD.this.tableau.getSelectedRow();
            Object[] infos = ((TableModel)DialogGestionMatchD.this.tableau.getModel()).getInfoAt(selected);
            int id = Integer.parseInt(infos[0] + "");
            String prenom = String.valueOf(infos[1]);
            String nom = String.valueOf(infos[2]);
            String pays = String.valueOf(infos[4]);
            JLabel icon = new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(Main.getPaysIndex(pays)) + ".png")));
            DialogGestionMatchD.this.j4id = id;
            DialogGestionMatchD.this.j4prenom = prenom;
            DialogGestionMatchD.this.j4nom = nom;
            DialogGestionMatchD.this.j4pays = pays;
            DialogGestionMatchD.this.j4icon = icon;
            ((TableModel)DialogGestionMatchD.this.tableau.getModel()).removeRow(selected);
            DialogGestionMatchD.this.j4lab.setText("  J4 :  ");
            DialogGestionMatchD.this.j4labF.setIcon(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(Main.getPaysIndex(pays)) + ".png")));
            DialogGestionMatchD.this.j4labF.setText(prenom + " " + nom);
            DialogGestionMatchD.this.remove4.setVisible(true);
            DialogGestionMatchD.this.joueur4.setEnabled(false);
            if (!(DialogGestionMatchD.this.joueur1.isEnabled() || DialogGestionMatchD.this.joueur2.isEnabled() || DialogGestionMatchD.this.joueur3.isEnabled())) {
                DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(true);
                DialogGestionMatchD.this.chargeArbitreChaise();
            } else {
                DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(false);
            }
        }
    }

    public class J3Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = DialogGestionMatchD.this.tableau.getSelectedRow();
            Object[] infos = ((TableModel)DialogGestionMatchD.this.tableau.getModel()).getInfoAt(selected);
            int id = Integer.parseInt(infos[0] + "");
            String prenom = String.valueOf(infos[1]);
            String nom = String.valueOf(infos[2]);
            String pays = String.valueOf(infos[4]);
            JLabel icon = new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(Main.getPaysIndex(pays)) + ".png")));
            DialogGestionMatchD.this.j3id = id;
            DialogGestionMatchD.this.j3prenom = prenom;
            DialogGestionMatchD.this.j3nom = nom;
            DialogGestionMatchD.this.j3pays = pays;
            DialogGestionMatchD.this.j3icon = icon;
            ((TableModel)DialogGestionMatchD.this.tableau.getModel()).removeRow(selected);
            DialogGestionMatchD.this.j3lab.setText("  J3 :  ");
            DialogGestionMatchD.this.j3labF.setIcon(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(Main.getPaysIndex(pays)) + ".png")));
            DialogGestionMatchD.this.j3labF.setText(prenom + " " + nom);
            DialogGestionMatchD.this.remove3.setVisible(true);
            DialogGestionMatchD.this.joueur3.setEnabled(false);
            if (!(DialogGestionMatchD.this.joueur1.isEnabled() || DialogGestionMatchD.this.joueur2.isEnabled() || DialogGestionMatchD.this.joueur4.isEnabled())) {
                DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(true);
                DialogGestionMatchD.this.chargeArbitreChaise();
            } else {
                DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(false);
            }
        }
    }

    public class J2Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = DialogGestionMatchD.this.tableau.getSelectedRow();
            Object[] infos = ((TableModel)DialogGestionMatchD.this.tableau.getModel()).getInfoAt(selected);
            int id = Integer.parseInt(infos[0] + "");
            String prenom = String.valueOf(infos[1]);
            String nom = String.valueOf(infos[2]);
            String pays = String.valueOf(infos[4]);
            JLabel icon = new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(Main.getPaysIndex(pays)) + ".png")));
            DialogGestionMatchD.this.j2id = id;
            DialogGestionMatchD.this.j2prenom = prenom;
            DialogGestionMatchD.this.j2nom = nom;
            DialogGestionMatchD.this.j2pays = pays;
            DialogGestionMatchD.this.j2icon = icon;
            ((TableModel)DialogGestionMatchD.this.tableau.getModel()).removeRow(selected);
            DialogGestionMatchD.this.j2lab.setText("  J2 :  ");
            DialogGestionMatchD.this.j2labF.setIcon(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(Main.getPaysIndex(pays)) + ".png")));
            DialogGestionMatchD.this.j2labF.setText(prenom + " " + nom);
            DialogGestionMatchD.this.remove2.setVisible(true);
            DialogGestionMatchD.this.joueur2.setEnabled(false);
            if (!(DialogGestionMatchD.this.joueur1.isEnabled() || DialogGestionMatchD.this.joueur3.isEnabled() || DialogGestionMatchD.this.joueur4.isEnabled())) {
                DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(true);
                DialogGestionMatchD.this.chargeArbitreChaise();
            } else {
                DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(false);
            }
        }
    }

    public class J1Listener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = DialogGestionMatchD.this.tableau.getSelectedRow();
            Object[] infos = ((TableModel)DialogGestionMatchD.this.tableau.getModel()).getInfoAt(selected);
            int id = Integer.parseInt(infos[0] + "");
            String prenom = String.valueOf(infos[1]);
            String nom = String.valueOf(infos[2]);
            String pays = String.valueOf(infos[4]);
            JLabel icon = new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(Main.getPaysIndex(pays)) + ".png")));
            DialogGestionMatchD.this.j1id = id;
            DialogGestionMatchD.this.j1prenom = prenom;
            DialogGestionMatchD.this.j1nom = nom;
            DialogGestionMatchD.this.j1pays = pays;
            DialogGestionMatchD.this.j1icon = icon;
            ((TableModel)DialogGestionMatchD.this.tableau.getModel()).removeRow(selected);
            DialogGestionMatchD.this.j1lab.setText("  J1 :  ");
            DialogGestionMatchD.this.j1labF.setIcon(new ImageIcon(this.getClass().getResource("/flags/" + Main.nationaliteCode.get(Main.getPaysIndex(pays)) + ".png")));
            DialogGestionMatchD.this.j1labF.setText(prenom + " " + nom);
            DialogGestionMatchD.this.remove1.setVisible(true);
            DialogGestionMatchD.this.joueur1.setEnabled(false);
            if (!(DialogGestionMatchD.this.joueur2.isEnabled() || DialogGestionMatchD.this.joueur3.isEnabled() || DialogGestionMatchD.this.joueur4.isEnabled())) {
                DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(true);
                DialogGestionMatchD.this.chargeArbitreChaise();
            } else {
                DialogGestionMatchD.this.arbitrechaisecombo.setEnabled(false);
            }
        }
    }

    public class EResetListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!DialogGestionMatchD.this.ramasseur1.isEnabled()) {
                DialogGestionMatchD.this.eqremove1.doClick();
            }
            if (!DialogGestionMatchD.this.ramasseur2.isEnabled()) {
                DialogGestionMatchD.this.eqremove2.doClick();
            }
        }
    }

    public class ResetListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (DialogGestionMatchD.this.arbitrechaisecombo.getSelectedItem() != null) {
                DialogGestionMatchD.this.arbitrechaisecombo.setSelectedIndex(0);
            }
            if (!DialogGestionMatchD.this.joueur1.isEnabled()) {
                DialogGestionMatchD.this.remove1.doClick();
            }
            if (!DialogGestionMatchD.this.joueur2.isEnabled()) {
                DialogGestionMatchD.this.remove2.doClick();
            }
            if (!DialogGestionMatchD.this.joueur3.isEnabled()) {
                DialogGestionMatchD.this.remove3.doClick();
            }
            if (!DialogGestionMatchD.this.joueur4.isEnabled()) {
                DialogGestionMatchD.this.remove4.doClick();
            }
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

        public void setDataNull() {
            this.data = new Object[0][];
            DialogGestionMatchD.this.suivant.doClick();
            DialogGestionMatchD.this.precedent.doClick();
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

