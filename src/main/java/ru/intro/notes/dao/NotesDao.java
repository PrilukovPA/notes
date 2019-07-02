package ru.intro.notes.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.intro.notes.models.Note;

import java.util.List;

/**
 * Реализует доступ к данным через Hibernate
 */
public class NotesDao {

    /**
     * Сохраняет заметку в БД
     */
    public void save(Note note) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(note);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Удаляет заметку из БД
     */
    public void delete(Long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.get(Note.class, id));
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Возвращает список всех имеющихся заметок из БД
     */
    public List<Note> findAll() {
        List<Note> retVal;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query<Note> query = session.createQuery("FROM notes AS n ORDER BY n.creationDate");
        retVal = query.list();
        session.close();
        return retVal;
    }

    /**
     * Возвращает список заметок, в тексте или заголовке которых имеется заданная подстрока
     */
    public List<Note> find(String txt) {
        List<Note> retVal;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query<Note> query = session.createQuery("FROM notes AS n WHERE n.header LIKE :txt OR n.body LIKE :txt");
        query.setParameter("txt", "%" + txt + "%");
        retVal = query.list();
        session.close();
        return retVal;
    }

    /**
     * Возвращает заметку по ее id
     */
    public Note get(Long id) {
        Note retVal;
        Session session = HibernateUtils.getSessionFactory().openSession();
        retVal = session.get(Note.class, id);
        session.close();
        return retVal;
    }
}
