package web.member.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import core.config.TestConfig;
import web.member.entity.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class MemberServiceTest {
	@Autowired
	private MemberService service;
	
	@Test
    public void login() {
        Member member = new Member();
        member.setUsername("admin");
        member.setPassword("P@ssw0rd");
        member = service.login(member);
        assertThat(member.getNickname(), equalTo("Admin"));
    }
}
