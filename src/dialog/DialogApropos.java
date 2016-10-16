/*
 * Decompiled with CFR 0_110.
 */
package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogApropos
extends JDialog {
    private JPanel container = null;
    private JDialog dialog;
    private JLabel logo_bt;

    public DialogApropos(JFrame parent, String name, boolean modal) {
        super(parent, name, modal);
        this.dialog = this;
        this.setSize(559, 360);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(1);
        this.initDialog();
    }

    public void initDialog() {
        this.logo_bt = new JLabel(new ImageIcon(this.getClass().getResource("/imgs/logogptl.png")));
        JPanel panIcon = new JPanel();
        panIcon.setBackground(Color.black);
        panIcon.setLayout(new BorderLayout());
        panIcon.add((Component)this.logo_bt, "North");
        panIcon.setFocusable(true);
        panIcon.requestFocus();
        panIcon.addMouseListener(new MouseListener(){

            @Override
            public void mousePressed(MouseEvent e) {
                DialogApropos.this.dialog.setVisible(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Color.black);
        content.add((Component)panIcon, "North");
        this.getContentPane().add((Component)content, "Center");
    }

    public void showNouveauDialog() {
        this.setVisible(true);
    }

}

