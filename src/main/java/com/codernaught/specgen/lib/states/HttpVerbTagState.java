package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class HttpVerbTagState implements State {

    private Context context;

    @Override
    public void setState(State state) {
        this.context.setCurrentState(state);
    }

    public HttpVerbTagState(Context context) {
        this.context = context;
    }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        parser.nextToken();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            System.out.println("Tag: " + parser.getText());
        }
    }
}
