package pl.mizio.notesapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    public ErrorResponse(String message) {
        super();
        this.message = message;
    }

    private String message;
}
