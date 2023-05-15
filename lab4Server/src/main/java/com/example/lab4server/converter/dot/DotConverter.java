package com.example.lab4server.converter.dot;

public interface DotConverter<T,V> {
    V convert(T t);
}
