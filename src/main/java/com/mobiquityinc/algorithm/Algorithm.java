package com.mobiquityinc.algorithm;

import com.mobiquityinc.packer.items.InputTestCase;
import com.mobiquityinc.packer.items.Package;

public interface Algorithm {

    Package apply(InputTestCase testCase);

}
