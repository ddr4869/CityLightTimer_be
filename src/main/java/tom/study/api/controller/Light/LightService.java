package tom.study.api.controller.Light;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LightService {
    private final LightFeign lightFeign;

    public List<LightResponse> call(String apiKey, String itstId, String pageNo, String numOfRows) {
        log.info("apiKey: {}", apiKey);
        List<LightResponse> resp = lightFeign.call(apiKey, itstId, pageNo,numOfRows);

        return resp;
    }
}
