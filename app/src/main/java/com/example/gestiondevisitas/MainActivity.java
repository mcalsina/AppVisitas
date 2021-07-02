package com.example.gestiondevisitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button registrar, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        registrar = (Button) findViewById(R.id.btnregistrar);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this,"Por favor ingrese todos los campos",Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Registrado Correctamente",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),DetalleClienteFragment.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(MainActivity.this,"Registro Fallido",Toast.LENGTH_SHORT).show();

                            }

                        }
                        else {
                            Toast.makeText(MainActivity.this,"Usuario ya existe! por favor inicie sesión",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}