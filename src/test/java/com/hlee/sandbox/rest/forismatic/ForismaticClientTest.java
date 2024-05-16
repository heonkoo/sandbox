package com.hlee.sandbox.rest.forismatic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForismaticClientTest {

    private ForismaticClient client;

    @Test
    public void getQuote_whenEmptyInput_thenUseEnglish() {
        client = new ForismaticClient();
        assertEquals(Language.ENGLISH, client.getLanguage());

        QuoteResponse response = client.getQuote();
        String quote = response.getQuoteText();
        String author = response.getQuoteAuthor();
        assertNotNull(quote);
        assertTrue(quote.length() > 0);
        assertNotNull(author);
        // author can be empty
    }

    @Test
    public void getQuote_whenInputEnglish_thenSuccess() {
        client = new ForismaticClient(Language.ENGLISH);
        QuoteResponse response = client.getQuote();
        String quote = response.getQuoteText();
        String author = response.getQuoteAuthor();
        assertNotNull(quote);
        assertTrue(quote.length() > 0);
        assertNotNull(author);
        // author can be empty
    }

    @Test
    public void getQuote_whenInputRussian_thenSuccess() {
        client = new ForismaticClient(Language.RUSSIAN);
        QuoteResponse response = client.getQuote();
        String quote = response.getQuoteText();
        String author = response.getQuoteAuthor();
        assertNotNull(quote);
        assertTrue(quote.length() > 0);
        assertNotNull(author);
        // author can be empty
    }

    // TODO: write more test cases such as failure/exception cases
}