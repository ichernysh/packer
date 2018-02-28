package com.mobiquityinc.packer;

import com.mobiquityinc.algorithm.Algorithm;
import com.mobiquityinc.algorithm.PackerAlgorithm;
import com.mobiquityinc.packer.items.InputTestCase;
import com.mobiquityinc.packer.items.Item;
import com.mobiquityinc.packer.items.Package;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PackerAlgorithmTest {

    @Test
    public void testAlgorithmBasicWork() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1,9072, 13));
        items.add(new Item(7,8180, 45));
        items.add(new Item(8,1936, 79));
        items.add(new Item(9,676, 64));
        InputTestCase testCase = new InputTestCase(5600, items);

        Algorithm algorithm = new PackerAlgorithm();

        Package result = algorithm.apply(testCase);

        assertThat("8,9", is(result.toString()));
    }

}
