package com.mobiquityinc.validator;

import java.util.function.Predicate;

public class PredicateWrapper<K>  {

    private Predicate<K> predicate;
    private String onErrorMessage;

    public static <K> PredicateWrapper<K> wrap(Predicate<K> predicate, String onErrorMessage) {
        return new PredicateWrapper<>(predicate, onErrorMessage);
    }

    private PredicateWrapper(Predicate<K> predicate, String onErrorMessage) {
        this.predicate = predicate;
        this.onErrorMessage = onErrorMessage;
    }

    ValidationResult test(K param) {
        return predicate.test(param) ? ValidationResult.ok() : ValidationResult.fail(onErrorMessage);
    }

}