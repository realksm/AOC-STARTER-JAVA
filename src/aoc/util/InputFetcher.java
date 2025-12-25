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

    public static String fetchDescription(int day) throws Exception {
        Path path = Paths.get("inputs/day%02d_desc.txt".formatted(day));

        if (Files.exists(path)) {
            return Files.readString(path);
        }

        String url = "https://adventofcode.com/%d/day/%d".formatted(AoCConfig.YEAR, day);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Cookie", "session=" + AoCConfig.SESSION)
                .header("User-Agent", "AoC Java Client")
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Extract the <article> content which contains the puzzle text
        String html = response.body();
        String startTag = "<article class=\"day-desc\">";
        String endTag = "</article>";

        int start = html.indexOf(startTag);
        int end = html.lastIndexOf(endTag);

        if (start == -1) throw new RuntimeException("Could not find puzzle description.");

        String rawDesc = html.substring(start, end + endTag.length());

        // Simple cleaning: Remove HTML tags and fix entities
        String cleanDesc = rawDesc
                .replaceAll("--- Day (\\d+): (.*) ---", "--- Day $1: $2 ---") // No color codes
                .replaceAll("<[^>]*>", "")
                .replaceAll("&gt;", ">")
                .replaceAll("&lt;", "<")
                .trim();

        Files.writeString(path, cleanDesc);
        return cleanDesc;
    }

    public static void openDescription(int day) throws Exception {
        Path path = Paths.get("inputs/day%02d_desc.txt".formatted(day));

        // 1. Fetch if it doesn't exist (using your existing logic)
        if (!Files.exists(path)) {
            fetchDescription(day);
        }

        // 2. Open the file using the default system editor (Notepad/IntelliJ)
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // "start" is the PowerShell/CMD command to open a file with its default app
            new ProcessBuilder("cmd", "/c", "start", path.toString()).start();
        }
    }
}
