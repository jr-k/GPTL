/*
 * Decompiled with CFR 0_110.
 */
package dialog;

import calendrier.CalendrierMain;
import entrainement.EntrainementInfos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import util.EnsembleJoueurs;
import util.Joueur;

public class DialogAjoutEntrainement
extends JDialog {
    private JPanel container = null;
    private JLabel logo_bt;
    private JComboBox joueur1combo = new JComboBox();
    private JDialog dialog;

    public DialogAjoutEntrainement(JFrame parent, String name, boolean modal, EntrainementInfos iargs, Vector<EntrainementInfos> iargsV) {
        super(parent, name, modal);
        this.dialog = this;
        this.setSize(440, 350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(1);
        this.initDialog(iargs, iargsV);
    }

    public void showEntrainementDialog() {
        this.setVisible(true);
    }

    public void initDialog(final EntrainementInfos iargs, Vector<EntrainementInfos> iargsV) {
        this.logo_bt = new JLabel(new ImageIcon(this.getClass().getResource("/imgs/gptladd.png")));
        JPanel panIcon = new JPanel();
        panIcon.setBackground(Color.white);
        panIcon.setLayout(new BorderLayout());
        panIcon.add(this.logo_bt);
        JPanel panempty = new JPanel();
        panempty.setPreferredSize(new Dimension(200, 30));
        panempty.setBackground(Color.white);
        JPanel contrepane = new JPanel();
        contrepane.setBackground(Color.white);
        contrepane.setPreferredSize(new Dimension(320, 30));
        JLabel contrelab = new JLabel("<html><b>contre</b></html>");
        contrepane.add(contrelab);
        JPanel joueur1pane = new JPanel();
        joueur1pane.setBackground(Color.white);
        joueur1pane.setPreferredSize(new Dimension(320, 40));
        joueur1pane.setBorder(BorderFactory.createEtchedBorder());
        JLabel joueur1lab = new JLabel("Joueur 1 :");
        joueur1pane.add(joueur1lab);
        joueur1pane.add(this.joueur1combo);
        this.joueur1combo.addMouseListener(new comboJoueur1Listener());
        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.add(panempty);
        content.add(joueur1pane);
        content.setBorder(BorderFactory.createTitledBorder("Entrainement \u00e0 " + iargs.getHeureString() + "h"));
        JPanel control = new JPanel();
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("FERMER");
        control.add(ok);
        control.add(cancel);
        ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Joueur j1 = (Joueur)DialogAjoutEntrainement.this.joueur1combo.getSelectedItem();
                iargs.setJoueur(j1);
                iargs.setInit(false);
                DialogAjoutEntrainement.this.setVisible(false);
            }
        });
        cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAjoutEntrainement.this.setVisible(false);
            }
        });
        CalendrierMain.joueurs.chargeInComboBox(this.joueur1combo);
        for (int i = 0; i < this.joueur1combo.getItemCount(); ++i) {
            Joueur jo = (Joueur)this.joueur1combo.getItemAt(i);
            boolean oks = true;
            for (int j = 0; j < iargsV.size(); ++j) {
                if (jo.getNumero() != iargsV.get(j).getJoueur().getNumero()) continue;
                oks = false;
            }
            if (oks) continue;
            this.joueur1combo.removeItemAt(i);
        }
        this.getContentPane().add((Component)panIcon, "West");
        this.getContentPane().add((Component)content, "Center");
        this.getContentPane().add((Component)control, "South");
    }

    public class comboJoueur1Listener
    implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
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
    }

}

