/*
 * Decompiled with CFR 0_110.
 */
package entrainement;

import java.awt.Component;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Joueur;

public class EntrainementInfos {
    int id = 0;
    boolean init = false;
    static int lastid = 1;
    int numeroTerrain = 0;
    Joueur joueur = null;
    Date date;
    String debutString;
    SimpleDateFormat debut;

    public EntrainementInfos(String heure, Joueur joueurreserv, int terrain) {
        try {
            this.date = new SimpleDateFormat("HH").parse(heure);
        }
        catch (Exception ex) {
            // empty catch block
        }
        this.debutString = heure;
        this.debut = new SimpleDateFormat("HH");
        this.debut.format(this.date);
        this.numeroTerrain = terrain;
        if (joueurreserv == null) {
            this.init = true;
        } else {
            this.joueur = joueurreserv;
        }
        this.id = lastid++;
    }

    public int getNumeroTerrain() {
        return this.numeroTerrain;
    }

    public String getNumeroTerrainS() {
        return "" + this.numeroTerrain + "";
    }

    public void setNumeroTerrain(int t) {
        this.numeroTerrain = t;
    }

    public String getNumTerrainString() {
        return "[Court n\u00b0" + this.getNumeroTerrain() + "] ";
    }

    public int getId() {
        return this.id;
    }

    public boolean getInit() {
        return this.init;
    }

    public String toString() {
        if (this.init) {
            return "<html><b><ul>" + this.getNumTerrainString() + "Aucun entrainement \u00e0 " + this.debutString + "h</ul></b></html>";
        }
        return "<html><b><ul>" + this.getNumTerrainString() + "Entrainement \u00e0 " + this.debutString + "h</ul></b></html>";
    }

    public String getHeureString() {
        return this.debutString;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void setDebutString(String debutString) {
        this.debutString = debutString;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public String getFlags() {
        return this.joueur.getNationalite();
    }

    public JPanel toJPanel(JPanel pane) {
        if (this.init) {
            pane.removeAll();
            pane.add(new JLabel(" "));
            pane.revalidate();
        } else {
            String flags = this.getFlags();
            Joueur joueur = this.getJoueur();
            pane.removeAll();
            pane.add(this.getLabelFlag(flags));
            pane.add(this.getLabelJoueur());
            pane.add(new JLabel("<html><b> a r\u00e9serv\u00e9 ce cr\u00e9neau horaire sur le court " + this.getNumeroTerrainS() + "<b></html>"));
        }
        pane.revalidate();
        return pane;
    }

    public JLabel getLabelJoueur() {
        return new JLabel(EntrainementInfos.up1(this.joueur.getPrenom()) + " " + this.joueur.getNom().toUpperCase());
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

