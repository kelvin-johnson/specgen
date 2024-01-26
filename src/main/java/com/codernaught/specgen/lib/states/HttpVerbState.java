package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class HttpVerbState extends State {
    protected HttpVerbState() { }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();
            if("tags".equals(fieldName)) {
                IState httpVerbTagState = HttpVerbTagState.getInstance(context);
                setState(httpVerbTagState);
                context.process();
                continue;
            }

            if("summary".equals(fieldName)) {
                parser.nextToken();
                System.out.println("summary: " + parser.getText());
                continue;
            }

            if("description".equals(fieldName)) {
                parser.nextToken();
                System.out.println("description: " + parser.getText());
                continue;
            }

            if("operationId".equals(fieldName)) {
                parser.nextToken();
                System.out.println("operationId: " + parser.getText());
                continue;
            }

            if("produces".equals(fieldName)) {
                IState httpVerbProducesState = HttpVerbParametersState.getInstance(context);
                setState(httpVerbProducesState);
                context.process();
                continue;
            }

            if("parameters".equals(fieldName)) {
                IState httpVerbParametersState = HttpVerbParametersState.getInstance(context);
                setState(httpVerbParametersState);
                context.process();
                continue;
            }

            if("responses".equals(fieldName)) {
                IState httpVerbResponsesState = HttpVerbParametersState.getInstance(context);
                setState(httpVerbResponsesState);
                context.process();
                continue;
            }

            if("security".equals(fieldName)) {
                IState httpVerbSecurityState = HttpVerbParametersState.getInstance(context);
                setState(httpVerbSecurityState);
                context.process();
            }
        }
    }
    public static HttpVerbState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final HttpVerbState INSTANCE = new HttpVerbState();
    }
}
