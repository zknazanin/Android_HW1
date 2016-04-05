package io.sharif.prj1.st91106224.st91105693.fnapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class manotoActivity extends AppCompatActivity {
    ImageView anim,player,up,left,right,down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manoto);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotation);
        anim = (ImageView) findViewById(R.id.myImage1);
        anim.setAnimation(animation);

        player = (ImageView) findViewById(R.id.myImage2);
        left = (ImageView) findViewById(R.id.left);
        up = (ImageView) findViewById(R.id.up);
        right = (ImageView) findViewById(R.id.right);
        down = (ImageView) findViewById(R.id.down);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setX(player.getX()+1);
                return;
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setX(player.getX() - 1);
                return;
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setX(player.getY()+1);
                return;
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setX(player.getY()-1);
                return;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
