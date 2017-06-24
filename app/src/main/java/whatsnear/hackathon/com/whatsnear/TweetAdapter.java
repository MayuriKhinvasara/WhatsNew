package whatsnear.hackathon.com.whatsnear;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by piyush on 24/6/17.
 */
public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.MyViewHolder> {

    private List<Tweet> tweetList;

    public TweetAdapter(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tweet tweet = tweetList.get(position);
        holder.text.setText(tweet.getText());
        holder.timestamp.setText(tweet.getTimestamp());
        holder.username.setText(tweet.getUsername());
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text, timestamp, username;

        public MyViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.tweet_text);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
            username = (TextView) view.findViewById(R.id.username);
        }
    }
}
