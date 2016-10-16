/*
 * Decompiled with CFR 0_110.
 */
package calendrier;

import calendrier.CalendrierMain;
import static calendrier.Main.arbitreCode;
import static calendrier.Main.courtCode;
import static calendrier.Main.equipeCode;
import static calendrier.Main.nationalite;
import static calendrier.Main.nationaliteCode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import util.ConnexionOracleFactory;
import util.SQL;

public class Main {
    public static Connection connect = ConnexionOracleFactory.creerConnexionMysql();
    public static CalendrierMain gptl = null;
    public static Vector<String> nationalite = new Vector();
    public static Vector<String> nationaliteCode = new Vector();
    public static Vector<String> arbitreCode = new Vector();
    public static Vector<String> equipeCode = new Vector();
    public static Vector<String> courtCode = new Vector();

    public static void main(String[] args) {
        Main.chargeNationalite();
        Main.chargeCategorieArbitres();
        Main.chargeEquipeRamasseurs();
        Main.chargeCourt();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            // empty catch block
        }
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                Main.gptl = new CalendrierMain();
                Main.gptl.setVisible(true);
            }
        });
    }

    public static void chargeNationalite() {
        nationalite.removeAllElements();
        nationaliteCode.removeAllElements();
        try {
            ResultSet resultat = SQL.query("select * from nationalite order by id", 1004, 1007);
            while (resultat.next()) {
                String PAYS = (String)resultat.getObject("PAYS");
                String LABEL = (String)resultat.getObject("LABEL");
                nationalite.add(PAYS);
                nationaliteCode.add(LABEL);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getPaysIndex(String pays) {
        for (int i = 0; i < nationalite.size(); ++i) {
            if (!nationalite.get(i).equals(pays)) continue;
            return i;
        }
        return 0;
    }

    public static int getPaysIndexLabel(String pays) {
        for (int i = 0; i < nationaliteCode.size(); ++i) {
            if (!nationaliteCode.get(i).equals(pays)) continue;
            return i;
        }
        return 0;
    }

    private static void chargeCategorieArbitres() {
        arbitreCode.removeAllElements();
        try {
            ResultSet resultat = SQL.query("select * from categoriearbitre order by id", 1004, 1007);
            while (resultat.next()) {
                String LABEL = (String)resultat.getObject("LABEL");
                arbitreCode.add(LABEL);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getCategorieArbitreIndexLabel(String label) {
        for (int i = 0; i < arbitreCode.size(); ++i) {
            if (!arbitreCode.get(i).toLowerCase().equals(label.toLowerCase())) continue;
            return i;
        }
        return 0;
    }

    private static void chargeEquipeRamasseurs() {
        equipeCode.removeAllElements();
        try {
            ResultSet resultat = SQL.query("select * from equiperamasseur order by id", 1004, 1007);
            while (resultat.next()) {
                String LABEL = (String)resultat.getObject("LABEL");
                equipeCode.add(LABEL);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getEquipeRamasseurIndexLabel(String label) {
        for (int i = 0; i < equipeCode.size(); ++i) {
            if (!equipeCode.get(i).toLowerCase().equals(label.toLowerCase())) continue;
            return i;
        }
        return 0;
    }

    private static void chargeCourt() {
        courtCode.removeAllElements();
        try {
            ResultSet resultat = SQL.query("select * from court order by id", 1004, 1007);
            while (resultat.next()) {
                String LABEL = (String)resultat.getObject("LABEL");
                courtCode.add(LABEL);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getCourtIndexLabel(String label) {
        for (int i = 0; i < courtCode.size(); ++i) {
            if (!courtCode.get(i).toLowerCase().equals(label.toLowerCase())) continue;
            return i;
        }
        return 0;
    }

    public static void chargeAllMain() {
        Main.chargeNationalite();
        Main.chargeCategorieArbitres();
        Main.chargeEquipeRamasseurs();
        Main.chargeCourt();
    }

}

