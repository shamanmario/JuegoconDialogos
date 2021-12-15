package com.example.juegocondialogos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random r;
    Button boton;
    DatePicker calendarioSpinner;
    DatePickerDialog calendarioDialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = new Random();
        boton = findViewById(R.id.button);
        calendarioSpinner = findViewById(R.id.calendarioSpinner);

        Calendar calendario = Calendar.getInstance();

        calendarioSpinner.updateDate(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));

        inicializarCalendario();

        /*
        * para crear un entero aleatorio dentro de un rango se utiliza "nextInt"
        * r.nextInt(MAX - MIN) + MIN
        * */

        int valorRandom = r.nextInt(10); //en este caso, un entero aleatorio entre el 0 y el 9
        //int valorRandom = r.nextInt(10) + 1; //en este caso, un entero aleatorio entre el 1 y el 10
        //int valorRandom = r.nextInt(50) + 20; //en este caso, un entero aleatorio entre el 20 y el 49

        boton.setText("Valor Random: "+valorRandom);

    }

    public void MostrarMensaje(View view) {
        new AlertDialog.Builder(this)

                .setTitle("Fecha seleccionada")
                .setMessage(calendarioSpinner.getDayOfMonth()+" - "+NombreMes(calendarioSpinner.getMonth()+1)+" - "+calendarioSpinner.getYear())
                .setCancelable(true)

                .setPositiveButton("Cerrar App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Aplicación cerrada", Toast.LENGTH_LONG).show();
                        finish();
                    }
                })

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })

                .create()
                .show();


    }

    private String NombreMes(int mes){
        switch(mes){
            case 1:
                return "ENE";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "ABR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AGO";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DIC";
            default:
                return "ENE";
        }
    }

    public void MostrarCalendario(View view){
        calendarioDialogo.show();
    }

    private void inicializarCalendario(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
                mes = mes +1;
                Toast.makeText(MainActivity.this, "La fecha seleccionada es: "+dia+" "+NombreMes(mes)+" "+año, Toast.LENGTH_LONG).show();
            }
        };

        Calendar c = Calendar.getInstance();

        calendarioDialogo = new DatePickerDialog(this, dateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }
}