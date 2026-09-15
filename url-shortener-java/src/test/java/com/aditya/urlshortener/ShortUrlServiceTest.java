package com.aditya.urlshortener;
import com.aditya.urlshortener.dto.CreateUrlRequest;
import com.aditya.urlshortener.repository.ShortUrlRepository;
import com.aditya.urlshortener.service.ShortUrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ShortUrlServiceTest{
 @Mock ShortUrlRepository repository; @InjectMocks ShortUrlService service;
 @Test void customAliasCreatesUrl(){
  Mockito.when(repository.existsByAlias("github")).thenReturn(false);
  Mockito.when(repository.save(Mockito.any())).thenAnswer(i->i.getArgument(0));
  var r=service.create(new CreateUrlRequest("https://github.com","github",null));
  assertEquals("github",r.alias()); assertEquals("https://github.com",r.originalUrl());
 }
 @Test void duplicateAliasFails(){
  Mockito.when(repository.existsByAlias("github")).thenReturn(true);
  assertThrows(RuntimeException.class,()->service.create(new CreateUrlRequest("https://github.com","github",null)));
 }
}