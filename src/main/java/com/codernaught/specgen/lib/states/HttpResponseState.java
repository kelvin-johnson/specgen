package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class HttpResponseState implements State {
    private Context context;

    public HttpResponseState(Context context) {
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

            if("description".equals(fieldName)) {
                parser.nextToken();
                System.out.println("description: " + parser.getText());
                continue;
            }

            if("schema".equals(fieldName)) {
                State schemaState = new SchemaState(context);
                schemaState.process();
                continue;
            }
        }
    }
}
