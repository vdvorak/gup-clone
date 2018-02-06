package ua.com.gup.notify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.gup.notify.config.NotifyRootConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NotifyRootConfig.class, NotifyApplication.class})
public class NotifyApplicationTests {

	@Test
	public void contextLoads() {
	}

}
