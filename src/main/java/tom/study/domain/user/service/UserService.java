package tom.study.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tom.study.domain.user.model.entity.Authority;
import tom.study.domain.user.model.entity.Favorites;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.repository.AuthorityRepository;
import tom.study.domain.user.repository.FavoritesRepository;
import tom.study.domain.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final FavoritesRepository favoritesRepository;
    private final AuthorityRepository authorityRepository;
    //public User createUser()

    public Favorites addFavorites(Favorites favorites) {
        return favoritesRepository.save(favorites);
    }

    public void delFavorites(Favorites favorites) {
        favoritesRepository.delete(favorites);
    }

    public User signupUser(User user) {
        User createdUser = userRepository.save(user);
        Authority authority = new Authority();
        authority.setUser(createdUser);
        authority.setUserName(createdUser.getUsername());
        authority.setAuthority(Authority.Authority_Type.ROLE_USER);
        authorityRepository.save(authority);
        //createdUser.addAuthorities(Authority.Authority_Type.ROLE_USER);
        return createdUser;
    }
    public void empowermentUser(User user) {
        List<Authority> authorityList = authorityRepository.findAuthorityByUserName(user.getUsername());
        if (authorityList.size()==1) {
            Authority authority = new Authority();
            authority.setUser(user);
            authority.setUserName(user.getUsername());
            authority.setAuthority(Authority.Authority_Type.ROLE_ADMIN);
            authorityRepository.save(authority);
            user.addAuthorities(Authority.Authority_Type.ROLE_ADMIN);
        } else if (authorityList.size()==2){
            Optional<Authority> authority = authorityRepository.findAuthorityByUserNameAndAuthority(user.getUsername(), Authority.Authority_Type.ROLE_ADMIN);
            authorityRepository.delete(authority.stream().findFirst().orElseThrow());
        }
    }
}
