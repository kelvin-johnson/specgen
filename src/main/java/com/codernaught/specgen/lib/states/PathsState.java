package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class PathsState implements State {

    private Context context;

    public PathsState(Context context) {
        this.context = context;
    }

    @Override
    public void setState(State state) {
        context.setCurrentState(state);
    }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();

            if (fieldName.startsWith("/")) {
                System.out.println("path: " + fieldName);
                State pathState = new PathState(context);
                pathState.process();
            }

        }
    }
}
