/*
 * Decompiled with CFR 0_110.
 */
package calendrier;

import calendrier.CalendrierCell;
import calendrier.CalendrierInfos;
import calendrier.CalendrierTableModel;
import calendrier.Main;
import dialog.DialogConnexion;
import dialog.DialogGestionMatchD;
import dialog.DialogGestionMatchS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import util.Arbitre;
import util.CMenuBar;
import util.EnsembleArbitres;
import util.EnsembleEquipeRamasseurs;
import util.EnsembleJoueurs;
import util.EnsembleRamasseurs;
import util.EquipeRamasseurs;
import util.JXPanel;
import util.Joueur;
import util.SQL;

public class CalendrierMain
extends JFrame {
    CalendrierMain win;
    JPanel conteneur;
    public static JTabbedPane joursPane = new JTabbedPane();
    public static Vector<List<CalendrierInfos>> joursInfos = new Vector();
    public static Vector<CalendrierInfos> INFOS = new Vector();
    public static Vector<JTable> joursTables = new Vector();
    public static EnsembleJoueurs joueurs = new EnsembleJoueurs();
    public static EnsembleArbitres arbitres = new EnsembleArbitres();
    public static EnsembleRamasseurs ramasseurs = new EnsembleRamasseurs();
    public static EnsembleEquipeRamasseurs equiperamasseurs = new EnsembleEquipeRamasseurs();
    JPanel options;
    JButton modifier;
    JButton supprimer;
    public JButton precedent;
    public JButton suivant;
    public static JLabel infosplanningtext = new JLabel();
    private JLabel logo_bt;

    public CalendrierMain() {
        super("Calendrier des matchs");
        this.win = this;
        this.conteneur = new JPanel(new BorderLayout());
        this.options = new JPanel(new BorderLayout());
        this.modifier = new JButton("Modifier");
        this.supprimer = new JButton("Supprimer");
        this.precedent = new JButton(new ImageIcon(this.getClass().getResource("/imgs/prev.png")));
        this.suivant = new JButton(new ImageIcon(this.getClass().getResource("/imgs/suiv.png")));
        this.logo_bt = new JLabel(new ImageIcon(this.getClass().getResource("/imgs/gptltennisc.png")));
        this.setDefaultCloseOperation(3);
        this.setSize(900, 650);
        this.setLocationRelativeTo(null);
        this.setContentPane(this.conteneur);
        this.setJMenuBar(new CMenuBar(this));
        try {
            this.setIconImage(ImageIO.read(this.getClass().getResource("/imgs/logogptl.png")));
        }
        catch (IOException es) {
            // empty catch block
        }
        EnsembleJoueurs.chargeIn();
        EnsembleArbitres.chargeIn();
        EnsembleRamasseurs.chargeIn();
        EnsembleEquipeRamasseurs.chargeIn();
        DialogConnexion connectCD = new DialogConnexion(null, "Connexion planning GPTL", true);
        connectCD.showConnexionDialog();
        this.add((Component)this.logo_bt, "North");
        this.add((Component)this.suivant, "East");
        this.add((Component)this.precedent, "West");
        this.add((Component)joursPane, "Center");
        this.add((Component)this.options, "South");
        this.initCalendrier();
        JXPanel infosplanning = new JXPanel(Color.white).add(CalendrierMain.refreshInfosPlanning());
        JXPanel operationsplanning = new JXPanel().add((JComponent)this.modifier, this.supprimer);
        this.modifier.addActionListener(new ModifieListener());
        this.supprimer.addActionListener(new SupprimeListener());
        this.options.add((Component)infosplanning, "North");
        this.options.add((Component)operationsplanning, "South");
        this.refreshPlanningFromBD();
        this.suivant.addActionListener(new SuivantListener());
        this.precedent.addActionListener(new PrecedentListener());
    }

    public void initCalendrier() {
        String[] jours = new String[]{"Samedi", "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
        String[] heures = new String[]{"8", "11", "15", "18", "21"};
        boolean finale = false;
        boolean stopSimpleFinale = false;
        boolean stopDoubleFinale = false;
        for (int i = 0; i < jours.length; ++i) {
            CalendrierInfos ci;
            int j;
            Vector<List<CalendrierInfos>> tabinfos = new Vector<List<CalendrierInfos>>();
            ArrayList<CalendrierInfos> infos = new ArrayList<CalendrierInfos>();
            for (j = 0; j < heures.length; ++j) {
                ci = null;
                if (i == 8 && heures[j].equals("15")) {
                    ci = new CalendrierInfos(heures[j], true, 1, i, 4, this);
                } else if (i == 7 && heures[j].equals("18")) {
                    ci = new CalendrierInfos(heures[j], true, 0, i, 2, this);
                    stopSimpleFinale = true;
                } else {
                    ci = !stopSimpleFinale ? new CalendrierInfos(heures[j], false, 0, i, 2, this) : new CalendrierInfos(heures[j], false, 3, i, 2, this);
                }
                infos.add(ci);
                INFOS.add(ci);
            }
            tabinfos.add(infos);
            infos = new ArrayList();
            for (j = 0; j < heures.length; ++j) {
                ci = null;
                if (i == 8 && heures[j].equals("15")) {
                    ci = new CalendrierInfos(heures[j], false, 3, i, 4, this);
                    stopDoubleFinale = true;
                } else {
                    ci = !stopDoubleFinale ? new CalendrierInfos(heures[j], false, 1, i, 4, this) : new CalendrierInfos(heures[j], false, 3, i, 4, this);
                }
                infos.add(ci);
                INFOS.add(ci);
            }
            tabinfos.add(infos);
            JTable table = new JTable(new CalendrierTableModel(tabinfos));
            table.setDefaultRenderer(CalendrierInfos.class, new CalendrierCell());
            table.setDefaultEditor(CalendrierInfos.class, new CalendrierCell());
            table.setRowHeight(70);
            table.setSelectionMode(0);
            table.setRowSelectionAllowed(true);
            table.setColumnSelectionAllowed(true);
            joursInfos.add(infos);
            joursTables.add(table);
            joursPane.add((Component)new JScrollPane(table), jours[i]);
        }
    }

    public void refreshPlanningFromBD() {
        try {
            int j2n;
            int idm;
            int j1n;
            PreparedStatement requete = Main.connect.prepareStatement("select * from smatchs", 1004, 1007);
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                idm = Integer.parseInt(resultat.getObject("ID") + "");
                j1n = Integer.parseInt(resultat.getObject("JOUEUR1") + "");
                j2n = Integer.parseInt(resultat.getObject("JOUEUR2") + "");
                int finaleInt = Integer.parseInt(resultat.getObject("FINALE") + "");
                boolean finale = false;
                if (finaleInt == 1) {
                    finale = true;
                }
                String creneau = (String)resultat.getObject("CRENEAU");
                int court = Integer.parseInt(resultat.getObject("COURT") + "");
                int jour = Integer.parseInt(resultat.getObject("JOUR") + "");
                int arbitrechaise = Integer.parseInt(resultat.getObject("ARBITRECHAISE") + "");
                int ramasseurs1 = Integer.parseInt(resultat.getObject("RAMASSEURS1") + "");
                int ramasseurs2 = Integer.parseInt(resultat.getObject("RAMASSEURS2") + "");
                String arbitresbd = (String)resultat.getObject("ARBITRES");
                int nbarbitres = 9;
                Arbitre[] arb = new Arbitre[nbarbitres];
                Vector<String> champ = CalendrierMain.separe_champ(arbitresbd, ":", 3, nbarbitres);
                for (int i = 0; i < nbarbitres; ++i) {
                    arb[i] = arbitres.getArbitreById(Integer.parseInt(champ.get(i)));
                }
                Joueur[] joueursTab = new Joueur[]{joueurs.getJoueurById(j1n), joueurs.getJoueurById(j2n)};
                EquipeRamasseurs[] ramasseursTab = new EquipeRamasseurs[]{EnsembleEquipeRamasseurs.getEquipeRamasseursById(ramasseurs1), EnsembleEquipeRamasseurs.getEquipeRamasseursById(ramasseurs2)};
                for (int i2 = 0; i2 < INFOS.size(); ++i2) {
                    if (INFOS.get(i2).getId() != idm) continue;
                    INFOS.get(i2).setDebutString(creneau);
                    INFOS.get(i2).setInit(false);
                    INFOS.get(i2).setFinale(finale);
                    INFOS.get(i2).setType(2);
                    INFOS.get(i2).setJoueurs(joueursTab);
                    INFOS.get(i2).setCourt(court);
                    INFOS.get(i2).setJour(jour);
                    INFOS.get(i2).setArbitreChaise(arbitres.getArbitreById(arbitrechaise));
                    INFOS.get(i2).setEquipeRamasseurs(ramasseursTab);
                    INFOS.get(i2).setArbitres(arb);
                }
            }
            requete = Main.connect.prepareStatement("select * from dmatchs", 1004, 1007);
            resultat = requete.executeQuery();
            while (resultat.next()) {
                idm = Integer.parseInt(resultat.getObject("ID") + "");
                j1n = Integer.parseInt(resultat.getObject("JOUEUR1") + "");
                j2n = Integer.parseInt(resultat.getObject("JOUEUR2") + "");
                int j3n = Integer.parseInt(resultat.getObject("JOUEUR3") + "");
                int j4n = Integer.parseInt(resultat.getObject("JOUEUR4") + "");
                int finaleInt = Integer.parseInt(resultat.getObject("FINALE") + "");
                boolean finale = false;
                if (finaleInt == 1) {
                    finale = true;
                }
                String creneau = (String)resultat.getObject("CRENEAU");
                int court = Integer.parseInt(resultat.getObject("COURT") + "");
                int jour = Integer.parseInt(resultat.getObject("JOUR") + "");
                int arbitrechaise = Integer.parseInt(resultat.getObject("ARBITRECHAISE") + "");
                int ramasseurs1 = Integer.parseInt(resultat.getObject("RAMASSEURS1") + "");
                int ramasseurs2 = Integer.parseInt(resultat.getObject("RAMASSEURS2") + "");
                String arbitresbd = (String)resultat.getObject("ARBITRES");
                int nbarbitres = 9;
                Arbitre[] arb = new Arbitre[nbarbitres];
                Vector<String> champ = CalendrierMain.separe_champ(arbitresbd, ":", 3, nbarbitres);
                for (int i = 0; i < nbarbitres; ++i) {
                    arb[i] = arbitres.getArbitreById(Integer.parseInt(champ.get(i)));
                }
                Joueur[] joueursTab = new Joueur[]{joueurs.getJoueurById(j1n), joueurs.getJoueurById(j2n), joueurs.getJoueurById(j3n), joueurs.getJoueurById(j4n)};
                EquipeRamasseurs[] ramasseursTab = new EquipeRamasseurs[]{EnsembleEquipeRamasseurs.getEquipeRamasseursById(ramasseurs1), EnsembleEquipeRamasseurs.getEquipeRamasseursById(ramasseurs2)};
                for (int i3 = 0; i3 < INFOS.size(); ++i3) {
                    if (INFOS.get(i3).getId() != idm) continue;
                    INFOS.get(i3).setDebutString(creneau);
                    INFOS.get(i3).setInit(false);
                    INFOS.get(i3).setFinale(finale);
                    INFOS.get(i3).setType(4);
                    INFOS.get(i3).setJoueurs(joueursTab);
                    INFOS.get(i3).setCourt(court);
                    INFOS.get(i3).setJour(jour);
                    INFOS.get(i3).setArbitreChaise(arbitres.getArbitreById(arbitrechaise));
                    INFOS.get(i3).setEquipeRamasseurs(ramasseursTab);
                    INFOS.get(i3).setArbitres(arb);
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(CalendrierMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.suivant.doClick();
        this.precedent.doClick();
        CalendrierMain.refreshInfosPlanning();
    }

    public static Vector<String> separe_champ(String ligne, String delimiteur, int numero, int nbc) {
        Vector<String> champs = new Vector<String>();
        int p2 = 0;
        for (int j = nbc; j > 0; --j) {
            int postinit = p2;
            p2 = ligne.indexOf(delimiteur, postinit);
            String champ = ligne.substring(postinit, p2);
            ++p2;
            champs.add(champ);
        }
        return champs;
    }

    public static String getMatchCount(String type) {
        int nbs = 0;
        int nbd = 0;
        for (int i = 0; i < INFOS.size(); ++i) {
            if (!INFOS.get(i).getInit() && INFOS.get(i).getCourt() == 0) {
                ++nbs;
                continue;
            }
            if (INFOS.get(i).getInit() || INFOS.get(i).getCourt() != 1) continue;
            ++nbd;
        }
        if (nbs > 1 && type.equals("s")) {
            return "" + nbs + " matchs simples";
        }
        if (nbs <= 1 && type.equals("s")) {
            return "" + nbs + " match simple";
        }
        if (nbd > 1 && type.equals("d")) {
            return "" + nbd + " matchs doubles";
        }
        if (nbd <= 1 && type.equals("d")) {
            return "" + nbd + " match double";
        }
        return "aucun match";
    }

    public static JLabel refreshInfosPlanning() {
        infosplanningtext.setText("<html>Le planning pr\u00e9sente <b>" + CalendrierMain.getMatchCount("s") + "</b> et <b>" + CalendrierMain.getMatchCount("d") + "</b>");
        return infosplanningtext;
    }

    public static CalendrierInfos MatchTypeInverseInfo(CalendrierInfos info) {
        int inverseType = info.getCourt() == 0 ? 1 : 0;
        for (int i = 0; i < INFOS.size(); ++i) {
            if (INFOS.get(i).getJour() != info.getJour() || !INFOS.get(i).getHeureString().equals(info.getHeureString()) || INFOS.get(i).getCourt() != inverseType) continue;
            return INFOS.get(i);
        }
        return null;
    }

    public class PrecedentListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CalendrierMain.joursPane.getSelectedIndex() > 0) {
                CalendrierMain.joursPane.setSelectedIndex(CalendrierMain.joursPane.getSelectedIndex() - 1);
            } else {
                CalendrierMain.joursPane.setSelectedIndex(CalendrierMain.joursPane.getTabCount() - 1);
            }
        }
    }

    public class SuivantListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (CalendrierMain.joursPane.getSelectedIndex() < CalendrierMain.joursPane.getTabCount() - 1) {
                CalendrierMain.joursPane.setSelectedIndex(CalendrierMain.joursPane.getSelectedIndex() + 1);
            } else {
                CalendrierMain.joursPane.setSelectedIndex(0);
            }
        }
    }

    public class SupprimeListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane confirm = new JOptionPane();
            if (JOptionPane.showConfirmDialog(null, "\u00cates-vous s\u00fbr de vouloir supprimer cette entr\u00e9e ?", "Supprimer", 0) == 0) {
                int idSelected = CalendrierCell.selected;
                int id2 = 0;
                for (int j = 0; j < CalendrierMain.INFOS.size(); ++j) {
                    if (idSelected != CalendrierMain.INFOS.get(j).getId()) continue;
                    id2 = j;
                }
                CalendrierInfos info = CalendrierMain.INFOS.get(id2);
                if (info.getType().equals("simple")) {
                    SQL.update("delete from smatchs where id=" + idSelected);
                } else if (info.getType().equals("double")) {
                    SQL.update("delete from dmatchs where id=" + idSelected);
                }
                CalendrierMain.refreshInfosPlanning();
                CalendrierCell cell = (CalendrierCell)CalendrierMain.joursTables.get(CalendrierMain.joursPane.getSelectedIndex()).getCellEditor();
                info.reset();
                cell.updateData(info, true, CalendrierMain.joursTables.get(CalendrierMain.joursPane.getSelectedIndex()));
            }
        }
    }

    public class ModifieListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idSelected = CalendrierCell.selected;
            int id2 = 0;
            for (int j = 0; j < CalendrierMain.INFOS.size(); ++j) {
                if (idSelected != CalendrierMain.INFOS.get(j).getId()) continue;
                id2 = j;
            }
            CalendrierInfos info = CalendrierMain.INFOS.get(id2);
            if (info.getType().contains("simple")) {
                DialogGestionMatchS msimple = new DialogGestionMatchS(null, "Gestion match simple", true, CalendrierMain.this.win, info);
                msimple.showGestionDialog();
                CalendrierCell cell = (CalendrierCell)CalendrierMain.joursTables.get(CalendrierMain.joursPane.getSelectedIndex()).getCellEditor();
                cell.updateData(info, true, CalendrierMain.joursTables.get(CalendrierMain.joursPane.getSelectedIndex()));
            } else if (info.getType().contains("double")) {
                DialogGestionMatchD mdouble = new DialogGestionMatchD(null, "Gestion match double", true, CalendrierMain.this.win, info);
                mdouble.showGestionDialog();
                CalendrierCell cell = (CalendrierCell)CalendrierMain.joursTables.get(CalendrierMain.joursPane.getSelectedIndex()).getCellEditor();
                cell.updateData(info, true, CalendrierMain.joursTables.get(CalendrierMain.joursPane.getSelectedIndex()));
            }
        }
    }

}

