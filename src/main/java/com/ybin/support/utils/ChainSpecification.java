package com.ybin.support.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.function.Function;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChainSpecification<T> implements Specification<T> {
    private final Specification<T> specification;
    public static <T> ChainSpecification<T> select(Class<T> tClass) {
        return new ChainSpecification<>(null);
    }
    public ChainSpecification<T> and(Specification<T> specification) {
        if (this.specification==null) return new ChainSpecification<>(specification);
            return new ChainSpecification<>(this.specification.and(specification));
    }
    public ChainSpecification<T> or(Specification<T> specification) {
        if (this.specification==null) return new ChainSpecification<>(specification);
        return new ChainSpecification<>(this.specification.or(specification));
    }
    public static <U> ChainSpecification<U> example(U u) {
        return new ChainSpecification<>((root, criteriaQuery, criteriaBuilder) ->
                QueryByExamplePredicateBuilder.getPredicate(root,criteriaBuilder, Example.of(u)));
    }
    public ChainSpecification<T> eq(String path,Object o) {
        return and((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(toPath(root,path),o));
    }
    public ChainSpecification<T> ne(String path,Object o) {
        return and((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.notEqual(toPath(root,path),o));
    }
    public ChainSpecification<T> like(String path,String string) {
        return and((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(toPath(root,path),string));
    }
    public <E> ChainSpecification<T> in(String path, Collection<E> collection) {
        return and((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.in(toPath(root,path)).in(collection));
    }
    public ChainSpecification<T> in(String path,Object... objects){
        return and((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.in(toPath(root,path)).in(objects));
    }
    public ChainSpecification<T> cond(boolean cond, Function<ChainSpecification<T>,ChainSpecification<T>> function)
    {
        if (cond) return and(function.apply(this));
        return this;
    }

    public ChainSpecification<T> eq(boolean cond, String path, Object o) {
        return cond(cond,x -> x.eq(path, o));
    }
    private <X,Y> Path<Y> toPath(Path<X> root, String path) {
        // todo
        return root.get(path);
    }
    @Override
    public Predicate toPredicate(Root<T> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return specification.toPredicate(r,cq,cb);
    }

    public static String sd(String s) throws SQLException{
        return null;
    }
}
