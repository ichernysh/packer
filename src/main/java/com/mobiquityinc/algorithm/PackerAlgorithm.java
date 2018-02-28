package com.mobiquityinc.algorithm;

import com.mobiquityinc.packer.items.InputTestCase;
import com.mobiquityinc.packer.items.Item;
import com.mobiquityinc.packer.items.Package;

import java.util.Comparator;
import java.util.List;

import static java.lang.Math.max;

public class PackerAlgorithm implements Algorithm {

    @Override
    public Package apply(InputTestCase testCase) {
        return fillPackage(testCase);
    }

    // Returns the Package which is the list of item's ids that should be inside the package
    private static Package fillPackage(InputTestCase testCase) {
        List<Item> items = testCase.getItems();

        // we need to sort items by it's weight, so that the item with less weight and same value will be putted to package
        items.sort(Comparator.comparingInt(Item::getWeight));

        int amountOfItemsInTestCase = items.size() + 1;
        int maxTestCaseWeight = testCase.getMaxWeight() + 1;
        int[][] matrix = new int[amountOfItemsInTestCase][maxTestCaseWeight];

        // Building matrix due to Knapsack algorithm.
        for (int itemPosition = 1; itemPosition < amountOfItemsInTestCase; itemPosition++) {
            Item item = items.get(itemPosition - 1);

            for (int weight = 1; weight < maxTestCaseWeight; weight++) {
                if (item.getWeight() > weight) {
                    // If weight of the nth item is more than testCase capacity maxTestCaseWeight
                    // then item above(row -1 , same column) current position at the matrix that should be included in the optimal solution
                    matrix[itemPosition][weight] = matrix[itemPosition - 1][weight];
                } else {
                    // If weight of the nth item is less than testCase capacity maxTestCaseWeight
                    // than we need to choose max cost value between:
                    // 1. Value above current position or  sum of current item's cost and the value;
                    // 2. Value of current item plus maximum value obtained by n-1 items and maxTestCaseWeight minus weight of the current item
                    int firstCandidate = item.getCost() + matrix[itemPosition - 1][weight - item.getWeight()];
                    int secondCandidate = matrix[itemPosition - 1][weight];
                    matrix[itemPosition][weight] = max(firstCandidate, secondCandidate);
                }
            }
        }

        // At this point we have the matrix built. It is time to determine comma separated string of item's ids that should be inside the package
        return fillAndGetPackage(items, amountOfItemsInTestCase, maxTestCaseWeight, matrix);
    }

    // Builds comma separated string of item's ids to put at the package
    private static Package fillAndGetPackage(List<Item> items, int amountOfThingsInTestCase, int maxTestCaseWeight, int[][] matrix) {
        Package packageToFill = new Package();

        int maxRowPosition = amountOfThingsInTestCase - 1;
        int maxColumnPosition = maxTestCaseWeight - 1;

        // Starting loop from the end of matrix until current value is "0"
        while (matrix[maxRowPosition][maxColumnPosition] != 0) {
            // If cell value comes from the current row, then add index to the output string
            if (matrix[maxRowPosition][maxColumnPosition] != matrix[maxRowPosition - 1][maxColumnPosition]) {
                Item item = items.get(maxRowPosition - 1);
                packageToFill.addItemIndex(item.getIndex());
                //Navigating to new cell with potential index to putt in output string
                maxRowPosition--;
                maxColumnPosition = maxColumnPosition - item.getWeight();
            } else {
                // If cell value did not com from the current row, navigating to previous row
                maxRowPosition--;
            }
        }
        return packageToFill;
    }



}
