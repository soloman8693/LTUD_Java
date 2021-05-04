package Utils;

import Model.DataDictionaryDTO;
import com.mysql.cj.util.StringUtils;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Helper {
    private static Connection con;

    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dictionnary", "root", "admin");
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void readFile() {
        try {
            File file = new File("C:\\Users\\hoa.luu-duc\\Downloads\\slang.txt");
            List<String> slangs = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            int i = 0;
            String qr = "";
            while ((st = br.readLine()) != null) {
                System.out.println(i++);
                String[] arr = st.split("`");
                if (arr.length > 1) {
                    String query = "Insert into data_dictionary (data_key, data_content) VALUES ('" + arr[0] + "','" + arr[1] + "')";
                    slangs.add(query);
                    qr = qr + query + "\n";
                }
            }
            System.out.println("");

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\hoa.luu-duc\\Downloads\\Convert_slang.txt"));
            writer.write(qr);

            writer.close();
        } catch (IOException ex) {

        }
    }

}
