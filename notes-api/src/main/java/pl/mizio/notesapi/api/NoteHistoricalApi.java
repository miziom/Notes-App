package pl.mizio.notesapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mizio.notesapi.dto.NoteDTO;
import pl.mizio.notesapi.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/historical")
public class NoteHistoricalApi {

    private NoteService noteManager;

    @Autowired
    public NoteHistoricalApi(NoteService noteManager) {
        this.noteManager = noteManager;
    }

    @GetMapping
    public List<NoteDTO> getHistory(@RequestParam int id) {
        return noteManager.findHistoricalData(id);
    }
}
