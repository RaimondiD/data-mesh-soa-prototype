package data_class;

import javax.swing.text.html.Option;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.Optional;

import static helpers.StringHelpersFunction.reduceListStrings;
import static helpers.StringHelpersFunction.stringOfActualTime;

@XmlRootElement
public class SQLInsertQuery {
    private String table;
    private ArrayList<String> columns;
    private ArrayList<String> values;


    public SQLInsertQuery(){};
    public SQLInsertQuery(String table, ArrayList<String> columns, ArrayList<String> values) {
        this.table = table;
        this.columns = columns;
        this.values = values;
    }

    public String generateInsertQuery(){
        return "INSERT INTO " + table + "(" + process_columns() + ") \n VALUES (" + process_values() + ")";
    }

    private String process_columns(){
        ArrayList<String> copy_of_column = new ArrayList<>(columns);
        copy_of_column.add("date_of_insertion"); //avoid side effect
        Optional<String> reduced_column = reduceListStrings(copy_of_column, ",");
        return reduced_column.orElse(copy_of_column.get(0));
    }

    private String process_values(){
        ArrayList<String> copy_of_values = new ArrayList<>(values);
        copy_of_values.add("'"+ stringOfActualTime()+"'");
        return reduceListStrings(copy_of_values, ",").orElse(copy_of_values.get(0));
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


}
