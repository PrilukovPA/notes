package ru.intro.notes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.intro.notes.dao.NotesDao;
import ru.intro.notes.models.Note;
import ru.intro.notes.models.SearchRequest;

import java.util.List;

@Service
public class NotesService {

    private NotesDao notesDao;

    @Autowired
    public void setNotesDao(NotesDao notesDao) {
        this.notesDao = notesDao;
    }

    /**
     * Сохраняет заметку в БД
     */
    public void save(Note note) {
        notesDao.save(note);
    }

    /**
     * Удаляет заметку из БД
     */
    public void delete(Long id) {
        notesDao.delete(id);
    }

    /**
     * Возвращает список всех имеющихся заметок из БД
     */
    public List<Note> findAll() {
        return notesDao.findAll();
    }

    /**
     * Возвращает список заметок, соответствующих поисковому запросу
     */
    public List<Note> find(SearchRequest searchRequest) {
        String pattern = searchRequest.getTxt();
        return notesDao.find(pattern);
    }

    /**
     * Возвращает заметку по ее id
     */
    public Note get(Long id) {
        return notesDao.get(id);
    }
}
