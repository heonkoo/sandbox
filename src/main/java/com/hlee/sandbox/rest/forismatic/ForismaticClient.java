package com.hlee.sandbox.rest.forismatic;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ForismaticClient {

    private static String BASE_URL = "https://api.forismatic.com/api/1.0/";
    private static String PARAM_NAME_METHOD = "method";
    private static String PARAM_NAME_LANG = "lang";
    private static String PARAM_NAME_FORMAT = "format";

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    }

    public static void main(String[] args) {
        Language language;
        if (args == null || args.length == 0) {
            language = Language.ENGLISH;
        } else {
            String inputLang = args[0];
            if (inputLang == null || inputLang.isBlank()) {
                language = Language.ENGLISH;
            } else {
                if ("English".equals(inputLang)) {
                    language = Language.ENGLISH;
                } else if ("Russian".equals(inputLang)) {
                    language = Language.RUSSIAN;
                } else {
                    throw new IllegalArgumentException("Supported language: English or Russian");
                }
            }
        }

        ForismaticClient client = new ForismaticClient(language);
        QuoteResponse quote = client.getQuote();
        System.out.printf("Quote: \"%s\"%n", quote.getQuoteText());
        System.out.printf("Author: %s%n", quote.getQuoteAuthor());
    }

    public ForismaticClient() {
        this(Language.ENGLISH);
    }

    public ForismaticClient(Language language) {
        this.language = language;
    }

    private Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public QuoteResponse getQuote() {
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(buildUri("getQuote", "json", language.value())))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), QuoteResponse.class);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            // TODO: if exception can be handled gracefully, take care of it
            throw new RuntimeException(e);
        }
    }

    private String buildUri(String method, String format, String lang) {
        String params = PARAM_NAME_METHOD + "=" + method + "&" +
                PARAM_NAME_LANG + "=" + lang + "&" +
                PARAM_NAME_FORMAT + "=" + format;
        return BASE_URL + "?" + params;
    }
}
