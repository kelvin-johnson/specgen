package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class HttpVerbSecurityState implements State {

    private Context context;

    public HttpVerbSecurityState(Context context) {
        this.context = context;
    }

    @Override
    public void setState(State state) {
        this.context.setCurrentState(state);
    }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            State methodSecurityState = new MethodSecurityState(context);
            setState(methodSecurityState);
            context.process();
        }
    }
}
