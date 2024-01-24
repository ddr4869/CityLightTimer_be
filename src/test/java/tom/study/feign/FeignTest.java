package tom.study.feign;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tom.study.domain.intersection.service.IntersectionService;

import java.io.IOException;

//@RequiredArgsConstructor
@SpringBootTest
public class FeignTest {
    @Autowired
    private IntersectionService intersectionService;

    @Test
    public void intersectionSimpleInformationIfLightTimingExistTest() throws IOException {
        intersectionService.intersectionSimpleInformationIfLightTimingExist();
    }

    @Test
    public void intersectionSimpleInformationIfLightTimingExistTest2() throws IOException {
        intersectionService.intersectionSimpleInformationIfLightTimingExist2();
    }
}
