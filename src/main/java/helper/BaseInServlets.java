package helper;

import entity.Action;
import entity.User;
import service.JDBCServiceAction;
import service.JDBCServiceUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseInServlets {


    public StringBuilder getRequest(BufferedReader s) throws IOException {

        StringBuilder sb = new StringBuilder();
        String line = null;

            BufferedReader reader = s;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }


        return sb;
    }

    public List<Action> getDataForInvite(String email) {
        List<Action> actionList = new JDBCServiceAction().getAllActionInvite(email);
        List<Integer> idInvitedUsers = new ArrayList<>();

        for (Action q : actionList) {
            idInvitedUsers.add(q.getIdInviteUsers());
        }

        for (int i = 0; i < idInvitedUsers.size(); i++) {
            User user = new JDBCServiceUser().getUserById(idInvitedUsers.get(i));

            actionList.get(i).setInvitedUsers(user);
        }

        return actionList;
    }
}
