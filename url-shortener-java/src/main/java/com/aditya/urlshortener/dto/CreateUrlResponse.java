package com.aditya.urlshortener.dto;
import java.time.Instant;
public record CreateUrlResponse(String shortUrl,String originalUrl,String alias,Instant expiresAt) {}