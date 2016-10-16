/*
 * Decompiled with CFR 0_110.
 */
package dialog;

import calendrier.CalendrierInfos;
import dialog.DialogAjoutDouble;
import dialog.DialogAjoutSimple;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogSimpleDouble
extends JDialog {
    private JDialog dialog;

    public DialogSimpleDouble(JFrame parent, String name, String content, boolean modal, CalendrierInfos iargs) {
        super(parent, name, modal);
        this.dialog = this;
        this.setSize(440, 90);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(1);
        this.initDialog(content, iargs);
    }

    public void showSDDialog() {
        this.setVisible(true);
    }

    public void initDialog(String content, final CalendrierInfos iargs) {
        JPanel labPane = new JPanel();
        labPane.add(new JLabel(content));
        JPanel contents = new JPanel();
        contents.add(labPane);
        JPanel control = new JPanel();
        JButton simples = new JButton("Match simple");
        JButton doubles = new JButton("Match double");
        control.add(simples);
        control.add(doubles);
        simples.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogSimpleDouble.this.setVisible(false);
                DialogAjoutSimple add = new DialogAjoutSimple(null, "Ajouter un match simple - GPTL", true, iargs);
                add.showAjoutDialog();
            }
        });
        doubles.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogSimpleDouble.this.setVisible(false);
                DialogAjoutDouble add = new DialogAjoutDouble(null, "Ajouter un match double - GPTL", true, iargs);
                add.showAjoutDialog();
            }
        });
        this.getContentPane().add((Component)contents, "Center");
        this.getContentPane().add((Component)control, "South");
    }

}

