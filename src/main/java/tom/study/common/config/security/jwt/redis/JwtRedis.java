package tom.study.common.config.security.jwt.redis;

import io.lettuce.core.api.sync.RedisSetCommands;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Component
@Service
@RequiredArgsConstructor
public class JwtRedis {
    private final RedisTemplate<String, Object> redisTemplate;

    public void setValues(String key, String data) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key,data);
    }

    @Transactional(readOnly = true)
    public String getValues(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get(key) == null) {
            return "false";
        }
        return (String) values.get(key);
    }

    public void setJwtSets(String key, String data) {
        SetOperations<String, Object> sets = redisTemplate.opsForSet();
        sets.add(key, data);
    }

    public Set<Object> getJwtSets(String key) {
        SetOperations<String, Object> sets = redisTemplate.opsForSet();
        return sets.members(key);
    }
}
