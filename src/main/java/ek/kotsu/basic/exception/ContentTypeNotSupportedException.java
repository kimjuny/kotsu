package ek.kotsu.basic.exception;

/**
 * Created by Eric Kim on 16/2/24.
 */
public class ContentTypeNotSupportedException extends Exception {

    public ContentTypeNotSupportedException() {
        super("Content-Type not supported.");
    }

    public ContentTypeNotSupportedException(String msg) {
        super(msg);
    }

}
