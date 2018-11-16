package edu.pushnoe.student.ui;

import edu.pushnoe.student.domain.Street;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StreetTableModel extends AbstractTableModel {

    public static  final int OBJECT_COL        = -1;
    private static final int STREED_CODE_COL  =  0;
    private static final int STREED_NAME_COL =  1;

    private String[] columNames = {"Код улицы", "Название улицы"};

    private List<Street> streets;

    public StreetTableModel(List<Street> streets){

        this.streets = streets;

    }

    @Override
    public int getRowCount() {

        return streets.size();
    }

    @Override
    public int getColumnCount() {

        return columNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {

        Street tempStreet  = streets.get(row);

        switch (col){
            case STREED_CODE_COL :
                return tempStreet.getStreetCode();
            case STREED_NAME_COL :
                return tempStreet.getStreetName();
                default:
                    return tempStreet.getStreetName();
        }
    }
}