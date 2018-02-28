package com.mobiquityinc.packer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class PackerTest {

    @Test
    public void testBasicWork() throws IOException {
        String result = Packer.pack(getFileAbsPath());
        assertThat("4\n" +
                "-\n" +
                "2,7\n" +
                "8,9", is(result));
    }

    private String getFileAbsPath() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("inputTestFile.txt");
        if (resource != null) {
            return new File(resource.getFile()).getAbsolutePath();
        }
        throw new IOException("Unable to locate inputTestFile.txt");
    }

}
