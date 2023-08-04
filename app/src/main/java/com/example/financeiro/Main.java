package com.example.financeiro;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.ActionBar;
import com.google.android.material.navigation.NavigationView;

public class Main extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    Toast.makeText(Main.this, "Home selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.contact) {
                    Toast.makeText(Main.this, "Contact selected", Toast.LENGTH_SHORT).show();
                    return true;
                }  else if (itemId == R.id.financeiro) {
                    startActivity(new Intent(Main.this, CadastrarContas.class));
                    return true;
                }  else if (itemId == R.id.resumo) {
                    startActivity(new Intent(Main.this, VisualizarGastos.class));
                    return true;
                }  else if (itemId == R.id.about) {
                    Toast.makeText(Main.this, "About selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.login) {
                    Toast.makeText(Main.this, "Login selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
//                else if (itemId == R.id.share) {
//                    Toast.makeText(Main.this, "Share selected", Toast.LENGTH_SHORT).show();
//                    return true;
//                } else if (itemId == R.id.rateus) {
//                    Toast.makeText(Main.this, "Rate us selected", Toast.LENGTH_SHORT).show();
//                    return true;
//                }

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
