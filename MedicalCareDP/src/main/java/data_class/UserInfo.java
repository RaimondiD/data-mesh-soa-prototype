package data_class;

import java.util.HashMap;

public class UserInfo {

    private String cf;
    private String id;
    private String action;
    private String del_columns;
    private String del_keys;
    private String format;
    private String profession;


    public UserInfo(){
    }




    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDel_columns() {
        return del_columns;
    }

    public void setDel_columns(String del_columns) {
        this.del_columns = del_columns;
    }

    public String getDel_keys() {
        return del_keys;
    }

    public void setDel_keys(String del_keys) {
        this.del_keys = del_keys;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
