package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class MethodSecurityState extends State {
    protected MethodSecurityState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();

            if("basicAuth".equals(fieldName)) {
                while(parser.nextToken() != JsonToken.END_ARRAY) {
                    String basicAuthFieldName = parser.getCurrentName();
                    System.out.println("basicAuthFieldName: " + basicAuthFieldName);
                    if("username".equals(basicAuthFieldName)) {
                        parser.nextToken();
                        System.out.println("username: " + parser.getText());
                        continue;
                    }
                    if("password".equals(basicAuthFieldName)) {
                        parser.nextToken();
                        System.out.println("password: " + parser.getText());
                    }
                }
            }
        }
    }

    public static MethodSecurityState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final MethodSecurityState INSTANCE = new MethodSecurityState();
    }
}
