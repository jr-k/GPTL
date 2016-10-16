/*
 * Decompiled with CFR 0_110.
 */
package entrainement;

import entrainement.EntrainementCell;
import entrainement.EntrainementInfos;
import entrainement.EntrainementTableModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import util.Joueur;

public class EntrainementMain
extends JFrame {
    JPanel conteneur = new JPanel();
    public static JTabbedPane joursPane = new JTabbedPane();
    public static Vector<List<EntrainementInfos>> joursInfos = new Vector();
    public static Vector<JTable> joursTables = new Vector();
    JButton precedent;
    JButton suivant;
    public int nombreTerrains;

    public EntrainementMain() {
        super("Gestion des entrainements");
        this.precedent = new JButton(new ImageIcon(this.getClass().getResource("/imgs/prev.png")));
        this.suivant = new JButton(new ImageIcon(this.getClass().getResource("/imgs/suiv.png")));
        this.nombreTerrains = 4;
        this.setDefaultCloseOperation(1);
        this.setSize(800, 400);
        this.setContentPane(this.conteneur);
        this.conteneur.setLayout(new BorderLayout());
        try {
            this.setIconImage(ImageIO.read(this.getClass().getResource("/imgs/logogptl.png")));
        }
        catch (IOException es) {
            // empty catch block
        }
        this.add((Component)this.suivant, "East");
        this.add((Component)this.precedent, "West");
        this.add((Component)joursPane, "Center");
        this.initCalendrier();
        this.suivant.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (EntrainementMain.joursPane.getSelectedIndex() < EntrainementMain.joursPane.getTabCount() - 1) {
                    EntrainementMain.joursPane.setSelectedIndex(EntrainementMain.joursPane.getSelectedIndex() + 1);
                } else {
                    EntrainementMain.joursPane.setSelectedIndex(0);
                }
            }
        });
        this.precedent.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (EntrainementMain.joursPane.getSelectedIndex() > 0) {
                    EntrainementMain.joursPane.setSelectedIndex(EntrainementMain.joursPane.getSelectedIndex() - 1);
                } else {
                    EntrainementMain.joursPane.setSelectedIndex(EntrainementMain.joursPane.getTabCount() - 1);
                }
            }
        });
        this.setVisible(false);
    }

    public void initCalendrier() {
        String[] jours = new String[]{"Samedi", "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
        String[] heures = new String[]{"8", "11", "15", "18", "21"};
        boolean finale = false;
        for (int i = 0; i < jours.length; ++i) {
            ArrayList<EntrainementInfos> infos = new ArrayList<EntrainementInfos>();
            for (int j = 0; j < heures.length; ++j) {
                for (int t = 1; t <= this.nombreTerrains; ++t) {
                    infos.add(new EntrainementInfos(heures[j], null, t));
                }
            }
            JTable table = new JTable(new EntrainementTableModel(infos));
            table.setDefaultRenderer(EntrainementInfos.class, new EntrainementCell());
            table.setDefaultEditor(EntrainementInfos.class, new EntrainementCell());
            table.setRowHeight(70);
            joursInfos.add(infos);
            joursTables.add(table);
            joursPane.add((Component)new JScrollPane(table), jours[i]);
        }
    }

}

