package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class InfoContactState extends State {
    protected InfoContactState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();

            if ("name".equals(fieldName)) {
                parser.nextToken();
                System.out.println("Contact Name: " + parser.getText());
                continue;
            }

            if ("url".equals(fieldName)) {
                parser.nextToken();
                System.out.println("Contact URL: " + parser.getText());
            }
        }
    }

    public static InfoContactState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final InfoContactState INSTANCE = new InfoContactState();
    }
}
