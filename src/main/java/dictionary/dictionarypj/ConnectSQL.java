//package dictionary.dictionarypj;
//
//import java.io.*;
//import java.sql.Connection;
//import java.sql.*;
//
//public class ConnectSQL {
//
//    public static Connection getConnect() throws ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306/sqldiction?characterEncoding=utf8";
//        String user = "root";
//        String password = "aA123456789";
//
//        try {
//            Connection con = (Connection) DriverManager.getConnection(url, user, password);
//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("select * from btl_edict");
//            while(rs.next())
//                System.out.println(rs.getString("word"));
//            System.out.println(rs.getString("detail"));
//            con.close();
//            return con;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//
////    public static String SearchSql(String word) throws SQLException {
////        String sql = "select * from tbl_edict where word = ?";
////        Connection con = null;
////        PreparedStatement ps=con.prepareStatement(sql);
////        //truyền tham số
////        ps.setString(1, word);//qui định tham số
////        ResultSet rs=ps.executeQuery();//thực thi
////        String explain = ((ResultSet) rs).getString(3);
////        explain.replace("<br />", "\n").
////                replace("<C><F><I><N><Q>@", "").replace("</Q></N></I></F></C>", "")
////                .replace("-", "-\n");
////        return explain;
////    }
////
////    //thêm từ vào mysql
////    public static void addWord(String target, String explain) throws ClassNotFoundException, SQLException {
////        String sql = "INSERT INTO tbl_edict(word, detail) values (?,?)";
////        Connection conect = ConnectSQL.getConnect();
////        PreparedStatement ps = conect.prepareStatement(sql);
////        ps.setString(1, target);
////        ps.setString(2, explain);
////        ps.execute();
////    }
////
////    //xoa tu trong SQL
////    public static void deleteWord(String target) throws ClassNotFoundException, SQLException {
////        Connection conect = ConnectSQL.getConnect();
////        String sql = "DELETE FROM tbl_edict where word = ?";
////        PreparedStatement ps = conect.prepareStatement(sql);
////        ps.setString(1,target);
////        ps.execute();
////    }
////
////    public static void editWord(String target, String explain) throws SQLException, ClassNotFoundException {
////        Connection conect = ConnectSQL.getConnect();
////        String sql = "UPDATE tbl_edict SET detail = ? where word = ?";
////        try (PreparedStatement ps = conect.prepareStatement(sql)) {
////            ps.setString(1, explain);
////            ps.setString(2, target);
////            ps.execute();
////        }
////    }
//
//    public static void main(String[] args) {
//        try {
//            getConnect();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//}
