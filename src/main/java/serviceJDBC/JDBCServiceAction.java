package serviceJDBC;

import DBConnection.*;
import entity.Action;
import entity.TypeAction;
import entity.User;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class JDBCServiceAction {
    private final Connection daoFactory = DBConnection.getConnection();
    private static final PropertyInf propertyInf = new PropertyInf();

    public int addAction(Action action, int idToUser) {
        int idAction = 0;
        int idActionFromInvite = 0;

        Connection connection = null;

        try {
            connection = DBConnection.getConnection();

            try (PreparedStatement statementAction = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("ADD_ACTION"), PreparedStatement.RETURN_GENERATED_KEYS)) {
                String[] s = new String[]{action.getTitle(), action.getDescription(), String.valueOf(action.getDate()), String.valueOf(action.getTypeAction()), String.valueOf(action.getUser().getId())};

                int k = 1;
                connection.setAutoCommit(false);

                for (String q : s) {
                    statementAction.setString(k++, q);
                }

                statementAction.executeUpdate();

                try (ResultSet generatedKeys = statementAction.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idAction = generatedKeys.getInt(1);
                        System.out.println("idAction = " + idAction);
                    } else {
                        throw new SQLException("Creating action failed, no ID obtained.");
                    }
                }

                if (action.getTypeAction().equals(TypeAction.EVENT) && idToUser > 0) {

                        try (PreparedStatement statementInvite = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("ADD_INVITE_USERS"))) {

                            String[] ss = new String[]{String.valueOf(idAction), String.valueOf(idToUser)};
                            int kk = 1;
                            for (String q : ss) {
                                statementInvite.setString(kk++, q);
                            }
                            statementInvite.executeUpdate();

                        }
                }

                connection.commit();

                connection.setAutoCommit(true);
                System.out.println("action add in db");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception e) {
                System.err.println("connection close error");
            }

        }

        return idAction;
    }

    public List<Action> getAllActionForUser(String email) {
        List<Action> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("GET_FULL_ACTION"))) {
                statement.setString(1, email);
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        list.add(getInfAction(result));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return list;
    }

    public List<Action> getAllActionUserForManager() {
        List<Action> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("GET_FULL_ACTION_MANAGER"))) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        list.add(getInfAction(result));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return list;
    }

    public List<Action> getAllActionInvite(String inviteUsersEmail) {
        List<Action> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("GET_INVITE_USERS"))) {
                statement.setString(1, inviteUsersEmail);
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        list.add(getInfAction(result));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return list;
    }

    public Action getActionById(int id) {
        Action action = new Action();
        try (Connection connection = DBConnection.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("GET_ACTION_BY_ID"))) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {
                        action.setTitle(resultSet.getString("title"));
                        action.setDate(resultSet.getString("date"));
                        action.setTypeAction(TypeAction.valueOf(resultSet.getString("type_action")));
                        action.setDescription(resultSet.getString("description"));
                        return action;
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return action;
    }

    public boolean deletedInvite(int id) {
        try (Connection connection = DBConnection.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("DELETE_INVITE_BY_ID"))) {
                statement.setInt(1, id);

                int k = statement.executeUpdate();
                if (k > 0) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int[] getDataFromInvited(int id) {
        int[] k = new int[2];

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("GET_ID_ACTION_FROM_INVITED"))) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {
                        k[0] = resultSet.getInt("action_id");
                        k[1] = resultSet.getInt("users_id");

                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return k;
    }


    public void changeAcceptedEvent(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("ADD_ACCEPTED_EVENT"))) {
                statement.setInt(1, id);
                statement.executeUpdate();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Action> actionBetweenDate(String start, String end) {
        List<Action> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("GET_ACTION_BETWEEN"))) {
                statement.setString(1, start);
                statement.setString(2, end);
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        list.add(getInfAction(result));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return list;
    }

    public int countAcceptedEventBetweenDate(String start, String end){
        int k = 0;
        try(Connection connection = DBConnection.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("GET_COUNT_ACCEPTED_MONTH"))){
                statement.setString(1, start);
                statement.setString(2,end);
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        if(result.getInt("accepted_event") == 1){
                            k++;
                        }
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return k;
    }

    private static Action getInfAction(ResultSet result) throws SQLException {
        User user = new User();
        Action action = new Action();
        action.setId(result.getInt("id"));
        action.setDescription(result.getString("description"));
        action.setTitle(result.getString("title"));
        action.setDate(result.getString("date"));
        action.setTypeAction(TypeAction.valueOf(result.getString("type_action")));
        action.setAcceptedEvent(result.getInt("accepted_event"));
        user.setEmail(result.getString("email"));
        user.setSuspend(result.getInt("suspend"));
        action.setUser(user);
        action.setIdInviteUsers(result.getInt("users_id"));

        return action;
    }


    public static void main(String[] args) {
        JDBCServiceAction serviceAction = new JDBCServiceAction();

        System.out.println(serviceAction.countAcceptedEventBetweenDate("2021-01-08","2021-11-18"));
    }
}
