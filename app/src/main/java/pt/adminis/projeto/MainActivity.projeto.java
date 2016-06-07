        package pt.adminis.projeto;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.SharedPreferences;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Adapter;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.google.android.gms.appindexing.Action;
        import com.google.android.gms.common.api.GoogleApiClient;

        import java.util.ArrayList;
        import java.util.LinkedHashSet;
        import java.util.Set;

        import static pt.adminis.projeto.AppIndex.*;

        public class MainActivity extends AppCompatActivity {

                private ArrayList<String> music;

                protected GoogleApiClient client;
                private ArrayList<String> Music;

                public MainActivity() {
                        client = AppIndex.;
                }


                @Override
                protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        setContentView(R.layout.activity_main);

                        SharedPreferences sp = getSharedPreferences("appMusic", 0);
                        Set<String> Music = sp.getStringSet("musicKay", new LinkedHashSet<String>())



                        Music = new ArrayList<String>();
//                          music.add("Charlie Puth | One Call away");
//                          music.add("Djodje | Txukinha");
//                          music.add("Boss AC | A Carta que eu nunca te escrevi");
//                          music.add("Carlão | Na Batalha");
//                          music.add("Kova M | Fronta");
//                          music.add("Empire[cast] | Conqueror");


                        //Código da List view
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                android.R.layout.simple_list_item_1, music);

                        ListView listView = (ListView) findViewById(R.id.listView_musics);
                        ArrayAdapter<CharSequence> adaptere = null;
                        listView.setAdapter(adaptere);
                        //Código para Spinner
                        Spinner spinner = (Spinner) findViewById(R.id.spinner);
                        // Create an ArrayAdapter using the string array and a default spinner layout
                        adaptere = ArrayAdapter.createFromResource(this,
                                R.array.planets_array, android.R.layout.simple_spinner_item);
                        // Specify the layout to use when the list of choices appears
                        adaptere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // Apply the adapter to the spinner
                        spinner.setAdapter(adaptere);



                        ListView.setOnIntemClickListener((new Adapter.View.OnItemclickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent,
                                                        View view,
                                                        int position,
                                                        long id) {
                                        Toast.makeText(MainActivity.this, "Clicou no item" + position, Toast.LENGTH_LONG).getClass().show();
                                }
                        });

                                        Int position;

                        Music.remove(position);


                        @Override
                        protected void onStop(){
                                super.onStop();
                                Toast.makeText(MainActivity.this, "A guardar musicas", Toast.LENGTH_LONG).show();

                                SharedPreferences sp = new LinkedHashSet(Music);
                                SharedPreferences.Editor edit = sp.edit();

                                LinkedHashSet musicset = new LinkedHashSet(music);

                                edit.putString("musicKay", musicSet);

                                edit.commit();
                        }


                public void onClick_search(View view) {
                        //ir buscar as referências para a editText, o spinner e a listView
                        EditText et = (EditText) findViewById(R.id.editText_search);
                        Spinner sp = (Spinner) findViewById(R.id.spinner_search);
                        ListView lv = (ListView) findViewById(R.id.listView_musics);

                        //criar uma nova lista, que guarde os contactos pesquisados
                        ArrayList<String> searchedmusic = new ArrayList<>();

                        //percorrer todos os contactos, e adicionar os que contêm
                        //o termo a pesquisar à nova lista
                        String termo = et.getText().toString();
                        String categoria = sp.getSelectedItem().toString();

                        for (String c : music) {
                                if (c.contains(termo)) ;
                                {
                                        searchedmusic.add(c);
                                }
                        }  if (categoria.equals("Name")) {
                                //pesquisar pelo nome
                                for (String c: music) {
                                        String[] split = c.split("\\|");
                                        String name = split[0];
                                        if (name.contains(termo)){
                                                searchedmusic.add(c);
                                        }
                                }
                        }else if (categoria.equals("Album")) {
                                //pesquisar pelo Album
                                for (String c: music) {
                                        String[] split = c.split("\\|");
                                        String phone = split[1];
                                        if (phone.contains(termo)){
                                                searchedmusic.add(c);
                                        }
                                }
                        }
                        //mostrar o conteudo da lista de musicas pesquisados na Listview
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                android.R.layout.simple_list_item_1, searchedmusic);
                        lv.setAdapter(adapter);

                        //mostrar uma mensagem a dizer "Showing searched ."
                        Toast.makeText(MainActivity.this, "Showing searched", Toast.LENGTH_SHORT).show();
                }

                public void onClick_add(View view) {
                        Toast.makeText(MainActivity.this, "Escolha a musica", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);

                        // Get the layout inflater
                        LayoutInflater inflater = this.getLayoutInflater();

                        // Inflate and set the layout for the dialog
                        // Pass null as the parent view because its going in the dialog layout
                        builder.setView(inflater.inflate(R.layout.dialog_add, null));
                        // Add the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                        // User clicked OK button
                                        Toast.makeText(MainActivity.this, "carreguei no Ok",Toast.LENGTH_SHORT).show();
                                }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                        // User cancelled the dialog
                                        Toast.makeText(MainActivity.this, "carreguei no cancel",Toast.LENGTH_SHORT).show();

                                }
                        });
                        // Set other dialog propertie

                        builder.setMessage("Introduza o nome do cantor");
                        // Create the AlertDialog
                        AlertDialog dialog = builder.create();
                        dialog.show();

                }

                @Override
                public void onStart() {
                        super.onStart();

                        // ATTENTION: This was auto-generated to implement the App Indexing API.
                        // See https://g.co/AppIndexing/AndroidStudio for more information.
                        client.connect();
                        Action viewAction = Action.newAction(
                                Action.TYPE_VIEW, // TODO: choose an action type.
                                "Main Page", // TODO: Define a title for the content shown.
                                // TODO: If you have web page content that matches this app activity's content,
                                // make sure this auto-generated web page URL is correct.
                                // Otherwise, set the URL to null.
                                Uri.parse("http://host/path"),
                                // TODO: Make sure this auto-generated app deep link URI is correct.
                                Uri.parse("android-app://pt.adminis.projeto/http/host/path")
                        );
                        com.google.android.gms.appindexing.AppIndex.AppIndexApi.start(client, viewAction);
                }

                @Override
                public void onStop() {
                        super.onStop();

                        // ATTENTION: This was auto-generated to implement the App Indexing API.
                        // See https://g.co/AppIndexing/AndroidStudio for more information.
                        Action viewAction = Action.newAction(
                                Action.TYPE_VIEW, // TODO: choose an action type.
                                "Main Page", // TODO: Define a title for the content shown.
                                // TODO: If you have web page content that matches this app activity's content,
                                // make sure this auto-generated web page URL is correct.
                                // Otherwise, set the URL to null.
                                Uri.parse("http://host/path"),
                                // TODO: Make sure this auto-generated app deep link URI is correct.
                                Uri.parse("android-app://pt.adminis.projeto/http/host/path")
                        );
                        com.google.android.gms.appindexing.AppIndex.AppIndexApi.end(client, viewAction);
                        client.disconnect();
                }
        }