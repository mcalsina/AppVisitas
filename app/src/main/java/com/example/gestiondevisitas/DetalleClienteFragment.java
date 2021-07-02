package com.example.gestiondevisitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import android.view.Gravity;

public class DetalleClienteFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detalle_cliente);
    }

    public void grabar(View view) {

        EditText numeroDocumento = (EditText) findViewById(R.id.numeroDocumento);
        EditText nombres = (EditText) findViewById(R.id.nombres);
        EditText apellidoPaterno = (EditText) findViewById(R.id.apellidoPaterno);
        EditText apellidoMaterno = (EditText) findViewById(R.id.apellidoMaterno);
        EditText telefono = (EditText) findViewById(R.id.telefono);
        EditText comentarios = (EditText) findViewById(R.id.comentarios);


        ClienteDao dao = new ClienteDao(getBaseContext());
        try {
            //dao.eliminarTodos();
            dao.insertar(numeroDocumento.getText().toString(), nombres.getText().toString(),apellidoPaterno.getText().toString(),apellidoMaterno.getText().toString(),telefono.getText().toString(),comentarios.getText().toString());

            Toast toast= Toast.makeText(getApplicationContext(), "Se insertó correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

            numeroDocumento.setText("");
            nombres.setText("");
            apellidoPaterno.setText("");
            apellidoMaterno.setText("");
            telefono.setText("");
            comentarios.setText("");

        } catch (DAOException e) {
            Log.i("DetalleClienteFragment", "====> " + e.getMessage());
        }
    }
}