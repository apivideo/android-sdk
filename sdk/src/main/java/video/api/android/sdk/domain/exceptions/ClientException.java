package video.api.android.sdk.domain.exceptions;

import androidx.annotation.Nullable;

public class ClientException extends Exception {
    private final String    message;
    private final Throwable cause;

    public ClientException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    @Nullable
    @Override
    public synchronized Throwable getCause() {
        return cause;
    }
}
