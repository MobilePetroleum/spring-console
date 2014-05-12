package com.mobilepetroleum.springconsole.client.console;

class SpringCommand {

    private Remote[] remotes = new Remote[]{};
    private Invocation[] invocations = new Invocation[]{};

    public Remote[] getRemotes() {
        return remotes;
    }

    public void setRemotes(Remote[] remotes) {
        this.remotes = remotes;
    }

    public Invocation[] getInvocations() {
        return invocations;
    }

    public void setInvocations(Invocation[] invocations) {
        this.invocations = invocations;
    }

}
