package com.guorong.factory.method.plan_2;

/**
 *
 */
public enum EnvironmentType implements IEnvironmentType {
    ONLINE("online"),
    OFFLINE("offline"),
    ;
    private final String name;

    EnvironmentType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
