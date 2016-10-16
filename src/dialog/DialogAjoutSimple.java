/*
 * Decompiled with CFR 0_110.
 */
package dialog;

import calendrier.CalendrierInfos;
import calendrier.CalendrierMain;
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
import util.SQL;

public class DialogAjoutSimple
extends JDialog {
    private JPanel container = null;
    private JLabel logo_bt;
    private JComboBox joueur1combo = new JComboBox();
    private JComboBox joueur2combo = new JComboBox();
    private JComboBox courtcombo = new JComboBox();
    private JDialog dialog;

    public DialogAjoutSimple(JFrame parent, String name, boolean modal, CalendrierInfos iargs) {
        super(parent, name, modal);
        this.dialog = this;
        this.setSize(440, 350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(1);
        this.initDialog(iargs);
    }

    public void showAjoutDialog() {
        this.setVisible(true);
    }

    public void initDialog(final CalendrierInfos iargs) {
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
        JPanel joueur2pane = new JPanel();
        joueur2pane.setBackground(Color.white);
        joueur2pane.setPreferredSize(new Dimension(320, 40));
        joueur2pane.setBorder(BorderFactory.createEtchedBorder());
        JLabel joueur2lab = new JLabel("Joueur 2 :");
        joueur2pane.add(joueur2lab);
        joueur2pane.add(this.joueur2combo);
        this.joueur2combo.addMouseListener(new comboJoueur2Listener());
        JPanel courtpane = new JPanel();
        courtpane.setBackground(Color.white);
        courtpane.setPreferredSize(new Dimension(320, 40));
        courtpane.setBorder(BorderFactory.createEtchedBorder());
        JLabel courtlab = new JLabel("Court :");
        courtpane.add(courtlab);
        courtpane.add(this.courtcombo);
        this.courtcombo.addItem("Principal");
        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.add(panempty);
        content.add(joueur1pane);
        content.add(contrepane);
        content.add(joueur2pane);
        content.add(courtpane);
        content.setBorder(BorderFactory.createTitledBorder("Match \u00e0 " + iargs.getHeureString() + "h"));
        JPanel control = new JPanel();
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("FERMER");
        control.add(ok);
        control.add(cancel);
        ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Joueur j1 = (Joueur)DialogAjoutSimple.this.joueur1combo.getSelectedItem();
                Joueur j2 = (Joueur)DialogAjoutSimple.this.joueur2combo.getSelectedItem();
                String idm = "" + iargs.getId() + ",";
                String j1n = "" + j1.getNumero() + ",";
                String j2n = "" + j2.getNumero() + ",";
                String creneau = "'" + iargs.getHeureString() + "',";
                String court = "" + iargs.getCourt() + ",";
                String finale = iargs.getFinale() ? "1" : "0";
                String req = "";
                if (iargs.getJoueurs() == null) {
                    SQL.update("insert into smatchs values(" + idm + j1n + j2n + creneau + court + finale + ")");
                } else {
                    SQL.update("update smatchs set joueur1=" + j1n + "joueur2=" + j2n + "creneau=" + creneau + "court=" + court + "finale=" + finale + " where id=" + iargs.getId());
                }
                iargs.setJoueurs(new Joueur[]{j1, j2});
                iargs.setFinale(iargs.getFinale());
                iargs.setType(2);
                iargs.setInit(false);
                DialogAjoutSimple.this.setVisible(false);
            }
        });
        cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAjoutSimple.this.setVisible(false);
            }
        });
        CalendrierMain.joueurs.chargeInComboBox(this.joueur1combo);
        CalendrierMain.joueurs.chargeInComboBox(this.joueur2combo);
        CalendrierMain.joueurs.newCharge(1, this.getVectorCombo());
        CalendrierMain.joueurs.newCharge(2, this.getVectorCombo());
        if (iargs.getJoueurs() != null) {
            Joueur plj;
            int i;
            for (i = 0; i < this.joueur1combo.getItemCount(); ++i) {
                plj = (Joueur)this.joueur1combo.getItemAt(i);
                if (plj.getNumero() != iargs.getJoueurs()[0].getNumero()) continue;
                this.joueur1combo.setSelectedIndex(i);
            }
            for (i = 0; i < this.joueur2combo.getItemCount(); ++i) {
                plj = (Joueur)this.joueur2combo.getItemAt(i);
                if (plj.getNumero() != iargs.getJoueurs()[1].getNumero()) continue;
                this.joueur2combo.setSelectedIndex(i);
            }
        }
        this.getContentPane().add((Component)panIcon, "West");
        this.getContentPane().add((Component)content, "Center");
        this.getContentPane().add((Component)control, "South");
    }

    public Vector<JComboBox> getVectorCombo() {
        Vector<JComboBox> jos = new Vector<JComboBox>();
        jos.add(this.joueur1combo);
        jos.add(this.joueur2combo);
        return jos;
    }

    public class comboJoueur2Listener
    implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            CalendrierMain.joueurs.newCharge(2, DialogAjoutSimple.this.getVectorCombo());
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

    public class comboJoueur1Listener
    implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            CalendrierMain.joueurs.newCharge(1, DialogAjoutSimple.this.getVectorCombo());
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

