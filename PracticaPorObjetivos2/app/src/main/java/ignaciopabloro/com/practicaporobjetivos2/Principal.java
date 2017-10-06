package ignaciopabloro.com.practicaporobjetivos2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {

    EditText txtNombre, txtClave;
    Spinner spinEdad, spinPais, spinEduc;
    Button btnReg, btnCan;
    RadioButton radioMasc, radioFem;
    String edadTexto, paisTexto, educTexto;


    @Override
    public void onStart() {
        super.onStart();
        vaciarEscrito();
    }

    protected void vaciarEscrito(){
        txtNombre.setText("");
        txtClave.setText("");
        spinEdad.setSelection(0);
        spinPais.setSelection(0);
        spinEduc.setSelection(0);
        edadTexto = "";
        paisTexto = "";
        educTexto = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        File archivo = new File("/data/data/ignaciopabloro.com.practicaporobjetivos2/shared_prefs/RegistroUsuario.xml");
        if(archivo.exists() == true){
            Toast.makeText(Principal.this, "El archivo existe", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(this, MenuPrincipal.class);
            this.startActivity(in);
        } else {
            crearArtilugios();
            accionesBotones();
            accionesRadio();
            rellenarSpinnerEdad();
            rellenarSpinnerPais();
            rellenarSpinnerEduc();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void cancelar(){
        File archivo = new File("/data/data/ignaciopabloro.com.practicaporobjetivos2/shared_prefs/RegistroUsuario.xml");
        if(archivo.exists() == true){
            Intent in = new Intent(this, MenuPrincipal.class);
            this.startActivity(in);
        } else {
            Toast.makeText(Principal.this, "Primero tienes que registrar :v", Toast.LENGTH_SHORT).show();
        }
    }

    private void registrar(){
        if(txtNombre.getText().toString().equals("") || txtClave.getText().toString().equals("")
                || edadTexto.equals("-") || paisTexto.equals("-") || educTexto.equals("-")){
            Toast.makeText(Principal.this, "Uno o varios campos estan vacios.", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences registro = getSharedPreferences("RegistroUsuario", MODE_PRIVATE);
            SharedPreferences.Editor editreg = registro.edit();
            editreg.putString("Nombre", txtNombre.getText().toString());
            editreg.putString("Clave", txtClave.getText().toString());
            editreg.putString("Edad", edadTexto);

            if(radioMasc.isChecked() == true){
                editreg.putString("Genero", "Masculino");
            } else
            if(radioFem.isChecked() == true){
                editreg.putString("Genero", "Femenino");
            }

            editreg.putString("Pais", paisTexto);
            editreg.putString("Educacion", educTexto);
            editreg.commit();
            Toast.makeText(Principal.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();

            Intent in = new Intent(this, MenuPrincipal.class);
            this.startActivity(in);
        }
    }

    private void crearArtilugios(){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtClave = (EditText) findViewById(R.id.txtClave);
        spinEdad = (Spinner) findViewById(R.id.spinEdad);
        spinPais = (Spinner) findViewById(R.id.spinPais);
        spinEduc = (Spinner) findViewById(R.id.spinEduc);
        btnReg = (Button) findViewById(R.id.btnReg);
        btnCan = (Button) findViewById(R.id.btnCan);
        radioFem = (RadioButton) findViewById(R.id.radioFem);
        radioMasc = (RadioButton) findViewById(R.id.radioMasc);
    }

    private void accionesBotones(){
        btnReg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registrar();
                    }
                }
        );

        btnCan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelar();
                    }
                }
        );
    }

    private void accionesRadio(){
        radioMasc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(radioFem.isChecked() == true){
                            radioFem.setChecked(false);
                        }
                    }
                }
        );

        radioFem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(radioMasc.isChecked() == true){
                            radioMasc.setChecked(false);
                        }
                    }
                }
        );
    }

    private void rellenarSpinnerEdad(){
        List<String> edad = new ArrayList<>();
        edad.add("-");
        edad.add("10-17");
        edad.add("18-24");
        edad.add("25-35");
        edad.add("36-45");
        edad.add("más de 45");

        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, edad);

        spinEdad.setAdapter(adap);

        spinEdad.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView t = (TextView)view;
                        edadTexto = t.getText().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    private void rellenarSpinnerPais(){
        List<String> pais = new ArrayList<>();
        pais.add("-");
        pais.add("Congo");
        pais.add("Irán");
        pais.add("Arabia Saudita");

        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pais);

        spinPais.setAdapter(adap);

        spinPais.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView t = (TextView)view;
                        paisTexto = t.getText().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    private void rellenarSpinnerEduc(){
        List<String> educ = new ArrayList<>();
        educ.add("-");
        educ.add("Básica");
        educ.add("Secundaria");
        educ.add("Preparatoria");
        educ.add("Universidad");

        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, educ);

        spinEduc.setAdapter(adap);

        spinEduc.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView t = (TextView)view;
                        educTexto = t.getText().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }
}
