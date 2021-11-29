package service;

import db.DBConnection;
import db.PropertyInf;
import enums.Role;
import entity.User;
import enums.StateProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCServiceUser {

    private final Connection daoFactory = DBConnection.getConnection();
    private final PropertyInf propertyInf = new PropertyInf();

    public Role getByEmailAndPassword(String email, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(propertyInf.getDataFromProperties(StateProperties.SQL).getProperty("GET_BY_EMAIL_AND_PASSWORD"))) {
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

        try (Connection connection = daoFactory) {
            connection.setAutoCommit(false);
            try (PreparedStatement statementUser = connection.prepareStatement(propertyInf.getDataFromProperties(StateProperties.SQL).getProperty("ADD_USER"), PreparedStatement.RETURN_GENERATED_KEYS)) {
                String[] sUser = new String[]{user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()};

                int k = 1;
                for (String value : sUser) {
                    statementUser.setString(k++, value);
                }
                statementUser.executeUpdate();

                try (ResultSet generatedKeys = statementUser.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idUser = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

                try (PreparedStatement statementRole = connection.prepareStatement(propertyInf.getDataFromProperties(StateProperties.SQL).getProperty("ADD_ROLE"), PreparedStatement.RETURN_GENERATED_KEYS)) {
                    statementRole.setString(1, String.valueOf(role));

                    statementRole.executeUpdate();

                    try (ResultSet generatedKeys = statementRole.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            idRole = generatedKeys.getInt(1);
                        } else {
                            throw new SQLException("Creating role failed, no ID obtained.");
                        }
                    }
                }

                try (PreparedStatement statementUsersRole = connection.prepareStatement(propertyInf.getDataFromProperties(StateProperties.SQL).getProperty("ADD_ROLE_USERS"))) {
                    String[] sUsersRole = new String[]{String.valueOf(idUser), String.valueOf(idRole)};

                    int n = 1;
                    for (String value : sUsersRole) {
                        statementUsersRole.setString(n++, value);
                    }
                    statementUsersRole.executeUpdate();

                }
                connection.commit();

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void suspendUser(int id){
        try(Connection connection = DBConnection.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getDataFromProperties(StateProperties.SQL).getProperty("ADD_SUSPEND_USERS"))){
                statement.setInt(1,id);
                statement.executeUpdate();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void anSuspendUser(int id){
        try(Connection connection = DBConnection.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getDataFromProperties(StateProperties.SQL).getProperty("DELETED_SUSPEND_USERS"))){
                statement.setInt(1,id);
                statement.executeUpdate();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getUserByEmail(String email) {
        int k = 0;

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getDataFromProperties(StateProperties.SQL).getProperty("GET_USER_BY_EMAIL"))) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {
                        k = resultSet.getInt("id");
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return k;
    }

    public Role getRoleUsersById(int id) {

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getDataFromProperties(StateProperties.SQL).getProperty("GET_ROLE_BY_USERS_ID"))) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {
                         return Role.valueOf(resultSet.getString("role"));
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Role getRoleUsersByEmail(String email) {

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getDataFromProperties(StateProperties.SQL).getProperty("GET_ROLE_BY_EMAIL"))) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {
                        return Role.valueOf(resultSet.getString("role"));
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User getUserById(int id) {
        User user = new User();
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getDataFromProperties(StateProperties.SQL).getProperty("GET_USER_BY_ID"))) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {
                        user.setEmail(resultSet.getString("email"));
                        user.setFirstName(resultSet.getString("firstName"));
                        user.setLastName(resultSet.getString("lastName"));
                        return user;
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return null;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getDataFromProperties(StateProperties.SQL).getProperty("GET_ALL_USERS"))) {
                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        list.add(getInfUsers(resultSet,true));
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<User> getUsersByRole(Role role) {
        List<User> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getDataFromProperties(StateProperties.SQL).getProperty("GET_USERS_BY_ROLE"))) {
                statement.setString(1,role.name());
                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        list.add(getInfUsers(resultSet,false));
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }



    public boolean deletedUsers(int id) {
        try (Connection connection = DBConnection.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getDataFromProperties(StateProperties.SQL).getProperty("DELETE_USERS_BY_ID"))) {
                statement.setInt(1, id);
                statement.execute("SET FOREIGN_KEY_CHECKS=0");

                int k = statement.executeUpdate();
                if (k > 0) {
                    return true;
                }

                statement.execute("SET FOREIGN_KEY_CHECKS=1");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private User getInfUsers(ResultSet resultSet, boolean role) throws SQLException {

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setEmail(resultSet.getString("email"));
        user.setSuspend(resultSet.getInt("suspend"));
        if (role == true) {
            user.setRole(Role.valueOf(resultSet.getString("role")));
        }
        return user;
    }

    public static void main(String[] args) {
        System.out.println(new JDBCServiceUser().getUsersByRole(Role.USER));
    }


}
