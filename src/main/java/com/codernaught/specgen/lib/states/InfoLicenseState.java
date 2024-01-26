package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class InfoLicenseState extends State {
    protected InfoLicenseState() {}

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
            }
        }
    }

    public static InfoLicenseState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final InfoLicenseState INSTANCE = new InfoLicenseState();
    }
}
