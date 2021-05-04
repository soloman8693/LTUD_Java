package Controller;

import Model.DataDictionaryDTO;
import Utils.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDictionaryController {


    public List<DataDictionaryDTO> search(String parameter) {
        List<DataDictionaryDTO> result = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection con = null;
        if (parameter.equals("")) return result;
        try {
            result = new ArrayList<>();
            con = Helper.connect();
            stmt = con.prepareStatement("select * from data_dictionary where lower(data_key) like ?");
            stmt.setString(1, "%" + parameter.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                DataDictionaryDTO temp = new DataDictionaryDTO();
                temp.setIddata_dictionary(rs.getInt(1));
                temp.setData_key(rs.getString(2));
                temp.setData_content(rs.getString(3));
                result.add(temp);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    public List<DataDictionaryDTO> loadAll() {
        List<DataDictionaryDTO> result = new ArrayList<>();
        Statement stmt = null;
        Connection con = null;
        try {
            result = new ArrayList<>();
            con = Helper.connect();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from data_dictionary");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                DataDictionaryDTO temp = new DataDictionaryDTO();
                temp.setIddata_dictionary(rs.getInt(1));
                temp.setData_key(rs.getString(2));
                temp.setData_content(rs.getString(3));
                result.add(temp);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Integer update(String key, String newKeyValue, String newContentValue) {
        List<DataDictionaryDTO> result = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection con = null;
        if (key.equals("") || newKeyValue.equals("") || newContentValue.equals("")) return 0;
        try {
            result = new ArrayList<>();
            con = Helper.connect();
            stmt = con.prepareStatement("update data_dictionary set data_key = ?,data_content = ? where data_key = ?");
            stmt.setString(1, newKeyValue);
            stmt.setString(2, newContentValue);
            stmt.setString(3, key);
            int rs = stmt.executeUpdate();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Integer add(String key, String descrip) {
        List<DataDictionaryDTO> result = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection con = null;
        if (key.equals("") || descrip.equals("")) return 0;
        try {
            con = Helper.connect();
            stmt = con.prepareStatement("INSERT INTO data_dictionary (data_key, data_content) VALUES (?,?)");
            stmt.setString(1, key);
            stmt.setString(2, descrip);
            int rs = stmt.executeUpdate();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<DataDictionaryDTO> findByKey(String key) {
        PreparedStatement stmt = null;
        List<DataDictionaryDTO> result = new ArrayList<>();
        Connection con = null;
        try {
            con = Helper.connect();
            stmt = con.prepareStatement("select * from data_dictionary where data_key = ?");
            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                DataDictionaryDTO temp = new DataDictionaryDTO();
                temp.setIddata_dictionary(rs.getInt(1));
                temp.setData_key(rs.getString(2));
                temp.setData_content(rs.getString(3));
                result.add(temp);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Integer delete(Integer id) {
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            con = Helper.connect();
            stmt = con.prepareStatement("Delete from data_dictionary where iddata_dictionary = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
