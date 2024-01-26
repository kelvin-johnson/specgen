package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class TagsState extends State {
    protected TagsState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            String fieldName = parser.getCurrentName();

            if ("name".equals(fieldName)) {
                parser.nextToken();
                System.out.println("Tag Name: " + parser.getText());
            }
        }
    }

    public static TagsState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final TagsState INSTANCE = new TagsState();
    }
}
