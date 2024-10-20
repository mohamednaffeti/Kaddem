package org.example.kaddem.utils;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class Util{

    public String generateId(String prefix){
        return prefix+Math.abs(new Random().nextLong());
    }
}
