/*
 * Decompiled with CFR 0_110.
 */
package dialog;

import calendrier.CalendrierInfos;
import calendrier.CalendrierMain;
import calendrier.Main;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class DialogAjoutDouble
extends JDialog {
    private JPanel container = null;
    private JLabel logo_bt;
    private JComboBox joueur1combo = new JComboBox();
    private JComboBox joueur2combo = new JComboBox();
    private JComboBox joueur3combo = new JComboBox();
    private JComboBox joueur4combo = new JComboBox();
    private JDialog dialog;

    public DialogAjoutDouble(JFrame parent, String name, boolean modal, CalendrierInfos iargs) {
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
        JPanel joueur3pane = new JPanel();
        joueur3pane.setBackground(Color.white);
        joueur3pane.setPreferredSize(new Dimension(320, 40));
        joueur3pane.setBorder(BorderFactory.createEtchedBorder());
        JLabel joueur3lab = new JLabel("Joueur 3 :");
        joueur3pane.add(joueur3lab);
        joueur3pane.add(this.joueur3combo);
        this.joueur3combo.addMouseListener(new comboJoueur3Listener());
        JPanel joueur4pane = new JPanel();
        joueur4pane.setBackground(Color.white);
        joueur4pane.setPreferredSize(new Dimension(320, 40));
        joueur4pane.setBorder(BorderFactory.createEtchedBorder());
        JLabel joueur4lab = new JLabel("Joueur 4 :");
        joueur4pane.add(joueur4lab);
        joueur4pane.add(this.joueur4combo);
        this.joueur4combo.addMouseListener(new comboJoueur4Listener());
        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.add(panempty);
        content.add(joueur1pane);
        content.add(joueur2pane);
        content.add(contrepane);
        content.add(joueur3pane);
        content.add(joueur4pane);
        content.setBorder(BorderFactory.createTitledBorder("Match \u00e0 " + iargs.getHeureString() + "h"));
        JPanel control = new JPanel();
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("FERMER");
        control.add(ok);
        control.add(cancel);
        ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Joueur j1 = (Joueur)DialogAjoutDouble.this.joueur1combo.getSelectedItem();
                    Joueur j2 = (Joueur)DialogAjoutDouble.this.joueur2combo.getSelectedItem();
                    Joueur j3 = (Joueur)DialogAjoutDouble.this.joueur3combo.getSelectedItem();
                    Joueur j4 = (Joueur)DialogAjoutDouble.this.joueur4combo.getSelectedItem();
                    String idm = "" + iargs.getId() + ",";
                    String j1n = "" + j1.getNumero() + ",";
                    String j2n = "" + j2.getNumero() + ",";
                    String j3n = "" + j3.getNumero() + ",";
                    String j4n = "" + j4.getNumero() + ",";
                    String creneau = "'" + iargs.getHeureString() + "',";
                    String court = "" + iargs.getCourt() + ",";
                    String finale = iargs.getFinale() ? "1," : "0,";
                    String req = "";
                    req = iargs.getJoueurs() == null ? "insert into dmatchs values(" + idm + j1n + j2n + j3n + j4n + creneau + court + finale + iargs.getJour() + ")" : "update dmatchs set joueur1=" + j1n + "joueur2=" + j2n + "joueur3=" + j3n + "joueur4=" + j4n + "creneau=" + creneau + "court=" + court + "finale=" + finale + " jour=" + iargs.getJour() + " where id=" + iargs.getId();
                    PreparedStatement requete = Main.connect.prepareStatement(req);
                    int resultat = requete.executeUpdate();
                    Main.connect.commit();
                    iargs.setJoueurs(new Joueur[]{j1, j2, j3, j4});
                    iargs.setFinale(iargs.getFinale());
                    iargs.setType(4);
                    iargs.setInit(false);
                    DialogAjoutDouble.this.setVisible(false);
                }
                catch (SQLException ex) {
                    Logger.getLogger(DialogAjoutDouble.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAjoutDouble.this.setVisible(false);
            }
        });
        CalendrierMain.joueurs.chargeInComboBox(this.joueur1combo);
        CalendrierMain.joueurs.chargeInComboBox(this.joueur2combo);
        CalendrierMain.joueurs.chargeInComboBox(this.joueur3combo);
        CalendrierMain.joueurs.chargeInComboBox(this.joueur4combo);
        CalendrierMain.joueurs.newCharge(1, this.getVectorCombo());
        CalendrierMain.joueurs.newCharge(2, this.getVectorCombo());
        CalendrierMain.joueurs.newCharge(3, this.getVectorCombo());
        CalendrierMain.joueurs.newCharge(4, this.getVectorCombo());
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
            for (i = 0; i < this.joueur3combo.getItemCount(); ++i) {
                plj = (Joueur)this.joueur3combo.getItemAt(i);
                if (plj.getNumero() != iargs.getJoueurs()[2].getNumero()) continue;
                this.joueur3combo.setSelectedIndex(i);
            }
            for (i = 0; i < this.joueur4combo.getItemCount(); ++i) {
                plj = (Joueur)this.joueur4combo.getItemAt(i);
                if (plj.getNumero() != iargs.getJoueurs()[3].getNumero()) continue;
                this.joueur4combo.setSelectedIndex(i);
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
        jos.add(this.joueur3combo);
        jos.add(this.joueur4combo);
        return jos;
    }

    public class comboJoueur4Listener
    implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            CalendrierMain.joueurs.newCharge(4, DialogAjoutDouble.this.getVectorCombo());
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

    public class comboJoueur3Listener
    implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            CalendrierMain.joueurs.newCharge(3, DialogAjoutDouble.this.getVectorCombo());
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

    public class comboJoueur2Listener
    implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            CalendrierMain.joueurs.newCharge(2, DialogAjoutDouble.this.getVectorCombo());
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
            CalendrierMain.joueurs.newCharge(1, DialogAjoutDouble.this.getVectorCombo());
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

