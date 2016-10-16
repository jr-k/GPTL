/*
 * Decompiled with CFR 0_110.
 */
package util;

import calendrier.CalendrierInfos;
import calendrier.CalendrierMain;
import calendrier.Main;
import dialog.DialogGestionEquipeRamasseurs;
import dialog.DialogGestionMatchD;
import dialog.DialogGestionMatchS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import util.Arbitre;
import util.EnsembleRamasseurs;
import util.EquipeRamasseurs;

public class EnsembleEquipeRamasseurs {
    public static Vector<EquipeRamasseurs> equiperamasseurs = new Vector();

    public static EquipeRamasseurs[] toArray() {
        EquipeRamasseurs[] tmp = new EquipeRamasseurs[equiperamasseurs.size()];
        for (int i = 0; i < equiperamasseurs.size(); ++i) {
            tmp[i] = equiperamasseurs.get(i);
        }
        return tmp;
    }

    public static int getMaxId() {
        int max = -1;
        for (int i = 0; i < equiperamasseurs.size(); ++i) {
            if (equiperamasseurs.get(i).getNumero() <= max) continue;
            max = equiperamasseurs.get(i).getNumero();
        }
        return max + 1;
    }

    public static void chargeInComboBox(JComboBox combo) {
        combo.removeAllItems();
        for (int i = 0; i < equiperamasseurs.size(); ++i) {
            EquipeRamasseurs equipe = equiperamasseurs.get(i);
            if (EnsembleRamasseurs.isEquipFull(equipe.getNumero())) continue;
            combo.addItem(equipe);
        }
    }

    public void toTables(JTable table) {
        for (int i = 0; i < equiperamasseurs.size(); ++i) {
            EquipeRamasseurs tmp = equiperamasseurs.get(i);
            Object[] donnee = new Object[]{tmp.getNumero(), tmp.getEquipe()};
            ((DialogGestionEquipeRamasseurs.TableModel)table.getModel()).addRow(donnee);
        }
    }

    public void toTablesMatchS(JTable table, CalendrierInfos iargs) {
        for (int i = 0; i < equiperamasseurs.size(); ++i) {
            EquipeRamasseurs tmp = equiperamasseurs.get(i);
            int selection2 = 0;
            CalendrierInfos infoMirror = CalendrierMain.MatchTypeInverseInfo(iargs);
            if (infoMirror.getVEquipeRamasseurs() != null && infoMirror.getVEquipeRamasseurs().contains(tmp) || !EnsembleRamasseurs.isEquipFull(tmp.getNumero())) continue;
            for (int j = 0; j < Main.equipeCode.size(); ++j) {
                if (!Main.equipeCode.get(j).toLowerCase().equals(tmp.getEquipe().toLowerCase())) continue;
                selection2 = j;
            }
            Object[] donnee = new Object[]{tmp.getNumero(), Main.equipeCode.get(selection2).toUpperCase()};
            ((DialogGestionMatchS.TableModel)table.getModel()).addRow(donnee);
        }
    }

    public void toTablesMatchD(JTable table, CalendrierInfos iargs) {
        for (int i = 0; i < equiperamasseurs.size(); ++i) {
            EquipeRamasseurs tmp = equiperamasseurs.get(i);
            int selection2 = 0;
            CalendrierInfos infoMirror = CalendrierMain.MatchTypeInverseInfo(iargs);
            if (infoMirror.getVEquipeRamasseurs() != null && infoMirror.getVEquipeRamasseurs().contains(tmp) || !EnsembleRamasseurs.isEquipFull(tmp.getNumero())) continue;
            for (int j = 0; j < Main.equipeCode.size(); ++j) {
                if (!Main.equipeCode.get(j).toLowerCase().equals(tmp.getEquipe().toLowerCase())) continue;
                selection2 = j;
            }
            Object[] donnee = new Object[]{tmp.getNumero(), Main.equipeCode.get(selection2).toUpperCase()};
            ((DialogGestionMatchD.TableModel)table.getModel()).addRow(donnee);
        }
    }

    public Vector<EquipeRamasseurs> getEquipeRamasseurs() {
        return equiperamasseurs;
    }

    public static EquipeRamasseurs getEquipeRamasseursById(int id) {
        EquipeRamasseurs tmp = null;
        for (int i = 0; i < equiperamasseurs.size(); ++i) {
            if (id != equiperamasseurs.get(i).getNumero()) continue;
            tmp = equiperamasseurs.get(i);
        }
        return tmp;
    }

    public void add(EquipeRamasseurs r) {
        equiperamasseurs.add(r);
    }

    public int size() {
        return equiperamasseurs.size();
    }

    public EquipeRamasseurs get(int i) {
        return equiperamasseurs.get(i);
    }

    public EquipeRamasseurs getEquipeRamasseurById(int id) {
        EquipeRamasseurs tmp = null;
        for (int i = 0; i < equiperamasseurs.size(); ++i) {
            if (id != equiperamasseurs.get(i).getNumero()) continue;
            tmp = equiperamasseurs.get(i);
        }
        return tmp;
    }

    public void remove(int i) {
        equiperamasseurs.remove(i);
    }

    public void removeAll() {
        equiperamasseurs.removeAllElements();
    }

    public void setElementAt(EquipeRamasseurs r, int i) {
        equiperamasseurs.setElementAt(r, i);
    }

    public static void chargeIn() {
        equiperamasseurs.removeAllElements();
        try {
            PreparedStatement requete = Main.connect.prepareStatement("select * from equiperamasseur order by id", 1004, 1007);
            ResultSet resultat = requete.executeQuery();
            ResultSetMetaData metas = resultat.getMetaData();
            while (resultat.next()) {
                int id = Integer.parseInt(resultat.getObject("ID") + "");
                String eq = String.valueOf(resultat.getObject("LABEL") + "");
                equiperamasseurs.add(new EquipeRamasseurs(id, eq));
            }
        }
        catch (Exception e) {
            // empty catch block
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
        for (int j = 0; j < equiperamasseurs.size(); ++j) {
            boolean ok = true;
            EquipeRamasseurs equiperamasseur = equiperamasseurs.get(j);
            for (int k = 0; k < jos.size(); ++k) {
                if (equiperamasseur.getNumero() != ((Arbitre)jos.get(k).getSelectedItem()).getNumero()) continue;
                ok = false;
            }
            if (!ok) continue;
            basecombo.addItem(equiperamasseur);
        }
        basecombo.setSelectedIndex(selectsave);
    }
}

