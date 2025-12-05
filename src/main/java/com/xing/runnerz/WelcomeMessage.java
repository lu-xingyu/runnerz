package com.xing.runnerz;

import org.springframework.stereotype.Component;

// Spring by default uses the class name with a lowercase first letter as the Bean name
@Component // this class is available to Spring
public class WelcomeMessage {
    public String getWelcomeMessage() {
        return "Welcome to the String Boot Application!";
    }
}
