package com.horstman.javase8.chapter1;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T> {
    default void foreachIf(Consumer<T> action, Predicate<T> filter) {
        final Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (filter.test(element)) {
                action.accept(element);
            }
        }
    }
}
