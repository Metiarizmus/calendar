
import entity.Action;
import entity.Role;
import serviceJDBC.JDBCServiceAction;

import java.time.LocalDate;
import java.util.List;


class Main {
    public static void main(String[] args) {

        List<Action> actionList = new JDBCServiceAction().getAllActionUserForManager();

        for (int i = 0; i < actionList.size(); i++) {
            if (actionList.get(i).getUser().getRole().equals(Role.MANAGER) || actionList.get(i).getUser().getRole().equals(Role.ADMIN)) {
                actionList.remove(i);
            }
        }

        for (Action q : actionList) {
            System.out.println(q);
        }




    }
}
