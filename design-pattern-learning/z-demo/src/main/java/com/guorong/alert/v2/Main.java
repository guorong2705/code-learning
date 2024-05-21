package com.guorong.alert.v2;

public class Main {
    public static void main(String[] args) {
        ApiInfo apiInfo = new ApiInfo(100,2, 0);
        Alert alert = ApplicationContext.getInstance().getAlert();
        alert.check(apiInfo);
    }
}
