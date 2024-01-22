package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class SchemaState implements State {

    private Context context;


    public SchemaState(Context context) {
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
            if ("$ref".equals(fieldName)) {
                parser.nextToken();
                System.out.println("$ref: " + parser.getText());
                continue;
            }

            if("type".equals(fieldName)) {
                parser.nextToken();
                System.out.println("type: " + parser.getText());
                continue;
            }

            if("items".equals(fieldName)) {
                parser.nextToken();
                State schemaItemsState = new SchemaItemsState(context);
                setState(schemaItemsState);
                context.process();
            }
        }
    }
}
