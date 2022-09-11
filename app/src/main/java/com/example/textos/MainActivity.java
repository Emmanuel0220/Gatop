package com.example.textos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView junop, jdosp, st;
    private Button[] buttons = new Button[9];
    private Button reiniciar;
    private int puntu1, puntu2, cont;
    boolean actjug;

    int [] estado = {2,2,2,2,2,2,2,2,2};
    int [][] ganar = {
            {0,1,2,3},{1,2,3,4},{2,3,4,5},
            {6,7,8,9},{7,8,9,10},{8,9,10,11},
            {12,13,14,15},{13,14,15,16},{14,15,16,17},
            {18,19,20,21},{19,20,21,22},{20,21,22,23},
            {24,25,26,27},{25,26,27,28},{26,27,28,29},
            {30,31,32,33},{31,32,33,34},{32,33,34,35},
            {12,19,26,33},{6,13,20,27},{13,20,27,34},{0,7,14,21},{7,14,21,28},{14,21,28,35},{1,8,15,22},{8,15,22,29},{2,9,16,23},
            {17,22,27,32},{11,16,21,26},{16,21,26,31},{5,10,15,20},{10,15,20,25},{15,20,25,30},{4,9,14,19},{9,14,19,24},{3,8,13,18}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        junop = (TextView) findViewById(R.id.punt1);
        jdosp = (TextView) findViewById(R.id.punt2);
        st = (TextView) findViewById(R.id.st);
        reiniciar = (Button) findViewById(R.id.reset);
        for (int i=0; i<buttons.length; i++){
            String btnId = "btn"+ i;
            int ID = getResources().getIdentifier(btnId, "id", getPackageName());
            buttons[i] = (Button) findViewById(ID);
            buttons[i].setOnClickListener(this);
        }
        cont=0;
        puntu1=0;
        puntu2=0;
        actjug=true;
    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        String ID =view.getResources().getResourceEntryName(view.getId());
        int jpunt= Integer.parseInt(ID.substring(ID.length()-1, ID.length()));

        if (actjug) {
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#9A39D2"));
            estado[jpunt] = 0;
        }else{
            ((Button) view).setText("O");
            ((Button) view).setTextColor(Color.parseColor("#C639D2"));
            estado[jpunt] = 1;
        }
        cont++;
        if(ganador()){
            if(actjug){
                puntu1++;
                actual();
                Toast.makeText(this, "El jugador 1 ganó", Toast.LENGTH_SHORT).show();
                reinicio();
            }else {
                puntu2++;
                actual();
                Toast.makeText(this, "El jugador 2 ganó", Toast.LENGTH_SHORT).show();
                reinicio();
            }

        }else if(cont== 9){
            reinicio();
            Toast.makeText(this, "Es un empate", Toast.LENGTH_SHORT).show();
            
        }else {
            actjug = !actjug; 
        }

    }
    public boolean ganador(){
        boolean result =false;

        for(int[] posi: ganar){
            if(estado[posi[0]]==estado[posi[1]] && estado[posi[1]] == estado[posi[2]] && estado[posi[0]] != 2){
                result=true;
            }
        }
        return result;
    }
    public void actual(){
        junop.setText(Integer.toString(puntu1));
        jdosp.setText(Integer.toString(puntu2));
    }
    public void reinicio(){
        cont=0;
        actjug=true;

        for(int i=0;i<buttons.length; i++){
            estado[i]=1;
            buttons[i].setText("");
        }
    }
}