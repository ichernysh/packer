package com.mobiquityinc.constants;

public class Constants {

    public class ITEM {
        public static final String INDEX = "index";
        public static final String WEIGHT = "weight";
        public static final String COST = "cost";
    }

    public class PATTERN {
        public static final String PACKAGE_REGEX = "\\((?<" + ITEM.INDEX + ">\\d+)\\,(?<" + ITEM.WEIGHT + ">\\d+(\\.\\d{1,2})?)\\,€(?<" + ITEM.COST + ">\\d+(\\.\\d{1,2})?)\\)";
    }


}
