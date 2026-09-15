package com.aditya.urlshortener.dto;
import java.time.Instant;
public record StatsResponse(String alias,String originalUrl,long clicks,Instant createdAt,Instant expiresAt) {}