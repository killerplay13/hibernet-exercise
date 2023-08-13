package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import web.member.dao.MemberDao;
import web.member.entity.Member;

public class TestApp2 {
	public static void main(String[] args) {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		MemberDao memberDao = applicationContext.getBean(MemberDao.class);
//		for(Member member : memberDao.selectAll()) {
//			System.out.println(member.getNickname());
//		}
		
//		DataSource dataSource = applicationContext.getBean(DataSource.class);
//		try (
//			Connection conn = dataSource.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement("select * from MEMBER where ID = ?")
//				){
//			pstmt.setInt(1, 2);
//			try (ResultSet rs = pstmt.executeQuery()){
//				rs.next();
//				System.out.println(rs.getString("NiCKNAME"));
//			} 
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		GenericApplicationContext applicationContext = new GenericApplicationContext();
        new XmlBeanDefinitionReader(applicationContext).loadBeanDefinitions("applicationContext.xml");
        applicationContext.refresh();
            
        MemberDao memberDao = applicationContext.getBean(MemberDao.class);
        System.out.println(memberDao.selectById(1).getNickname());
        
        ((ConfigurableApplicationContext) applicationContext).close();
	}
}
