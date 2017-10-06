package ignaciopabloro.com.practicaporobjetivos2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Consultar extends AppCompatActivity {

    TextView lblNombre, lblClave, lblEdad, lblGenero, lblPais, lblEduc;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        inicializar();
        imprimirDatos();
        accionBoton();
    }

    private void inicializar(){
        lblNombre = (TextView) findViewById(R.id.lblNombreConsul);
        lblClave = (TextView) findViewById(R.id.lblClaveConsul);
        lblEdad = (TextView) findViewById(R.id.lblEdadConsul);
        lblGenero = (TextView) findViewById(R.id.lblGeneroConsul);
        lblPais = (TextView) findViewById(R.id.lblPaisConsul);
        lblEduc = (TextView) findViewById(R.id.lblEduConsul);
        btnRegresar = (Button) findViewById(R.id.btnRegreso);
    }

    private void imprimirDatos(){
        SharedPreferences registro = getSharedPreferences("RegistroUsuario", MODE_PRIVATE);
        lblNombre.setText(registro.getString("Nombre",""));
        lblClave.setText(registro.getString("Clave",""));
        lblEdad.setText(registro.getString("Edad",""));
        lblGenero.setText(registro.getString("Genero",""));
        lblPais.setText(registro.getString("Pais",""));
        lblEduc.setText(registro.getString("Educacion",""));
    }

    private void accionBoton(){
        btnRegresar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        irMenu();
                    }
                }
        );
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    private void irMenu(){
        onBackPressed();

        /*
        Intent inte = new Intent(this, MenuPrincipal.class);
        this.startActivity(inte);
        */
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
