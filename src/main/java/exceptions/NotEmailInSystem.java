package exceptions;

public class NotEmailInSystem extends IllegalArgumentException {
    public NotEmailInSystem(String s) {
        super(s);
    }
}
