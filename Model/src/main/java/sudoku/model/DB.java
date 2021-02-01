package sudoku.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.model.exception.SudokuBoardException;
import sudoku.model.exception.SudokuDataBaseException;

import java.sql.*;

public class DB {
    private static final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);
    private Connection connect() throws SudokuDataBaseException {
        String url = "jdbc:sqlite:C://sqlite/sudokuBoard";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            throw new SudokuDataBaseException(e.getMessage());
        }
        return conn;
    }

    public void insert(final String tableName, final Integer value, int id) throws SudokuDataBaseException {
        String sql = "INSERT INTO " + tableName + " (id,wartosc) VALUES(?,?)";
        //String sql = "INSERT INTO sudokuBoard (id,wartosc) VALUES(?,?);";
        try {
            Connection conn = this.connect();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, value);
            pstmt.executeUpdate();
            conn.commit();
            conn.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new SudokuDataBaseException(e.getMessage());
        }
    }

    public void createNewDatabase() throws SudokuDataBaseException {

        try {
            Connection conn = this.connect();
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                logger.info("Database created");
            }

        } catch (SQLException e) {
            throw new SudokuDataBaseException(e.getMessage());
        }
    }
    public void createTable(final String tableName) throws SudokuDataBaseException {

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + " id INT PRIMARY KEY,\n"
                + " wartosc INT\n"
                + ");";

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException e) {
            throw  new SudokuDataBaseException(e.getMessage());

        }
    }
    public Integer select(final String tableName, final int x, final int y) throws SudokuBoardException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SudokuDataBaseException(e.toString());
        }
        Integer tmp = 9 * x + y + 1;
        String strId = tmp.toString();
        String sql = "SELECT wartosc FROM " + tableName + " WHERE id=" + strId + ";";

        try {
            Connection conn = this.connect();
            conn.setAutoCommit(false);
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            int value = rs.getInt("wartosc");
            conn.commit();
            stmt.close();
            rs.close();
            conn.close();
            return value;

        } catch (SQLException e) {
            throw new SudokuDataBaseException(e.getMessage());
        }
    }
    public void close(){
        Connection conn= null;
        try {
            conn = this.connect();
        } catch (SudokuDataBaseException e) {
            e.printStackTrace();
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public DB() {

    }

}

