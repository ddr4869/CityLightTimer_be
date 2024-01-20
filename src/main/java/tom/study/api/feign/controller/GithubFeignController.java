package tom.study.api.feign.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.feign.dto.Contributor;
import tom.study.api.feign.service.GithubFeignService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GithubFeignController {
    private final GithubFeignService githubFeignService;

    @GetMapping("/v1/github/{owner}/{repo}")
    List<Contributor> getGithubContributors(@PathVariable("owner") String owner, @PathVariable("repo") String repo ) {
        return githubFeignService.getContributors(owner, repo);
    }
}
