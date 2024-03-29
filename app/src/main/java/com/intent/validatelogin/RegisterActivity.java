package com.intent.validatelogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtUsername, edtPassword, edtEmail, edtNama, edtAsalSekolah, edtAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Register");
        }

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtNama = findViewById(R.id.edtNama);
        edtAsalSekolah = findViewById(R.id.edtAsalSekolah);
        edtAlamat = findViewById(R.id.edtAlamat);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnSimpan:
                validasi();
                break;
        }
    }

    private void validasi() {
        if (isValidation()) {
            simpanFileData();
        } else {
            Toast.makeText(this, "Mohon Lengkapi Seluruh Data", Toast.LENGTH_SHORT).show();
        }
    }

    boolean isValidation() {
        if (edtUsername.getText().toString().equals("") ||
                edtPassword.getText().toString().equals("") ||
                edtEmail.getText().toString().equals("") ||
                edtNama.getText().toString().equals("") ||
                edtAsalSekolah.getText().toString().equals("") ||
                edtAlamat.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    void simpanFileData(){
        String isiFile = edtUsername.getText().toString() + ";" +
        edtPassword.getText().toString() + ";" +
        edtEmail.getText().toString() + ";" +
        edtNama.getText().toString() + ";" +
        edtAsalSekolah.getText().toString() + ";" +
        edtAlamat.getText().toString();

        File file = new File(getFilesDir(), edtUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }


}
