package domain;

import dbconnector.h2Connection;

public class serviceFactory {
    public TodoService todolistServiceFactroy() {
        return new TodoService(new h2Connection());
    }
}
