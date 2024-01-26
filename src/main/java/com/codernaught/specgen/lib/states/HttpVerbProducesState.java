package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class HttpVerbProducesState extends State {
    private HttpVerbProducesState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        parser.nextToken();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            System.out.println("produces: " + parser.getText());
        }
    }

    public static HttpVerbProducesState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final HttpVerbProducesState INSTANCE = new HttpVerbProducesState();
    }
}
