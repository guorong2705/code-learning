package com.guorong.try_with_resource.demo_2;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException {
        try(
                First first = new First();
                Second second = new Second();
        ) {
        }
    }
}
