package app.exceptions;

public class TeamManagerException extends Exception{
    private ErrorCode errorCode;

    public TeamManagerException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getDetailMessage(){
        return errorCode + ": " + getMessage() ;
    }
}
