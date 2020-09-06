package th.ac.su.cp.speedcalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText distanceNumber = findViewById(R.id.enter_distance);
                String textDistanceNumber = distanceNumber.getText().toString();
                Log.i("MainActivity", "DistanceNumber :" + textDistanceNumber);

                EditText timeNumber = findViewById(R.id.enter_time);
                String textTimeNumber = timeNumber.getText().toString();
                Log.i("MainActivity", "TimeNumber :" + textTimeNumber);
                if (textDistanceNumber.isEmpty() || textTimeNumber.isEmpty()) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.distance_time_required, Toast.LENGTH_LONG);
                    toast.show();
                } else if (textTimeNumber.equals("0")) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.time_than_zero, Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    double distance = Double.parseDouble(textDistanceNumber);
                    double time = Double.parseDouble(textTimeNumber);
                    double averageSpeed = (distance / time) * 18 / 5;

                    String str = String.format(
                            Locale.getDefault(), "%.2f", averageSpeed
                    );
                    TextView averageSpeedTextView = findViewById(R.id.average_speed_text_view);
                    averageSpeedTextView.setText(str);
                    if (averageSpeed > 80) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("SPEED CALCULATOR");
                        dialog.setMessage(R.string.speed_over);
                        dialog.setPositiveButton("OK", null);
                        dialog.show();
                    }
                }
            }
        });

        Button clearButton = findViewById(R.id.clear_button);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText distanceNumber = findViewById(R.id.enter_distance);
                distanceNumber.setText("");

                EditText timeNumber = findViewById(R.id.enter_time);
                timeNumber.setText("");

                TextView averageSpeedTextView = findViewById(R.id.average_speed_text_view);
                averageSpeedTextView.setText("");
            }
        });
    }
}