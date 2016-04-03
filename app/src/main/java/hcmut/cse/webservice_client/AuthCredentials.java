package hcmut.cse.webservice_client;

/**
 * Created by hoangdo on 4/3/16.
 */
public class AuthCredentials {
    private static String username = "default";
    private static String password  = "default";

    public static void setCredentials(String u, String p) {
        username = u;
        password = p;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
