package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class HttpVerbParametersState implements State {

    private Context context;

    @Override
    public void setState(State state) {
        this.context.setCurrentState(state);
    }

    public HttpVerbParametersState(Context context) {
        this.context = context;
    }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            State parameterState = new ParameterState(context);
            setState(parameterState);
            context.process();
        }
    }
}
