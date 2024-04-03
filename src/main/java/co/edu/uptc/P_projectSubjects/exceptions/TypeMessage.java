package co.edu.uptc.P_projectSubjects.exceptions;

import org.springframework.http.HttpStatus;

public enum TypeMessage {
    NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "Item Not Found", 404),
    NO_ITEMS(HttpStatus.BAD_REQUEST.value(), "No items in the list", 405),
    MAX_ITEMS(HttpStatus.BAD_REQUEST.value(), "Max items reached", 406  ),
    INFORMATION_INCOMPLETE(HttpStatus.BAD_REQUEST.value(), "Information Incomplete", 407),
    ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), "The information already exists in the system", 408),
    SAVE(HttpStatus.OK.value(), "Saved", 210);

    public final String message;
    public final int code;
    public final int codeHttp;

    private TypeMessage(int codeHttp, String message, int code) {
        {
            this.message = message;
            this.code = code;
            this.codeHttp = codeHttp;
        }

    }
}
