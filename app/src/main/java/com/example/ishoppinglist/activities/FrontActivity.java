package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;

/**
 * @author Cristina
 */
public class FrontActivity extends AppCompatActivity {

    /**
     * Este método es llamado cuando la actividad se crea por primera vez.
     * Configura el layout de la actividad, ajusta los márgenes de la interfaz de usuario para que se adapten
     * a los bordes del sistema y asigna un evento click al botón "Inicio".
     *
     * @param savedInstanceState Si la actividad se recrea después de haber sido destruida, este bundle contiene el estado más reciente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_front);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.front), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Botón para navegar a MainActivity
        Button btnInicio = findViewById(R.id.btnInicio);

        // Asigna el evento onClick para iniciar MainActivity cuando se presiona el botón
        btnInicio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            /* Para navegar a otra Activity necesitamos crear un Intent(contextActivity,
            new Intent(estadoDelActvityActual, ActivityALaQueQueremosNavegar.class)
            */
                Intent intentDetailActivity = new Intent(FrontActivity.this, MainActivity.class);
                // Método para abrir otra activity
                startActivity(intentDetailActivity);
            }
        });

    }

    /**
     * Este método es llamado cuando la actividad está a punto de ser visible para el usuario.
     * Aquí se registra en el log que el ciclo de vida ha entrado en el estado onStart.
     */
    @Override
    protected void onStart() {

        super.onStart();

        Log.i("Ciclo de vida", "Ha entrado en el método onStart");
    }

    /**
     * Este método es llamado cuando la actividad ha comenzado y está lista para interactuar con el usuario.
     * Aquí se registra en el log que el ciclo de vida ha entrado en el estado onResume.
     */
    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Ciclo de vida", "Ha entrado en el método onResume");
    }

    /**
     * Este método es llamado cuando el sistema está a punto de pausar la actividad.
     * Se utiliza para guardar el estado de la actividad o pausar cualquier operación que no debe continuar cuando la actividad está en segundo plano.
     * Aquí se registra en el log que el ciclo de vida ha entrado en el estado onPause.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo de vida", "Ha entrado en el método onPause");
    }

    /**
     * Este método es llamado cuando la actividad ya no está visible para el usuario.
     * Se utiliza para detener operaciones que no deberían continuar mientras la actividad está oculta.
     * Aquí se registra en el log que el ciclo de vida ha entrado en el estado onStop.
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo de vida", "Ha entrado en el método onStop");
    }

    /**
     * Este método es llamado antes de que la actividad sea destruida.
     * Aquí se libera cualquier recurso que pueda estar en uso y se registra en el log que la actividad ha entrado en el estado onDestroy.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo de vida", "Ha entrado en el método onDestroy");
    }

}
