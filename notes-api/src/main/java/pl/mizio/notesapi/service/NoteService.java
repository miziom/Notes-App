package pl.mizio.notesapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mizio.notesapi.dao.Note;
import pl.mizio.notesapi.dao.repository.NoteHistoryRepo;
import pl.mizio.notesapi.dao.repository.NoteRepo;
import pl.mizio.notesapi.dao.entity.NoteEntity;
import pl.mizio.notesapi.dao.entity.NoteHistoryEntity;
import pl.mizio.notesapi.dto.NoteDTO;
import pl.mizio.notesapi.dao.NoteUpdate;
import pl.mizio.notesapi.exception.NotFoundException;
import pl.mizio.notesapi.mapper.NoteMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private NoteRepo noteRepo;
    private NoteHistoryRepo noteHistoryRepo;

    @Autowired
    public NoteService(NoteRepo noteRepo, NoteHistoryRepo noteHistoryRepo) {
        this.noteRepo = noteRepo;
        this.noteHistoryRepo = noteHistoryRepo;
    }

    public NoteDTO findById(int id) {
        return noteRepo.findById(id)
                .filter(e -> !e.getIsDeleted())
                .map(NoteMapper::mapFromNoteEntity)
                .orElseThrow(() -> new NotFoundException("note", id));
    }

    public List<NoteDTO> findAll() {
        return noteRepo.findAll()
                .stream()
                .filter(e -> !e.getIsDeleted())
                .map(NoteMapper::mapFromNoteEntity)
                .collect(Collectors.toList());
    }

    public NoteDTO save(Note note) {
        NoteEntity noteEntity = noteRepo.save(new NoteEntity());
        NoteHistoryEntity noteHistoryEntity = new NoteHistoryEntity(noteEntity, note);
        return NoteMapper.mapFromNoteHistoryEntity(noteHistoryRepo.save(noteHistoryEntity));
    }

    public NoteDTO update(int id, NoteUpdate noteUpdate) {
        NoteEntity noteEntity = noteRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("note", id));
        NoteHistoryEntity noteHistoryEntity = noteEntity.getNoteHistoryEntityList()
                .stream()
                .filter(e -> e.getVersion()
                        .equals(noteEntity.getLastVersion()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(noteEntity.getLastVersion()));
        noteEntity.setLastModifiedDate(LocalDateTime.now());
        noteEntity.incrementVersion();
        noteRepo.save(noteEntity);
        Note note = new Note(Objects.nonNull(noteUpdate.getTitle()) ? noteUpdate.getTitle() : noteHistoryEntity.getTitle(),
                Objects.nonNull(noteUpdate.getContent()) ? noteUpdate.getContent() : noteHistoryEntity.getContent());
        NoteHistoryEntity newNoteHistoryEntity = new NoteHistoryEntity(noteEntity, note);
        return NoteMapper.mapFromNoteHistoryEntity(noteHistoryRepo.save(newNoteHistoryEntity));
    }

    public void deleteById(int id) {
        NoteEntity noteEntity = noteRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("note", id));
        NoteHistoryEntity noteHistoryEntity = noteEntity.getNoteHistoryEntityList()
                .stream()
                .filter(e -> e.getVersion()
                        .equals(noteEntity.getLastVersion()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(noteEntity.getLastVersion()));
        noteEntity.setLastModifiedDate(LocalDateTime.now());
        noteEntity.setIsDeleted(true);
        noteEntity.incrementVersion();
        noteRepo.save(noteEntity);
        Note note = new Note(noteHistoryEntity.getTitle(), noteHistoryEntity.getContent());
        NoteHistoryEntity newNoteHistoryEntity = new NoteHistoryEntity(noteEntity, note);
        noteHistoryRepo.save(newNoteHistoryEntity);
    }

    public List<NoteDTO> findHistoricalData(int id) {
        return noteRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("historical note", id))
                .getNoteHistoryEntityList()
                .stream()
                .map(NoteMapper::mapFromNoteHistoryEntity)
                .collect(Collectors.toList());
    }
}
