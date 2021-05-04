package Model;

import java.io.Serializable;

public class DataDictionaryDTO implements Serializable {
    private Integer iddata_dictionary;
    private String data_key;
    private String data_content;

    public Integer getIddata_dictionary() {
        return iddata_dictionary;
    }

    public void setIddata_dictionary(Integer iddata_dictionary) {
        this.iddata_dictionary = iddata_dictionary;
    }

    public String getData_key() {
        return data_key;
    }

    public void setData_key(String data_key) {
        this.data_key = data_key;
    }

    public String getData_content() {
        return data_content;
    }

    public void setData_content(String data_content) {
        this.data_content = data_content;
    }
}
