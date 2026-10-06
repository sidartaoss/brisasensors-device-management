package com.brisasensors.device.management;

import com.brisasensors.device.management.common.IdGenerator;
import io.hypersistence.tsid.TSID;
import org.junit.jupiter.api.Test;

class TSIDTest {

    @Test
    void shouldGenerateTSID() {
        final var tsid1 = TSID.fast();
        final var tsid2 = TSID.fast();
        final var tsid3 = TSID.fast();
        final var tsid4 = TSID.fast();

        System.out.println("TSID 1: " + tsid1);
        System.out.println("TSID 1 Long: " + tsid1.toLong());
        System.out.println("TSID 1 Instant: " + tsid1.getInstant());
        System.out.println("TSID 2: " + tsid2);
        System.out.println("TSID 2 Long: " + tsid2.toLong());
        System.out.println("TSID 2 Instant: " + tsid2.getInstant());
        System.out.println("TSID 3: " + tsid3);
        System.out.println("TSID 3 Long: " + tsid3.toLong());
        System.out.println("TSID 3 Instant: " + tsid3.getInstant());
        System.out.println("TSID 4: " + tsid4);
        System.out.println("TSID 4 Long: " + tsid4.toLong());
        System.out.println("TSID 4 Instant: " + tsid4.getInstant());
    }

    @Test
    void shouldGenerateTSIDFactory() {
        final var tsid1 = IdGenerator.generateTSID();

        System.out.println("TSID 1: " + tsid1);
        System.out.println("TSID 1 Long: " + tsid1.toLong());
        System.out.println("TSID 1 Instant: " + tsid1.getInstant());
    }
}
