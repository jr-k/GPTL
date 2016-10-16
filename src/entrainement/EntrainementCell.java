/*
 * Decompiled with CFR 0_110.
 */
package entrainement;

import dialog.DialogAjoutEntrainement;
import entrainement.EntrainementInfos;
import entrainement.EntrainementMain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import util.Joueur;

public class EntrainementCell
extends AbstractCellEditor
implements TableCellEditor,
TableCellRenderer {
    JPanel panel;
    JLabel text = new JLabel();
    JPanel flagpane = new JPanel();
    JButton modifButton = new JButton("Modifier");
    JButton supprButton;
    EntrainementInfos infos;

    public EntrainementCell() {
        this.modifButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "Reading");
            }
        });
        this.supprButton = new JButton("Supprimer");
        this.supprButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "Reading ");
            }
        });
        this.panel = new JPanel(new BorderLayout());
        this.panel.add((Component)this.text, "North");
        this.panel.add((Component)this.flagpane, "South");
        this.flagpane.setLayout(new FlowLayout(0));
    }

    public void mousePressed(EntrainementInfos iargs, JTable table) {
        if (iargs.getJoueur() == null) {
            Vector<List<EntrainementInfos>> listinfoparjour = EntrainementMain.joursInfos;
            List<EntrainementInfos> filinfos = listinfoparjour.get(EntrainementMain.joursPane.getSelectedIndex());
            Vector<EntrainementInfos> iargsV = new Vector<EntrainementInfos>();
            for (int i = 0; i < filinfos.size(); ++i) {
                if (!filinfos.get(i).getHeureString().equals(iargs.getHeureString()) || filinfos.get(i).getNumeroTerrain() == iargs.getNumeroTerrain() || filinfos.get(i).getJoueur() == null) continue;
                iargsV.add(filinfos.get(i));
            }
            DialogAjoutEntrainement add = new DialogAjoutEntrainement(null, "Ajouter un entrainement - GPTL", true, iargs, iargsV);
            add.showEntrainementDialog();
            this.updateData(iargs, true, table);
        }
    }

    public void updateData(EntrainementInfos infosArg, boolean isSelected, JTable table) {
        this.infos = infosArg;
        infosArg.toJPanel(this.flagpane);
        this.text.setText(infosArg.toString());
        if (isSelected) {
            this.panel.setBackground(table.getSelectionBackground());
            this.flagpane.setBackground(table.getSelectionBackground());
        } else {
            this.panel.setBackground(table.getSelectionForeground());
            this.flagpane.setBackground(table.getSelectionForeground());
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        EntrainementInfos info = (EntrainementInfos)value;
        this.updateData(info, true, table);
        this.mousePressed(info, table);
        return this.panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        EntrainementInfos info = (EntrainementInfos)value;
        this.updateData(info, isSelected, table);
        return this.panel;
    }

}

