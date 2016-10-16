/*
 * Decompiled with CFR 0_110.
 */
package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class JXPanel
extends JPanel {
    public JXPanel(int w, int h, Color c) {
        super.setPreferredSize(new Dimension(w, h));
        super.setBackground(c);
    }

    public JXPanel(Color c) {
        super.setBackground(c);
    }

    public JXPanel(int w, int h) {
        super.setPreferredSize(new Dimension(w, h));
    }

    public JXPanel(LayoutManager lay, Color c) {
        super.setLayout(lay);
        super.setBackground(c);
    }

    public JXPanel(LayoutManager lay) {
        super.setLayout(lay);
    }

    public JXPanel(LayoutManager lay, int w, int h, Color c, Border b) {
        super.setLayout(lay);
        super.setPreferredSize(new Dimension(w, h));
        super.setBackground(c);
        super.setBorder(b);
    }

    public JXPanel(LayoutManager lay, int w, int h, Border b) {
        super.setLayout(lay);
        super.setPreferredSize(new Dimension(w, h));
        super.setBorder(b);
    }

    public JXPanel(int w, int h, Border b) {
        super.setPreferredSize(new Dimension(w, h));
        super.setBorder(b);
    }

    public JXPanel(int w, int h, Color c, Border b) {
        super.setPreferredSize(new Dimension(w, h));
        super.setBackground(c);
        super.setBorder(b);
    }

    public JXPanel(Color c, Border b) {
        super.setBackground(c);
        super.setBorder(b);
    }

    public JXPanel add(JComponent[] composants) {
        for (int i = 0; i < composants.length; ++i) {
            super.add(composants[i]);
        }
        return this;
    }

    public JXPanel add(JComponent c1) {
        super.add(c1);
        return this;
    }

    public JXPanel add(JComponent c1, JComponent c2) {
        super.add(c1);
        super.add(c2);
        return this;
    }

    public JXPanel add(JComponent c1, JComponent c2, JComponent c3) {
        super.add(c1);
        super.add(c2);
        super.add(c3);
        return this;
    }

    public JXPanel add(JComponent c1, JComponent c2, JComponent c3, JComponent c4) {
        super.add(c1);
        super.add(c2);
        super.add(c3);
        super.add(c4);
        return this;
    }

    public JXPanel add(JComponent c1, JComponent c2, JComponent c3, JComponent c4, JComponent c5) {
        super.add(c1);
        super.add(c2);
        super.add(c3);
        super.add(c4);
        super.add(c5);
        return this;
    }

    public JXPanel add(JComponent c1, JComponent c2, JComponent c3, JComponent c4, JComponent c5, JComponent c6) {
        super.add(c1);
        super.add(c2);
        super.add(c3);
        super.add(c4);
        super.add(c5);
        super.add(c6);
        return this;
    }

    public JXPanel add(JComponent c1, JComponent c2, JComponent c3, JComponent c4, JComponent c5, JComponent c6, JComponent c7) {
        super.add(c1);
        super.add(c2);
        super.add(c3);
        super.add(c4);
        super.add(c5);
        super.add(c6);
        super.add(c7);
        return this;
    }

    public JXPanel() {
    }

    public JXPanel add(JComponent c1, String borderLayoutPosition) {
        super.add((Component)c1, borderLayoutPosition);
        return this;
    }
}

