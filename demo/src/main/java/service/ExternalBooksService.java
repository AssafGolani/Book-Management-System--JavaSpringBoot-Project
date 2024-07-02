package service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class ExternalBooksService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public CompletableFuture<String> searchBooksByTitle(String title){
        String url = "http://www.openlibrary.org/search.json?title=" + title;
        String result = restTemplate.getForObject(url, String.class);
        return CompletableFuture.completedFuture(result);
    }
}
