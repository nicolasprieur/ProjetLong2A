package perriobarreteau.apprentissagemusique;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Accordeur extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accordeur);

        int resultat = -1;

        Button buttonRecord = (Button) findViewById(R.id.buttonRecord);
        buttonRecord.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                float[] signal = Note.enregistrement();

                int resultat = Note.reconnaissanceDeNote(signal, 8000);
                System.out.println(resultat);

                TextView textViewNote = (TextView) findViewById(R.id.textViewNote);
                if (resultat == -1) {
                    textViewNote.setText("...");
                }
                else {
                    textViewNote.setText(notes[resultat].nom);
                }

            }
        });


    }

}
