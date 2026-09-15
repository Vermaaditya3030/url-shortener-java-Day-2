package com.aditya.urlshortener.dto;
import jakarta.validation.constraints.*;
public record CreateUrlRequest(
 @NotBlank @Size(max=2048) @Pattern(regexp="https?://.+",message="URL must start with http:// or https://") String url,
 @Size(min=3,max=32) @Pattern(regexp="[A-Za-z0-9_-]+",message="Alias may contain letters, numbers, _ and -") String customAlias,
 Long expiryHours) {}