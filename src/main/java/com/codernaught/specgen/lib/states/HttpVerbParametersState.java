package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.IOException;

public class HttpVerbParametersState extends State {

    private HttpVerbParametersState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            IState parameterState = ParameterState.getInstance(context);
            setState(parameterState);
            context.process();
        }
    }

    public static HttpVerbParametersState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final HttpVerbParametersState INSTANCE = new HttpVerbParametersState();
    }
}
