package helper;

import entity.Action;
import enums.Role;
import service.JDBCServiceUser;

import java.util.Collections;
import java.util.List;

public class HelperOnlyUser {
    public List<Action> getActionForUser(List<Action> actionList) {

        JDBCServiceUser serviceUser = new JDBCServiceUser();
        for (int i = 0; i < actionList.size(); i++) {
            actionList.get(i).getUser().setRole(serviceUser.getRoleUsersById(actionList.get(i).getIdInviteUsers()));
            if (actionList.get(i).getUser().getRole() == (Role.MANAGER)) {
                actionList.set(i,null);

            }else
            if (actionList.get(i).getUser().getRole() == (Role.ADMIN)) {
                actionList.set(i,null);
            }
        }

        actionList.removeAll(Collections.singleton(null));

        return actionList;
    }
}
