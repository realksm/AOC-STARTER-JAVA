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

    public static void fetchDescription(int day) throws Exception {
        Path path = Paths.get("inputs/day%02d_desc.md".formatted(day));

        if (Files.exists(path)) {
            Files.readString(path);
            return;
        }

        String url = "https://adventofcode.com/%d/day/%d".formatted(AoCConfig.YEAR, day);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Cookie", "session=" + AoCConfig.SESSION)
                .header("User-Agent", "Mozilla/5.0 Java-AoC-Fetcher")
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        String html = response.body();

        // Find all <article> tags (Part 1 and Part 2 are in separate articles)
        StringBuilder markdown = new StringBuilder();
        java.util.regex.Matcher matcher = java.util.regex.Pattern
                .compile("<article class=\"day-desc\">(.*?)</article>", java.util.regex.Pattern.DOTALL)
                .matcher(html);

        while (matcher.find()) {
            String content = matcher.group(1);
            markdown.append(htmlToMarkdown(content)).append("\n\n");
        }

        String result = markdown.toString().trim();
        Files.createDirectories(path.getParent());
        Files.writeString(path, result);
    }

    private static String htmlToMarkdown(String html) {
        return html
                .replaceAll("<h2.*?>(.*?)</h2>", "## $1")            // Headers
                .replaceAll("<code><em>(.*?)</em></code>", " `***$1***` ") // Bolded code
                .replaceAll("<code>(.*?)</code>", " `$1` ")          // Inline code
                .replaceAll("<em>(.*?)</em>", " **$1** ")            // Emphasis to Bold
                .replaceAll("<ul>", "\n")                            // Lists
                .replaceAll("<li>", "* ")
                .replaceAll("</li>", "\n")
                .replaceAll("</ul>", "\n")
                .replaceAll("<p>", "\n")                             // Paragraphs
                .replaceAll("</p>", "\n")
                .replaceAll("<pre><code>", "\n```\n")                // Code blocks
                .replaceAll("</code></pre>", "\n```\n")
                .replaceAll("&gt;", ">")                             // Entities
                .replaceAll("&lt;", "<")
                .replaceAll("&amp;", "&")
                .replaceAll("<[^>]*>", "");                          // Strip remaining tags
    }

    public static void openDescription(int day) throws Exception {
        Path path = Paths.get("inputs/day%02d_desc.md".formatted(day));

        if (!Files.exists(path)) {
            fetchDescription(day);
        }

        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop.getDesktop().open(path.toFile());
        } else {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "start", path.toString()).start();
            } else if (os.contains("mac")) {
                new ProcessBuilder("open", path.toString()).start();
            }
        }
    }
}
