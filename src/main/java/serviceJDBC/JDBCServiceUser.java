package serviceJDBC;

import DBConnection.*;
import entity.Role;
import entity.User;

import java.sql.*;

public class JDBCServiceUser {

    //private final Connection daoFactory = DBConnection.getInstance().getConnection();
    private final PropertyInf propertyInf = new PropertyInf();

    public Role getByEmailAndPassword(String email, String password) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(propertyInf.getSqlQuery().getProperty("GET_BY_EMAIL_AND_PASSWORD"))) {
                stmt.setString(1, email);
                stmt.setString(2, password);

                try (ResultSet resultSet = stmt.executeQuery()) {
                    if (resultSet.next()) {
                        return Role.valueOf(resultSet.getString("role"));
                    }
                }
            } catch (SQLException ignored) {

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addUser(User user, Role role) {
        int idUser;
        int idRole;

        try(Connection connection = DBConnection.getInstance().getConnection()) {
             connection.setAutoCommit(false);
            try (PreparedStatement statementUser = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("ADD_USER"),PreparedStatement.RETURN_GENERATED_KEYS)){
                String[] sUser = new String[]{user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()};

                int k = 1;
                for (String value : sUser) {
                    statementUser.setString(k++, value);
                }
                statementUser.executeUpdate();

                try (ResultSet generatedKeys = statementUser.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idUser = generatedKeys.getInt(1);
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

            try (PreparedStatement statementRole = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("ADD_ROLE"),PreparedStatement.RETURN_GENERATED_KEYS)){
                statementRole.setString(1, String.valueOf(role));

                statementRole.executeUpdate();

                try (ResultSet generatedKeys = statementRole.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idRole = generatedKeys.getInt(1);
                    }
                    else {
                        throw new SQLException("Creating role failed, no ID obtained.");
                    }
                }
            }

            try (PreparedStatement statementUsersRole = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("ADD_ROLE_USERS"))){
                String[] sUsersRole = new String[]{String.valueOf(idUser), String.valueOf(idRole)};

                int n = 1;
                for (String value : sUsersRole) {
                    statementUsersRole.setString(n++, value);
                }
                statementUsersRole.executeUpdate();

            }
              connection.commit();
            }


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getUserByEmail(String email) {
        int k = 0;

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("GET_USER_BY_EMAIL"))) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {

                    if(resultSet.next()) {
                        k = resultSet.getInt("id");
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return k;
    }


    public static void main(String[] args) {

    }
}
