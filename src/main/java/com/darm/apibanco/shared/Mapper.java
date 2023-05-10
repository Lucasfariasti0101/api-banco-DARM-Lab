package com.darm.apibanco.shared;

public interface Mapper<S, T> {
    T map(S source);
}
