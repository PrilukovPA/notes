package ru.intro.notes.services;

import org.junit.Before;
import org.junit.Test;
import ru.intro.notes.dao.NotesDao;
import ru.intro.notes.models.Note;
import ru.intro.notes.models.SearchRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NotesServiceTest {

    NotesDao notesDao;
    NotesService notesService;

    @Before
    public void before() {
        notesDao = new NotesDao();
        notesService = new NotesService();
        notesService.setNotesDao(notesDao);
    }

    @Test
    public void save() {
        Note in = new Note("header", "body");
        notesService.save(in);
        Note out = notesService.get(in.getId());
        assertEquals(in, out);
    }

    @Test
    public void delete() {
        Note in = new Note("header", "body");
        notesService.save(in);
        notesService.delete(in.getId());
        Note out = notesService.get(in.getId());
        assertNull(out);
    }

    @Test
    public void findAll() {
        NotesDao notesDao = new NotesDao();
        Note note1 = new Note("header1", "body1");
        Note note2 = new Note("header2", "body2");
        Note note3 = new Note("header3", "body3");
        notesDao.save(note1);
        notesDao.save(note2);
        notesDao.save(note3);
        List<Note> all = notesService.findAll();
        boolean res = all.containsAll(new ArrayList<Note>() {{ add(note1); add(note2); add(note3); }});
        assertTrue(res);
    }

    @Test
    public void find() {
        Note note1 = new Note("header1", "body1");
        notesService.save(note1);
        List<Note> res = notesService.find(new SearchRequest("eader"));
        assertTrue(notesService.findAll().containsAll(res));
        res = notesService.find(new SearchRequest("ody"));
        assertTrue(notesService.findAll().containsAll(res));
        res = notesService.find(new SearchRequest("l;kjdsl;fg8u52-872345jl;kjsdfg"));
        assertTrue(res.size() == 0);
    }

    @Test
    public void get() {
        Note in = new Note("header", "body");
        notesService.save(in);
        Note out = notesService.get(in.getId());
        assertEquals(in, out);
    }
}