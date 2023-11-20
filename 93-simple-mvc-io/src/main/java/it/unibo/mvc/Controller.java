package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String HOME = System.getProperty("user.home");
    private static final String FILENAME = "output.txt";

    private File dest = new File(HOME + File.separator, FILENAME);
    
    public File getDest() {
        return dest;
    }

    public String getFilePath() {
        return dest.getPath();
    }

    public void setDest(final String file) {
        setDest(new File(file));
    }

    public void setDest(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            dest = file;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }

    public void save(final String text) throws IOException {
        try (PrintStream out = new PrintStream(dest, StandardCharsets.UTF_8)) {
            out.print(text);
        }
    }
}
