package com.example.parcial_app_moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button calcular;
    EditText txtempleado, txtsalario, txthorasExtra, txtvalorHoras, txtpension, txtsalud, txtsalarioTotal;
    double salario, horasExtra, valorHoras, salarioBruto, salarioTotal, pension, salud;
    String empleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcular = findViewById(R.id.btnCalcular);
        txtempleado = findViewById(R.id.txtEmpleado);
        txtsalario = findViewById(R.id.txtSalario);
        txthorasExtra = findViewById(R.id.txtHorasExtra);
        txtvalorHoras = findViewById(R.id.txtValorHoras);
        txtpension = findViewById(R.id.txtPension);
        txtpension.setEnabled(false);
        txtsalud = findViewById(R.id.txtSalud);
        txtsalud.setEnabled(false);
        txtsalarioTotal = findViewById(R.id.txtSalarioTotal);
        txtsalarioTotal.setEnabled(false);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCampo()){
                    obtenerCampos();
                    salarioBruto = salario + (horasExtra*valorHoras);
                    pension = salarioBruto * 0.04;
                    salud = salarioBruto * 0.04;
                    txtpension.setText(String.valueOf(pension));
                    txtsalud.setText(String.valueOf(salud));
                    salarioTotal = salarioBruto - pension - salud;
                    txtsalarioTotal.setText(String.valueOf(salarioTotal));
                }
            }
        });
    }

    boolean validarCampo(){
        if(TextUtils.isEmpty(txtempleado.getText().toString()) || TextUtils.isEmpty(txtsalario.getText().toString()) || TextUtils.isEmpty(txthorasExtra.getText().toString()) || TextUtils.isEmpty(txtvalorHoras.getText().toString())){
            Toast.makeText(getApplicationContext(), "Rellena los campos", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    void obtenerCampos(){
        empleado = txtempleado.getText().toString();
        salario = Float.parseFloat(txtsalario.getText().toString());
        horasExtra = Float.parseFloat(txthorasExtra.getText().toString());
        valorHoras = Float.parseFloat(txtvalorHoras.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_salario:
                txtsalario.setText("877.802");
                break;
            case R.id.menu_limpiar:
                txtempleado.setText("");
                txtsalario.setText("");
                txthorasExtra.setText("");
                txtvalorHoras.setText("");
                txtpension.setText("");
                txtsalud.setText("");
                txtsalarioTotal.setText("");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}