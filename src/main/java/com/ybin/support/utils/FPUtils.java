package com.ybin.support.utils;

import lombok.Lombok;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@UtilityClass
public class FPUtils {
    public static <T,U> Predicate<T> eq(Function<T,U> functor,U u) {
        return t -> Objects.equals(functor.apply(t),u);
    }

    public static <T> Predicate<T> eq(T t) {
        return eq(Function.identity(),t);
    }

    public static <T> Predicate<T> ne(T t) {
        return eq(Function.identity(),t).negate();
    }
    public static <T> Supplier<T> supp(T t) {
        return () -> t;
    }
    public static <T,R> Function<T,R> sneaky(FunctionSneaky<T,R> functor) {
        return t -> {
            try {
                return functor.apply(t);
            } catch (Throwable e) {
                throw  Lombok.sneakyThrow(e);
            }
        };
    }

    interface FunctionSneaky<T,U> {
        U apply(T t) throws Throwable;
    }
}
