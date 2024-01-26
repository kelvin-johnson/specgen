package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;

import java.io.IOException;

public interface IState {

    void setContext(Context context);

    void setState(IState state);

    void process() throws IOException;
    //void process(String fieldName, String fieldValue) throws IOException;


}
