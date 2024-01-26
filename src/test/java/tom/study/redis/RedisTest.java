package tom.study.redis;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.BasicJsonTester;
import tom.study.common.config.security.jwt.JwtUtil;
import tom.study.common.config.security.jwt.redis.JwtRedis;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Map;
import java.util.Set;

@Slf4j
@SpringBootTest
public class RedisTest {
    @Autowired
    private JwtRedis jwtRedis;
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void insertTest() {
        jwtRedis.setValues("testKey", "testData");
        log.info("redis insert");
    }

    @Test
    public void queryTest() {
        String value = jwtRedis.getValues("testKey");
        log.info("query success: {}", value);
    }

    @Test
    public void insertAccessTokenTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String access = jwtUtil.createAccessJwt("testUser");
        jwtRedis.setValues("access", access);
        log.info("redis insert");
    }

    @Test
    public void getAccessTokenTest() throws JsonProcessingException {
        String access = jwtRedis.getValues("access");
        String payloadJWT = access.split("\\.")[1];

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(payloadJWT));

        BasicJsonParser jsonParser = new BasicJsonParser();
        Map<String, Object> jsonArray = jsonParser.parseMap(payload);
        log.info("jsonArray: {}",jsonArray);
    }

    @Test
    public void addTokenPayload() {
        jwtRedis.setJwtSets("testSet", "1");
        jwtRedis.setJwtSets("testSet", "2");
    }
    @Test
    public void getJwtSets() {
        Set<Object> sets = jwtRedis.getJwtSets("testSet");
        log.info("set: {}", sets);
    }
}
