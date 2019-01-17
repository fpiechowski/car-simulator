package carsimulator.car.pedal;

import org.junit.Test;
import org.junit.runner.notification.RunListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PedalTest {

    @Test
    public void testPush() throws InterruptedException {
        Pedal pedal = new Pedal();
        pedal.push();
        Thread.sleep(1000);
        assertTrue(pedal.getPushPercentage() < 1);
        System.out.println("Pedal pushed in " + pedal.getPushPercentage() * 100 + " percent after 1 sec.");
        Thread.sleep(3000);
        assertEquals(1, pedal.getPushPercentage(), 0.0);
        System.out.println("Pedal pushed in " + pedal.getPushPercentage() * 100 + " percent after 3 sec.");
        pedal.interrupt();
    }

    @Test
    public void testRelease() throws InterruptedException {
        Pedal pedal = new Pedal();
        pedal.push();
        System.out.println("Pedal pushed");
        Thread.sleep(1000);
        assertTrue(pedal.getPushPercentage() < 1 && pedal.getPushPercentage() > 0);
        pedal.release();
        System.out.println("Pedal released");
        assertEquals(0, pedal.getPushPercentage(), 0.0);
    }
}
