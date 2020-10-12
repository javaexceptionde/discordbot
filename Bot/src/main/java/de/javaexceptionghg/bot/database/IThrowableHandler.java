package de.javaexceptionghg.bot.database;


public interface IThrowableHandler<T> {

    void handle(T t) throws Throwable;
}

