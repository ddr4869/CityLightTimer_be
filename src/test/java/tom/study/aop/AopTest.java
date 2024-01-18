package tom.study.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import tom.study.api.controller.user.ReadUserController;
import tom.study.common.logger.aop.LogAop;

@SpringBootTest
@Slf4j
@Import(LogAop.class)
public class AopTest {
    @Autowired
    ReadUserController readUserController;

    @Test
    public void basicTest() {
        String login = readUserController.login();
        log.info("{}", login);
    }
}
