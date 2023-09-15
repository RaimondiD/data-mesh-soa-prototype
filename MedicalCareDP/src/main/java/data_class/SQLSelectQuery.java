package data_class;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

import static helpers.StringHelpersFunction.reduceListStrings;

@XmlRootElement
public class SQLSelectQuery {

    private String dp;
    private String tablename;
    private ArrayList<String> columns;
    private ArrayList<String> conditions;
    private String lastRead;

    public SQLSelectQuery(){};

    public SQLSelectQuery(String dp, String tablename, ArrayList<String> columns) {
        this.dp = dp;
        this.tablename = tablename;
        this.columns = columns;
        conditions = new ArrayList<>();
    }

    public SQLSelectQuery(String dp, String tablename, ArrayList<String> columns, ArrayList<String> conditions) {
        this.dp = dp;
        this.tablename = tablename;
        this.columns = columns;
        this.conditions = conditions;
    }

    public SQLSelectQuery(String dp, String tablename, ArrayList<String> columns, ArrayList<String> conditions, String lastRead) {
        this.dp = dp;
        this.tablename = tablename;
        this.columns = columns;
        this.conditions = conditions;
        this.lastRead = lastRead;
    }

    public SQLSelectQuery(String tablename, ArrayList<String> columns) {
        this.tablename = tablename;
        this.columns = columns;
    }

    public void queryConditionPreProcess(){
        if (conditions == null)
            conditions = new ArrayList<>();
        for(int i=0; i < conditions.size(); i++){
            String clean_condition = conditions.get(i);
            clean_condition = clean_condition.replace("-"," ");
            clean_condition = clean_condition.replace("equals", "=");
            conditions.set(i,clean_condition);
        }
    }
    public ArrayList<String> getConditions() {
        return conditions;
    }


    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<String> columns) {
        this.columns = columns;
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

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String generateSelectQuery() {
        return "SELECT " + formatColumn() + " FROM " + tablename + " WHERE " + formatConditions();

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
        return reduceListStrings(conditions_copy,"AND").orElse(conditions_copy.get(0));


    }
}
