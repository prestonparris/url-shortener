package com.prestonparris.urlshortener;

import com.prestonparris.urlshortener.controller.UrlController;
import com.prestonparris.urlshortener.service.UrlService;
import com.prestonparris.urlshortener.view.CreateUrlRequest;
import com.prestonparris.urlshortener.view.CreateUrlResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

@SpringBootTest
class UrlShortenerApplicationTests {

    @Autowired
    UrlController urlController;

    // TODO: add more unit tests

    @Test
    void testShortUrlWorkflow() throws Exception {
        CreateUrlRequest createUrlRequest = new CreateUrlRequest();

        String longUrlOne = "https://en.wikipedia.org/wiki/Apple";
        createUrlRequest.setLongUrl(longUrlOne);

        ResponseEntity<CreateUrlResponse> responseEntity = urlController.createShortUrl(createUrlRequest);

        // assert that we can successfully create a new short url
        assertEquals(null, responseEntity.getStatusCode(), HttpStatus.CREATED);
        assertEquals(null, responseEntity.getBody().success, true);

        String shortUrlOne = responseEntity.getBody().url;
        assertEquals(null, shortUrlOne.length(), 6);

        RedirectView redirectView = urlController.redirectUrl(shortUrlOne);
        String urlRedirectOne = redirectView.getUrl();

        // assert that the short url will redirect to the correct long url
        assertEquals(null, longUrlOne, urlRedirectOne);

        ResponseEntity<CreateUrlResponse> responseEntityTwo = urlController.createShortUrl(createUrlRequest);

        // assert that the same long url produces the same short url
        assertEquals(null, responseEntity.getBody().url, responseEntityTwo.getBody().url);

        CreateUrlRequest createUrlRequestTwo = new CreateUrlRequest();

        String longUrlThree = "https://news.ycombinator.com";
        createUrlRequestTwo.setLongUrl(longUrlThree);

        ResponseEntity<CreateUrlResponse> responseEntityThree = urlController.createShortUrl(createUrlRequestTwo);

        String shortUrlThree = responseEntityThree.getBody().url;

        // assert that unique long urls produce unique short urls
        assertNotEquals(null, shortUrlOne, shortUrlThree);

        RedirectView redirectViewThree = urlController.redirectUrl(shortUrlThree);
        String urlRedirectThree = redirectViewThree.getUrl();

        // assert that the short url will redirect to the correct long url again
        assertEquals(null, longUrlThree, urlRedirectThree);

    }
}
