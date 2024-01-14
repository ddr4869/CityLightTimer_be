package tom.study.domain.customer.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = 1264277053L;

    public static final QCustomer customer = new QCustomer("customer");

    public final StringPath customerAddress = createString("customerAddress");

    public final StringPath customerDelete = createString("customerDelete");

    public final StringPath customerDetailAddress = createString("customerDetailAddress");

    public final StringPath customerEmail = createString("customerEmail");

    public final StringPath customerExtraAddress = createString("customerExtraAddress");

    public final StringPath customerGrade = createString("customerGrade");

    public final StringPath customerName = createString("customerName");

    public final StringPath customerPostcode = createString("customerPostcode");

    public final StringPath customerPw = createString("customerPw");

    public final StringPath customerTel = createString("customerTel");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCustomer(String variable) {
        super(Customer.class, forVariable(variable));
    }

    public QCustomer(Path<? extends Customer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomer(PathMetadata metadata) {
        super(Customer.class, metadata);
    }

}

