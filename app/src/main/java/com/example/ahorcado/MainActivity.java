package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView txtPalabraParaSerAdivinada;
    String PalabraParaSerAdivinada;
    String palabraMostradaString;
    char [] palabraMostradaCharArray;
    ArrayList<String> listaPalabras;
    EditText editInput;
    TextView txtLetraIntentada;
    String letraIntentada;
    final String MSJ_WITH_LETRAS_INTENTADAS = "Letras intentadas: ";
    TextView txtIntentosRestantes;
    String intentosRestantes;
    final String MSJ_GANADOR = "Ganaste";
    final String MSJ_PERDEDOR = "Perdiste";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }




}
