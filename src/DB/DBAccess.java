/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author DELL
 */
public class DBAccess {
    private Connection con;
    private Statement stmt;

    public DBAccess() {
        try {
            MyConnection mycon = new MyConnection();
            con = mycon.getConnection();

            // Kiểm tra xem kết nối có null không
            if (con != null) {
                stmt = con.createStatement();
            } else {
                throw new SQLException("Không thể kết nối đến cơ sở dữ liệu");
            }
        } catch (SQLException e) {
            // Ghi lỗi chi tiết khi gặp sự cố kết nối
            JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Ghi lại các lỗi khác
            JOptionPane.showMessageDialog(null, "Lỗi khác: " + e.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int Update(String str) {
        try {
            // Kiểm tra xem statement có null không
            if (stmt != null) {
                return stmt.executeUpdate(str);
            } else {
                throw new SQLException("Statement chưa được khởi tạo.");
            }
        } catch (SQLException e) {
            // Thông báo lỗi SQL
            JOptionPane.showMessageDialog(null, "Lỗi khi thực hiện update: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return -1;
        } catch (Exception e) {
            // Xử lý lỗi chung
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public ResultSet Query(String str) {
        try {
            // Kiểm tra xem statement có null không
            if (stmt != null) {
                return stmt.executeQuery(str);
            } else {
                throw new SQLException("Statement chưa được khởi tạo.");
            }
        } catch (SQLException e) {
            // Thông báo lỗi SQL
            JOptionPane.showMessageDialog(null, "Lỗi khi thực hiện truy vấn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception e) {
            // Xử lý lỗi chung
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Connection getConnection() {
        return con;
    }

    // Đảm bảo đóng kết nối khi không còn sử dụng
    public void close() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi đóng kết nối: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
