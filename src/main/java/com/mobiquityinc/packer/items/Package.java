package com.mobiquityinc.packer.items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Package {

    private List<Integer> indexes = new ArrayList<>();

    public void addItemIndex(int index) {
        indexes.add(index);
    }

    @Override
    public String toString() {
        String result = indexes.stream().sorted().map(Object::toString).collect(Collectors.joining(","));
        return result.isEmpty() ? "-" : result;
    }
}
