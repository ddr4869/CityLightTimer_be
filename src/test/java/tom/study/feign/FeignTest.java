package tom.study.feign;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import tom.study.common.feign.LightFeignClient;
import tom.study.common.feign.resp.IntersectionSimpleResponse;
import tom.study.common.feign.resp.LightFeignResponse;
import tom.study.domain.intersection.service.IntersectionService;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.lang.Boolean.TRUE;

//@RequiredArgsConstructor
@SpringBootTest
@Slf4j
public class FeignTest {
    @Autowired
    private IntersectionService intersectionService;
    @Autowired
    private LightFeignClient lightFeignClient;
    @Value("${map.naver.apikey}")
    private String apiKey;

    @Test
    public void generateWorkingLightInfo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<LightFeignResponse> intersections = objectMapper.readValue(new File("/Users/ieungyu/go/src/github.com/ddr4869/CityLightTimer_be/result.json"), new TypeReference<List<LightFeignResponse>>() {});
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        for (LightFeignResponse resp: intersections) {
            map.put(resp.getItstId(), TRUE);
        }
        Set<String> workingLights = map.keySet();

        JsonNode rootNode = objectMapper.readTree( new File("/Users/ieungyu/go/src/github.com/ddr4869/CityLightTimer_be/intersection.json"));
        ArrayNode arrayNode = (ArrayNode) rootNode;
        Iterator<JsonNode> iterator = arrayNode.iterator();

        while (iterator.hasNext()) {
            JsonNode currentNode = iterator.next();
            String itstId = currentNode.get("itstId").asText();
            if (!workingLights.contains(itstId)) {
                iterator.remove();
            }
        }
        objectMapper.writeValue(new File("/Users/ieungyu/go/src/github.com/ddr4869/CityLightTimer_be/result.json"), rootNode);
    }
}
