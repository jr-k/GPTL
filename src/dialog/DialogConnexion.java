/*
 * Decompiled with CFR 0_110.
 */
package dialog;

import calendrier.CalendrierMain;
import calendrier.Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import util.JSplashPanel;
import util.JTablePanel;

public class DialogConnexion
extends JDialog {
    private JPanel container = null;
    private JDialog dialog;
    private JLabel logo_bt;
    private JTextField loginField;
    private JPasswordField passField;
    public static boolean init = true;
    JLabel ok;
    JLabel cancel;
    BufferedImage bi;
    BufferedImage bibloc;
    BufferedImage cokbloc;
    BufferedImage ccancel;

    public DialogConnexion(JFrame parent, String name, boolean modal) {
        super(parent, name, modal);
        this.dialog = this;
        this.loginField = new JTextField(20);
        this.passField = new JPasswordField(20);
        this.ok = null;
        this.cancel = null;
        this.bi = null;
        this.bibloc = null;
        this.cokbloc = null;
        this.ccancel = null;
        this.setSize(490, 320);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addWindowListener(new WindowListener(){

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        this.initDialog();
    }

    public void initDialog() {
        try {
            this.bi = ImageIO.read(this.getClass().getResource("/imgs/connexiontenniso.png"));
            this.bibloc = ImageIO.read(this.getClass().getResource("/imgs/connexionbloc.png"));
            this.cokbloc = ImageIO.read(this.getClass().getResource("/imgs/connexionokbloc.png"));
        }
        catch (IOException ex) {
            Logger.getLogger(DialogConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSplashPanel panIcon = new JSplashPanel(this.getWidth(), this.getHeight(), this.bi);
        panIcon.requestFocus();
        panIcon.setFocusable(true);
        panIcon.addKeyListener(new ValidationListener());
        this.add(panIcon);
        panIcon.add(new JTablePanel(200, 105, 0));
        JSplashPanel content = new JSplashPanel(290, 95, null, new BorderLayout());
        JTablePanel loginpane = new JTablePanel(0);
        loginpane.setPreferredSize(new Dimension(320, 60));
        JLabel loginlab = new JLabel("<html><b>Identifiant : &nbsp;&nbsp;&nbsp;</b></html>");
        loginlab.setForeground(Color.white);
        loginpane.add(loginlab);
        loginpane.add(this.loginField);
        JTablePanel passpane = new JTablePanel(0);
        passpane.setPreferredSize(new Dimension(320, 60));
        JLabel passlab = new JLabel("<html><b>Mot de passe :</b></html>");
        passlab.setForeground(Color.white);
        passpane.add(passlab);
        passpane.add(this.passField);
        this.passField.addKeyListener(new ValidationListener());
        this.loginField.addKeyListener(new ValidationListener());
        JSplashPanel connectpane = new JSplashPanel(290, 95, this.bibloc, new FlowLayout());
        connectpane.add(new JTablePanel(200, 14, 0));
        connectpane.add(loginpane);
        connectpane.add(passpane);
        content.add((Component)connectpane, "Center");
        JSplashPanel control = new JSplashPanel(120, 95, null, new FlowLayout());
        this.ok = new JLabel(new ImageIcon(this.getClass().getResource("/imgs/connexionok.png")));
        this.cancel = new JLabel(new ImageIcon(this.getClass().getResource("/imgs/connexionquitter.png")));
        control.add(new JTablePanel(200, 7, 0));
        control.add(this.ok);
        control.add(this.cancel);
        this.ok.addMouseListener(new MouseListener(){

            @Override
            public void mousePressed(MouseEvent e) {
                DialogConnexion.this.validation();
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
        this.cancel.addMouseListener(new MouseListener(){

            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
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
        panIcon.add(content);
        panIcon.add(control);
    }

    public void showConnexionDialog() {
        this.setVisible(true);
    }

    public void validation() {
        try {
            PreparedStatement requete = Main.connect.prepareStatement("select * from USERGPTL", 1004, 1007);
            ResultSet resultat = requete.executeQuery();
            boolean connexionOK = false;
            while (resultat.next()) {
                String login = (String)resultat.getObject("LOGIN");
                String pass = (String)resultat.getObject("PASS");
                String loginF = this.loginField.getText();
                String passF = this.passField.getText();
                if (!login.equals(loginF) || !pass.equals(passF)) continue;
                connexionOK = true;
            }
            if (connexionOK) {
                if (init) {
                    init = false;
                    this.setVisible(false);
                } else {
                    Main.gptl.setVisible(true);
                    this.setVisible(false);
                }
            } else {
                JOptionPane erreurConnect = new JOptionPane();
                JOptionPane.showMessageDialog(null, "Vos identifiants sont incorrects...", "Erreur de connexion", 0);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DialogConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class ValidationListener
    implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 10) {
                DialogConnexion.this.validation();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

}

