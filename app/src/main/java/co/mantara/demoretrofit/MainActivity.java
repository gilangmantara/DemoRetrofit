package co.mantara.demoretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import co.mantara.demoretrofit.model.GitHub;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        final TextView tvName = (TextView) findViewById(R.id.tvName);
        final TextView tvLocation = (TextView) findViewById(R.id.tvLocation);
        final TextView tvUrl = (TextView) findViewById(R.id.tvUrl);

        GitHubService gitHubService = new GitHubAPI().getGitHubService();
        Call<GitHub> call = gitHubService.getGitHubUsers("gilangmantara");

        call.enqueue(new Callback<GitHub>() {
            @Override
            public void onResponse(Call<GitHub> call, Response<GitHub> response) {
                GitHub gitHub = response.body();
                tvUsername.setText(gitHub.getLogin());
                tvName.setText(gitHub.getName());
                tvLocation.setText(gitHub.getLocation());
                tvUrl.setText(gitHub.getHtml_url());
            }

            @Override
            public void onFailure(Call<GitHub> call, Throwable t) {
                String fail = "Failure";
                tvUsername.setText(fail);
                tvName.setText(fail);
                tvLocation.setText(fail);
                tvUrl.setText(fail);
            }
        });

    }
}
