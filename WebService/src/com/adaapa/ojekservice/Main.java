package com.adaapa.ojekservice;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] argv) {
        Object implementor = new OjekOnline ();
        String address = "http://localhost:8002/OjekOnline";
        Endpoint.publish(address, implementor);
    }
}

