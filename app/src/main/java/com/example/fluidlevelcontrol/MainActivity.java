package com.example.fluidlevelcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    TextView sensorData;
    ImageView imgDat;
    EditText tartaly;
    DatabaseReference sData;
    Button ok;
    int statusFb;
    int status;
    String max;
    int maxI = 0;
    int statusKorr;
    int statusPC1;
    int statusPC2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sData = FirebaseDatabase.getInstance().getReference();
        sensorData = findViewById(R.id.sensorData);
        imgDat = findViewById(R.id.imgData);
        tartaly = findViewById(R.id.tartalyET);
        ok = findViewById(R.id.ok_btn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                max = tartaly.getText().toString();
                maxI = Integer.parseInt(max);

        sData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                statusFb = Integer.parseInt(dataSnapshot.child("distance").getValue().toString());
                statusKorr = maxI - statusFb;
                status = (int) (((double) statusKorr / (double) maxI) * 100);

                Log.d("statPC", Integer.toString(status));

                sensorData.setText(Integer.toString(status) + " %");
                if (status >= 95 && status <= 100)
                {
                    imgDat.setImageResource(R.drawable.i100);
                }

                else if (status >= 90 && status < 95)
                {
                    imgDat.setImageResource(R.drawable.i95);
                }

                else if (status >= 80 && status < 90)
                {
                    imgDat.setImageResource(R.drawable.i90);
                }

                else if (status >= 70 && status < 80)
                {
                    imgDat.setImageResource(R.drawable.i80);
                }

                else if (status >= 55 && status < 70)
                {
                    imgDat.setImageResource(R.drawable.i70);
                }

                else if (status >= 50 && status < 55)
                {
                    imgDat.setImageResource(R.drawable.i55);
                }

                else if (status >= 40 && status < 50)
                {
                    imgDat.setImageResource(R.drawable.i50);
                }

                else if (status >= 30 && status < 40)
                {
                    imgDat.setImageResource(R.drawable.i40);
                }

                else if (status >= 20 && status < 30)
                {
                    imgDat.setImageResource(R.drawable.i30);
                }

                else if (status >= 15 && status < 20)
                {
                    imgDat.setImageResource(R.drawable.i20);
                }

                else if (status >= 10 && status < 15)
                {
                    imgDat.setImageResource(R.drawable.i15);
                }

                else if (status > 0 && status < 10)
                {
                    imgDat.setImageResource(R.drawable.i10);
                }

                else if (status < 0)
                {
                    imgDat.setImageResource(R.drawable.i0);
                    sensorData.setText("");
                }

                else
                {
                    imgDat.setImageResource(R.drawable.i0);
                    sensorData.setText("");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

            }
        });

    }
}
