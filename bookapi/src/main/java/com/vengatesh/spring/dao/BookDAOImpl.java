package com.vengatesh.spring.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vengatesh.spring.model.Book;

@Repository
public class BookDAOImpl implements BookDAO {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public long save(Book book) {
		Session session=sessionFactory.getCurrentSession();
		session.save(book);
		return book.getId();
	}

	@Override
	public Book get(long id) {
		
		Session session=sessionFactory.getCurrentSession();
		
		Book book=(Book)session.get(Book.class, id);
		
		return book;
	}

	@Override
	public List<Book> list() {
		Session session=sessionFactory.getCurrentSession();
		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
		CriteriaQuery<Book> criteriaQuery=criteriaBuilder.createQuery(Book.class);
		
		Root<Book> root=criteriaQuery.from(Book.class);
		
		criteriaQuery.select(root);
		
		Query query=session.createQuery(criteriaQuery);
		
		
		List<Book> list=query.getResultList();
		
		return list;
	}

	@Override
	public void update(long id, Book book) {
		Session session=sessionFactory.getCurrentSession();
		Book oldBook=session.get(Book.class,id);
		
		oldBook.setAuthor(book.getAuthor());
		oldBook.setTitle(book.getTitle());
		
	}

	@Override
	public void delete(long id) {
		Session session=sessionFactory.getCurrentSession();
		
		Book book=(Book)session.get(Book.class, id);
		
		session.delete(book);
		
	}

}
