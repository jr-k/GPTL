/*
 * Decompiled with CFR 0_110.
 */
package util;

import calendrier.Main;
import java.util.Vector;
import util.Personne;

public class Ramasseur
extends Personne {
    private int equipe = 0;

    public Ramasseur() {
    }

    public Ramasseur(int num, String pre, String name, int nat, int equipe) {
        super(num, pre, name, nat);
        this.equipe = equipe;
    }

    public int getEquipe() {
        return this.equipe;
    }

    public String getEquipeString() {
        return Main.equipeCode.get(this.equipe);
    }

    public String toString() {
        String str = "[" + Ramasseur.up1(this.getNationalite()) + "] " + Ramasseur.up1(this.getPrenom()) + " " + this.getNom().toUpperCase() + "   ";
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

