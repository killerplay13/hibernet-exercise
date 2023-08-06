package app;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.bcel.generic.Select;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.member.pojo.Member;


public class TestApp {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		//from member
		Root<Member> root = criteriaQuery.from(Member.class);
		//where
		criteriaQuery.where(criteriaBuilder.and(
				criteriaBuilder.equal(root.get("username"), "admin"),
				criteriaBuilder.equal(root.get("password"),"P@ssw0rd" )));
		//select USERNAME,NICKNAME
		criteriaQuery.multiselect(root.get("username"), root.get("nickname"));
		// order by ID
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		
		Member member =session.createQuery(criteriaQuery).uniqueResult();
		System.out.println(member.getNickname());
		
//		Member member = new Member();
//		TestApp app = new TestApp();
//		member.setUsername("使用者名稱");
//		member.setPassword("密碼");
//		member.setNickname("暱稱");		
//		System.out.println(app.insert(member));

//		app.insert(member);		
//		System.out.println(app.deleteById(3));

//		member.setId(1);
//		member.setPass(true);
//		member.setRoleId(1);
//		System.out.println(app.updateById(member));

//		member.setId(1);
//		System.out.println(app.SelectById(1).getUsername());

//		app.SelectAll().forEach(member -> System.out.println(member.getNickname()));

	}

	public Integer insert(Member member) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public Integer deleteById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);

			session.remove(member);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}

	public int updateById(Member newMember) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member oldMember = session.get(Member.class, newMember.getId());

			final Boolean pass = newMember.getPass();
			if (pass != null) {
				oldMember.setPass(pass);
			}
			final Integer roleId = newMember.getRoleId();
			if (roleId != null) {
				oldMember.setRoleId(roleId);
			}
			transaction.commit();
			return 1;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}

	public Member SelectById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);
			transaction.commit();
			return member;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public List<Member> SelectAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			Transaction transaction = session.beginTransaction();
			session.createQuery("FROM Member");
			Query<Member> query = session
					.createQuery("SELECT new web.member.pojo.Member(username, nickname)FROM Member", Member.class);
			List<Member> list = query.getResultList();

			transaction.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

}
