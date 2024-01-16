package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class TagsState implements State {

    private Context context;

    public TagsState(Context context) {
        this.context = context;
    }

    @Override
    public void setState(State state) {
        context.setCurrentState(state);
    }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            String fieldName = parser.getCurrentName();

            if ("name".equals(fieldName)) {
                parser.nextToken();
                System.out.println("Tag Name: " + parser.getText());
                continue;
            }
        }
    }
}
