package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class HttpVerbSecurityState extends State {
    protected HttpVerbSecurityState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            IState methodSecurityState = MethodSecurityState.getInstance(context);
            setState(methodSecurityState);
            context.process();
        }
    }

    public static HttpVerbSecurityState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final HttpVerbSecurityState INSTANCE = new HttpVerbSecurityState();
    }

}
