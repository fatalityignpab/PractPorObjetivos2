package ignaciopabloro.com.practicaporobjetivos2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MenuPrincipal extends AppCompatActivity {

    TextView lblNombre;
    Button btnConsu, btnBorrar, btnSalir;
    Intent ir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        inicializar();
        accionesBoton();
    }

    private void inicializar(){
        lblNombre = (TextView) findViewById(R.id.lblNombreMenu);
        btnConsu = (Button) findViewById(R.id.btnConsulMenu);
        btnBorrar = (Button) findViewById(R.id.btnBorrarMenu);
        btnSalir = (Button) findViewById(R.id.btnSalirMenu);

        SharedPreferences registro = getSharedPreferences("RegistroUsuario", MODE_PRIVATE);
        lblNombre.setText(registro.getString("Nombre",""));
    }

    private void accionesBoton(){
        btnConsu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        irConsulta();
                    }
                }
        );

        btnBorrar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        irBorrar();
                    }
                }
        );

        btnSalir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        salirApp();
                    }
                }
        );
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void salirApp(){
        Intent sal = new Intent(Intent.ACTION_MAIN); // Mandará al inicio
        sal.addCategory(Intent.CATEGORY_HOME);
        sal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(sal);
        finish(); // Termina el proceso
        System.exit(0);
    }

    public void irConsulta(){
        ir = new Intent(this, Consultar.class);
        this.startActivity(ir);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    public void irBorrar(){
        //ir = new Intent(this, Principal.class);
        //**********************************************************
        //SharedPreferences registro = getSharedPreferences("RegistroUsuario", MODE_PRIVATE);
        //SharedPreferences.Editor editreg = registro.edit();
        //editreg.clear(); // Para borrar el preferences
        //editreg.commit();
        //**********************************************************
        //editreg.putString("Nombre", "");
        //editreg.putString("Clave", "");
        //editreg.putString("Edad", "");
        //editreg.putString("Genero", "");
        //editreg.putString("Pais", "");
        //editreg.putString("Educacion", "");
        //editreg.commit();
        //**********************************************************
        File archivo = new File("/data/data/ignaciopabloro.com.practicaporobjetivos2/shared_prefs/RegistroUsuario.xml");
        archivo.delete();
        onBackPressed();
        //this.startActivity(ir);
    }

}
