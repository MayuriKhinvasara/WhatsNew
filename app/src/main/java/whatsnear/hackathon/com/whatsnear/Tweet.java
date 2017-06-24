package whatsnear.hackathon.com.whatsnear;

/**
 * Created by piyush on 24/6/17.
 */
public class Tweet {
    private String text, timestamp, username;

    public Tweet(String text, String timestamp, String username) {
        this.text = text;
        this.timestamp = timestamp;
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
