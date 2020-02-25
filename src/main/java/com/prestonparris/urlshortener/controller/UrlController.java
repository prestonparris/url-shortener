package com.prestonparris.urlshortener.controller;

import com.prestonparris.urlshortener.service.UrlService;
import com.prestonparris.urlshortener.view.CreateUrlRequest;
import com.prestonparris.urlshortener.view.CreateUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {

    @Autowired
    UrlService urlService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<CreateUrlResponse> createShortUrl(@RequestBody CreateUrlRequest request) {
        // TODO: url validation

        String shortUrl = urlService.createShortUrl(request.getLongUrl());

        return new ResponseEntity<>(new CreateUrlResponse(true, shortUrl), HttpStatus.CREATED);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirectUrl(@PathVariable(value = "shortUrl") String shortUrl) {
        if (shortUrl == null || shortUrl.trim().isEmpty()) {
            throw new RuntimeException("Expected short url.");
        }

        if (shortUrl.trim().length() != 6) {
            throw new RuntimeException("Short url not valid.");
        }

        String longUrl = urlService.getLongUrl(shortUrl);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(longUrl);

        return redirectView;
    }
}

