package tom.study.api.feign.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tom.study.api.feign.client.GithubFeignClient;
import tom.study.api.feign.dto.Contributor;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class GithubFeignService {
    private final GithubFeignClient githubFeignClient;
    public List<Contributor> getContributors(String owner, String repo) {
        List<Contributor> contributors = githubFeignClient.getContributors(owner, repo);
        for (Contributor c : contributors) {
            //log.info("id: {}", c.getId());
        }
        log.info("!! contributors !! : {}", contributors);
        return contributors;
    }
}
