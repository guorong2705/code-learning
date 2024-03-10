package com.guorong.proxy.v1;

import lombok.RequiredArgsConstructor;

/**
 * 巫师
 */
@RequiredArgsConstructor
class Wizard {
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
