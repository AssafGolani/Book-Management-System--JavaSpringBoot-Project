package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExternalBooksService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public CompletableFuture<List<BookDTO>> searchBooksByTitle(String title) {
        String url = "https://openlibrary.org/search.json?title=" + title;
        return CompletableFuture.supplyAsync(() -> {
            String response = restTemplate.getForObject(url, String.class);
            try {
                return convertResultToBookDTOs(response);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    private List<BookDTO> convertResultToBookDTOs(String result) throws JsonProcessingException {
        // Parse the JSON response and convert to List<BookDTO>
        // Assuming you have a JSON library to parse the response (e.g., Jackson)

        // Example using Jackson:
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(result);
        JsonNode docsNode = rootNode.path("docs");

        return StreamSupport.stream(docsNode.spliterator(), false)
                .map(docNode -> {
                    BookDTO bookDTO = new BookDTO();
                    bookDTO.setTitle(docNode.path("title").asText());
                    bookDTO.setIsbn(docNode.path("isbn").isArray() ? docNode.path("isbn").get(0).asText() : null);
                    bookDTO.setPublisher("");  // Set other fields as needed
                    bookDTO.setYear(docNode.path("first_publish_year").asInt());
                    return bookDTO;
                })
                .collect(Collectors.toList());
    }
}
