package ek.kotsu.basic.struct;

/**
 * Created by Eric Kim on 16/3/24.
 */
public class ValidResult {

    private boolean result;

    private String message;

    public ValidResult(boolean result) {
        this.result = result;
    }

    public ValidResult(boolean result, String message) {

        this.result = result;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
