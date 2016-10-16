/*
 * Decompiled with CFR 0_110.
 */
package util;

public class EquipeRamasseurs {
    private int numero = 0;
    private String equipe = "";

    public EquipeRamasseurs() {
    }

    public EquipeRamasseurs(int n, String lab) {
        this.numero = n;
        this.equipe = lab;
    }

    public String getEquipe() {
        return this.equipe;
    }

    public void setEquipe(String label) {
        this.equipe = label;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int num) {
        this.numero = num;
    }

    public String toString() {
        return this.getEquipe();
    }
}

