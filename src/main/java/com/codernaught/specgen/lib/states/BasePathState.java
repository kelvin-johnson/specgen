package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class BasePathState implements State {

    private Context context;

    public BasePathState(Context context) {
        this.context = context;
    }
    @Override
    public void setState(State state) {
        context.setCurrentState(state);
    }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();

        String fieldName = parser.getCurrentName();
        System.out.println("fieldName: " + fieldName);
        parser.nextToken();
        System.out.println("fieldValue: " + parser.getText());

    }
}
