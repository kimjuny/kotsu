package ek.kotsu.basic.exception;

/**
 * Created by Eric Kim on 16/3/24.
 */
public class StrategyNotFoundException extends RuntimeException {

    public StrategyNotFoundException(String message) {
        super(message);
    }

}
