package water;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.water.service.CabinetService;

import junit.framework.TestCase;

public class Test extends TestCase{

	public ApplicationContext context = null;

	protected void setUp() throws Exception {
		String[] files = { "applicationContext.xml", "spring-mvc.xml"};
		context = (ApplicationContext) new ClassPathXmlApplicationContext(files);
	}

	public void test() {
		CabinetService cabinetService = (CabinetService) context.getBean("cabinet");
		cabinetService.queryAll();
	}
	
}
