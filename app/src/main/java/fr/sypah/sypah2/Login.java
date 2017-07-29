package fr.sypah.sypah2;



        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

public class Login extends AppCompatActivity {
    DatabaseHelper helper_pass = new DatabaseHelper(this);

    private Button button_compte, button_connection, button_invite;
    private EditText editText_Login, editText_pass;
    private TextView login, pass;
    private ImageView im_esiee;


    public void OnClick(View v){
        Context context = Login.this;
        CharSequence message = "prénom ou login incorrect";
        int duree = Toast.LENGTH_SHORT;

        if(v.getId() == R.id.button_connection)
        {
            EditText login = (EditText) findViewById(R.id.editText_login);
            String loginstr = login.getText().toString();
            EditText pass = (EditText) findViewById(R.id.editText_pass);
            String passstr = pass.getText().toString();

            String pass_trouve = helper_pass.searchPass(loginstr);
            if(passstr.equals(pass_trouve)){
                Intent intent = new Intent(Login.this,MainActivity.class); //passage de l'activité homepage à interfaceMainActivity
                intent.putExtra("prenom",loginstr);// associe les données à l'intention d'ouverture
                startActivity(intent);//lance l'activité

            }
            else{

                Toast toast = Toast.makeText(context, message, duree); // créé un Toast qui fournit un commentaire sur une opération dans un popup
                toast.show();
            }
        }
        if(v.getId()==R.id.button_compte) {
            Intent intent = new Intent(Login.this, Inscription.class);
            startActivity(intent);
        }

        if(v.getId()==R.id.button_invite) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_compte = (Button) findViewById(R.id.button_compte);
        button_connection = (Button) findViewById(R.id.button_connection);
        button_invite = (Button) findViewById(R.id.button_invite);
    }




}

