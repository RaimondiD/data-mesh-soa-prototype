package org.example.REST.data_class;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class SQLInsertQuery {
    private String table;

    public SQLInsertQuery(String table, ArrayList<String> columns, ArrayList<String> values) {
        this.table = table;
        this.columns = columns;
        this.values = values;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<String> columns) {
        this.columns = columns;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    private ArrayList<String> columns;
    private ArrayList<String> values;
}
