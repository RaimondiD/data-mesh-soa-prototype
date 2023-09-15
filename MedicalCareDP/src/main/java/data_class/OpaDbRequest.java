package data_class;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OpaDbRequest {

    private String dp;
    private String request;
    private SQLSelectQuery query;

    public OpaDbRequest() {
    }

    public OpaDbRequest(String dp, String request, SQLSelectQuery query) {
        this.dp = dp;
        this.request = request;
        this.query = query;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public SQLSelectQuery getQuery() {
        return query;
    }

    public void setQuery(SQLSelectQuery query) {
        this.query = query;
    }
}
