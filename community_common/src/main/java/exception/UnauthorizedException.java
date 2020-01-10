package exception;

public class UnauthorizedException extends RuntimeException {
    private UnauthorizedException(String message) {
        super(message);
    }

    public static UnauthorizedException create(String message) {
        return new UnauthorizedException(message);
    }
    public static UnauthorizedException create() {
        return new UnauthorizedException(null);
    }
}
