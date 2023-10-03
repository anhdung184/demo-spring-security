package com.example.api.Repository.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.api.Model.Book;
import com.example.api.Repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Transactional
@Service
public class BookRepositoryimpl implements BookRepository {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select c from Book c", Book.class);
        return query.getResultList();
    }

    @Override
    public Book findById(Long id) {
        TypedQuery<Book> query = em.createQuery("select c from Book c where  c.id=:id", Book.class);
        query.setParameter("id", id);
        try {   
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Book book) { 
            em.persist(book);
    }

    @Override
    public void remove(Long id) {
        Book book = findById(id);
        if (book != null) {
            em.remove(book);
        }
    }
}
