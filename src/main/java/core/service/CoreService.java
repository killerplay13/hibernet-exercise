package core.service;

import javax.transaction.Transaction;

import core.util.HibernateUtil;

import static core.util.HibernateUtil.getSessionFactory;

public interface CoreService {

	default Transaction beginTransaction() {
		return (Transaction) HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	}

	default void commit() {
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	}

	default void rollback() {
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
	}

}
