package com.nikhil.ChainOfResponsibilityPattern;

public class Main {
    public static void main(String[] args){
        LogProcessor logProcessor = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));
        logProcessor.log(logProcessor.ERROR, "error caught");
        logProcessor.log(logProcessor.DEBUG, "debugging");
        logProcessor.log(logProcessor.INFO, "getting info");
    }
}
