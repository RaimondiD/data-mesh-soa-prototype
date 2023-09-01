package org.example.REST.data_class;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

import static helpers.StringHelpersFunction.reduceListStrings;

@XmlRootElement
public class SQLSelectQuery {
    private String table;
    private ArrayList<String> columns;
    private ArrayList<String> conditions;
    private String lastRead;

    public SQLSelectQuery(){};

    public SQLSelectQuery(String table, ArrayList<String> columns) {
        this.table = table;
        this.columns = columns;
    }

    public SQLSelectQuery(String table, ArrayList<String> columns, ArrayList<String> conditions) {
        this.table = table;
        this.columns = columns;
        this.conditions = conditions;
    }

    public SQLSelectQuery(String table, ArrayList<String> columns, ArrayList<String> conditions, String lastRead) {
        this.table = table;
        this.columns = columns;
        this.conditions = conditions;
        this.lastRead = lastRead;
    }

    public ArrayList<String> getConditions() {
        return conditions;
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
    public void setConditions(ArrayList<String> conditions) {
        this.conditions = conditions;
    }

    public String getLastRead() {
        return lastRead;
    }

    public void setLastRead(String lastRead) {
        this.lastRead = lastRead;
    }

    public String generateSelectQuery() {
        return "SELECT " + formatColumn() + " FROM " + table + " WHERE " + formatConditions();

    }
    private String formatColumn(){
        return reduceListStrings(columns,",").orElse(columns.get(0));
    }
    private String formatConditions(){
        ArrayList<String> conditions_copy = new ArrayList<>(conditions); //avoide side effects
        if(lastRead != null){
            conditions_copy.add("date_of_insertion > "+ lastRead );
        }
        if (conditions_copy.isEmpty()) {
            conditions_copy.add("true");
        }
        return reduceListStrings(conditions_copy,"AND").orElse(conditions.get(0));


    }
}
