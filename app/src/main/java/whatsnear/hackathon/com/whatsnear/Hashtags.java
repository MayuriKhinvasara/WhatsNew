package whatsnear.hackathon.com.whatsnear;

import java.util.List;

/**
 * Created by piyush on 24/6/17.
 */
public class Hashtags {
    private String HTname;
    private List<Tweet> tweets;

    public Hashtags(String HTname, List<Tweet> tweets) {
        this.HTname = HTname;
        this.tweets = tweets;
    }

    public String getHTname() {
        return HTname;
    }

    public void setHTname(String HTname) {
        this.HTname = HTname;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
