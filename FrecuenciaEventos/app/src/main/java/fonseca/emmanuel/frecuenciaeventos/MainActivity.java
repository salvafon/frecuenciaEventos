package fonseca.emmanuel.frecuenciaeventos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Identificadores etiquetas
    private TextView onCreate;
    private TextView onPause;
    private TextView onResume;
    private TextView onStop;
    private TextView onActionDown;
    private TextView onActionUp;
    private TextView onActionMove;
    private TextView onTap;
    private TextView onBackedPressed;


    // Contadores eventos
    private int contadorCreate = 0;
    private int contadorPause = 0;
    private int contadorResume = 0;
    private int contadorStop = 0;
    private int contadorActionDown = 0;
    private int contadorActionUp = 0;
    private int contadorActionMove = 0;
    private int contadorTap = 0;
    private int contadorBackedPressed = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Ya que el tiempo de carga de la aplicación es muy corto por que no hay elementos que la demoren, agregamos manualmente un retraso de 2seg. en el SplashScreen.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Aplicamos el tema del SplashScreen para que cargue nuestro diseño
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Creamos una instancia del ActionBar para poder modificar el estilo por el personalizado
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient));
        }
        // Inicializamos elementos para los contadores
        inicializaElementos();
    }

    private void inicializaElementos() {
        onCreate = findViewById(R.id.contadorCreate);
        onPause = findViewById(R.id.contadorPause);
        onResume = findViewById(R.id.contadorResume);
        onStop = findViewById(R.id.contadorStop);
        onActionDown = findViewById(R.id.contadorActionDown);
        onActionUp = findViewById(R.id.contadorActionUp);
        onActionMove = findViewById(R.id.contadorActionMove);
        onTap = findViewById(R.id.contadorTap);
        onBackedPressed = findViewById(R.id.contadorBackPressed);
        contadorCreate++;
        onCreate.setText(Integer.toString(contadorCreate));
    }

    // Override de los eventos que necesitamos
    @Override
    protected void onPause() {
        contadorPause++; // Añadimos + 1 al contador de Pause
        onPause.setText(Integer.toString(contadorPause)); // Refrescamos el contador mostrado en onPause
        super.onPause();
    }

    @Override
    protected void onResume() {
        contadorResume++; // Añadimos + 1 al contador de Resume
        onResume.setText(Integer.toString(contadorResume)); // Refrescamos el contador mostrado en onResume
        super.onResume();
    }

    @Override
    protected void onStop() {
        contadorStop++; // Añadimos + 1 al contador de Stop
        onStop.setText(Integer.toString(contadorStop)); // Refrescamos el contador mostrado en onStop
        super.onStop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                contadorActionDown++; // Añadimos + 1 al contador de ACTION_DOWN
                onActionDown.setText(Integer.toString(contadorActionDown)); // Refrescamos el contador mostrado en ACTION_DOWN
                return true;
            case (MotionEvent.ACTION_UP) :
                contadorActionUp++; // Añadimos + 1 al contador de ACTION_UP
                onActionUp.setText(Integer.toString(contadorActionUp)); // Refrescamos el contador mostrado en ACTION_UP
                return true;
            case (MotionEvent.ACTION_MOVE) :
                contadorActionMove++; // Añadimos + 1 al contador de ACTION_MOVE
                onActionMove.setText(Integer.toString(contadorActionMove)); // Refrescamos el contador mostrado en ACTION_MOVE
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

    // Override del método usado para mostrar alerta al presionar el  botón de regresar
    @Override
    public void onBackPressed() {
        contadorBackedPressed++;
        onBackedPressed.setText(Integer.toString(contadorBackedPressed));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea salir realmente?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Aplicación cerrada",Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Método utilizado para llevar conteo en el botón "Tap Button"
    public void onClick(View view)
    {
        contadorTap++; // Añadimos + 1 al contador de Tap Button
        onTap.setText(Integer.toString(contadorTap)); // Refrescamos el contador mostrado en Tap Button
    }


}
