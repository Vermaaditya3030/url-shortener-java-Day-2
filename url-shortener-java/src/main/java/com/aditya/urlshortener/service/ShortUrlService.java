package com.aditya.urlshortener.service;
import com.aditya.urlshortener.dto.*;
import com.aditya.urlshortener.exception.ApiException;
import com.aditya.urlshortener.model.ShortUrl;
import com.aditya.urlshortener.repository.ShortUrlRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Random;
@Service
public class ShortUrlService{
 private static final String CHARS="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
 private final ShortUrlRepository repository; private final Random random=new Random();
 public ShortUrlService(ShortUrlRepository repository){this.repository=repository;}
 public CreateUrlResponse create(CreateUrlRequest r){
  String alias=r.customAlias();
  if(alias==null||alias.isBlank()) alias=generateAlias();
  if(repository.existsByAlias(alias)) throw new ApiException(HttpStatus.CONFLICT,"Alias already exists");
  Instant expires=null;
  if(r.expiryHours()!=null){
   if(r.expiryHours()<1||r.expiryHours()>8760) throw new ApiException(HttpStatus.BAD_REQUEST,"expiryHours must be between 1 and 8760");
   expires=Instant.now().plusSeconds(r.expiryHours()*3600);
  }
  ShortUrl s=repository.save(new ShortUrl(r.url(),alias,expires));
  return new CreateUrlResponse("http://localhost:8080/"+s.getAlias(),s.getOriginalUrl(),s.getAlias(),s.getExpiresAt());
 }
 public ShortUrl resolve(String alias){
  ShortUrl s=repository.findByAlias(alias).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Short URL not found"));
  if(s.getExpiresAt()!=null&&Instant.now().isAfter(s.getExpiresAt())) throw new ApiException(HttpStatus.GONE,"Short URL has expired");
  s.incrementClicks(); return repository.save(s);
 }
 public StatsResponse stats(String alias){
  ShortUrl s=repository.findByAlias(alias).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Short URL not found"));
  return new StatsResponse(s.getAlias(),s.getOriginalUrl(),s.getClicks(),s.getCreatedAt(),s.getExpiresAt());
 }
 public void delete(String alias){
  ShortUrl s=repository.findByAlias(alias).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Short URL not found"));
  repository.delete(s);
 }
 private String generateAlias(){
  for(int a=0;a<10;a++){StringBuilder b=new StringBuilder(7);for(int i=0;i<7;i++)b.append(CHARS.charAt(random.nextInt(CHARS.length())));
   String x=b.toString();if(!repository.existsByAlias(x))return x;}
  throw new ApiException(HttpStatus.SERVICE_UNAVAILABLE,"Could not generate unique alias");
 }
}