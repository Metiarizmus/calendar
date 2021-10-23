package serviceJDBC;

import DBConnection.*;
import entity.Action;
import entity.TypeAction;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCServiceAction {
    private final Connection daoFactory = DBConnection.getConnection();
    private final PropertyInf propertyInf = new PropertyInf();

    public void addAction(Action action, int idToUser) {
        int idAction;

        try (Connection connection = daoFactory) {
            connection.setAutoCommit(false);
            try (PreparedStatement statementAction = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("ADD_ACTION"), PreparedStatement.RETURN_GENERATED_KEYS)) {
                String[] s = new String[]{action.getTitle(), action.getDescription(), String.valueOf(action.getDate()), String.valueOf(action.getTypeAction()), String.valueOf(action.getUser().getId())};

                int k = 1;
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


                if (action.getTypeAction().equals(TypeAction.EVENT)) {
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
                System.out.println("action add in db");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public List<Action> getAllActionForUser(String email) {
        List<Action> list = new ArrayList<>();

        try (Connection connection = daoFactory) {
            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("GET_FULL_ACTION"))) {
                statement.setString(1,email);
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

        try (Connection connection = daoFactory) {
            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("GET_INVITE_USERS"))) {
                statement.setString(1,inviteUsersEmail);
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

    private static Action getInfAction(ResultSet result) throws SQLException {
        User user = new User();
        Action action = new Action();
        action.setId(result.getInt("id"));
        action.setDescription(result.getString("description"));
        action.setTitle(result.getString("title"));
        action.setDate(result.getString("date"));
        action.setTypeAction(TypeAction.valueOf(result.getString("type_action")));
        user.setEmail(result.getString("email"));
        action.setUser(user);
        action.setIdInviteUsers(result.getInt("users_id"));

        return action;
    }




}
