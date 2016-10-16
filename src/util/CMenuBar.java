/*
 * Decompiled with CFR 0_110.
 */
package util;

import calendrier.CalendrierMain;
import calendrier.Main;
import dialog.DialogApropos;
import dialog.DialogConnexion;
import dialog.DialogGestionArbitre;
import dialog.DialogGestionEquipeRamasseurs;
import dialog.DialogGestionJoueur;
import dialog.DialogGestionRamasseur;
import entrainement.EntrainementMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class CMenuBar
extends JMenuBar {
    CalendrierMain win;
    public static JMenu fichier = new JMenu("Fichier");
    public static JMenu gptl = new JMenu("GPTL Manager");
    public static JMenu gestion = new JMenu("Gestion");
    public JMenuItem SP = new JMenuItem("SP");
    public static JMenuItem nouveau = new JMenuItem("Nouveau");
    public static JMenuItem ouvrir = new JMenuItem("Ouvrir");
    public static JMenuItem imprimer = new JMenuItem("Imprimer");
    public static JMenuItem enregistrersous = new JMenuItem("Enregistrer sous");
    public static JMenuItem joueurs = new JMenuItem("Joueurs");
    public static JMenuItem arbitres = new JMenuItem("Arbitres");
    public static JMenuItem ramasseurs = new JMenuItem("Ramasseurs");
    public static JMenuItem equiperamasseurs = new JMenuItem("Equipe de ramasseurs");
    public static JMenuItem deconnexion = new JMenuItem("D\u00e9connexion");
    public static JMenuItem entrainements = new JMenuItem("Gestion entrainements");
    public static JMenuItem apropos = new JMenuItem("\u00c0 propos");
    public static JMenuItem quitter = new JMenuItem("Quitter");
    JMenu[] jmt;
    JMenuItem[] jmit0;
    JMenuItem[] jmit1;
    JMenuItem[] jmit2;
    KeyStroke[] kst;
    EntrainementMain em = new EntrainementMain();

    public CMenuBar(CalendrierMain frame) {
        this.win = frame;
        this.initJMenu();
        this.initJMenuItem();
        this.win.setJMenuBar(this);
    }

    public void initJMenu() {
        this.jmt = new JMenu[]{fichier, gestion, gptl};
        for (int i = 0; i < this.jmt.length; ++i) {
            this.add(this.jmt[i]);
            this.jmt[i].setMnemonic(this.jmt[i].getText().charAt(0));
        }
    }

    public void initJMenuItem() {
        this.jmit0 = new JMenuItem[]{deconnexion, imprimer, this.SP, quitter};
        this.kst = new KeyStroke[]{KeyStroke.getKeyStroke(68, 2), KeyStroke.getKeyStroke(80, 2), null, KeyStroke.getKeyStroke(81, 2)};
        this.chargeIn(this.jmit0, 0, this.kst);
        imprimer.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        deconnexion.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.gptl.setVisible(false);
                DialogConnexion connectD = new DialogConnexion(null, "Connexion planning GPTL", true);
                connectD.showConnexionDialog();
            }
        });
        quitter.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        this.jmit1 = new JMenuItem[]{joueurs, arbitres, ramasseurs, equiperamasseurs};
        this.kst = new KeyStroke[]{null, null, null, null};
        this.chargeIn(this.jmit1, 1, this.kst);
        joueurs.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.chargeAllMain();
                DialogGestionJoueur apn = new DialogGestionJoueur(null, "Gestion des joueurs - GPTL", true, CMenuBar.this.win);
                apn.showGestionDialog();
            }
        });
        arbitres.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.chargeAllMain();
                DialogGestionArbitre apn = new DialogGestionArbitre(null, "Gestion des arbitres - GPTL", true, CMenuBar.this.win);
                apn.showGestionDialog();
            }
        });
        ramasseurs.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.chargeAllMain();
                DialogGestionRamasseur apn = new DialogGestionRamasseur(null, "Gestion des ramasseurs - GPTL", true, CMenuBar.this.win);
                apn.showGestionDialog();
            }
        });
        equiperamasseurs.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.chargeAllMain();
                DialogGestionEquipeRamasseurs apn = new DialogGestionEquipeRamasseurs(null, "Gestion des \u00e9quipes de ramasseurs - GPTL", true, CMenuBar.this.win);
                apn.showGestionDialog();
            }
        });
        this.jmit2 = new JMenuItem[]{apropos, entrainements};
        this.kst = new KeyStroke[]{null, null};
        this.chargeIn(this.jmit2, 2, this.kst);
        apropos.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogApropos apn = new DialogApropos(null, "\u00c0 propos - GPTL", true);
                apn.showNouveauDialog();
            }
        });
        entrainements.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                CMenuBar.this.em.setVisible(true);
            }
        });
    }

    public void chargeIn(JMenuItem[] jmat, int jmenuIndex) {
        for (int i = 0; i < jmat.length; ++i) {
            if (jmat[i].getText().equals("SP")) {
                this.jmt[jmenuIndex].addSeparator();
                continue;
            }
            this.jmt[jmenuIndex].add(jmat[i]);
        }
    }

    public void chargeIn(JMenuItem[] jmat, int jmenuIndex, KeyStroke[] kmat) {
        for (int i = 0; i < jmat.length; ++i) {
            if (jmat[i].getText().equals("SP")) {
                this.jmt[jmenuIndex].addSeparator();
                continue;
            }
            this.jmt[jmenuIndex].add(jmat[i]);
            jmat[i].setAccelerator(kmat[i]);
        }
    }

    public static void refresh(JComponent o) {
        o.setSize(o.getWidth() + 1, o.getHeight() + 1);
        o.setSize(o.getWidth() - 1, o.getHeight() - 1);
    }

}

