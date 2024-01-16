package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class InfoLicenseState implements State {

    private Context context;

    public InfoLicenseState(Context context) {
        this.context = context;
    }

    @Override
    public void setState(State state) {
        context.setCurrentState(state);
    }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();

            if ("name".equals(fieldName)) {
                parser.nextToken();
                System.out.println("License Name: " + parser.getText());
                continue;
            }

            if ("url".equals(fieldName)) {
                parser.nextToken();
                System.out.println("License URL: " + parser.getText());
                continue;
            }
        }
    }
}