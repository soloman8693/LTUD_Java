package Controller;

import Model.DataDictionaryDTO;
import Model.HistoryDTO;
import Utils.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryController {

    public List<HistoryDTO> loadAll() {
        List<HistoryDTO> result = new ArrayList<>();
        Statement stmt = null;
        Connection con = null;
        try {
            result = new ArrayList<>();
            con = Helper.connect();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from history h order by h.createdDate desc limit 10");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                HistoryDTO temp = new HistoryDTO();
                temp.setIdHistory(rs.getInt(1));
                temp.setInput(rs.getString(2));
                temp.setCreatedDate(rs.getDate(3));
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

    public Integer add(String key) {
        List<DataDictionaryDTO> result = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection con = null;
        if (key.equals("")) return 0;
        try {
            con = Helper.connect();
            stmt = con.prepareStatement("INSERT INTO history (input, createdDate) VALUES (?,now())");
            stmt.setString(1, key);
//            stmt.setString(2, descrip);
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
}
