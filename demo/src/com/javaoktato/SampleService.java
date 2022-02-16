package com.javaoktato;

public sealed interface SampleService permits SampleServiceImpl {
    void operation();
}
