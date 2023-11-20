package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawResult;

import static java.lang.System.out;

public class StdoutDrawNumberView implements DrawNumberView {

    @Override
    public void result(final DrawResult res) {
        out.println(res.getDescription());
    }

    @Override
    public void setController(DrawNumberController observer) {
        throw new UnsupportedOperationException("Unimplemented method 'setController'");
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

}