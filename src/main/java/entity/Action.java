package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Action {
    @Getter @Setter private int id;
    @Getter @Setter private String title;
    @Getter @Setter private String description;
    @Getter @Setter private String date;
    @Getter @Setter private TypeAction typeAction;
    @Getter @Setter private int acceptedEvent;
    @Getter @Setter private User user;
    @Getter @Setter private int idInviteUsers;
    @Getter @Setter private User invitedUsers;

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", typeAction=" + typeAction +
                ", user=" + user +
                ", idInviteUsers=" + idInviteUsers +
                ", fromUser='" + invitedUsers + '\'' +
                '}';
    }
}
