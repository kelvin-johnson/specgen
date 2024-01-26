package com.codernaught.specgen.lib.states;

import com.codernaught.specgen.lib.Context;

public abstract class State implements IState {
    protected Context context;

    @Override
    public void setContext(Context context) { this.context = context; }

    @Override
    public void setState(IState state) {
        context.setCurrentState(state);
    }

}
