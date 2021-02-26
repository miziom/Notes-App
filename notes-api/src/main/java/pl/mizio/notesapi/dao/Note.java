package pl.mizio.notesapi.dao;

import lombok.Getter;
import lombok.NonNull;
import org.junit.platform.commons.util.StringUtils;

@Getter
public class Note {

    @NonNull
    private final String title;

    @NonNull
    private final String content;

    public Note(final String title, final String content) {
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("Title can't be blank/empty/null");
        }
        if (StringUtils.isBlank(content)) {
            throw new IllegalArgumentException("Content can't be blank/empty/null");
        }
        this.title = title;
        this.content = content;
    }

}
