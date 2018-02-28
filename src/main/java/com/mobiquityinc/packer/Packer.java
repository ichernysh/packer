package com.mobiquityinc.packer;

import com.mobiquityinc.algorithm.Algorithm;
import com.mobiquityinc.algorithm.factory.AlgorithmFactory;
import com.mobiquityinc.packer.items.InputTestCase;
import com.mobiquityinc.packer.items.Package;
import com.mobiquityinc.parsers.PackageParser;
import com.mobiquityinc.reader.InputFileReader;
import com.mobiquityinc.validator.InputTestCaseValidator;

import java.util.List;
import java.util.stream.Collectors;

class Packer {
    private static InputFileReader inputFileReader = new InputFileReader();
    private static PackageParser parser = new PackageParser();
    private static InputTestCaseValidator validator = new InputTestCaseValidator();

    public static String pack(String path) {
        List<String> rows = inputFileReader.read(path);
        List<InputTestCase> testCaseList = parser.parse(rows);
        testCaseList.forEach(inputTestCase -> validator.validate(inputTestCase));

        return calculate(testCaseList);
    }

    private static String calculate(List<InputTestCase> testCaseList) {
        return getPackages(testCaseList).stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    private static List<Package> getPackages(List<InputTestCase> testCaseList) {
        Algorithm algorithm = AlgorithmFactory.getAlgorithm();
        return testCaseList.parallelStream().map(algorithm::apply).collect(Collectors.toList());
    }

   

}
