package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class ParameterState extends State {
    protected ParameterState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {

            String fieldName = parser.getCurrentName();

            if ("in".equals(fieldName)) {
                parser.nextToken();
                System.out.println("in: " + parser.getText());
                continue;
            }

            if ("name".equals(fieldName)) {
                parser.nextToken();
                System.out.println("name: " + parser.getText());
                continue;
            }

            if ("required".equals(fieldName)) {
                parser.nextToken();
                System.out.println("required: " + parser.getText());
                continue;
            }

            if ("type".equals(fieldName)) {
                parser.nextToken();
                System.out.println("type: " + parser.getText());
                continue;
            }

            if ("schema".equals(fieldName)) {
                IState schemaState = SchemaState.getInstance(context);
                setState(schemaState);
                context.process();
            }
        }
    }

    public static ParameterState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final ParameterState INSTANCE = new ParameterState();
    }
}
