package sudoku.model;

import sudoku.model.exception.SudokuBoardException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class DB {

    private Connection connect() {
        String url = "jdbc:sqlite:C://sqlite/sudokuBoard";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(final String tableName, final Integer value, int id) {
        String sql = "INSERT INTO " + tableName + " (id,wartosc) VALUES(?,?)";
        //String sql = "INSERT INTO sudokuBoard (id,wartosc) VALUES(?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createNewDatabase() {

        try {
            Connection conn = this.connect();
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createTable(final String tableName) {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + " id INT PRIMARY KEY,\n"
                + " wartosc INT\n"
                + ");";
        //String drop = "DROP TABLE IF EXISTS"+ tableName;
        /*String sql = "CREATE TABLE IF NOT EXISTS sudokuBoard (\n"
                + " id INT PRIMARY KEY,\n"
                + " wartosc INT\n"
                + ");";*/

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            //stmt.execute(drop);
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Integer select(final String tableName, final int x, final int y) throws SudokuBoardException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SudokuBoardException(e.toString());
        }
        Integer tmp = 9 * x + y + 1;
        String strId = tmp.toString();
        String sql = "SELECT wartosc FROM " + tableName + " WHERE id=" + strId + ";";
        //String sql = "SELECT wartosc FROM sudokuBoard WHERE id=" + strId + ";";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            return rs.getInt("wartosc");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public DB() {

    }

}

