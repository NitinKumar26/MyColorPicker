package com.nitin.colorpicker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.nitin.library.R;
import com.nitin.library.colorpicker.ColorPickerDialog;
import com.nitin.library.colorpicker.ColorPickerSwatch;

public class MainActivity extends AppCompatActivity {

    private int mSelectedColor;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSelectedColor = ContextCompat.getColor(this, R.color.flamingo);

        textView = findViewById(R.id.text);

        int[] mColors = getResources().getIntArray(R.array.default_rainbow);

        ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
                mColors,
                mSelectedColor,
                5, // Number of columns
                ColorPickerDialog.SIZE_SMALL,
                true // True or False to enable or disable the serpentine effect
                //0, // stroke width
                //Color.BLACK // stroke color
        );

        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {

            @Override
            public void onColorSelected(int color) {
                mSelectedColor = color;
                textView.setTextColor(mSelectedColor);
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }

        });

        dialog.show(getFragmentManager(), "color_dialog_test");
    }
}
