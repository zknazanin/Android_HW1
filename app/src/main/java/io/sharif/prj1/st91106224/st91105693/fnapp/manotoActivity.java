package io.sharif.prj1.st91106224.st91105693.fnapp;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class manotoActivity extends AppCompatActivity {
    ImageView anim,player,up,left,right,down;
    DisplayMetrics displayMetrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manoto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotation);
        anim = (ImageView) findViewById(R.id.myImage1);
        anim.setAnimation(animation);

        player = (ImageView) findViewById(R.id.myImage2);
        left = (ImageView) findViewById(R.id.left);
        up = (ImageView) findViewById(R.id.up);
        right = (ImageView) findViewById(R.id.right);
        down = (ImageView) findViewById(R.id.down);

        displayMetrics = getResources().getDisplayMetrics();

        LinearLayout firstLayout = (LinearLayout)findViewById(R.id.first);
        Log.e("first x", firstLayout.getX() + firstLayout.getWidth() + 5 + "");
        Log.e("first y", firstLayout.getY() + firstLayout.getHeight() + 5 + "");

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (player.getX() < 2 * displayMetrics.widthPixels / 5)
                    player.setX(player.getX() + 5);
                return true;
            }
        });
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (player.getX() > displayMetrics.widthPixels / 8 * -1)
                    player.setX(player.getX() - 5);
                return true;
            }
        });
        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (player.getY() > displayMetrics.heightPixels / 5)
                    player.setY(player.getY() - 5);
                return true;
            }
        });
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (player.getY() < 3 * displayMetrics.heightPixels / 2)
                    player.setY(player.getY() + 5);
                return true;
            }
        });




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
            createDialogGuide();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
