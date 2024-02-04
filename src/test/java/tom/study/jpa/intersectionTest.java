package tom.study.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tom.study.domain.intersection.model.entity.Intersection;
import tom.study.domain.intersection.repository.IntersectionRepository;
import tom.study.domain.intersection.service.IntersectionService;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
public class intersectionTest {
    @Autowired
    IntersectionRepository intersectionRepository;
    @Autowired
    IntersectionService intersectionService;
    @Test
    void intersectionTest1() throws IOException {
        intersectionService.insertIntersectionSimpleInformation();
    }

    @Test
    void intersectionTest2() {
        List<Intersection> intersections = intersectionRepository.findNearbyLocations(127.043871984, 37.51028803, 1000);
        log.info("intersection length: {}", intersections.size());
        for (Intersection intersection : intersections) {
            log.info("intersection: {}", intersection.getItstNm());
        }
    }
}
