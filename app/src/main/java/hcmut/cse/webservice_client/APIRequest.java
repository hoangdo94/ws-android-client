package hcmut.cse.webservice_client;

import com.loopj.android.http.*;

/**
 * Created by hoangdo on 4/3/16.
 */
public class APIRequest {
    private static final String BASE_URL = "http://api.ws.hoangdo.info/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setBasicAuth(AuthCredentials.getUsername(), AuthCredentials.getPassword());
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setBasicAuth(AuthCredentials.getUsername(), AuthCredentials.getPassword());
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void authenticate(String username, String password, AsyncHttpResponseHandler responseHandler) {
        client.setBasicAuth(username, password);
        client.post(getAbsoluteUrl("auth"), null, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
