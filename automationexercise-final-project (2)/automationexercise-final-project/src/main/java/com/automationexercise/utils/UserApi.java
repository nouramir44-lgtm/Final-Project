package com.automationexercise.utils;

import com.automationexercise.config.ConfigReader;
import com.automationexercise.model.User;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Creates and deletes accounts through the site's public REST API.
 * Used for fast test setup and cleanup; every method returns false instead of throwing,
 * so callers can fall back to the UI.
 */
public final class UserApi {

    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private UserApi() {
    }

    public static boolean createAccount(User user) {
        Map<String, String> form = new LinkedHashMap<>();
        form.put("name", user.name());
        form.put("email", user.email());
        form.put("password", user.password());
        form.put("title", user.title());
        form.put("birth_date", user.birthDay());
        form.put("birth_month", user.birthMonth());
        form.put("birth_year", user.birthYear());
        form.put("firstname", user.firstName());
        form.put("lastname", user.lastName());
        form.put("company", user.company());
        form.put("address1", user.address1());
        form.put("address2", user.address2());
        form.put("country", user.country());
        form.put("zipcode", user.zipcode());
        form.put("state", user.state());
        form.put("city", user.city());
        form.put("mobile_number", user.mobileNumber());
        return send("POST", "/api/createAccount", form, 201);
    }

    public static boolean deleteAccount(String email, String password) {
        Map<String, String> form = new LinkedHashMap<>();
        form.put("email", email);
        form.put("password", password);
        return send("DELETE", "/api/deleteAccount", form, 200);
    }

    private static boolean send(String method, String path, Map<String, String> form, int expectedCode) {
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(ConfigReader.get("base.url") + path))
                    .timeout(Duration.ofSeconds(20))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("User-Agent", "Mozilla/5.0 (AutomationExercise test suite)")
                    .method(method, HttpRequest.BodyPublishers.ofString(encode(form)))
                    .build();
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            // The API always answers HTTP 200; the real status is the "responseCode" field in the JSON body.
            return Pattern.compile("\"responseCode\"\\s*:\\s*" + expectedCode).matcher(response.body()).find();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (IOException | RuntimeException e) {
            return false;
        }
    }

    private static String encode(Map<String, String> form) {
        return form.entrySet().stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8)
                        + "=" + URLEncoder.encode(entry.getValue() == null ? "" : entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }
}
