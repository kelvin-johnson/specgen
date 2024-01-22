package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;

public class RootState implements State {
    private Context context;

    public RootState(Context context) {
        this.context = context;
    }

    @Override
    public void setState(State state) {
        context.setCurrentState(state);
    }

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
                State infoState = new InfoState(context);
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
                State tagsState = new TagsState(context);
                setState(tagsState);
                context.process();
                continue;
            }

            if ("paths".equals(fieldName)) {
                State pathState = new PathsState(context);
                setState(pathState);
                context.process();
                continue;
            }

            if ("schemes".equals(fieldName)) {
                State schemeState = new SchemeState(context);
                setState(schemeState);
                context.process();
            }
        }
        parser.close();
    }


    public void process(String fieldName, String fieldValue) throws IOException {}
}
