package pl.mizio.notesapi.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class NoteTest {

    @Test
    void creatingNoteObject_titleAndContentNull_throwsIllegalArgumentException() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Note(null, null));
    }

    @Test
    void creatingNoteObject_titleNull_throwsIllegalArgumentException() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Note(null, "content"));
    }

    @Test
    void creatingNoteObject_contentNull_throwsIllegalArgumentException() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Note("title", null));
    }

    @Test
    void creatingNoteObject_titleAndContentBlank_throwsIllegalArgumentException() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Note("", ""));
    }

    @Test
    void creatingNoteObject_titleBlank_throwsIllegalArgumentException() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Note("", "content"));
    }

    @Test
    void creatingNoteObject_contentBlank_throwsIllegalArgumentException() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Note("title", ""));
    }

}