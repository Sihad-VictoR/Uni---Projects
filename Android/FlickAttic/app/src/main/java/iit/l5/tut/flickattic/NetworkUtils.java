package iit.l5.tut.flickattic;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

        private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
        private static  String searchTitle_URL ;
        static String getMovieInfo(String queryString,boolean value) {

            // Set up variables for the try block that need to be closed in the
            // finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieJSONString = null;

            //API keys
            //k_t4fj5dv8
            //k_evgn3gqn
            //k_e8zl7wuy
            try {
                if(value){
                    searchTitle_URL ="https://imdb-api.com/en/API/SearchTitle/k_t4fj5dv8/"+queryString;
                }else {
                    searchTitle_URL ="https://imdb-api.com/en/API/Ratings/k_t4fj5dv8/"+queryString;
                }
                Uri builtURI = Uri.parse(searchTitle_URL).buildUpon()
                        .build();

                // Convert the URI to a URL.
                URL requestURL = new URL(builtURI.toString());

                // Open the network connection.
                urlConnection = (HttpURLConnection) requestURL.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Get the InputStream.
                InputStream inputStream = urlConnection.getInputStream();

                // Create a buffered reader from that input stream.
                reader = new BufferedReader(new InputStreamReader(inputStream));

                // Use a StringBuilder to hold the incoming response.
                StringBuilder builder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    // Add the current line to the string.
                    builder.append(line);

                    // Since this is JSON, adding a newline isn't necessary (it won't
                    // affect parsing) but it does make debugging a *lot* easier
                    // if you print out the completed buffer for debugging.
                    builder.append("\n");
                }

                if (builder.length() == 0) {
                    // Stream was empty.  Exit without parsing.
                    return null;
                }

                movieJSONString = builder.toString();
                Log.d(LOG_TAG, movieJSONString);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Close the connection and the buffered reader.
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Write the final JSON response to the log
            return movieJSONString;
        }
}