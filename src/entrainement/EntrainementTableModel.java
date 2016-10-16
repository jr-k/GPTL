/*
 * Decompiled with CFR 0_110.
 */
package entrainement;

import entrainement.EntrainementInfos;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class EntrainementTableModel
extends AbstractTableModel
implements TableModel {
    List<EntrainementInfos> infos;

    public EntrainementTableModel(List<EntrainementInfos> i) {
        this.infos = i;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return EntrainementInfos.class;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "Tournoi GPTL - Gestion des courts d'entrainement";
    }

    @Override
    public int getRowCount() {
        return this.infos == null ? 0 : this.infos.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.infos == null ? null : this.infos.get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        return true;
    }
}

