package perriobarreteau.apprentissagemusique;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

public class DicteeDeNote_Button extends AppCompatActivity implements View.OnClickListener {

    // Il faut ajouter les images et les sons des notes #
    Note Do = new Note(0, "Do", R.drawable.ic_note_do, R.raw.aaa);
    Note Dod = new Note(1, "Do#", R.drawable.ic_note_do, R.raw.aaa);
    Note Re = new Note(2, "Ré", R.drawable.ic_note_re, R.raw.re);
    Note Red = new Note(3, "Ré#", R.drawable.ic_note_re, R.raw.re);
    Note Mi = new Note(4, "Mi", R.drawable.ic_note_mi, R.raw.mi);
    Note Fa = new Note(5, "Fa", R.drawable.ic_note_fa, R.raw.fa);
    Note Fad = new Note(6, "Fa#", R.drawable.ic_note_fa, R.raw.fa);
    Note Sol = new Note(7, "Sol", R.drawable.ic_note_sol, R.raw.sol);
    Note Sold = new Note(8, "Sol#", R.drawable.ic_note_sol, R.raw.sol);
    Note La = new Note(9, "La", R.drawable.ic_note_la, R.raw.la);
    Note Lad = new Note(10, "La#", R.drawable.ic_note_la, R.raw.la);
    Note Si = new Note(11, "Si", R.drawable.ic_note_si, R.raw.si);
    Note[] notes = {Do, Dod, Re, Red, Mi, Fa, Fad, Sol, Sold, La, Lad, Si};
    MediaPlayer mediaplayer;
    Random random = new Random();
    int idQuestion = random.nextInt(12); // Choix aléatoire de la note à deviner
    int nbEcoutes = 0;
    int nbFautes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictee_de_note_button);

        // Gestion du lecteur audio
        ImageButton imageButtonNote = (ImageButton) findViewById(R.id.imageButtonNote);
        imageButtonNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                nbEcoutes ++;
                if (nbEcoutes <= 5) {
                    mediaplayer = MediaPlayer.create(getApplicationContext(), notes[idQuestion].son);
                    mediaplayer.start();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Proposez une réponse", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Boutons de réponse
        Button buttonDo = (Button) findViewById(R.id.buttonDo);
        buttonDo.setTag(Integer.valueOf(0));
        buttonDo.setOnClickListener(this);
        Button buttonDod = (Button) findViewById(R.id.buttonDod);
        buttonDod.setTag(Integer.valueOf(1));
        buttonDod.setOnClickListener(this);
        Button buttonRe = (Button) findViewById(R.id.buttonRe);
        buttonRe.setTag(Integer.valueOf(2));
        buttonRe.setOnClickListener(this);
        Button buttonRed = (Button) findViewById(R.id.buttonRed);
        buttonRed.setTag(Integer.valueOf(3));
        buttonRed.setOnClickListener(this);
        Button buttonMi = (Button) findViewById(R.id.buttonMi);
        buttonMi.setTag(Integer.valueOf(4));
        buttonMi.setOnClickListener(this);
        Button buttonFa = (Button) findViewById(R.id.buttonFa);
        buttonFa.setTag(Integer.valueOf(5));
        buttonFa.setOnClickListener(this);
        Button buttonFad = (Button) findViewById(R.id.buttonFad);
        buttonFad.setTag(Integer.valueOf(6));
        buttonFad.setOnClickListener(this);
        Button buttonSol = (Button) findViewById(R.id.buttonSol);
        buttonSol.setTag(Integer.valueOf(7));
        buttonSol.setOnClickListener(this);
        Button buttonSold = (Button) findViewById(R.id.buttonSold);
        buttonSold.setTag(Integer.valueOf(8));
        buttonSold.setOnClickListener(this);
        Button buttonLa = (Button) findViewById(R.id.buttonLa);
        buttonLa.setTag(Integer.valueOf(9));
        buttonLa.setOnClickListener(this);
        Button buttonLad = (Button) findViewById(R.id.buttonLad);
        buttonLad.setTag(Integer.valueOf(10));
        buttonLad.setOnClickListener(this);
        Button buttonSi = (Button) findViewById(R.id.buttonSi);
        buttonSi.setTag(Integer.valueOf(11));
        buttonSi.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        mediaplayer.release();
        nbEcoutes = 0;

        // Récupération de la réponse
        int idReponse = (int) view.getTag();

        // Traitement de la réponse
            // Cas : Bonne réponse
        if (idReponse == idQuestion) {
            Toast.makeText(getApplicationContext(), "Bravo !", Toast.LENGTH_SHORT).show();
            // On choisi aléatoirement une nouvelle note à deviner
            idQuestion = newIdQuestion(idQuestion);
            this.nbFautes = 0;
        }
            // Cas : Mauvaise réponse
        else {
            this.nbFautes ++;

            if (nbFautes == 1) {
                Toast.makeText(getApplicationContext(), "C'est faux... rééssayez", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "C'est faux... la bonne réponse était : " + notes[this.idQuestion -1].nom, Toast.LENGTH_SHORT).show();
                idQuestion =  newIdQuestion(idQuestion);
                this.nbFautes = 0;
            }
        }

    }

    public int newIdQuestion(int idQuestion) {
        int idQuestionNew = random.nextInt(12);
        while (idQuestionNew == this.idQuestion) {
            idQuestionNew = random.nextInt(12);
        }
        return(idQuestionNew);

    }

}