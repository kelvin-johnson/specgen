package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class AdditionalPropertiesState extends State {
    protected AdditionalPropertiesState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();

            if("type".equals(fieldName)) {
                parser.nextToken();
                System.out.println("type: " + parser.getText());
            }
        }
    }

    public static AdditionalPropertiesState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final AdditionalPropertiesState INSTANCE = new AdditionalPropertiesState();
    }
}
