package com.example.openbankpaydemo.Service;

import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Setter
public class HttpMethod {
    String url;

    public HttpMethod(String url){
        this.url = url;
    }

    public ResponseEntity<String> postRequest(String data) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).build();
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).POST(HttpRequest.BodyPublishers.ofString(data))
                .setHeader("Content-Type", "application/json").build();

        String response = client.send(req, HttpResponse.BodyHandlers.ofString()).body();

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
