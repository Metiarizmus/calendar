package serviceJDBC;

import DBConnection.*;
import entity.Action;
import entity.TypeAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCServiceAction {
    //private final Connection daoFactory = DBConnection.getInstance().getConnection();
    private final PropertyInf propertyInf = new PropertyInf();

    public void addAction(Action action, int idToUser) {
        int idAction;

        try (Connection connection = DBConnection.getInstance().getConnection()){
            connection.setAutoCommit(false);
            try (PreparedStatement statementAction = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("ADD_ACTION"),PreparedStatement.RETURN_GENERATED_KEYS)){
                String[] s = new String[]{action.getTitle(), action.getDescription(), String.valueOf(action.getDate()), String.valueOf(action.getTypeAction()), String.valueOf(action.getUser().getId())};

                int k = 1;
                for (String q : s){
                    statementAction.setString(k++,q);
                }

                statementAction.executeUpdate();

                try (ResultSet generatedKeys = statementAction.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idAction = generatedKeys.getInt(1);
                    }
                    else {
                        throw new SQLException("Creating action failed, no ID obtained.");
                    }
                }

                connection.rollback();
                if (action.getTypeAction().equals(TypeAction.EVENT)){
                    try (PreparedStatement statementInvite = connection.prepareStatement(propertyInf.getSqlQuery().getProperty("ADD_INVITE_USERS"))){
                        String[] ss = new String[]{String.valueOf(idAction), String.valueOf(idToUser)};
                        int kk = 1;
                        for (String q : ss){
                            statementInvite.setString(kk++,q);
                        }

                        statementInvite.executeUpdate();
                    }
                }
                System.out.println("action add in db");
                connection.commit();
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
