package com.mobiquityinc.packer;

import com.mobiquityinc.exceptions.APIException;
import com.mobiquityinc.packer.items.InputTestCase;
import com.mobiquityinc.packer.items.Item;
import com.mobiquityinc.validator.InputTestCaseValidator;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class InputTestCaseValidatorTest {


    private InputTestCaseValidator validator = new InputTestCaseValidator();

    @Test
    public void check_validation_ok(){
        InputTestCase inputTestCase = new InputTestCase();
        inputTestCase.setMaxWeight(100);
        for (int i = 0; i < 3; i++) {
            inputTestCase.add(new Item(i+1, 55, 20));
        }

        boolean result = validator.validate(inputTestCase);
        assertThat(result, is(true));
    }

    @Test(expected = APIException.class)
    public void check_max_weight_greater_100_throw_error(){
        InputTestCase inputTestCase = new InputTestCase();
        inputTestCase.setMaxWeight(110 * 100);
        inputTestCase.add(new Item(1, 55, 20));

        validator.validate(inputTestCase);
    }

    @Test(expected = APIException.class)
    public void check_items_size_greater_15_throw_error(){
        InputTestCase inputTestCase = new InputTestCase();
        inputTestCase.setMaxWeight(110);
        for (int i = 0; i < 16; i++) {
            inputTestCase.add(new Item(i+1, 55, 20));
        }

        validator.validate(inputTestCase);
    }

    @Test(expected = APIException.class)
    public void check_max_cost_greater_100_throw_error(){
        InputTestCase inputTestCase = new InputTestCase();
        inputTestCase.setMaxWeight(110);
        inputTestCase.add(new Item(1, 55, 120));

        validator.validate(inputTestCase);
    }

    @Test(expected = APIException.class)
    public void check_max_item_weight_greater_100_throw_error(){
        InputTestCase inputTestCase = new InputTestCase();
        inputTestCase.setMaxWeight(110 * 100);
        inputTestCase.add(new Item(1, 150, 10));

        validator.validate(inputTestCase);
    }



}
