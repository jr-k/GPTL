/*
 * Decompiled with CFR 0_110.
 */
package calendrier;

import calendrier.CalendrierMain;
import java.awt.Component;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import util.Arbitre;
import util.EquipeRamasseurs;
import util.JTablePanel;
import util.Joueur;

public class CalendrierInfos {
    int id = 0;
    int court = 0;
    int type;
    boolean finale = false;
    boolean init = false;
    static int lastid = 1;
    Joueur[] joueurs = null;
    Arbitre[] arbitres = null;
    Arbitre arbitrechaise = null;
    EquipeRamasseurs[] ramasseurs = null;
    Date date;
    String debutString;
    SimpleDateFormat debut;
    int jour = 0;
    CalendrierMain win = null;

    public int getCourt() {
        return this.court;
    }

    public void setCourt(int c) {
        this.court = c;
    }

    public CalendrierInfos(String heure, boolean fin, int ct, int day, int tpe, CalendrierMain w) {
        this.win = w;
        try {
            this.date = new SimpleDateFormat("HH").parse(heure);
        }
        catch (Exception ex) {
            // empty catch block
        }
        this.jour = day;
        this.court = ct;
        this.debutString = heure;
        this.debut = new SimpleDateFormat("HH");
        this.debut.format(this.date);
        this.finale = fin;
        this.type = tpe;
        this.init = true;
        this.id = lastid++;
    }

    public void reset() {
        this.joueurs = null;
        this.init = true;
    }

    public void setJour(int j) {
        this.jour = j;
    }

    public int getJour() {
        return this.jour;
    }

    public int getId() {
        return this.id;
    }

    public boolean getInit() {
        return this.init;
    }

    public String toString() {
        if (this.court == 3) {
            return "<html><b><ul></ul></b></html>";
        }
        if (this.init) {
            return "<html><b><ul>" + this.getFinaleString() + "Aucun match \u00e0 " + this.debutString + "h</ul></b></html>";
        }
        return "<html><b><ul>" + this.getFinaleString() + "Match " + this.getType() + " \u00e0 " + this.debutString + "h</ul></b></html>";
    }

    public String getType() {
        if (this.type == 4) {
            return "double";
        }
        if (this.type == 2) {
            return "simple";
        }
        return null;
    }

    public String getFinaleString() {
        String type;
        String string = type = this.court == 0 ? "SIMPLE" : "DOUBLE";
        if (this.finale) {
            return "[FINALE " + type + "] ";
        }
        return "";
    }

    public boolean getFinale() {
        return this.finale;
    }

    public String getHeureString() {
        return this.debutString;
    }

    public Joueur[] getJoueurs() {
        return this.joueurs;
    }

    public Vector<Joueur> getVJoueurs() {
        Vector<Joueur> v = new Vector<Joueur>();
        if (this.joueurs != null) {
            for (int i = 0; i < this.joueurs.length; ++i) {
                if (this.joueurs[i] == null) continue;
                v.add(this.joueurs[i]);
            }
        }
        return v;
    }

    public EquipeRamasseurs[] getEquipeRamasseurs() {
        return this.ramasseurs;
    }

    public void setEquipeRamasseurs(EquipeRamasseurs[] er) {
        this.ramasseurs = er;
    }

    public Vector<EquipeRamasseurs> getVEquipeRamasseurs() {
        Vector<EquipeRamasseurs> v = new Vector<EquipeRamasseurs>();
        if (this.ramasseurs != null) {
            for (int i = 0; i < this.ramasseurs.length; ++i) {
                if (this.ramasseurs[i] == null) continue;
                v.add(this.ramasseurs[i]);
            }
        }
        return v;
    }

    public void setArbitreChaise(Arbitre a) {
        this.arbitrechaise = a;
    }

    public Arbitre getArbitreChaise() {
        return this.arbitrechaise;
    }

    public void setArbitres(Arbitre[] as) {
        this.arbitres = as;
    }

    public Arbitre[] getArbitres() {
        return this.arbitres;
    }

    public Vector<Arbitre> getVArbitres() {
        Vector<Arbitre> v = new Vector<Arbitre>();
        if (this.arbitres != null) {
            for (int i = 0; i < this.arbitres.length; ++i) {
                if (this.arbitres[i] == null) continue;
                v.add(this.arbitres[i]);
            }
        }
        return v;
    }

    public void setDebutString(String debutString) {
        this.debutString = debutString;
    }

    public void setFinale(boolean finale) {
        this.finale = finale;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getFlags() {
        String[] flags = new String[this.joueurs.length];
        for (int i = 0; i < flags.length; ++i) {
            flags[i] = this.joueurs[i].getNationalite();
        }
        return flags;
    }

    public JTablePanel toJPanel(JTablePanel pane) {
        if (this.init) {
            pane.removeAll();
            pane.add(new JLabel(" "));
            pane.revalidate();
        } else {
            String[] flags = this.getFlags();
            Joueur[] joueurs = this.getJoueurs();
            pane.removeAll();
            if (this.getType().equals("simple")) {
                pane.add(this.getLabelFlag(flags[0]));
                pane.add(this.getLabelJoueur(0));
                pane.add(new JLabel("<html><b>contre<b></html>"));
                pane.add(this.getLabelFlag(flags[1]));
                pane.add(this.getLabelJoueur(1));
            } else {
                pane.add(this.getLabelFlag(flags[0]));
                pane.add(this.getLabelFlag(flags[1]));
                pane.add(this.getLabelJoueur(0));
                pane.add(new JLabel("<html><b>et<b></html>"));
                pane.add(this.getLabelJoueur(1));
                pane.add(new JLabel("<html><b>|<b></html>"));
                pane.add(this.getLabelFlag(flags[2]));
                pane.add(this.getLabelFlag(flags[3]));
                pane.add(this.getLabelJoueur(2));
                pane.add(new JLabel("<html><b>et<b></html>"));
                pane.add(this.getLabelJoueur(3));
            }
            pane.revalidate();
        }
        return pane;
    }

    public JLabel getLabelJoueur(int i) {
        if (this.getType().equals("simple")) {
            return new JLabel(CalendrierInfos.up1(this.joueurs[i].getPrenom()) + "." + this.joueurs[i].getNom().toUpperCase());
        }
        return new JLabel(CalendrierInfos.up1(this.joueurs[i].getPrenom()) + "." + new StringBuilder().append(this.joueurs[i].getNom().charAt(0)).append("").toString().toUpperCase());
    }

    public JLabel getLabelFlag(String flag) {
        return new JLabel(new ImageIcon(this.getClass().getResource("/flags/" + flag + ".png")));
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
}

