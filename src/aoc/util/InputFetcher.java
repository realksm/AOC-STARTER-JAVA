package aoc.util;

import aoc.config.AoCConfig;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputFetcher {
    public static String fetch(int day) throws Exception {
        Path path = Paths.get("inputs/day%02d.txt".formatted(day));
        if(Files.exists(path)) return Files.readString(path);

        String url = "https://adventofcode.com/%d/day/%d/input".formatted(AoCConfig.YEAR, day);

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Cookie", "session=" + AoCConfig.SESSION)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
                .build();

        HttpResponse<String> resp = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());

        Files.createDirectories(path.getParent());
        Files.writeString(path, resp.body());
        return resp.body();
    }
}
