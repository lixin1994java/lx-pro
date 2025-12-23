package com.lxpro.lxmanagerservice;

import com.lxpro.mapper.user.UserMapper;
import com.lxpro.service.LxManagerServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = LxManagerServiceApplication.class,
		properties = {
				"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
		}
)
public class LxManagerServiceApplicationTests {

	@MockBean
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
	}

}
