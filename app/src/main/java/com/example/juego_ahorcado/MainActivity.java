package com.example.juego_ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtLetra;
    Button btnVerificar, btnLimpiar;
    TextView tvAhoracado, tvResultado;
    ImageView cabeza, cuerpo, brazoizq, brazoder, piernaizq, piernader;
    TextView[] tvLetras; // Array para almacenar los TextView de las letras

    // Definir un array de palabras
    String[] palabras = {"ANDROID", "BUCLES", "WHILE", "FOR", "IOS", "BOTONES"};

    // Índice para rastrear la palabra actual
    int indicePalabraActual = 0;
    int intentos = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLetra = findViewById(R.id.edtLetra);

        btnVerificar = findViewById(R.id.btnVerificar);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        tvAhoracado = findViewById(R.id.tvAhoracado);
        tvResultado = findViewById(R.id.tvResultado);
        cabeza=findViewById(R.id.cabeza);
        cuerpo=findViewById(R.id.cuerpo);
        brazoizq=findViewById(R.id.brazoizq);
        brazoder=findViewById(R.id.brazodere);
        piernaizq=findViewById(R.id.piernaizq);
        piernader=findViewById(R.id.piernadere);

        // Inicializar el array de TextView para las letras
        tvLetras = new TextView[10];
        tvLetras[0] = findViewById(R.id.tvLetra1);
        tvLetras[1] = findViewById(R.id.tvLetra2);
        tvLetras[2] = findViewById(R.id.tvLetra3);
        tvLetras[3] = findViewById(R.id.tvLetra4);
        tvLetras[4] = findViewById(R.id.tvLetra5);
        tvLetras[5] = findViewById(R.id.tvLetra6);
        tvLetras[6] = findViewById(R.id.tvLetra7);
        tvLetras[7] = findViewById(R.id.tvLetra8);
        tvLetras[8] = findViewById(R.id.tvLetra9);
        tvLetras[9] = findViewById(R.id.tvLetra10);

        // Mostrar la primera palabra al iniciar la aplicación
        mostrarNuevaPalabra();

        //funcionamiento de los botones
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //primero verificar que no esté vacío el EditText para ingresar las letras
                if (!edtLetra.getText().toString().isEmpty()){
                    //Obtener la letra ingresada y convertirla a mayúsculas
                    String letraIngresada = edtLetra.getText().toString().trim().toUpperCase();

                    //Obtener la palabra actual
                    String palabraActual = palabras[indicePalabraActual];

                    //Verificar si la letra ingresada está en la palabra actual
                    if (palabraActual.contains(letraIngresada)) {
                        //Mostrar la letra ingresada en los TextView correspondientes
                        for (int i = 0; i < palabraActual.length(); i++) {
                            if (palabraActual.charAt(i) == letraIngresada.charAt(0)) {
                                tvLetras[i].setText(letraIngresada);
                            }
                        }
                    } else {
                        //Incrementar el contador de intentos
                        intentos++;

                        //Mostrar la parte correspondiente del ahorcado según los intentos
                        switch (intentos) {
                            case 1:
                                cabeza.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                cuerpo.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                brazoizq.setVisibility(View.VISIBLE);
                                break;
                            case 4:
                                brazoder.setVisibility(View.VISIBLE);
                                break;
                            case 5:
                                piernaizq.setVisibility(View.VISIBLE);
                                break;
                            case 6:
                                piernader.setVisibility(View.VISIBLE);
                                //Mostrar el mensaje de resultado
                                mostrarPerdisteDialog();
                                break;
                        }
                    }

                    // Verificar si se completaron todas las letras de la palabra y si aún no se ha alcanzado el límite de intentos
                    boolean palabraCompleta = true;
                    for (int i = 0; i < palabraActual.length(); i++) {
                        if (tvLetras[i].getText().toString().isEmpty()) {
                            palabraCompleta = false;
                            break;
                        }
                    }
                    if (palabraCompleta && intentos <= 6) {
                        //Mostrar el mensaje de resultado
                        tvResultado.setText("Ganaste!");
                    }

                    //Limpiar el EditText después de verificar
                    edtLetra.setText("");
                }
            }
        });

        //funcionamiento del botón Limpiar
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Incrementar el índice para cambiar a la siguiente palabra
                indicePalabraActual = (indicePalabraActual + 1) % palabras.length;

                //Mostrar la nueva palabra
                mostrarNuevaPalabra();

                //Limpiar el mensaje de resultado
                tvResultado.setText("");

                //Ocultar todas las partes del ahorcado
                cabeza.setVisibility(View.INVISIBLE);
                cuerpo.setVisibility(View.INVISIBLE);
                brazoizq.setVisibility(View.INVISIBLE);
                brazoder.setVisibility(View.INVISIBLE);
                piernaizq.setVisibility(View.INVISIBLE);
                piernader.setVisibility(View.INVISIBLE);

                //Restablecer los intentos
                intentos = 0;
            }
        });
    }

    // Método para mostrar un cuadro de diálogo de alerta cuando se pierde
    private void mostrarPerdisteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Perdiste");
        builder.setMessage("Lo siento, has agotado todos tus intentos. ¿Quieres intentarlo de nuevo?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Reiniciar el juego si el usuario elige "Sí"
                reiniciarJuego();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Cierra el cuadro de diálogo si el usuario elige "No"
            }
        });
        builder.show(); // Muestra el cuadro de diálogo
    }

    // Método para reiniciar el juego
    private void reiniciarJuego() {
        // Incrementar el índice para cambiar a la siguiente palabra
        indicePalabraActual = (indicePalabraActual + 1) % palabras.length;

        // Mostrar la nueva palabra
        mostrarNuevaPalabra();

        // Limpiar el EditText después de verificar
        edtLetra.setText("");

        // Ocultar todas las partes del ahorcado
        cabeza.setVisibility(View.INVISIBLE);
        cuerpo.setVisibility(View.INVISIBLE);
        brazoizq.setVisibility(View.INVISIBLE);
        brazoder.setVisibility(View.INVISIBLE);
        piernaizq.setVisibility(View.INVISIBLE);
        piernader.setVisibility(View.INVISIBLE);

        // Restablecer los intentos
        intentos = 0;
    }

    // Método para mostrar la nueva palabra
    private void mostrarNuevaPalabra() {
        String palabraActual = palabras[indicePalabraActual];
        // Mostrar guiones bajos en lugar de las letras
        for (int i = 0; i < palabraActual.length(); i++) {
            tvLetras[i].setText("_");
            tvLetras[i].setVisibility(View.VISIBLE);
        }
    }
}
