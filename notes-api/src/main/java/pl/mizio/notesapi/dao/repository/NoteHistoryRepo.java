package pl.mizio.notesapi.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mizio.notesapi.dao.entity.NoteHistoryEntity;

@Repository
public interface NoteHistoryRepo extends JpaRepository<NoteHistoryEntity, Integer> {

}
