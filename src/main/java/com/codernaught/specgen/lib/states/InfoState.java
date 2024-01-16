package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class InfoState implements State {

    private Context context;

    public InfoState(Context context) {
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

            if ("description".equals(fieldName)) {
                System.out.println("fieldName: " + fieldName);
                parser.nextToken();
                System.out.println("fieldValue: " + parser.getText());
                continue;
            }

            if ("version".equals(fieldName)) {
                System.out.println("fieldName: " + fieldName);
                parser.nextToken();
                System.out.println("fieldValue: " + parser.getText());
                continue;
            }

            if ("title".equals(fieldName)) {
                System.out.println("fieldName: " + fieldName);
                parser.nextToken();
                System.out.println("fieldValue: " + parser.getText());
                continue;
            }

            if ("contact".equals(fieldName)) {
                State infoContactState = new InfoContactState(context);
                infoContactState.process();
                continue;
            }

            if ("license".equals(fieldName)) {
                State infoLicenseState = new InfoLicenseState(context);
                infoLicenseState.process();
                continue;
            }
        }
    }


    public void process(String fieldName, String fieldValue) throws IOException {

    }
}
