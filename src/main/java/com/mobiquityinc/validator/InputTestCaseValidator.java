package com.mobiquityinc.validator;

import com.mobiquityinc.packer.items.InputTestCase;
import com.mobiquityinc.packer.items.Item;

import static com.mobiquityinc.validator.helpers.ValidationHelper.lowerOrEqThan;

public class InputTestCaseValidator {


    public boolean validate(InputTestCase inputTestCase) {

        lowerOrEqThan(100 * 100, "Max weight that a package can take is ≤ 100").test(inputTestCase.getMaxWeight()).throwIfInvalid();
        lowerOrEqThan(15, "There might be up to 15 items you need to choose from").test(inputTestCase.getItems().size()).throwIfInvalid();

        for (Item item : inputTestCase.getItems()) {
            lowerOrEqThan(100, "Max cost of an item is ≤ 100").test(item.getCost()).throwIfInvalid();
            lowerOrEqThan(100 * 100, "Max weight of an item is ≤ 100").test(item.getWeight()).throwIfInvalid();
        }

        return true;
    }
}
