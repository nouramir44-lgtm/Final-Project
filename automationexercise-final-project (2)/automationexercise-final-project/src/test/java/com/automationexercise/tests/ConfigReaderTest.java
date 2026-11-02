package com.automationexercise.tests;

import com.automationexercise.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigReaderTest {

    @Test
    public void baseUrlIsConfigured() {
        String baseUrl = ConfigReader.get("base.url");
        Assert.assertNotNull(baseUrl, "base.url must be set in config.properties");
        Assert.assertTrue(baseUrl.startsWith("http"), "base.url must be an http(s) URL");
    }

    @Test
    public void timeoutFallsBackToDefaultWhenMissing() {
        Assert.assertEquals(ConfigReader.getInt("does.not.exist", 7), 7);
    }
}
