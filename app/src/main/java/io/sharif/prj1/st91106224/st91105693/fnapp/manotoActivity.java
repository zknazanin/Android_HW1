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
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

public class manotoActivity extends AppCompatActivity {
    SharedPreferences sharedPref ;
    SharedPreferences.Editor editor;
    ImageView anim,player,up,left,right,down,menu;
    Float centerX,centerY;
    DisplayMetrics displayMetrics;

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
        // forough
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
        // zahra
        displayMetrics = getResources().getDisplayMetrics();

        LinearLayout firstLayout = (LinearLayout)findViewById(R.id.first);
        Log.e("first x", firstLayout.getX() + firstLayout.getWidth() + 5 + "");
        Log.e("first y", firstLayout.getY() + firstLayout.getHeight() + 5 + "");

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("Player y",""+player.getY());
                Log.e("heightPixels",""+displayMetrics.heightPixels);
                if (player.getX() < 2 * displayMetrics.widthPixels / 5)
                    player.setX(player.getX() + 5);
                return true;
            }
        });
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("Player y",""+player.getY());
                Log.e("heightPixels",""+displayMetrics.heightPixels);
                if (player.getX() > displayMetrics.widthPixels / 8 * -1)
                    player.setX(player.getX() - 5);
                return true;
            }
        });
        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("Player y",""+player.getY());
                Log.e("heightPixels",""+displayMetrics.heightPixels);
                if (player.getY() > 0)
                    player.setY(player.getY() - 5);
                return true;
            }
        });
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("Player y",""+player.getY());
                Log.e("heightPixels",""+displayMetrics.heightPixels);
                if (player.getY() < 1 * displayMetrics.heightPixels / 3)
                    player.setY(player.getY() + 5);
                return true;
            }
        });

    }
    // forough
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

    @Override // zahra
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            createDialogGuide();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // forough
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
    // zahra
    private void createDialogGuide() {
        Dialog dialog = new Dialog(manotoActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_about);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.guide_linear);
        int width = (int)(displayMetrics.widthPixels);
        int height = (int) (width*0.71);
        dialog.getWindow().setLayout(width, height);
        dialog.setTitle("درباره ما");
        dialog.show();
    }
}