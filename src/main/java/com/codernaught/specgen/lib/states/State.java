package com.codernaught.specgen.lib.states;

import java.io.IOException;

public interface State {

    void setState(State state);

    void process() throws IOException;
    //void process(String fieldName, String fieldValue) throws IOException;


}
