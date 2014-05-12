package com.mobilepetroleum.springconsole.client.console;

import com.mobilepetroleum.Invocable;
import com.mobilepetroleum.SpringService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

class PojoSpringConsoleService {

    private SpringService springService;

    private PojoSpringConsoleService(SpringService springService) {
        this.springService = springService;
    }

    static PojoSpringConsoleService create(String host, String port, String rmiName) {
        return new PojoSpringConsoleService(RmiUtil.create(host, port, rmiName, SpringService.class));
    }

    public String invoke(Invocable invocable) {
        try {
            return springService.invoke(invocable);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
