package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class HttpVerbTagState extends State {
    protected HttpVerbTagState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        parser.nextToken();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            System.out.println("Tag: " + parser.getText());
        }
    }

    public static HttpVerbTagState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final HttpVerbTagState INSTANCE = new HttpVerbTagState();
    }
}
