package tom.study.domain.intersection.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tom.study.api.controller.intersection.model.NeighborIntersectionRequest;
import tom.study.common.feign.IntersectionFeignClient;
import tom.study.common.feign.LightFeignClient;
import tom.study.common.feign.resp.IntersectionResponse;
import tom.study.common.feign.resp.IntersectionSimpleResponse;
import tom.study.common.response.CommonResponse;
import tom.study.domain.intersection.model.entity.Intersection;
import tom.study.domain.intersection.repository.IntersectionRepository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntersectionService {
    private final IntersectionFeignClient intersectionFeignClient;
    private final IntersectionRepository intersectionRepository;
    @Value("${map.naver.apikey}")
    public String apiKey;

    public ResponseEntity<Object> intersectionInformation() {
        return CommonResponse.ResponseEntitySuccess(intersectionFeignClient.intersectionList(apiKey));
    }

    public ResponseEntity<Object> intersectionSimpleInformation() throws IOException {
        List<IntersectionSimpleResponse> intersections = intersectionFeignClient.intersectionSimpleList(apiKey, "1");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("/Users/ieungyu/go/src/github.com/ddr4869/CityLightTimer_be/intersection3.json"), intersections);
        return CommonResponse.ResponseEntitySuccess(intersections);
    }

    // JSON 파일을 읽어서 DB에 저장하는 메소드
    public void insertIntersectionSimpleInformation() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonParser jsonParser = new JsonParser();
        Reader reader = new FileReader("/Users/ieungyu/go/src/github.com/ddr4869/CityLightTimer_be/intersection.json");
        JsonArray dataArray = (JsonArray) JsonParser.parseReader(reader);
        for (int i = 0; i < dataArray.size(); i++) {
            String itstId = dataArray.get(i).getAsJsonObject().get("itstId").getAsString();
            String itstNm = dataArray.get(i).getAsJsonObject().get("itstNm").getAsString();
            Double mapCtptIntLat =dataArray.get(i).getAsJsonObject().get("mapCtptIntLat").getAsDouble()*0.0000001f;
            Double mapCtptIntLot = dataArray.get(i).getAsJsonObject().get("mapCtptIntLot").getAsDouble()*0.0000001f;
            Intersection intersection = new Intersection(itstId, itstNm, mapCtptIntLat, mapCtptIntLot);
            intersectionRepository.save(intersection);
        }
    }

    public List<Intersection> neighborIntersectionSimpleInformation(NeighborIntersectionRequest neighborIntersectionRequest)  {
        List<Intersection> intersections = intersectionRepository.findNearbyLocations(neighborIntersectionRequest.getLongitude(), neighborIntersectionRequest.getLatitude(), neighborIntersectionRequest.getDistance());
        for (Intersection intersection : intersections) {
            log.info("{}", intersection.getItstNm());
        }
        return intersections;
    }
}