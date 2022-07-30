package me.dio.simulator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.dio.simulator.databinding.ActivityMainBinding;

public class mainActivity extends AppCompatActivity {

    //        binding Private para ActivityMainBinding
    private ActivityMainBinding binding;

    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater()); //Criando o elemnto binding
        setContentView(binding.getRoot());

    }
}



