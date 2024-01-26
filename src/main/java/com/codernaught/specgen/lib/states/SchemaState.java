package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class SchemaState extends State {
    protected SchemaState() {}

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
                IState schemaItemsState = SchemaItemsState.getInstance(context);
                setState(schemaItemsState);
                context.process();
            }
        }
    }
    public static SchemaState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final SchemaState INSTANCE = new SchemaState();
    }
}
