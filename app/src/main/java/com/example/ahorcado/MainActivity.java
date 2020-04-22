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
    String palabraParaSerAdivinada;
    String palabraMostradaString;
    char [] palabraMostradaCharArray;
    ArrayList<String> listaPalabras;
    EditText editInput;
    TextView txtLetraIntentada;
    String letraIntentada;
    final String MSJ_CON_LETRAS_INTENTADAS = "Letras intentadas: ";
    TextView txtIntentosRestantes;
    String intentosRestantes;
    final String MSJ_GANADOR = "Ganaste";
    final String MSJ_PERDEDOR = "Perdiste";

    void revealLetterInWord(char letter){
        int indexOfLetter = palabraParaSerAdivinada.indexOf(letter);

        while(indexOfLetter >= 0){
            palabraMostradaCharArray[indexOfLetter] = palabraParaSerAdivinada.charAt(indexOfLetter);
            indexOfLetter = palabraParaSerAdivinada.indexOf(letter,indexOfLetter + 2);

        }

        palabraMostradaString = String.valueOf(palabraMostradaCharArray);



    }

    void displayWordOnScreen(){
        String formattedString = "";
        for(char character : palabraMostradaCharArray){
            formattedString += character + "";
        }

        txtPalabraParaSerAdivinada.setText(formattedString);
    }

    void iniciar() {
        Collections.shuffle(listaPalabras);
        palabraParaSerAdivinada = listaPalabras.get(0);
        listaPalabras.remove(0);

        //inicializar array de caracteres
        palabraMostradaCharArray = palabraParaSerAdivinada.toCharArray();

        //agregar guion bajo
        for (int i = 1; i < palabraMostradaCharArray.length - 1; i++) {
            palabraMostradaCharArray[i] = '_';
        }

        revealLetterInWord(palabraMostradaCharArray[0]);

        revealLetterInWord(palabraMostradaCharArray[palabraMostradaCharArray.length - 1]);

        palabraMostradaString = String.valueOf(palabraMostradaCharArray);

        displayWordOnScreen();

        editInput.setText("");

        letraIntentada = "";

        txtLetraIntentada.setText(MSJ_CON_LETRAS_INTENTADAS);

        intentosRestantes = " X X X X X";

        txtIntentosRestantes.setText(intentosRestantes);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }




}
