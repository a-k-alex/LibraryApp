package ua.alekseytsev.LibraryApp.web.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.FineService;

import java.util.TimerTask;

/**
 * Checks new fines
 */
public final class TaskChekingFines extends TimerTask {
    private static final Logger LOG = LogManager.getLogger(TaskChekingFines.class);
    private static final FineService FINE_SERVICE = new FineService();
    private static TaskChekingFines instance;

    private TaskChekingFines() {
    }

    public static TaskChekingFines getInstance() {
        if (instance == null) {
            instance = new TaskChekingFines();
        }
        return instance;
    }

    @Override
    public void run() {
        try {
            LOG.debug("Checks new fines");
            FINE_SERVICE.findNew();
        } catch (LibraryException e) {
            LOG.error("Can not perform searching new fines", e);
            throw new IllegalStateException(e);
        }
    }
}
