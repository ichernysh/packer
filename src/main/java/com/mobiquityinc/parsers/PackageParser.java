package com.mobiquityinc.parsers;

import com.mobiquityinc.exceptions.APIException;
import com.mobiquityinc.packer.items.InputTestCase;
import com.mobiquityinc.packer.items.Item;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.mobiquityinc.constants.Constants.ITEM.INDEX;
import static com.mobiquityinc.constants.Constants.ITEM.COST;
import static com.mobiquityinc.constants.Constants.ITEM.WEIGHT;
import static com.mobiquityinc.constants.Constants.PATTERN.PACKAGE_REGEX;

public class PackageParser {

    public List<InputTestCase> parse(List<String> rows) {
        if (rows == null) {
            throw new APIException("Parser receive null as an input.");
        }

        if (rows.isEmpty()) {
            throw new APIException("Parser receive empty array as an input.");
        }

        return rows.stream().map(this::parseRow).collect(Collectors.toList());
    }


    private InputTestCase parseRow(String row) throws APIException {
        try {
            String[] splited = row.split(":");

            if (splited.length != 2) {
                throw new APIException("Test case should have `:`");
            }

            InputTestCase inputTestCase = new InputTestCase();

            int maxWeight = getMaxWeightForInputTestCase(splited[0]);

            inputTestCase.setMaxWeight(maxWeight * 100);

            parseItemsAndAddToTestCase(splited[1], inputTestCase);

            return inputTestCase;
        } catch (NumberFormatException e) {
            throw new APIException("Input file contains wrong data that could not be parsed.", e);
        }
    }

    private void parseItemsAndAddToTestCase(String input, InputTestCase inputTestCase) {
        Pattern pattern = Pattern.compile(PACKAGE_REGEX);
        Matcher matcher = pattern.matcher(input);

        int lastEnd = 0;

        while (matcher.find()) {
            if (matcher.start() != lastEnd + 1) {
                throw new APIException(String.format("Right side of `:` must be in the following pattern (%s) separated by space", PACKAGE_REGEX));
            }
            int index = Integer.valueOf(matcher.group(INDEX));
            int weight = (int) (Double.valueOf(matcher.group(WEIGHT)) * 100);
            int cost = Integer.valueOf(matcher.group(COST));

            Item item = new Item(index, weight, cost);
            inputTestCase.add(item);

            lastEnd = matcher.end();
        }

        if (lastEnd != input.length()) {
            throw new APIException("unexpected characters in the end of the row");
        }
    }

    private int getMaxWeightForInputTestCase(String maxWeight) {
        return Integer.valueOf(maxWeight.trim());
    }
}
