package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class HttpVerbResponsesState extends State {
    protected HttpVerbResponsesState() {}

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
                IState httpResponseState = HttpResponseState.getInstance(context);
                setState(httpResponseState);
                context.process();
            }
        }
    }

    public static HttpVerbResponsesState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final HttpVerbResponsesState INSTANCE = new HttpVerbResponsesState();
    }

}
