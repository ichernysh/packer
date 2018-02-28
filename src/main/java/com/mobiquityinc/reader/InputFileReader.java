package com.mobiquityinc.reader;

import com.mobiquityinc.exceptions.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.exists;
import static java.nio.file.Paths.get;

public class InputFileReader {

    public List<String> read(String filePath) {
        if (filePath == null) {
            throw new APIException("Path is null.");
        }

        if (filePath.isEmpty()) {
            throw new APIException("Path is empty.");
        }

        Path file = get(filePath);
        if (!exists(file)) {
            throw new APIException("Path should exists.");
        }

        return getFileContentAsListOfStrings(filePath);
    }

    private List<String> getFileContentAsListOfStrings(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath), UTF_8);
        } catch (IOException e) {
            throw new APIException("Enable to read input file.", e);
        }
    }
}
