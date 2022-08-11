package projeto.spring.data.aula;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceStringDataUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })

public class AppSpringDataTest {

	@Autowired
	private InterfaceStringDataUser interfaceStringDataUser;
	
	@Test
	public void testeInsert() {
		System.out.println("Spring testado");
		
	
	}

}
