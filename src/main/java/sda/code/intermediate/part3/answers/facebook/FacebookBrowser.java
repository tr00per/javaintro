package sda.code.intermediate.part3.answers.facebook;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.Insight;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static com.restfb.Parameter.with;
import static java.lang.System.out;

public class FacebookBrowser {

    // Graph API Explorer: https://developers.facebook.com/tools/explorer/145634995501895/
    public static void main(String[] args) throws Exception {
        FacebookClient facebookClient = authorizeApp();

        User user = facebookClient.fetchObject("me", User.class);
        Page page = facebookClient.fetchObject("koneserzyrozowychsloni", Page.class, with("fields", "link,category"));

        out.println("User name: " + user.getName());
        out.println("Page link: " + page.getLink());
        out.println("Page cateory: " + page.getCategory());

        FetchObjectsResults fetchObjectsResults = facebookClient
                .fetchObjects(Arrays.asList("me", "koneserzyrozowychsloni"), FetchObjectsResults.class);

        out.println("User name: " + fetchObjectsResults.me.getName());
        out.println("Page name: " + fetchObjectsResults.page.getName());

        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
        Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);

        out.println("Count of my friends: " + myFriends.getTotalCount());
        out.println("First item in my feed: " + myFeed.getData()

                .get(0));
        out.println("Size of my feed: " + myFeed.getData().size());

        Connection<Insight> insights = facebookClient.fetchConnection("cocacola/insights/page_fans_country/lifetime",
                Insight.class);

        for (Insight insight : insights.getData()) {
            out.println("Insight: " + insight.getName());
            for (JsonObject value : insight.getValues()) {
                out.println(insight.getName() + ": " + value.toString());
            }
        }
    }

    private static FacebookClient authorizeApp() throws IOException {
        final Properties secret = new Properties();
        secret.load(FacebookBrowser.class.getResourceAsStream("/fb.secret"));
        final String appToken = secret.getProperty("app.token");

        return new DefaultFacebookClient(appToken, Version.VERSION_12_0);
    }

}
