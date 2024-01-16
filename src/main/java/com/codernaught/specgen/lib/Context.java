package com.codernaught.specgen.lib;

import com.codernaught.specgen.lib.states.InfoState;
import com.codernaught.specgen.lib.states.RootState;
import com.codernaught.specgen.lib.states.State;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import org.springframework.core.io.FileSystemResource;
import java.io.IOException;

public class Context {
    private State currentState;
    public void setCurrentState(State currentState) { this.currentState = currentState; }

    private YAMLParser parser;
    public YAMLParser getParser() { return parser; }

    public Context(FileSystemResource fileSystemResource) throws IOException {
        YAMLFactory yamlFactory = new YAMLFactory();
        parser = yamlFactory.createParser(fileSystemResource.getFile());
        this.currentState = new RootState(this);
    }

    public void process() throws IOException {
        currentState.process();
    }
}
