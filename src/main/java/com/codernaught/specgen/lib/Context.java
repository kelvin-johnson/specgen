package com.codernaught.specgen.lib;

import com.codernaught.specgen.lib.states.RootState;
import com.codernaught.specgen.lib.states.State;
import com.codernaught.specgen.lib.states.IState;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import org.springframework.core.io.FileSystemResource;
import java.io.IOException;

public class Context {
    private IState currentState;
    public void setCurrentState(IState currentState) { this.currentState = currentState; }

    private YAMLParser parser;
    public YAMLParser getParser() { return parser; }

    public Context(FileSystemResource fileSystemResource) throws IOException {
        YAMLFactory yamlFactory = new YAMLFactory();
        parser = yamlFactory.createParser(fileSystemResource.getFile());
        this.currentState = RootState.getInstance(this);
    }

    public void process() throws IOException {
        currentState.process();
    }
}
