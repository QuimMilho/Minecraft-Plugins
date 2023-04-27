package net.quimmilho.plugins.bungee19;

import java.sql.*;
import java.util.ArrayList;

public class MySQL {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public MySQL() {
        openConnection();
    }

    //Opening

    private void openConnection() {
        createConnection();
        openStatement();
    }

    //Commands

    public boolean insertIntoDatabase(String table, String values) {
        checkDB();
        String mysqlCode = "INSERT INTO " + table + " VALUES (" + values + ");";
        try {
            statement.executeUpdate(mysqlCode);
        } catch (SQLException e) {
            Core.getInstance().getLogger().warning("Error adding to db!");
            Core.getInstance().getLogger().warning(mysqlCode);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertIntoDatabase(String table, String columns, String values) {
        checkDB();
        String mysqlCode = "INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ");";
        try {
            statement.executeUpdate(mysqlCode);
        } catch (SQLException e) {
            Core.getInstance().getLogger().warning("Error adding to db!");
            Core.getInstance().getLogger().warning(mysqlCode);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateDatabase(String table, String columns, String values, String where) {
        checkDB();
        String[] column = columns.split(",");
        String[] value = values.split(",");
        if (column.length != value.length) {
            Core.getInstance().getLogger().warning("Number of columns and values in update aren't " +
                    "the same!");
            return false;
        }
        String mysqlCode = "UPDATE " + table + " SET ";
        for (int i = 0; i < column.length; i++) {
            mysqlCode = mysqlCode.concat((column[i] + " = '" + value[i] + "'"));
            if (i < column.length - 1)
                mysqlCode = mysqlCode.concat(",");
        }
        mysqlCode = mysqlCode.concat(" WHERE " + where + ";");
        try {
            statement.executeUpdate(mysqlCode);
        } catch (SQLException e) {
            Core.getInstance().getLogger().warning("Error updating db!");
            Core.getInstance().getLogger().warning(mysqlCode);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteAllTableContents(String table) {
        checkDB();
        String mysqlCode = "DELETE FROM " + table + ";";
        try {
            statement.execute(mysqlCode);
        } catch (SQLException e) {
            Core.getInstance().getLogger().warning("Error deleting all table contents!");
            Core.getInstance().getLogger().warning(mysqlCode);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteTableContent(String table, String where) {
        checkDB();
        String mysqlCode = "DELETE FROM " + table + " WHERE " + where + ";";
        try {
            statement.execute(mysqlCode);
        } catch (SQLException e) {
            Core.getInstance().getLogger().warning("Error deleting table contents!");
            Core.getInstance().getLogger().warning(mysqlCode);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<String> selectAllFromDatabase(String table) {
        checkDB();
        String mysqlCode = "SELECT * FROM " + table + ";";
        try {
            resultSet =  statement.executeQuery(mysqlCode);
            return processResultSet();
        } catch (SQLException e) {
            Core.getInstance().getLogger().warning("Error loading all info from db!");
            Core.getInstance().getLogger().warning(mysqlCode);
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> selectAllFromDatabase(String table, String where) {
        checkDB();
        String mysqlCode = "SELECT * FROM " + table + " WHERE " + where + ";";
        try {
            resultSet =  statement.executeQuery(mysqlCode);
            return processResultSet();
        } catch (SQLException e) {
            Core.getInstance().getLogger().warning("Error loading all info from db with where!");
            Core.getInstance().getLogger().warning(mysqlCode);
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> selectFromDatabase(String table, String columns) {
        checkDB();
        String mysqlCode = "SELECT " + columns + " FROM " + table + ";";
        try {
            resultSet =  statement.executeQuery(mysqlCode);
            return processResultSet();
        } catch (SQLException e) {
            Core.getInstance().getLogger().warning("Error loading info from db!");
            Core.getInstance().getLogger().warning(mysqlCode);
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> selectFromDatabase(String table, String columns, String where) {
        checkDB();
        String mysqlCode = "SELECT " + columns + " FROM " + table + " WHERE " + where + ";";
        try {
            resultSet = statement.executeQuery(mysqlCode);
            return processResultSet();
        } catch (SQLException e) {
            Core.getInstance().getLogger().warning("Error loading info from db with where!");
            Core.getInstance().getLogger().warning(mysqlCode);
            e.printStackTrace();
        }
        return null;
    }

    //Information

    private ArrayList<String> processResultSet() throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int nColumns = resultSetMetaData.getColumnCount(), nRows = 0;
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            nRows++;
            for (int i = 1; i <= nColumns; i++) {
                temp.add(resultSet.getString(i));
            }
        }
        temp.add("" + nRows);
        return temp;
    }

    public int getRows(ArrayList<String> rs) {
        return Integer.parseInt(rs.get(rs.size() - 1));
    }

    private void checkDB() {
        try {
            if (connection.isClosed()) {
                createConnection();
                openStatement();
                return;
            }
            if (statement.isClosed()) {
                openStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QuimMilhoNetwork",
                    "lobby", "$r!5Av8nmB@Inma5");
        } catch (ClassNotFoundException | SQLException e) {
            Core.getInstance().getLogger().warning("Error connecting to db!");
            e.printStackTrace();
        }
    }

    private void openStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            Core.getInstance().getLogger().warning("Error creating statement!");
            e.printStackTrace();
        }
    }

    //Closing

    public void closeConnection() {
        try {
            statement.close();
        } catch (SQLException e) {
            Core.getInstance().getLogger().info("Error closing db statement!");
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            Core.getInstance().getLogger().info("Error closing connection to db!");
            e.printStackTrace();
        }
    }

}

