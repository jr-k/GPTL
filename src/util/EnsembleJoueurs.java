/*
 * Decompiled with CFR 0_110.
 */
package util;

import calendrier.CalendrierInfos;
import calendrier.CalendrierMain;
import calendrier.Main;
import dialog.DialogGestionJoueur;
import dialog.DialogGestionMatchD;
import dialog.DialogGestionMatchS;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import util.Joueur;

public class EnsembleJoueurs {
    public static Vector<Joueur> joueurs = new Vector();

    public static Joueur[] toArray() {
        Joueur[] tmp = new Joueur[joueurs.size()];
        for (int i = 0; i < joueurs.size(); ++i) {
            tmp[i] = joueurs.get(i);
        }
        return tmp;
    }

    public static int getMaxId() {
        int max = -1;
        for (int i = 0; i < joueurs.size(); ++i) {
            if (joueurs.get(i).getNumero() <= max) continue;
            max = joueurs.get(i).getNumero();
        }
        return max + 1;
    }

    public void toTables(JTable table) {
        for (int i = 0; i < joueurs.size(); ++i) {
            Joueur tmp = joueurs.get(i);
            int selection = 0;
            for (int j = 0; j < Main.nationalite.size(); ++j) {
                if (!Main.nationaliteCode.get(j).equals(tmp.getNationalite())) continue;
                selection = j;
            }
            Object[] donnee = new Object[]{tmp.getNumero(), EnsembleJoueurs.up1(tmp.getPrenom()), tmp.getNom().toUpperCase(), new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + tmp.getNationalite() + ".png"))), Main.nationalite.get(selection)};
            ((DialogGestionJoueur.TableModel)table.getModel()).addRow(donnee);
        }
    }

    public void toTablesMatchS(JTable table, CalendrierInfos iargs) {
        for (int i = 0; i < joueurs.size(); ++i) {
            Joueur tmp = joueurs.get(i);
            CalendrierInfos infoMirror = CalendrierMain.MatchTypeInverseInfo(iargs);
            if (infoMirror.getVJoueurs() != null && infoMirror.getVJoueurs().contains(tmp)) continue;
            int selection = 0;
            for (int j = 0; j < Main.nationalite.size(); ++j) {
                if (!Main.nationaliteCode.get(j).equals(tmp.getNationalite())) continue;
                selection = j;
            }
            Object[] donnee = new Object[]{tmp.getNumero(), EnsembleJoueurs.up1(tmp.getPrenom()), tmp.getNom().toUpperCase(), new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + tmp.getNationalite() + ".png"))), Main.nationalite.get(selection)};
            ((DialogGestionMatchS.TableModel)table.getModel()).addRow(donnee);
        }
    }

    public void toTablesMatchD(JTable table, CalendrierInfos iargs) {
        for (int i = 0; i < joueurs.size(); ++i) {
            Joueur tmp = joueurs.get(i);
            CalendrierInfos infoMirror = CalendrierMain.MatchTypeInverseInfo(iargs);
            if (infoMirror.getVJoueurs() != null && infoMirror.getVJoueurs().contains(tmp)) continue;
            int selection = 0;
            for (int j = 0; j < Main.nationalite.size(); ++j) {
                if (!Main.nationaliteCode.get(j).equals(tmp.getNationalite())) continue;
                selection = j;
            }
            Object[] donnee = new Object[]{tmp.getNumero(), EnsembleJoueurs.up1(tmp.getPrenom()), tmp.getNom().toUpperCase(), new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + tmp.getNationalite() + ".png"))), Main.nationalite.get(selection)};
            ((DialogGestionMatchD.TableModel)table.getModel()).addRow(donnee);
        }
    }

    public Vector<Joueur> getJoueurs() {
        return joueurs;
    }

    public void add(Joueur j) {
        joueurs.add(j);
    }

    public int size() {
        return joueurs.size();
    }

    public Joueur get(int i) {
        return joueurs.get(i);
    }

    public Joueur getJoueurById(int id) {
        Joueur tmp = null;
        for (int i = 0; i < joueurs.size(); ++i) {
            if (id != joueurs.get(i).getNumero()) continue;
            tmp = joueurs.get(i);
        }
        return tmp;
    }

    public void remove(int i) {
        joueurs.remove(i);
    }

    public void removeAll() {
        joueurs.removeAllElements();
    }

    public void setElementAt(Joueur j, int i) {
        joueurs.setElementAt(j, i);
    }

    public static void chargeIn() {
        joueurs.removeAllElements();
        try {
            PreparedStatement requete = Main.connect.prepareStatement("select * from joueurs order by id", 1004, 1007);
            ResultSet resultat = requete.executeQuery();
            ResultSetMetaData metas = resultat.getMetaData();
            while (resultat.next()) {
                int id = Integer.parseInt(resultat.getObject("ID") + "");
                String prenom = (String)resultat.getObject("PRENOM");
                String nom = (String)resultat.getObject("NOM");
                int nat = Integer.parseInt(resultat.getObject("PAYS") + "");
                joueurs.add(new Joueur(id, prenom, nom, nat));
            }
        }
        catch (Exception e) {
            // empty catch block
        }
    }

    public void chargeInComboBox(JComboBox combo) {
        combo.removeAllItems();
        for (int i = 0; i < joueurs.size(); ++i) {
            Joueur j = joueurs.get(i);
            combo.addItem(j);
        }
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

    public void newCharge(int i, Vector<JComboBox> jos) {
        JComboBox basecombo = jos.get(i - 1);
        jos.remove(i - 1);
        int selectsave = basecombo.getSelectedIndex();
        basecombo.removeAllItems();
        for (int j = 0; j < joueurs.size(); ++j) {
            boolean ok = true;
            Joueur player = joueurs.get(j);
            for (int k = 0; k < jos.size(); ++k) {
                if (player.getNumero() != ((Joueur)jos.get(k).getSelectedItem()).getNumero()) continue;
                ok = false;
            }
            if (!ok) continue;
            basecombo.addItem(player);
        }
        basecombo.setSelectedIndex(selectsave);
    }
}

