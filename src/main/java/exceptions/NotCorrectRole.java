package exceptions;

public class NotCorrectRole extends IllegalArgumentException {
    public NotCorrectRole(String s) {
        super(s);
    }
}
