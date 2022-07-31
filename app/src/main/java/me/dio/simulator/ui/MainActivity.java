package me.dio.simulator.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;
import java.util.Random;


import me.dio.simulator.R;
import me.dio.simulator.data.MatchesApi;
import me.dio.simulator.databinding.ActivityMainBinding;

import me.dio.simulator.domain.Match;
import me.dio.simulator.ui.adapter.MatchesAdapter;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final MatchesAdapter matchesAdapter = new MatchesAdapter(Collections.emptyList());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setupMatchesList();
        setupMatchesRefresh();
        setupFloatingActionButton();
    }

    private void setupMatchesList() {
        //Listar partidas
        binding.RVMatches.setHasFixedSize(true);
        binding.RVMatches.setLayoutManager(new LinearLayoutManager(this));
        binding.RVMatches.setAdapter(matchesAdapter);
        findMatchesFromApi();
    }

    private void findMatchesFromApi() {
    }

    private Call<List<Match>> getMatches() {
        Call<List<Match>> matches = MatchesApi.getMatches();
        return matches;
    }

    private void setupMatchesRefresh() {
        //Atualizar partidas
        binding.SRMatches.setOnRefreshListener(this::findMatchesFromApi);
    }

    private void setupFloatingActionButton() {
        //Criar Evento de Click e simulação Partida e criar um for Implementar o algoritmode simulção da partida..
        binding.SimulateButton.setOnClickListener(view -> {
            view.animate().rotationBy(360).setDuration(500).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Random random = new Random();
                    for (int i = 0; i < matchesAdapter.getItemCount(); i++) {
                        Match match = matchesAdapter.getMatches().get(i);
                        match.getHomeTeam().setScore(random.nextInt(match.getHomeTeam().getStars() + 1));
                        match.getAwayTeam().setScore(random.nextInt(match.getAwayTeam().getStars() + 1));
                        matchesAdapter.notifyItemChanged(i);
                    }
                }
            });
        });
    }

    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com/RAFARZ76/matches-simulator-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MatchesApi matchesAPI = retrofit.create(MatchesApi.class);
    }

    private void showErrorMessage() {
        Snackbar.make(binding.SimulateButton, R.string.error_api, Snackbar.LENGTH_LONG).show();
    }
}
