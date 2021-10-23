import entity.Action;
import entity.User;
import serviceJDBC.JDBCServiceAction;
import serviceJDBC.JDBCServiceUser;

import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {

        List<Action> actionList = new JDBCServiceAction().getAllActionInvite("admin@mail.ru");

        User user = new JDBCServiceUser().getUserById(actionList.get(0).getIdInviteUsers());
        actionList.get(0).setInvitedUsers(user);
        System.out.println(actionList);
        System.out.println(user);

    }
}
