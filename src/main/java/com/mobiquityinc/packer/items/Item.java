package com.mobiquityinc.packer.items;

public class Item {
    private final int index;
    private final int weight;
    private final int cost;

    public Item(int index, int weight, int cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }
}
