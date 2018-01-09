package com.example.leos.simplenote.utilities;

import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;


@SmallTest
public class DateUtilsTest {
    private long time = 1446885450;

    @Before
    public void setUp() throws Exception {

    }

    @Test( timeout = 50)
    public void getCurrentDateTime_NotNull() throws Exception {
        long miliDate = DateUtils.getCurrentDateTime();

        assertNotNull("DateTime in milis", miliDate);
        assertThat(miliDate, Is.isA(Long.class));
    }

    @Test
    public void getFormatedDate_NotNull() throws Exception {
        String expectedDate = "18-Jan-1970";

        String formatedDate = DateUtils.getFormatedDate(time);

        assertEquals("The formated date", expectedDate, formatedDate);
    }

    @Test
    public void getFormatedTime_NotNull() throws Exception {
        String expectedTime = "12:54:45 AM";

        String formatedTime = DateUtils.getFormatedTime(time);

        assertEquals("The formated date", expectedTime, formatedTime);
    }
}