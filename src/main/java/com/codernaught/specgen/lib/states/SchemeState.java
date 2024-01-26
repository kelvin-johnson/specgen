package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class SchemeState extends State {
    protected SchemeState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        parser.nextToken(); //Skip the array start token
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            System.out.println("scheme: " + parser.getText());
        }
    }

    public static SchemeState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final SchemeState INSTANCE = new SchemeState();
    }
}
