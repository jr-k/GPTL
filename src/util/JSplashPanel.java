/*
 * Decompiled with CFR 0_110.
 */
package util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class JSplashPanel
extends JPanel {
    public BufferedImage image = null;

    public JSplashPanel(int w, int h, BufferedImage img) {
        this.image = img;
        this.setPreferredSize(new Dimension(w, h));
    }

    public JSplashPanel(int w, int h, BufferedImage img, LayoutManager lay) {
        this.setLayout(lay);
        this.image = img;
        this.setPreferredSize(new Dimension(w, h));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}

