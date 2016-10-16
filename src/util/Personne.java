/*
 * Decompiled with CFR 0_110.
 */
package util;

import calendrier.Main;
import java.util.Vector;

public class Personne {
    protected int numero = 0;
    protected String prenom;
    protected String nom;
    protected int nationalite;

    public Personne() {
    }

    public Personne(int num, String pre, String name, int nat) {
        this.numero = num;
        this.prenom = pre;
        this.nom = name;
        this.nationalite = nat;
    }

    public String getNationalite() {
        return Main.nationaliteCode.get(this.nationalite);
    }

    public String getNom() {
        return this.nom;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setNationalite(int nationalite) {
        this.nationalite = nationalite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}

