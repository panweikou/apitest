package O2O.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Sean_Pan
 * @ClassName TradeBookingService
 * @Description TODO
 * @date 11/30/2021 6:40 PM
 * @Version 1.0
 */
public class TradeBookingService {
    private static Logger logger = LoggerFactory.getLogger(TradeBookingService.class);
    private static TradeBookingService instance = null;
    private final Lock lock = new ReentrantLock();

    public static TradeBookingService getInstance() {
        if (null == instance) {
            synchronized (TradeBookingService.class) {
                if (null == instance) {
                    instance = new TradeBookingService();
                }
            }
        }
        return instance;
    }

    public String generateEFXTRADEID() throws InterruptedException {
        return "HK" + "AL" + "01" + format36BaseNumber(10);
    }

    public String format36BaseNumber(int length) throws InterruptedException {
        return Long.toString(createTradeIDSeed());
    }

    public long createTradeIDSeed() throws InterruptedException {
        lock.lock();
        try {
            Thread.sleep(300);
            long ID = ((1923129381L- 1273812832L) / 100) * 100000;
            lock.unlock();
            return ID;
        }catch (Exception e) {
            lock.unlock();
            throw e;
        }
    }


}
