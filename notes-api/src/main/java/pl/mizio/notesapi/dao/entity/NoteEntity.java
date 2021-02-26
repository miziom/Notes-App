package pl.mizio.notesapi.dao.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "notes")
public class NoteEntity {

    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "note_id", nullable = false)
    private Integer id;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime lastModifiedDate;

    @Column(name = "last_version", nullable = false)
    private Integer lastVersion;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "noteEntity",
            targetEntity = NoteHistoryEntity.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.PERSIST)
    private List<NoteHistoryEntity> noteHistoryEntityList = new ArrayList<>();

    public NoteEntity() {
        this.createdDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
        this.lastVersion = 1;
        this.isDeleted = false;
    }

    public void incrementVersion() {
        this.lastVersion++;
    }
}
