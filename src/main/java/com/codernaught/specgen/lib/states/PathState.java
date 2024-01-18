package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class PathState implements State {
    private Context context;

    public PathState(Context context) {
        this.context = context;
    }
    @Override
    public void setState(State state) {
        this.context.setCurrentState(state);
    }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();

            if ("get".equals(fieldName)
                    || "post".equals(fieldName)
                    || "put".equals(fieldName)
                    || "delete".equals(fieldName)) {
                System.out.println("httpVerb: " + fieldName);
                State httpVerbState = new HttpVerbState(context);
                httpVerbState.process();
            }
        }
    }
}
