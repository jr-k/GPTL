/*
 * Decompiled with CFR 0_110.
 */
package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

public class JTablePanel
extends JPanel {
    private int cle = 1;
    public static int ACTIV = 1;
    public static int NO_ACTIV = 2;

    public JTablePanel() {
    }

    public JTablePanel(int cle) {
        this.cle = cle;
    }

    public JTablePanel(int w, int h, int cle) {
        this.setPreferredSize(new Dimension(w, h));
        this.cle = cle;
    }

    public JTablePanel(LayoutManager lay) {
        super(lay);
    }

    public JTablePanel(LayoutManager lay, int cle) {
        super(lay);
        this.cle = cle;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (this.cle > 0) {
            Graphics2D g2 = (Graphics2D)g;
            Color[] colors = null;
            if (this.cle == ACTIV) {
                colors = new Color[]{Color.decode("0x4fd300"), Color.decode("0x57f365")};
            } else if (this.cle == NO_ACTIV) {
                colors = new Color[]{Color.decode("0xeaeaea"), Color.decode("0xfafafa")};
            } else if (this.cle == 3) {
                colors = new Color[]{Color.decode("0x424242"), Color.decode("0x000000")};
            }
            LinearGradientPaint p = new LinearGradientPaint(new Point2D.Float(0.0f, 0.0f), new Point2D.Float(0.0f, this.getHeight()), new float[]{0.0f, 1.0f}, colors);
            g2.setPaint(p);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }

    public void setCle(int c) {
        this.cle = c;
    }
}

