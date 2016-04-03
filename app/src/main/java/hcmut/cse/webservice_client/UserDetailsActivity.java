package hcmut.cse.webservice_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        String username = intent.getStringExtra("username");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");

        setTitle(username + "'s details");

        TextView tvUid = (TextView) findViewById(R.id.tvUid);
        tvUid.setText(uid);
        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvUsername.setText(username);
        TextView tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(name);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvEmail.setText(email);
    }
}
