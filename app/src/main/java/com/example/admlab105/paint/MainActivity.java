package com.example.admlab105.paint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.AlertDialog;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton negro;
    ImageButton turquesa;
    ImageButton azul;
    ImageButton rojo;
    ImageButton verde;
    ImageButton amarillo;
    ImageButton blanco;
    ImageButton purpura;
    ImageButton gris;
    ImageButton fuscia;
    ImageButton trazo;
    ImageButton agregar;
    ImageButton borrar;
    ImageButton guardar;

    private Lienzo lienzo;

    float pPeque;
    float pMediano;
    float pGrande;
    float pDefecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        negro = (ImageButton) findViewById(R.id.colorNegro);
        negro.setOnClickListener(this);
        turquesa = (ImageButton) findViewById(R.id.colorTurquesa);
        turquesa.setOnClickListener(this);
        azul = (ImageButton) findViewById(R.id.colorAzul);
        azul.setOnClickListener(this);
        rojo = (ImageButton) findViewById(R.id.colorRojo);
        rojo.setOnClickListener(this);
        verde = (ImageButton) findViewById(R.id.colorVerde);
        verde.setOnClickListener(this);
        amarillo = (ImageButton) findViewById(R.id.colorAmarillo);
        amarillo.setOnClickListener(this);
        blanco = (ImageButton) findViewById(R.id.colorBlanco);
        blanco.setOnClickListener(this);
        purpura = (ImageButton) findViewById(R.id.colorPurpura);
        purpura.setOnClickListener(this);
        gris = (ImageButton) findViewById(R.id.colorGris);
        gris.setOnClickListener(this);
        fuscia = (ImageButton) findViewById(R.id.colorFuscia);
        fuscia.setOnClickListener(this);

        borrar = (ImageButton) findViewById(R.id.borrar);
        borrar.setOnClickListener(this);
        agregar = (ImageButton) findViewById(R.id.agregar);
        agregar.setOnClickListener(this);
        guardar = (ImageButton) findViewById(R.id.guardar);
        guardar.setOnClickListener(this);
        trazo = (ImageButton) findViewById(R.id.trazo);
        trazo.setOnClickListener(this);

        lienzo = (Lienzo) findViewById(R.id.lienzo);

        pPeque = 10;
        pMediano = 20;
        pGrande = 30;
        pDefecto = pMediano;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        String color = null;
        switch (view.getId()) {
            case R.id.colorNegro:
                color = view.getTag().toString();
                lienzo.setColor(color);
                break;

            case R.id.colorTurquesa:
                color = view.getTag().toString();
                lienzo.setColor(color);
                break;

            case R.id.colorAzul:
                color = view.getTag().toString();
                lienzo.setColor(color);
                break;

            case R.id.colorRojo:
                color = view.getTag().toString();
                lienzo.setColor(color);
                break;

            case R.id.colorVerde:
                color = view.getTag().toString();
                lienzo.setColor(color);
                break;

            case R.id.colorAmarillo:
                color = view.getTag().toString();
                lienzo.setColor(color);
                break;

            case R.id.colorBlanco:
                color = view.getTag().toString();
                lienzo.setColor(color);
                break;

            case R.id.colorPurpura:
                color = view.getTag().toString();
                lienzo.setColor(color);
                break;

            case R.id.colorGris:
                color = view.getTag().toString();
                lienzo.setColor(color);
                break;

            case R.id.colorFuscia:
                color = view.getTag().toString();
                lienzo.setColor(color);
                break;

            case R.id.agregar:
                AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
                newDialog.setTitle("Nuevo Dibujo");
                newDialog.setMessage("¿Comenzar nuevo dibujo? Perderá el dibujo actual.");
                newDialog.setPositiveButton("Aceptar", (dialog, which) -> {
                    lienzo.nuevoDibujo();
                    dialog.dismiss();
                });
                newDialog.setNegativeButton("Cancelar", (dialog, which) -> {
                    dialog.cancel();
                });
                newDialog.show();
                break;

            case R.id.borrar:
                final Dialog borrarPunto = new Dialog(this);
                borrarPunto.setTitle("Tamaño de borrado: ");
                borrarPunto.setContentView(R.layout.tam_punto);

                //listen for clicks on size buttons
                TextView smallBtnBorrar = (TextView) borrarPunto.findViewById(R.id.tPeque);
                smallBtnBorrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(true);
                        lienzo.setTamPunto(pPeque);
                        borrarPunto.dismiss();
                    }
                });

                TextView mediumBtnBorrar = (TextView) borrarPunto.findViewById(R.id.tMed);
                mediumBtnBorrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(true);
                        lienzo.setTamPunto(pMediano);
                        borrarPunto.dismiss();
                    }
                });

                TextView largeBtnBorrar = (TextView) borrarPunto.findViewById(R.id.tGrande);
                largeBtnBorrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(true);
                        lienzo.setTamPunto(pGrande);
                        borrarPunto.dismiss();
                    }
                });

                //show and wait for user interaction
                borrarPunto.show();
                break;

            case R.id.trazo:
                final Dialog tamPunto = new Dialog(this);
                tamPunto.setTitle("Tamaño del lápiz: ");
                tamPunto.setContentView(R.layout.tam_punto);

                //listen for clicks on size buttons
                TextView smallBtn = (TextView) tamPunto.findViewById(R.id.tPeque);
                smallBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(false);
                        lienzo.setTamPunto(pPeque);
                        tamPunto.dismiss();
                    }
                });

                TextView mediumBtn = (TextView) tamPunto.findViewById(R.id.tMed);
                mediumBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(false);
                        lienzo.setTamPunto(pMediano);
                        tamPunto.dismiss();
                    }
                });

                TextView largeBtn = (TextView) tamPunto.findViewById(R.id.tGrande);
                largeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(false);
                        lienzo.setTamPunto(pGrande);
                        tamPunto.dismiss();
                    }
                });

                //show and wait for user interaction
                tamPunto.show();
                break;

            case R.id.guardar:
                AlertDialog.Builder salvarDibujo = new AlertDialog.Builder(this);
                salvarDibujo.setTitle("Guardar dibujo");
                salvarDibujo.setMessage("¿Guardar dibujo en la galería?");
                salvarDibujo.setPositiveButton("Aceptar", (dialog, which) -> {
                    //Guardar dibujo
                    lienzo.setDrawingCacheEnabled(true);
                    //attempt to save
                    String imgSaved = MediaStore.Images.Media.insertImage(getContentResolver(), lienzo.getDrawingCache(),
                            UUID.randomUUID().toString()+".png", "drawing");
                    //Mensaje de todo correcto
                    if (imgSaved != null) {
                        Toast savedToast = Toast.makeText(getApplicationContext(), "¡Dibujo guardado en galería!", Toast.LENGTH_SHORT);
                        savedToast.show();
                    } else {
                        Toast unsavedToast = Toast.makeText(getApplicationContext(), "¡Error! La imagen no se pudo guardar", Toast.LENGTH_SHORT);
                        unsavedToast.show();
                    }
                    lienzo.destroyDrawingCache();
                });
                salvarDibujo.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
                salvarDibujo.show();
                break;

            default:
                break;
        }
    }
}