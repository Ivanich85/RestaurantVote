package restaurantvote.util;

public class SecondVoteDeniedException extends RuntimeException {
    public SecondVoteDeniedException(String message) {
        super(message);
    }
}
