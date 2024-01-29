package tom.study.domain.user.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFavoritesCompositeKey is a Querydsl query type for FavoritesCompositeKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFavoritesCompositeKey extends BeanPath<FavoritesCompositeKey> {

    private static final long serialVersionUID = 1775016643L;

    public static final QFavoritesCompositeKey favoritesCompositeKey = new QFavoritesCompositeKey("favoritesCompositeKey");

    public final StringPath itstId = createString("itstId");

    public final StringPath userName = createString("userName");

    public QFavoritesCompositeKey(String variable) {
        super(FavoritesCompositeKey.class, forVariable(variable));
    }

    public QFavoritesCompositeKey(Path<? extends FavoritesCompositeKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFavoritesCompositeKey(PathMetadata metadata) {
        super(FavoritesCompositeKey.class, metadata);
    }

}

