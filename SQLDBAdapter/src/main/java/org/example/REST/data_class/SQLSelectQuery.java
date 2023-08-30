package org.example.REST.data_class;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class SQLSelectQuery {
    private String table;
    private ArrayList<String> columns;
    private ArrayList<String> conditions;
    private  boolean incrementalData;


    public SQLSelectQuery(String table, ArrayList<String> columns, ArrayList<String> conditions) {
        this.table = table;
        this.columns = columns;
        this.conditions = conditions;
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

    public ArrayList<String> getCondition() {
        return conditions;
    }

    public void setCondition(ArrayList<String> conditions) {
        this.conditions = conditions;
    }
}
