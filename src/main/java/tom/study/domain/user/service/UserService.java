package tom.study.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tom.study.domain.user.model.entity.Favorites;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.repository.FavoritesRepository;
import tom.study.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final FavoritesRepository favoritesRepository;

    //public User createUser()

    public Favorites addFavorites(Favorites favorites) {
        return favoritesRepository.save(favorites);
    }

    public void delFavorites(Favorites favorites) {
        favoritesRepository.delete(favorites);

    }
}
