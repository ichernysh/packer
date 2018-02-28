package com.mobiquityinc.algorithm.factory;

import com.mobiquityinc.algorithm.Algorithm;
import com.mobiquityinc.algorithm.PackerAlgorithm;

public class AlgorithmFactory {

    public static Algorithm getAlgorithm() {
        return new PackerAlgorithm();
    }

}
