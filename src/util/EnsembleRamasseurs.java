/*
 * Decompiled with CFR 0_110.
 */
package util;

import calendrier.Main;
import dialog.DialogGestionRamasseur;
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
import util.Arbitre;
import util.Ramasseur;

public class EnsembleRamasseurs {
    public static Vector<Ramasseur> ramasseurs = new Vector();

    public static Ramasseur[] toArray() {
        Ramasseur[] tmp = new Ramasseur[ramasseurs.size()];
        for (int i = 0; i < ramasseurs.size(); ++i) {
            tmp[i] = ramasseurs.get(i);
        }
        return tmp;
    }

    public static boolean isEquipFull(int idequipe) {
        int nb = 0;
        for (int i = 0; i < ramasseurs.size(); ++i) {
            if (ramasseurs.get(i).getEquipe() != idequipe) continue;
            ++nb;
        }
        if (nb == 6) {
            return true;
        }
        return false;
    }

    public static int getMaxId() {
        int max = -1;
        for (int i = 0; i < ramasseurs.size(); ++i) {
            if (ramasseurs.get(i).getNumero() <= max) continue;
            max = ramasseurs.get(i).getNumero();
        }
        return max + 1;
    }

    public void toTables(JTable table) {
        for (int i = 0; i < ramasseurs.size(); ++i) {
            int j;
            Ramasseur tmp = ramasseurs.get(i);
            int selection = 0;
            int selection2 = 0;
            for (j = 0; j < Main.nationalite.size(); ++j) {
                if (!Main.nationaliteCode.get(j).equals(tmp.getNationalite())) continue;
                selection = j;
            }
            for (j = 0; j < Main.equipeCode.size(); ++j) {
                if (!Main.equipeCode.get(j).toLowerCase().equals(tmp.getEquipeString().toLowerCase())) continue;
                selection2 = j;
            }
            Object[] donnee = new Object[]{tmp.getNumero(), EnsembleRamasseurs.up1(tmp.getPrenom()), tmp.getNom().toUpperCase(), Main.equipeCode.get(selection2).toUpperCase(), new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + tmp.getNationalite() + ".png"))), Main.nationalite.get(selection)};
            ((DialogGestionRamasseur.TableModel)table.getModel()).addRow(donnee);
        }
    }

    public Vector<Ramasseur> getRamasseurs() {
        return ramasseurs;
    }

    public void add(Ramasseur r) {
        ramasseurs.add(r);
    }

    public int size() {
        return ramasseurs.size();
    }

    public Ramasseur get(int i) {
        return ramasseurs.get(i);
    }

    public static Ramasseur getRamasseurById(int id) {
        Ramasseur tmp = null;
        for (int i = 0; i < ramasseurs.size(); ++i) {
            if (id != ramasseurs.get(i).getNumero()) continue;
            tmp = ramasseurs.get(i);
        }
        return tmp;
    }

    public void remove(int i) {
        ramasseurs.remove(i);
    }

    public void removeAll() {
        ramasseurs.removeAllElements();
    }

    public void setElementAt(Ramasseur r, int i) {
        ramasseurs.setElementAt(r, i);
    }

    public static void chargeIn() {
        ramasseurs.removeAllElements();
        try {
            PreparedStatement requete = Main.connect.prepareStatement("select * from ramasseurs order by id", 1004, 1007);
            ResultSet resultat = requete.executeQuery();
            ResultSetMetaData metas = resultat.getMetaData();
            while (resultat.next()) {
                int id = Integer.parseInt(resultat.getObject("ID") + "");
                String prenom = (String)resultat.getObject("PRENOM");
                String nom = (String)resultat.getObject("NOM");
                int nat = Integer.parseInt(resultat.getObject("PAYS") + "");
                int eq = Integer.parseInt(resultat.getObject("EQUIPE") + "");
                ramasseurs.add(new Ramasseur(id, prenom, nom, nat, eq));
            }
        }
        catch (Exception e) {
            // empty catch block
        }
    }

    public void chargeInComboBox(JComboBox combo) {
        combo.removeAllItems();
        for (int i = 0; i < ramasseurs.size(); ++i) {
            Ramasseur a = ramasseurs.get(i);
            combo.addItem(a);
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
        for (int j = 0; j < ramasseurs.size(); ++j) {
            boolean ok = true;
            Ramasseur ramasseur = ramasseurs.get(j);
            for (int k = 0; k < jos.size(); ++k) {
                if (ramasseur.getNumero() != ((Arbitre)jos.get(k).getSelectedItem()).getNumero()) continue;
                ok = false;
            }
            if (!ok) continue;
            basecombo.addItem(ramasseur);
        }
        basecombo.setSelectedIndex(selectsave);
    }
}

