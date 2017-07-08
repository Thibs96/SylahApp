package fr.sypah.sypah2;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.sypah.sypah2.DatabaseHelper;


import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;




public class Inscription extends ActionBarActivity {
    private static final String TAG = "inscription";
    DatabaseHelper db;
    private Button button_confirm, btnviewAll;
    private EditText nom, pseudo,pass1, pass2, animal;
    private TextView text_nom, text_pseudo, text_pass, text_confirmpass,text_animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        db = new DatabaseHelper(this);
        btnviewAll = (Button)findViewById(R.id.btnviewAll);
        button_confirm = (Button)findViewById(R.id.button_confirm);

        nom = (EditText) findViewById(R.id.saisis_Nom);
        pseudo = (EditText) findViewById(R.id.saisis_pseudo);
        pass1 = (EditText) findViewById(R.id.saisis_pass);
        pass2 = (EditText) findViewById(R.id.saisis_confirmpass);
        animal = (EditText) findViewById(R.id.saisis_animal);

        try{
            AddData();
            viewAll();


        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }


    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Erreur","Rien Trouvé");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("PRENOM :"+ res.getString(1)+"\n");
                            buffer.append("PSEUDO :"+ res.getString(2)+"\n");
                            buffer.append("ANIMAL :"+ res.getString(3)+"\n\n");
                            buffer.append("PASS :"+ res.getString(4)+"\n\n");
                        }


                        showMessage("données utilisateurs",buffer.toString());
                    }
                }
        );
    }

    public  void AddData() {
        button_confirm.setOnClickListener(new View.OnClickListener() {

                                              public void onClick(View v) {
                                                  boolean isInserted = db.insertUtilisateurs(nom.getText().toString(),
                                                          pseudo.getText().toString(),
                                                          animal.getText().toString(),
                                                          pass1.getText().toString(),
                                                          pass2.getText().toString());
                                                  if(isInserted == true)
                                                      Toast.makeText(Inscription.this,"utilisateur ajouté",Toast.LENGTH_LONG).show();
                                                  else
                                                      Toast.makeText(Inscription.this,"utilisateur non ajouté",Toast.LENGTH_LONG).show();
                                              }
                                          }
        );
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

