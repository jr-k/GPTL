/*
 * Decompiled with CFR 0_110.
 */
package util;

import util.Personne;

public class Joueur
extends Personne {
    public Joueur() {
    }

    public Joueur(int num, String pre, String name, int nat) {
        super(num, pre, name, nat);
    }

    public String toString() {
        String str = "[" + Joueur.up1(this.getNationalite()) + "] " + Joueur.up1(this.getPrenom()) + " " + this.getNom().toUpperCase() + "   ";
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

