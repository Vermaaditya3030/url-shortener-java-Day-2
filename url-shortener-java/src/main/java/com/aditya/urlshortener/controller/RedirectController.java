package com.aditya.urlshortener.controller;
import com.aditya.urlshortener.model.ShortUrl;
import com.aditya.urlshortener.service.ShortUrlService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
public class RedirectController{
 private final ShortUrlService service;
 public RedirectController(ShortUrlService service){this.service=service;}
 @GetMapping("/{alias:[A-Za-z0-9_-]+}")
 public ResponseEntity<Void> redirect(@PathVariable String alias){
  ShortUrl s=service.resolve(alias);
  return ResponseEntity.status(HttpStatus.FOUND).location(java.net.URI.create(s.getOriginalUrl())).build();
 }
}