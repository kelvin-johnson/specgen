package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class HttpVerbProducesState implements State {

    private Context context;

    public HttpVerbProducesState(Context context) {
        this.context = context;
    }

    @Override
    public void setState(State state) {
        this.context.setCurrentState(state);
    }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        parser.nextToken();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            System.out.println("produces: " + parser.getText());
        }
    }
}
