package it.unibo.mvc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> printedStrings = new LinkedList<>();
    private String nextString;

    @Override
    public void setNextString(String s) {
        this.nextString = Objects.requireNonNull(s, "Not accepted NULL strings");
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> getPrintedStrings() {
        return Collections.unmodifiableList(this.printedStrings);
    }

    @Override
    public void printString() {
        if(this.nextString != null) {
            this.printedStrings.add(this.nextString);
            System.out.println(this.nextString);
        }
    }

}
