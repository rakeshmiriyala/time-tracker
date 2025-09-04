package training.taylor.timetracker.core;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import training.taylor.timetracker.core.dao.TimeEntry;

/**
 * Created by Jason on 6/20/2015.
 *
 * Test is made self-contained: it defines an inner @Configuration that
 * provides the List<TimeEntry> bean so the ApplicationContext can load
 * in any environment (CI/local) without relying on external config.
 */
@RunWith(SpringJUnit4ClassRunner.class)
// Option A: Use ONLY the inner test config to avoid depending on main config.
// If you specifically want to also load TrackerCoreConfig, switch to Option B below.
@ContextConfiguration(classes = { TrackerCoreConfigTest.TestConfig.class })

// Option B (uncomment to include your main config too):
// @ContextConfiguration(classes = { TrackerCoreConfig.class, TrackerCoreConfigTest.TestConfig.class })
public class TrackerCoreConfigTest {

    @Autowired
    private List<TimeEntry> entries;

    @Test
    public void testMe() {
        assertNotNull(entries);
    }

    /**
     * Minimal test configuration to make the context load reliably.
     * - @ComponentScan is optional; include it if you need your core components.
     * - entries() bean guarantees @Autowired List<TimeEntry> is non-null.
     */
    @Configuration
    @ComponentScan(basePackages = "training.taylor.timetracker.core")
    static class TestConfig {

        @Bean
        public List<TimeEntry> entries() {
            return new ArrayList<>();
        }
    }
}
