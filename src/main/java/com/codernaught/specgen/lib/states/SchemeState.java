package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class SchemeState implements State {

    private Context context;

    public SchemeState(Context context) {
        this.context = context;
    }

    @Override
    public void setState(State state) {
        context.setCurrentState(state);
    }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        parser.nextToken(); //Skip the array start token
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            System.out.println("scheme: " + parser.getText());
        }
    }
}
