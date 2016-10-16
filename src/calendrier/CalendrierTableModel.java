/*
 * Decompiled with CFR 0_110.
 */
package calendrier;

import calendrier.CalendrierInfos;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class CalendrierTableModel
extends AbstractTableModel
implements TableModel {
    Vector<List<CalendrierInfos>> tabinfos = new Vector();

    public CalendrierTableModel(Vector<List<CalendrierInfos>> i) {
        this.tabinfos = i;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return CalendrierInfos.class;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Court principal";
        }
        return "Court annexe";
    }

    @Override
    public int getRowCount() {
        return this.tabinfos.get(0) == null ? 0 : this.tabinfos.get(0).size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.tabinfos.get(columnIndex) == null ? null : this.tabinfos.get(columnIndex).get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        return true;
    }
}

