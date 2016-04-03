package hcmut.cse.webservice_client;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        final EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        final TextView tvNotify = (TextView) findViewById(R.id.tvNotify);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnView = (Button) findViewById(R.id.btnView);
        btnView.setVisibility(View.GONE);

        if (btnLogin != null) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click
                    final String username = txtUsername.getText().toString();
                    final String password = txtPassword.getText().toString();
                    if (username == null || password == null) return;
                    APIRequest.authenticate(username, password, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject r) {
                            try {
                                int status = r.getInt("status");
                                if (status == 1) {
                                    String m = "Logged in as " + username;
                                    displayToast("Logged In!");
                                    txtUsername.setText("");
                                    txtPassword.setText("");
                                    AuthCredentials.setCredentials(username, password);
                                    JSONObject user = r.getJSONObject("data");
                                    Boolean admin = user.getBoolean("admin");
                                    if (admin) {
                                        m += " (admin)";
                                        btnView.setVisibility(View.VISIBLE);
                                        viewUsersList();
                                    } else {
                                        m += " (normal user, cannot view Users List)";
                                        btnView.setVisibility(View.GONE);
                                    }
                                    tvNotify.setText(m);
                                } else {
                                    displayToast("Failed to Login");
                                    btnView.setVisibility(View.GONE);
                                    tvNotify.setText("");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                btnView.setVisibility(View.GONE);
                            }
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject r) {
                            displayToast("Failed to Login");
                            btnView.setVisibility(View.GONE);
                            tvNotify.setText("");
                        }
                    });
                }
            });
        }

        if (btnView != null) {
            btnView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    viewUsersList();
                }
            });
        }
    }

    public void displayToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public void viewUsersList() {
        Intent intent = new Intent(this, UsersActivity.class);
        startActivity(intent);
    }
}
