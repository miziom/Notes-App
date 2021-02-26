package pl.mizio.notesapi.dao.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.mizio.notesapi.dao.Note;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notes_history")
public class NoteHistoryEntity {

    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "note_history_id", nullable = false)
    private Integer id;

    @ManyToOne(targetEntity = NoteEntity.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "note_id", referencedColumnName = "note_id")
    private NoteEntity noteEntity;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;

    @Column(name = "version", nullable = false)
    private Integer version;

    public NoteHistoryEntity(NoteEntity noteEntity, Note note) {
        this.noteEntity = noteEntity;
        this.title = note.getTitle();
        this.content = note.getContent();
        this.modifiedDate = LocalDateTime.now();
        this.version = noteEntity.getLastVersion();
    }

    public NoteHistoryEntity() {

    }
}
