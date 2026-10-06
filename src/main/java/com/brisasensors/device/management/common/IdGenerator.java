package com.brisasensors.device.management.common;

import io.hypersistence.tsid.TSID;

import java.util.Optional;

public class IdGenerator {

    private static final TSID.Factory TSID_FACTORY;

    static {
        Optional.ofNullable(System.getenv("tsid.node"))
                .ifPresent(tsIdNode -> System.setProperty("tsid.node", tsIdNode));
        Optional.ofNullable(System.getenv("tsid.node.count"))
                .ifPresent(tsIdNodeCount -> System.setProperty("tsid.node.count", tsIdNodeCount));

        TSID_FACTORY = TSID.Factory.builder().build();
    }

    private IdGenerator() {
    }

    public static TSID generateTSID() {
        return TSID_FACTORY.generate();
    }
}
