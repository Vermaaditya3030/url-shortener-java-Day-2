package com.aditya.urlshortener.controller;
import com.aditya.urlshortener.dto.*;
import com.aditya.urlshortener.service.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/urls")
public class UrlController{
 private final ShortUrlService service;
 public UrlController(ShortUrlService service){this.service=service;}
 @PostMapping public ResponseEntity<CreateUrlResponse> create(@Valid @RequestBody CreateUrlRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
 @GetMapping("/{alias}/stats") public StatsResponse stats(@PathVariable String alias){return service.stats(alias);}
 @DeleteMapping("/{alias}") public ResponseEntity<Void> delete(@PathVariable String alias){service.delete(alias);return ResponseEntity.noContent().build();}
}