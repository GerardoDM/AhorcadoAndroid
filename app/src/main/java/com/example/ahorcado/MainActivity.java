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

    void mostrarLetraEnPalabra(char letter){
        int indexOfLetter = palabraParaSerAdivinada.indexOf(letter);

        while(indexOfLetter >= 0){
            palabraMostradaCharArray[indexOfLetter] = palabraParaSerAdivinada.charAt(indexOfLetter);
            indexOfLetter = palabraParaSerAdivinada.indexOf(letter,indexOfLetter + 2);

        }

        palabraMostradaString = String.valueOf(palabraMostradaCharArray);



    }

    void mostrarPalabraEnPantalla(){
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

        mostrarLetraEnPalabra(palabraMostradaCharArray[0]);

        mostrarLetraEnPalabra(palabraMostradaCharArray[palabraMostradaCharArray.length - 1]);

        palabraMostradaString = String.valueOf(palabraMostradaCharArray);

        mostrarPalabraEnPantalla();

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

        listaPalabras = new ArrayList<String>();
        txtPalabraParaSerAdivinada = (TextView) findViewById(R.id.txtPalabraParaAdivinar);
        editInput = (EditText) findViewById(R.id.editInput);
        txtLetraIntentada = (TextView) findViewById(R.id.txtLetrasIntentadas);
        txtIntentosRestantes = (TextView) findViewById(R.id.txtIntentosRestantes);

        iniciar();

        editInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // SI HAY ALGUNA LETRA EN EL INPUT
                if(charSequence.length() != 0){
                    checarLetraEnPalabra(charSequence.charAt(0));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    void checarLetraEnPalabra(char letter){
        //checar si la letra se encontró dentro de palabra
        if(palabraParaSerAdivinada.indexOf(letter) >= 0){

            // si la letra no se mostró
            if(palabraMostradaString.indexOf(letter) < 0){

                //reemplazar guiones bajos con la letra
                mostrarLetraEnPalabra(letter);

                //actualizar cambios en pantalla
                mostrarPalabraEnPantalla();

                //checar si ganó
                if(palabraMostradaString.contains("_")){
                    txtIntentosRestantes.setText(MSJ_GANADOR);
                }
            }
        }
        //si la letra no sé encontró...
        else{
            // quitar intentos y mostrar en pantalla
            disminuirIntentosRestantes();

            //checar si el juego se perdió
            if(intentosRestantes.isEmpty()){
                txtIntentosRestantes.setText(MSJ_PERDEDOR);
                txtPalabraParaSerAdivinada.setText(palabraParaSerAdivinada);
            }
        }

        //mostrar la letra que se probó
        if(letraIntentada.indexOf(letter) < 0){
            letraIntentada += letter + ", ";
            String messageToBeDisplayed = MSJ_CON_LETRAS_INTENTADAS + letraIntentada;
            txtLetraIntentada.setText(messageToBeDisplayed);
        }
    }

    void disminuirIntentosRestantes(){
        // si quedan todavia intentos
        if(!intentosRestantes.isEmpty()){

            intentosRestantes = intentosRestantes.substring(0, intentosRestantes.length() - 2);
            txtIntentosRestantes.setText(intentosRestantes);
        }
    }

    void reset(View v){
        //empezar otro juego
        iniciar();
    }




}
