package com.mobilepetroleum.springconsole.client.console;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mobilepetroleum.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SpringCommandRunner {

    private final static Logger logger = LoggerFactory.getLogger("console");

    public void run(SpringCommand springCommand) {

        Invocable[] invocables = transform(springCommand.getInvocations(), new Function<Invocation, Invocable>() {
            public Invocable apply(@Nullable Invocation input) {
                return new Invocable(input.getBeanName(), input.getMethod(), create(input.getTypes(), input.getArgs()));
            }
        });

        for (Remote remote : springCommand.getRemotes()) {
            execute(remote, springCommand, invocables);
        }

    }

    private MethodParameter[] create(String[] types, JsonElement values) {
        if (values.isJsonNull()) {
            return new MethodParameter[]{};
        }
        JsonArray valuesAsArray = values.getAsJsonArray();
        MethodParameter[] methodParameters = new MethodParameter[valuesAsArray.size()];

        for (int i = 0; i < valuesAsArray.size(); i++) {
            methodParameters[i] = new MethodParameter(Optional.of(types, i).orElse(""), valuesAsArray.get(i).toString());
        }

        return methodParameters;
    }

    private void execute(Remote remote, SpringCommand context, Invocable[] invocables) {

        PojoSpringConsoleService pojoSpringConsoleService = null;
        try {
            pojoSpringConsoleService = PojoSpringConsoleService.create(remote.getIp(), remote.getPort(), remote.getRmiName());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return;
        }

        for (int i = 0; i < invocables.length; i++) {
            Invocable invocable = invocables[i];
            try {
                logger.info(f("[%s] %s::%s(%s)",
                        remote.getName(), invocable.getBeanName(), invocable.getMethodName(), toString(context.getInvocations()[i])));
                String result = pojoSpringConsoleService.invoke(invocable);
                logger.info(f("[%s] Result: %s", remote.getName(), result));
            } catch (Exception e) {

                Optional<String> message = Optional.of(e.getMessage());
                String inlineMessage = message.orElse("").replace("\n", "");

                logger.error(f("[%s] %s", remote.getName(), inlineMessage), e);

            }
        }

    }

    private String toString(Invocation parameters) {
        if (parameters.getArgs().isJsonNull()) {
            return "";
        }
        JsonArray asJsonArray = parameters.getArgs().getAsJsonArray();
        return Joiner.on(", ").join(asJsonArray.iterator());
    }

    private String f(String format, Object... args) {
        return String.format(format, args);
    }

    public Invocable[] transform(Invocation[] invocations, Function<Invocation, Invocable> f) {
        Invocable[] invocables = new Invocable[invocations.length];
        for (int i = 0; i < invocations.length; i++) {
            invocables[i] = f.apply(invocations[i]);
        }
        return invocables;
    }

}
