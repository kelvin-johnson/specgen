package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class InfoState extends State {
    protected InfoState() { }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();

            if ("description".equals(fieldName)) {
                parser.nextToken();
                System.out.println("description: " + parser.getText());
                continue;
            }

            if ("version".equals(fieldName)) {
                parser.nextToken();
                System.out.println("version: " + parser.getText());
                continue;
            }

            if ("title".equals(fieldName)) {
                parser.nextToken();
                System.out.println("title: " + parser.getText());
                continue;
            }

            if ("contact".equals(fieldName)) {
                IState infoContactState = InfoContactState.getInstance(context);
                setState(infoContactState);
                context.process();
                continue;
            }

            if ("license".equals(fieldName)) {
                IState infoLicenseState = InfoLicenseState.getInstance(context);
                setState(infoLicenseState);
                context.process();
            }
        }
    }


    public void process(String fieldName, String fieldValue) throws IOException {

    }

    public static InfoState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final InfoState INSTANCE = new InfoState();
    }
}
