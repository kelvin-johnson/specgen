package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class MethodSecurityState implements State {

    private Context context;

    public MethodSecurityState(Context context) {
        this.context = context;
    }

    @Override
    public void setState(State state) {
        this.context.setCurrentState(state);
    }

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
}
