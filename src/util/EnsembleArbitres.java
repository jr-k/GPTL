/*
 * Decompiled with CFR 0_110.
 */
package util;

import calendrier.CalendrierInfos;
import calendrier.CalendrierMain;
import calendrier.Main;
import dialog.DialogGestionArbitre;
import dialog.DialogGestionMatchD;
import dialog.DialogGestionMatchS;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import util.Arbitre;
import util.SQL;

public class EnsembleArbitres {
    public static Vector<Arbitre> arbitres = new Vector();

    public static Arbitre[] toArray() {
        Arbitre[] tmp = new Arbitre[arbitres.size()];
        for (int i = 0; i < arbitres.size(); ++i) {
            tmp[i] = arbitres.get(i);
        }
        return tmp;
    }

    public static int getMaxId() {
        int max = -1;
        for (int i = 0; i < arbitres.size(); ++i) {
            if (arbitres.get(i).getNumero() <= max) continue;
            max = arbitres.get(i).getNumero();
        }
        return max + 1;
    }

    public void toTables(JTable table) {
        for (int i = 0; i < arbitres.size(); ++i) {
            int j;
            Arbitre tmp = arbitres.get(i);
            int selection = 0;
            int selection2 = 0;
            for (j = 0; j < Main.nationalite.size(); ++j) {
                if (!Main.nationaliteCode.get(j).equals(tmp.getNationalite())) continue;
                selection = j;
            }
            for (j = 0; j < Main.arbitreCode.size(); ++j) {
                if (!Main.arbitreCode.get(j).toLowerCase().equals(tmp.getCategorie().toLowerCase())) continue;
                selection2 = j;
            }
            Object[] donnee = new Object[]{tmp.getNumero(), EnsembleArbitres.up1(tmp.getPrenom()), tmp.getNom().toUpperCase(), Main.arbitreCode.get(selection2).toUpperCase(), new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + tmp.getNationalite() + ".png"))), Main.nationalite.get(selection)};
            ((DialogGestionArbitre.TableModel)table.getModel()).addRow(donnee);
        }
    }

    public void toTablesMatchS(JTable table, CalendrierInfos iargs) {
        for (int i = 0; i < arbitres.size(); ++i) {
            int j;
            Arbitre tmp = arbitres.get(i);
            CalendrierInfos infoMirror = CalendrierMain.MatchTypeInverseInfo(iargs);
            if (infoMirror.getVArbitres() != null && infoMirror.getVArbitres().contains(tmp) || !tmp.getCategorie().toLowerCase().equals("jat2")) continue;
            int selection = 0;
            int selection2 = 0;
            for (j = 0; j < Main.nationalite.size(); ++j) {
                if (!Main.nationaliteCode.get(j).equals(tmp.getNationalite())) continue;
                selection = j;
            }
            for (j = 0; j < Main.arbitreCode.size(); ++j) {
                if (!Main.arbitreCode.get(j).toLowerCase().equals(tmp.getCategorie().toLowerCase())) continue;
                selection2 = j;
            }
            Object[] donnee = new Object[]{tmp.getNumero(), EnsembleArbitres.up1(tmp.getPrenom()), tmp.getNom().toUpperCase(), Main.arbitreCode.get(selection2).toUpperCase(), new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + tmp.getNationalite() + ".png"))), Main.nationalite.get(selection)};
            ((DialogGestionMatchS.TableModel)table.getModel()).addRow(donnee);
        }
    }

    public void toTablesMatchD(JTable table, CalendrierInfos iargs) {
        for (int i = 0; i < arbitres.size(); ++i) {
            int j;
            Arbitre tmp = arbitres.get(i);
            CalendrierInfos infoMirror = CalendrierMain.MatchTypeInverseInfo(iargs);
            if (infoMirror.getVArbitres() != null && infoMirror.getVArbitres().contains(tmp) || !tmp.getCategorie().toLowerCase().equals("jat2")) continue;
            int selection = 0;
            int selection2 = 0;
            for (j = 0; j < Main.nationalite.size(); ++j) {
                if (!Main.nationaliteCode.get(j).equals(tmp.getNationalite())) continue;
                selection = j;
            }
            for (j = 0; j < Main.arbitreCode.size(); ++j) {
                if (!Main.arbitreCode.get(j).toLowerCase().equals(tmp.getCategorie().toLowerCase())) continue;
                selection2 = j;
            }
            Object[] donnee = new Object[]{tmp.getNumero(), EnsembleArbitres.up1(tmp.getPrenom()), tmp.getNom().toUpperCase(), Main.arbitreCode.get(selection2).toUpperCase(), new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + tmp.getNationalite() + ".png"))), Main.nationalite.get(selection)};
            ((DialogGestionMatchD.TableModel)table.getModel()).addRow(donnee);
        }
    }

    public Vector<Arbitre> getArbitres() {
        return arbitres;
    }

    public void add(Arbitre a) {
        arbitres.add(a);
    }

    public int size() {
        return arbitres.size();
    }

    public Arbitre get(int i) {
        return arbitres.get(i);
    }

    public Arbitre getArbitreById(int id) {
        Arbitre tmp = null;
        for (int i = 0; i < arbitres.size(); ++i) {
            if (id != arbitres.get(i).getNumero()) continue;
            tmp = arbitres.get(i);
        }
        return tmp;
    }

    public void remove(int i) {
        arbitres.remove(i);
    }

    public void removeAll() {
        arbitres.removeAllElements();
    }

    public void setElementAt(Arbitre a, int i) {
        arbitres.setElementAt(a, i);
    }

    public static void chargeIn() {
        arbitres.removeAllElements();
        try {
            PreparedStatement requete = Main.connect.prepareStatement("select * from arbitres order by id", 1004, 1007);
            ResultSet resultat = requete.executeQuery();
            ResultSetMetaData metas = resultat.getMetaData();
            while (resultat.next()) {
                int id = Integer.parseInt(resultat.getObject("ID") + "");
                String prenom = (String)resultat.getObject("PRENOM");
                String nom = (String)resultat.getObject("NOM");
                int nat = Integer.parseInt(resultat.getObject("PAYS") + "");
                int cat = Integer.parseInt(resultat.getObject("CATEGORIE") + "");
                arbitres.add(new Arbitre(id, prenom, nom, nat, cat));
            }
        }
        catch (Exception e) {
            // empty catch block
        }
    }

    public static void chargeInComboBox(JComboBox combo) {
        combo.removeAllItems();
        for (int i = 0; i < arbitres.size(); ++i) {
            Arbitre a = arbitres.get(i);
            combo.addItem(a);
        }
    }

    public static void chargeInComboBoxWithout(JComboBox combo, Vector<String> pays, CalendrierInfos iargs) {
        combo.removeAllItems();
        String table = iargs.getType().equals("simple") ? "smatchs" : "dmatchs";
        for (int i = 0; i < arbitres.size(); ++i) {
            Arbitre a = arbitres.get(i);
            CalendrierInfos infoMirror = CalendrierMain.MatchTypeInverseInfo(iargs);
            if (infoMirror.getArbitreChaise() != null && infoMirror.getArbitreChaise().getNumero() == a.getNumero() || pays.contains(a.getNationalite()) || Main.getCategorieArbitreIndexLabel(a.getCategorie()) != 1) continue;
            try {
                ResultSet resultat = SQL.query("select * from " + table + " where arbitrechaise=" + a.getNumero(), 1004, 1007);
                resultat.last();
                if (resultat.getRow() >= 2) continue;
                combo.addItem(a);
                continue;
            }
            catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        for (int j = 0; j < arbitres.size(); ++j) {
            boolean ok = true;
            Arbitre arbtr = arbitres.get(j);
            for (int k = 0; k < jos.size(); ++k) {
                if (arbtr.getNumero() != ((Arbitre)jos.get(k).getSelectedItem()).getNumero()) continue;
                ok = false;
            }
            if (!ok) continue;
            basecombo.addItem(arbtr);
        }
        basecombo.setSelectedIndex(selectsave);
    }
}

