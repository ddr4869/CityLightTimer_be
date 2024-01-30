package tom.study.api.controller.user.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tom.study.domain.user.model.entity.Favorites;
import tom.study.domain.user.model.entity.FavoritesCompositeKey;

import java.util.Date;

@Data
public class CreateFavoriteRequest {
    @NotNull
    public String itstId;
    public String userName;

    public Favorites ModelToEntity(CreateFavoriteRequest createFavoriteRequest) {
        Favorites favorites = new Favorites();
        FavoritesCompositeKey favoritesCompositeKey = new FavoritesCompositeKey();
        favoritesCompositeKey.setUserName(createFavoriteRequest.getUserName());
        favoritesCompositeKey.setItstId(createFavoriteRequest.getItstId());
        favorites.setUserItsId(favoritesCompositeKey);
        return favorites;
    }
}
