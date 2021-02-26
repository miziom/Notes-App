package pl.mizio.notesapi.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoteUpdate {

    private final String title;

    private final String content;

}
