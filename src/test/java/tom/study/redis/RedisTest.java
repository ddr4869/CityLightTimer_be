package tom.study.redis;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.BasicJsonTester;
import tom.study.common.config.security.jwt.JwtUtil;
import tom.study.common.config.security.jwt.redis.JwtRedis;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Slf4j
@SpringBootTest
public class RedisTest {
    @Autowired
    private JwtRedis jwtRedis;
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void isRunningRedis() {
        Assertions.assertThat(jwtRedis.isRedisConnected()).isTrue();

    }

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
        List<Object> datas = new ArrayList<>();
        datas.add("3");
        datas.add("4");
        jwtRedis.setJwtSet("testSet", datas);
    }
    @Test
    public void getJwtSetTest() {
        Set<Object> sets = jwtRedis.getJwtSet("eyJhbGciOiJIUzM4NCJ9.eyJ1c2VyTmFtZSI6InJpY2hhcmQiLCJzdWIiOiJyaWNoYXJkIiwiaWF0IjoxNzA2MjU1NjkxLCJleHAiOjE3MDYzNTU2OTF9.dFVvzyu2jo6Am0RSGFO7AdRROxrDgDwpD47QB_F4VSXgoSGkk9E4AmLbNWigbZNP");
        log.info("set: {}", sets);
    }

    @Test
    public void getEmptyJwtSets() {
        Set<Object> sets = jwtRedis.getJwtSet("e!m@p#t$y%S*e(t)");
        Assertions.assertThat(sets).isEmpty();
    }

    @Test
    public void setRedisHashTest() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("test1",1);
        hashMap.put("test2",2);
        jwtRedis.setJwtHash("testRedisKey", hashMap);
    }

    @Test
    public void getRedisHashTest() {
        Map<String, Object> hashMap = jwtRedis.getJwtHash("testRedisKey");
        log.info("test1: {}", hashMap.get("test1"));
        Assertions.assertThat(hashMap.get("test1")).isEqualTo(1);
        log.info("test2: {}", hashMap.get("test2"));
        Assertions.assertThat(hashMap.get("test2")).isEqualTo(2);
    }
    @Test
    public void delJwtSets() {
        jwtRedis.delKey("testSet");
    }
}
