package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class HttpVerbState implements State {
    private Context context;

    @Override
    public void setState(State state) { this.context.setCurrentState(state); }

    public HttpVerbState(Context context) { this.context = context; }

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();

            if ("get".equals(fieldName)
                    || "post".equals(fieldName)
                    || "put".equals(fieldName)
                    || "delete".equals(fieldName)) {
                System.out.println("httpVerb: " + fieldName);

                parser.nextToken();
                fieldName = parser.getCurrentName();
                if("tags".equals(fieldName)) {
                    State HttpVerbTagState = new HttpVerbTagState(context);
                    HttpVerbTagState.process();
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
                    State httpVerbProducesState = new HttpVerbProducesState(context);
                    httpVerbProducesState.process();
                    continue;
                }

                if("parameters".equals(fieldName)) {
                    State httpVerbParametersState = new HttpVerbParametersState(context);
                    httpVerbParametersState.process();
                    continue;
                }

                if("responses".equals(fieldName)) {
                    State httpVerbResponsesState = new HttpVerbResponsesState(context);
                    httpVerbResponsesState.process();
                    continue;
                }

                if("security".equals(fieldName)) {
                    State httpVerbSecurityState = new HttpVerbSecurityState(context);
                    httpVerbSecurityState.process();
                    continue;
                }
            }
        }
    }
}
