package com.aditya.urlshortener.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="short_urls", indexes=@Index(name="idx_alias",columnList="alias",unique=true))
public class ShortUrl {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=2048) private String originalUrl;
 @Column(nullable=false,unique=true,length=32) private String alias;
 @Column(nullable=false) private Instant createdAt;
 private Instant expiresAt;
 @Column(nullable=false) private long clicks;
 protected ShortUrl(){}
 public ShortUrl(String originalUrl,String alias,Instant expiresAt){this.originalUrl=originalUrl;this.alias=alias;this.expiresAt=expiresAt;this.createdAt=Instant.now();}
 public Long getId(){return id;} public String getOriginalUrl(){return originalUrl;} public String getAlias(){return alias;}
 public Instant getCreatedAt(){return createdAt;} public Instant getExpiresAt(){return expiresAt;} public long getClicks(){return clicks;}
 public void incrementClicks(){clicks++;}
}