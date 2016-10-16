/*
 * Decompiled with CFR 0_110.
 */
package util;

import calendrier.Main;
import java.util.Vector;
import util.Personne;

public class Arbitre
extends Personne {
    private int categorie = 0;

    public Arbitre() {
    }

    public Arbitre(int num, String pre, String name, int nat, int cat) {
        super(num, pre, name, nat);
        this.categorie = cat;
    }

    public String getCategorie() {
        return Main.arbitreCode.get(this.categorie);
    }

    public String toString() {
        String str = "  " + Arbitre.up1(this.getPrenom()) + " " + this.getNom().toUpperCase() + "   ";
        return str;
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

