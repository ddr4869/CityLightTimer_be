package tom.study.api.controller.Light;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LightService {
    private final LightFeign lightFeign;

    public String call(LightRequest lightRequest) {
        lightFeign.call(lightRequest);
        return null;
    }
}
