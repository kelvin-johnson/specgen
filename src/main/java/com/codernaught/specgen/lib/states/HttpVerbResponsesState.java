package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class HttpVerbResponsesState implements State {

    private Context context;

    public HttpVerbResponsesState(Context context) {
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

            if ( (fieldName.startsWith("1") ||
                    fieldName.startsWith("2") ||
                    fieldName.startsWith("3") ||
                    fieldName.startsWith("4") ||
                    fieldName.startsWith("5")) &&
                    fieldName.length() == 3) {

                System.out.println("Http Status Code: " + fieldName);
                State httpResponseState = new HttpResponseState(context);
                setState(httpResponseState);
                context.process();
            }
        }
    }
}
