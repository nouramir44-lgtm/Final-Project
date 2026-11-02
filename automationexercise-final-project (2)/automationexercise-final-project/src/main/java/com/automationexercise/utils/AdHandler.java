package com.automationexercise.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.Set;

/**
 * Best-effort handling of unexpected alerts, popup windows, advertisement overlays and
 * Google AdSense "#google_vignette" interstitials. Never fails a test when nothing is present.
 */
public class AdHandler {

    private static final String HIDE_ADS_SCRIPT = """
            const selectors = [
              'ins.adsbygoogle',
              'iframe[id^="aswift"]',
              'iframe[id^="google_ads"]',
              'iframe[src*="doubleclick"]',
              'iframe[src*="googlesyndication"]',
              'div[id^="google_ads"]',
              '#ad_position_box',
              '#google_vignette'
            ];
            selectors.forEach(function (selector) {
              document.querySelectorAll(selector).forEach(function (element) { element.remove(); });
            });
            if (window.location.hash.indexOf('google_vignette') !== -1) {
              history.replaceState(null, '', window.location.pathname + window.location.search);
            }
            """;

    private final WebDriver driver;
    private final String mainWindowHandle;

    public AdHandler(WebDriver driver) {
        this.driver = driver;
        this.mainWindowHandle = driver.getWindowHandle();
    }

    /** Runs every handler; safe to call at any time. */
    public void handleAll() {
        dismissUnexpectedAlert();
        closeUnexpectedWindows();
        hideAds();
    }

    public void dismissUnexpectedAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException ignored) {
            // nothing to dismiss
        } catch (WebDriverException ignored) {
            // best effort only
        }
    }

    public void closeUnexpectedWindows() {
        try {
            Set<String> handles = driver.getWindowHandles();
            if (handles.size() <= 1) {
                return;
            }
            for (String handle : handles) {
                if (!handle.equals(mainWindowHandle)) {
                    driver.switchTo().window(handle);
                    driver.close();
                }
            }
            driver.switchTo().window(mainWindowHandle);
        } catch (WebDriverException ignored) {
            // best effort only
        }
    }

    public void hideAds() {
        try {
            ((JavascriptExecutor) driver).executeScript(HIDE_ADS_SCRIPT);
        } catch (WebDriverException ignored) {
            // best effort only
        }
    }
}
