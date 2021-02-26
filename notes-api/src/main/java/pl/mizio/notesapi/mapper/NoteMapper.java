package pl.mizio.notesapi.mapper;

import pl.mizio.notesapi.dao.entity.NoteEntity;
import pl.mizio.notesapi.dao.entity.NoteHistoryEntity;
import pl.mizio.notesapi.dto.NoteDTO;
import pl.mizio.notesapi.exception.NotFoundException;

public class NoteMapper {

    public static NoteDTO mapFromNoteHistoryEntity(NoteHistoryEntity noteHistoryEntity) {
        return NoteDTO.builder()
                .id(noteHistoryEntity.getNoteEntity()
                        .getId())
                .version(noteHistoryEntity.getVersion())
                .title(noteHistoryEntity.getTitle())
                .content(noteHistoryEntity.getContent())
                .createdDate(noteHistoryEntity.getNoteEntity()
                        .getCreatedDate())
                .modifiedDate(noteHistoryEntity.getNoteEntity()
                        .getLastModifiedDate())
                .build();
    }

    public static NoteDTO mapFromNoteEntity(NoteEntity noteEntity) {

        NoteHistoryEntity noteHistoryEntity = noteEntity.getNoteHistoryEntityList()
                .stream()
                .filter(e -> e.getVersion()
                        .equals(noteEntity.getLastVersion()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(noteEntity.getLastVersion()));
        return NoteDTO.builder()
                .id(noteEntity.getId())
                .version(noteEntity.getLastVersion())
                .title(noteHistoryEntity.getTitle())
                .content(noteHistoryEntity.getContent())
                .createdDate(noteEntity.getCreatedDate())
                .modifiedDate(noteEntity.getLastModifiedDate())
                .build();
    }

}
