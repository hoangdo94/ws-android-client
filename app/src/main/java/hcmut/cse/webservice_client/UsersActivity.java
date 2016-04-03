package hcmut.cse.webservice_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import org.json.*;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Users List");
        setContentView(R.layout.activity_users);

        // Users data
        final JSONArray users = new JSONArray();

        // List view
        final ListView listview = (ListView) findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Loading ...");
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                try {
                    JSONObject user =  users.getJSONObject(position);
                    showDetails(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });



        APIRequest.get("users", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject r) {
                try {
                    list.remove(0);
                    JSONArray us = r.getJSONArray("data");
                    for (int i=0; i<us.length(); i++) {
                        JSONObject u = us.getJSONObject(i);
                        users.put(u);
                        String username = u.getString("username");
                        list.add(username);
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void showDetails(JSONObject user) throws JSONException {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("uid", user.getString("_id"));
        try {
            intent.putExtra("name", user.getString("name"));
        } catch (Exception e) {
            intent.putExtra("name", "Unknown");
        }
        intent.putExtra("username", user.getString("username"));
        intent.putExtra("email", user.getString("email"));
        startActivity(intent);
    }
}
