package com.mobiquityinc.packer;

import com.mobiquityinc.exceptions.APIException;
import com.mobiquityinc.packer.items.InputTestCase;
import com.mobiquityinc.packer.items.Item;
import com.mobiquityinc.parsers.PackageParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParserTest {

    PackageParser parser = new PackageParser();

    @Test(expected = APIException.class)
    public void whenPassingNullThrowException() {
        parser.parse(null);
    }

    @Test(expected = APIException.class)
    public void whenPassingEmptyArrayThrowException() {
        parser.parse(new ArrayList<>());
    }

    @Test
    public void parserShouldReturnArrayOfInputTestCases() {
        List<String> rows = new ArrayList<>();
        rows.add("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
        rows.add("8 : (1,15.3,€34)");
        rows.add("75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)");

        List<InputTestCase> result = parser.parse(rows);
        assertThat(result, instanceOf(List.class));
        assertThat(result, everyItem(instanceOf(InputTestCase.class)));
    }

    @Test
    public void whenInputFileHasTwoRowsParserShouldReturnTwoInputCase() {
        List<String> rows = new ArrayList<>();
        rows.add("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
        rows.add("8 : (1,15.3,€34)");

        List<InputTestCase> result = parser.parse(rows);
        assertThat(result.size(), is(2));
    }

    @Test(expected = APIException.class)
    public void whenInputFileHasIncorrectMaxWeightValParserShouldThrowException() {
        List<String> rows = new ArrayList<>();
        rows.add("incorrect : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
        rows.add("8 : (1,15.3,€34)");
        parser.parse(rows);
    }

    @Test(expected = APIException.class)
    public void whenInputFileHasIncorrectFormatParserShouldThrowException() {
        List<String> rows = new ArrayList<>();
        rows.add("81 :: (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
        rows.add("8 : (1,15.3,€34)");
        parser.parse(rows);
    }

    @Test(expected = APIException.class)
    public void whenInputFileHasIncorrectPatternParserShouldThrowException() {
        List<String> rows = new ArrayList<>();
        rows.add("81 :: (1,53.38,€45) (2,8862,98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
        rows.add("8 : (1,15.3,€34)");
        parser.parse(rows);
    }

    @Test(expected = APIException.class)
    public void whenPatternDidNotMatchedParserShouldThrowException() {
        List<String> rows = new ArrayList<>();
        rows.add("81 : (1,53.38,€45) (2,8862,98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
        rows.add("8 : (1,15.3,€34)");
        parser.parse(rows);
    }

    @Test(expected = APIException.class)
    public void whenInvalidSymbolInTheEndParserShouldThrowException() {
        List<String> rows = new ArrayList<>();
        rows.add("81 :: (1,53.38,€45) (2,8862,98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
        rows.add("8 : (1,15.3,€34))");
        parser.parse(rows);
    }

    @Test
    public void whenInputFileHasCorrectDataParserShouldReturnCorrectItems() {
        List<String> rows = new ArrayList<>();
        rows.add("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
        rows.add("8 : (1,15.3,€34)");

        List<InputTestCase> result = parser.parse(rows);

        InputTestCase firstInputTestCase = result.get(0);
        List<Item> items = firstInputTestCase.getItems();
        Item item = items.get(0);

        assertThat(firstInputTestCase.getMaxWeight(), is(81 * 100));
        assertThat(items.size(), is(6));
        assertThat(item.getIndex(), is(1));
        assertThat(item.getWeight(), is(5338));
        assertThat(item.getCost(), is(45));
    }

}
