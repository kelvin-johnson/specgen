package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class HttpResponseState extends State {
    protected HttpResponseState() {}

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
                IState schemaState = SchemaState.getInstance(context);
                setState(schemaState);
                context.process();
            }
        }
    }

    public static HttpResponseState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final HttpResponseState INSTANCE = new HttpResponseState();
    }
}
