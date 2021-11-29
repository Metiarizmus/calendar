import entity.Action;
import enums.Role;
import enums.State;
import helper.HelperGetFullDate;
import service.JDBCServiceAction;
import service.JDBCServiceUser;

import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {

        JDBCServiceAction serviceAction = new JDBCServiceAction();
        HelperGetFullDate helperGetFullDate = new HelperGetFullDate();
        String[] date = helperGetFullDate.getNowDateAndPlusMonth();

        List<Action> actionListUser = serviceAction.actionBetweenDate(date[1],date[0]);

        System.out.println(actionListUser);
    }
}
