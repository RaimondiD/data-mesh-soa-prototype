package data_class;

import orchestrator.OrchestratorMethods;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement
public class OpaReadResult
{
    private boolean allow;
    private String tablename = "medicalprescription";
    private ArrayList<String> query_columns;
    private ArrayList<String> query_conditions;
    private String user_id;
    private boolean cdc;  // must extend the class to use this information to grand CDC


    public OpaReadResult(boolean allow, String tablename, ArrayList<String> query_columns, ArrayList<String> query_conditions, String user_id, boolean cdc) {
        this.allow = allow;
        this.tablename = tablename;
        this.query_columns = query_columns;
        this.query_conditions = query_conditions;
        this.user_id = user_id;
        this.cdc = cdc;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public OpaReadResult(boolean allow, ArrayList<String> query_columns, ArrayList<String> query_conditions, String user_id) {
        this.allow = allow;
        this.query_columns = query_columns;
        this.query_conditions = query_conditions;
        this.user_id = user_id;
    }

    public OpaReadResult(boolean allow, ArrayList<String> query_columns, ArrayList<String> query_conditions, String user_id, boolean cdc) {
        this.allow = allow;
        this.query_columns = query_columns;
        this.query_conditions = query_conditions;
        this.user_id = user_id;
        this.cdc = cdc;
    }


    public OpaReadResult(){};

    public SQLSelectQuery generateSQLSelectQuery(){     // in CDC extended class here must check the last time the user do this types of read
        return new SQLSelectQuery(OrchestratorMethods.DPNAME,tablename,query_columns,query_conditions); //and then update it with the actual time. It is necessary use some
          //persistence method, like NoSql DB.
    }
    public boolean isAllow() {
        return allow;
    }

    public ArrayList<String> getQuery_columns() {
        return query_columns;
    }

    public ArrayList<String> getQuery_conditions() {
        return query_conditions;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public void setQuery_columns(ArrayList<String> query_columns) {
        this.query_columns = query_columns;
    }

    public void setQuery_conditions(ArrayList<String> query_conditions) {
        this.query_conditions = query_conditions;
    }


    public String getTablename() {
        return tablename;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isCdc() {
        return cdc;
    }

    public void setCdc(boolean cdc) {
        this.cdc = cdc;
    }

}
