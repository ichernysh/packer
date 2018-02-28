package com.mobiquityinc.validator.helpers;

import com.mobiquityinc.validator.PredicateWrapper;

public class ValidationHelper {

    public static PredicateWrapper<Integer> lowerOrEqThan(int max, String message){
        return PredicateWrapper.wrap((i) -> i <= max, message);
    }

}
