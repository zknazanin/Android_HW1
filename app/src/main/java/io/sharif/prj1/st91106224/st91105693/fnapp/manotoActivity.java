package io.sharif.prj1.st91106224.st91105693.fnapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class manotoActivity extends AppCompatActivity {
    SharedPreferences sharedPref ;
    SharedPreferences.Editor editor;
    ImageView anim,player,up,left,right,down,menu;
    Float centerX,centerY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manoto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotation);
        anim = (ImageView) findViewById(R.id.myImage1);
        anim.setAnimation(animation);
        menu = (ImageView) findViewById(R.id.menu);
        player = (ImageView) findViewById(R.id.myImage2);
        left = (ImageView) findViewById(R.id.left);
        up = (ImageView) findViewById(R.id.up);
        right = (ImageView) findViewById(R.id.right);
        down = (ImageView) findViewById(R.id.down);
        centerX = player.getTranslationX();
        centerY = player.getTranslationY();
        player.setTranslationX(sharedPref.getFloat(getString(R.string.saved_x),centerX));
        player.setTranslationY(sharedPref.getFloat(getString(R.string.saved_y), centerY));
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(manotoActivity.this, menu);
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals(getString(R.string.saveGame))){
                            editor.putFloat(getString(R.string.saved_x), player.getTranslationX());
                            editor.putFloat(getString(R.string.saved_y), player.getTranslationY());
                            editor.commit();
                        }else {
                            editor.putFloat(getString(R.string.saved_x), centerX);
                            editor.putFloat(getString(R.string.saved_y), centerY);
                            editor.commit();
                            player.setTranslationX(centerX);
                            player.setTranslationY(centerY);
                            ToastShow(item);
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setX(player.getX()+15);
                return;
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setX(player.getX() - 15);
                return;
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setY(player.getY()-15);
                return;
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setY(player.getY()+15);
                return;
            }
        });

    }

    private void ToastShow(MenuItem item) {
        SpannableString spanString = new SpannableString(item.getTitle());
        spanString.setSpan(new RainBowSpan(this), 0, item.getTitle().length(), 0);
        Toast.makeText(
                manotoActivity.this,
                spanString,
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this   items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manoto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static class RainBowSpan extends CharacterStyle implements UpdateAppearance {
        private final int[] colors;

        public RainBowSpan(Context context) {
            colors = context.getResources().getIntArray(R.array.rainbow);
        }

        @Override
        public void updateDrawState(TextPaint paint) {
            paint.setStyle(Paint.Style.FILL);
            Shader shader = new LinearGradient(0, 0, 0, paint.getTextSize() * colors.length, colors, null,
                    Shader.TileMode.MIRROR);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            shader.setLocalMatrix(matrix);
            paint.setShader(shader);
        }
    }
}


