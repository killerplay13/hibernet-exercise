package app;

import org.apache.bcel.generic.Select;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import web.member.pojo.Member;
import web.member.util.HibernateUtil;

public class TestApp {
	public static void main(String[] args) {
		Member member = new Member();
		TestApp app = new TestApp();
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
		
		member.setId(1);
		System.out.println(app.SelectById(1).getUsername());
		
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
			if(pass != null) {
				oldMember.setPass(pass);
			}
			final Integer roleId = newMember.getRoleId();
			if(roleId != null) {
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
}
