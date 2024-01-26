package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class PathState extends State {
    protected PathState() {}

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
                IState httpVerbState = HttpVerbState.getInstance(context);
                setState(httpVerbState);
                context.process();
            }
        }
    }

    public static PathState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final PathState INSTANCE = new PathState();
    }
}
