package com.mobilepetroleum.springconsole.client.console;

import com.mobilepetroleum.Exceptions;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

class RmiUtil {

    private RmiUtil() {}

    @SuppressWarnings("unchecked")
    static <T> T create(String host, String port, String rmiName, Class<T> clazz) {
        try {
            return (T) LocateRegistry.getRegistry(host, Integer.parseInt(port)).lookup(rmiName);
        } catch (Exception e) {
            throw Exceptions.runtime(e, "Can not connect to %s:%s:%s", host, port, rmiName);
        }
    }

}
