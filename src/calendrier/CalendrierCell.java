/*
 * Decompiled with CFR 0_110.
 */
package calendrier;

import calendrier.CalendrierInfos;
import calendrier.CalendrierMain;
import dialog.DialogGestionMatchD;
import dialog.DialogGestionMatchS;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import util.JTablePanel;
import util.Joueur;

public class CalendrierCell
extends AbstractCellEditor
implements TableCellEditor,
TableCellRenderer {
    public static int selected = 0;
    JTablePanel panel;
    JLabel text = new JLabel();
    JTablePanel flagpane = new JTablePanel(0);
    JButton modifButton = new JButton("Modifier");
    JButton supprButton;
    CalendrierInfos infos;

    public CalendrierCell() {
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
        this.panel = new JTablePanel(new BorderLayout());
        this.panel.add((Component)this.text, "North");
        this.panel.add((Component)this.flagpane, "South");
        this.flagpane.setLayout(new FlowLayout(0));
    }

    public void mousePressed(CalendrierInfos iargs, JTable table) {
        if (iargs.getJoueurs() == null && iargs.getCourt() != 3) {
            if (iargs.getCourt() == 0) {
                DialogGestionMatchS msimple = new DialogGestionMatchS(null, "Gestion match simple", true, iargs.win, iargs);
                msimple.showGestionDialog();
            } else if (iargs.getCourt() == 1) {
                DialogGestionMatchD mdouble = new DialogGestionMatchD(null, "Gestion match double", true, iargs.win, iargs);
                mdouble.showGestionDialog();
            }
            this.updateData(iargs, true, table);
        }
        selected = iargs.getId();
    }

    public void updateData(CalendrierInfos infosArg, boolean isSelected, JTable table) {
        this.infos = infosArg;
        infosArg.toJPanel(this.flagpane);
        this.text.setText(infosArg.toString());
        if (isSelected) {
            this.panel.setCle(JTablePanel.ACTIV);
        } else {
            this.panel.setCle(JTablePanel.NO_ACTIV);
        }
        this.panel.setSize(this.panel.getWidth() + 1, this.panel.getHeight() + 1);
        this.panel.setSize(this.panel.getWidth() - 1, this.panel.getHeight() - 1);
        CalendrierMain.refreshInfosPlanning();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CalendrierInfos info = (CalendrierInfos)value;
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
        CalendrierInfos info = (CalendrierInfos)value;
        this.updateData(info, isSelected, table);
        return this.panel;
    }

}

