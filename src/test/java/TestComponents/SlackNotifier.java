package TestComponents;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SlackNotifier {
    private static final String WEBHOOK_URL = "https://hooks.slack.com/services/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"; // paste here

    public static void sendMessage(String message) {
        try {
            URL url = new URL(WEBHOOK_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            String payload = "{\"text\":\"" + message + "\"}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes("utf-8"));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("⚠️ Slack notification failed. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}