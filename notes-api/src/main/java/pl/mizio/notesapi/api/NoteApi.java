package pl.mizio.notesapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mizio.notesapi.dao.Note;
import pl.mizio.notesapi.dto.NoteDTO;
import pl.mizio.notesapi.dao.NoteUpdate;
import pl.mizio.notesapi.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteApi {

    private NoteService noteManager;

    @Autowired
    public NoteApi(NoteService noteManager) {
        this.noteManager = noteManager;
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<NoteDTO> getAll() {
        return noteManager.findAll();
    }

    @GetMapping
    public NoteDTO getById(@RequestParam int id) {
        return noteManager.findById(id);
    }

    @PostMapping
    public NoteDTO addNote(@RequestBody Note note) {
        return noteManager.save(note);
    }

    @PutMapping
    public NoteDTO updateNote(@RequestParam int id, @RequestBody NoteUpdate noteUpdate) {
        return noteManager.update(id, noteUpdate);
    }

    @DeleteMapping
    public void deleteNote(@RequestParam int id) {
        noteManager.deleteById(id);
    }
}
