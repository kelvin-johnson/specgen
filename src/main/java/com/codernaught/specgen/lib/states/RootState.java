package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class RootState extends State {
    protected RootState() {}

    @Override
    public void process() throws IOException {
        YAMLParser parser = context.getParser();
        while (parser.nextToken() != null) {

            String fieldName = parser.getCurrentName();

            if ("swagger".equals(fieldName)) {
                parser.nextToken();
                System.out.println("swagger: " + parser.getText());
                continue;
            }

            if ("info".equals(fieldName)) {
                parser.hasCurrentToken();
                IState infoState = InfoState.getInstance(context);
                setState(infoState);
                context.process();
                continue;
            }

            if ("basePath".equals(fieldName)) {
                parser.nextToken();
                System.out.println("basePath: " + parser.getText());
                continue;
            }

            if ("tags".equals(fieldName)) {
                IState tagsState = TagsState.getInstance(context);
                setState(tagsState);
                context.process();
                continue;
            }

            if ("paths".equals(fieldName)) {
                IState pathState = PathsState.getInstance(context);
                setState(pathState);
                context.process();
                continue;
            }

            if ("schemes".equals(fieldName)) {
                IState schemeState = SchemeState.getInstance(context);
                setState(schemeState);
                context.process();
            }
        }
        parser.close();
    }

    public void process(String fieldName, String fieldValue) throws IOException {}

    public static RootState getInstance(Context context) {
        SingletonHelper.INSTANCE.setContext(context);
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final RootState INSTANCE = new RootState();
    }
}
