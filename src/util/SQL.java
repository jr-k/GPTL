/*
 * Decompiled with CFR 0_110.
 */
package util;

import calendrier.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQL {
    public static ResultSet query(String string, int TYPE_SCROLL_INSENSITIVE, int CONCUR_READ_ONLY) {
        try {
            PreparedStatement requete = Main.connect.prepareStatement(string, TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY);
            return requete.executeQuery();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static int update(String string) {
        try {
            PreparedStatement requete = Main.connect.prepareStatement(string);
            int r = requete.executeUpdate();
            Main.connect.commit();
            return r;
        }
        catch (Exception e) {
            return -1;
        }
    }
}

