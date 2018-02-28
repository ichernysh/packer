package com.mobiquityinc.packer;

import com.mobiquityinc.exceptions.APIException;
import com.mobiquityinc.reader.InputFileReader;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputFileReaderTest {

    private InputFileReader reader = new InputFileReader();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test(expected = APIException.class)
    public void whenInputFileDoesNotExistThrowError() {
        reader.read("invalid-path");
    }

    @Test(expected = APIException.class)
    public void whenInputFilePathIsEmptyThrowError() {
        reader.read("");
    }

    @Test(expected = APIException.class)
    public void whenInputFilePathIsNullThrowError() {
        reader.read(null);
    }

    @Test
    public void whenPathIsValidListOfRowsShouldBeReturn() throws IOException {
        final File tempInputFile = folder.newFile("tempInputFile.txt");

        FileUtils.writeStringToFile(tempInputFile, "test-contant");
        assertThat(reader.read(tempInputFile.getAbsolutePath()), instanceOf(ArrayList.class));
    }

    @Test
    public void listShouldBeReturn() throws IOException {
        final File tempInputFile = folder.newFile("tempInputFile.txt");

        FileUtils.writeStringToFile(tempInputFile, "test-contant");
        assertThat(reader.read(tempInputFile.getAbsolutePath()), instanceOf(ArrayList.class));
    }

}
