package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class PathsState extends State {
    protected PathsState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();

            if (fieldName.startsWith("/")) {
                System.out.println("path: " + fieldName);
                IState pathState = PathState.getInstance(context);
                setState(pathState);
                context.process();
            }

        }
    }

    public static PathsState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final PathsState INSTANCE = new PathsState();
    }
}
