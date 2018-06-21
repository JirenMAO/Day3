package me.mao.minigame.api.coreListener;

import me.mao.minigame.Core;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.IllegalPluginAccessException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class CoreListener implements Listener, EventExecutor {

    private final HashMap<Class<? extends Event>, Method> methodMap = new HashMap<Class<? extends Event>, Method>();

    private final Core core;
    protected String name;

    public CoreListener(Core core, String name) {
        this.core = core;
        this.name = name;

        for (Method method : this.getClass().getMethods()) {
            method.setAccessible(true);
            Annotation eventHandler = method.getAnnotation(EventHandler.class);
            if (eventHandler != null) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 1) {
                    registerEvent((Class<? extends Event>) params[0], ((EventHandler) eventHandler).priority(), method);
                }
            }
        }
    }

    private void registerEvent(Class<? extends Event> event, EventPriority priority, Method method) {
        try {
            Bukkit.getPluginManager().registerEvent(event, this, priority, this, core);
            methodMap.put(event, method);
        } catch (NullPointerException e) {
            Bukkit.getLogger().severe("Illegal event registration!");
        } catch (IllegalPluginAccessException e) {
            Bukkit.getLogger().severe("Illegal plugin access exception!");
            Bukkit.getLogger().severe(e.getMessage());
            Bukkit.getLogger().severe("Tried to register illegal event: " + event.getCanonicalName());
        }
    }

    @Override
    public void execute(Listener arg0, Event arg1) throws EventException {
        Method method = methodMap.get(arg1.getClass());
        if (method == null) {
            return;
        }
        try {
            method.invoke(this, arg1);
        } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
                InvocationTargetException ite = (InvocationTargetException) e;
                e.setStackTrace(ite.getCause().getStackTrace());
            }

            String eventName = arg1.getClass().getCanonicalName();
            core.getLogger().severe("Error on event: " + e + name + eventName);
            core.getLogger().severe("Unhandled exception in listener " + name + "! Please check the error logs for more information: " + name + ":" + e);
        }

    }

}
