package ru.intro.notes.dao;

import org.junit.Test;
import ru.intro.notes.models.Note;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NotesDaoTest {

    @Test
    public void save() {
        NotesDao notesDao = new NotesDao();
        Note in = new Note("header", "body");
        notesDao.save(in);
        Note out = notesDao.get(in.getId());
        assertEquals(in.getId(), out.getId());
        assertEquals(in.getHeader(), out.getHeader());
        assertEquals(in.getBody(), out.getBody());
    }

    @Test
    public void get() {
        NotesDao notesDao = new NotesDao();
        Note in = new Note("header", "body");
        notesDao.save(in);
        Note out = notesDao.get(in.getId());
        assertEquals(in, out);
    }

    @Test
    public void delete() {
        NotesDao notesDao = new NotesDao();
        Note in = new Note("header", "body");
        notesDao.save(in);
        notesDao.delete(in.getId());
        Note out = notesDao.get(in.getId());
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
        List<Note> all = notesDao.findAll();
        boolean res = all.containsAll(new ArrayList<Note>() {{ add(note1); add(note2); add(note3); }});
        assertTrue(res);
    }

    @Test
    public void find() {
        NotesDao notesDao = new NotesDao();
        Note note1 = new Note("header1", "body1");
        notesDao.save(note1);
        List<Note> res = notesDao.find("eader");
        assertTrue(notesDao.findAll().containsAll(res));
        res = notesDao.find("ody");
        assertTrue(notesDao.findAll().containsAll(res));
        res = notesDao.find("l;kjdsl;fg8u52-872345jl;kjsdfg");
        assertTrue(res.size() == 0);
    }
}