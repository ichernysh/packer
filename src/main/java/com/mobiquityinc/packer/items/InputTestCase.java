package com.mobiquityinc.packer.items;

import java.util.ArrayList;
import java.util.List;

public class InputTestCase {

    private int maxWeight;
    private List<Item> items = new ArrayList<>();

    public InputTestCase() {
    }

    public InputTestCase(int maxWeight, List<Item> items) {
        this.maxWeight = maxWeight;
        this.items = items;
    }

    public void add(Item item) {
        items.add(item);
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public List<Item> getItems() {
        return items;
    }
}
