package com.aditya.urlshortener.repository;
import com.aditya.urlshortener.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ShortUrlRepository extends JpaRepository<ShortUrl,Long>{
 Optional<ShortUrl> findByAlias(String alias);
 boolean existsByAlias(String alias);
}