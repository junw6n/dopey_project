package service;

import dbconnector.h2Connection;

public class serviceFactory {
    public todolistService todolistServiceFactroy() {
        return new todolistService(new h2Connection());
    }
}
