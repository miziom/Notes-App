package pl.mizio.notesapi.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String object, int id) {
        super("Could not found " + object + " with id: " + id);
    }

    public NotFoundException(int version) {
        super("Could not found historical notes with version: " + version);
    }
}
