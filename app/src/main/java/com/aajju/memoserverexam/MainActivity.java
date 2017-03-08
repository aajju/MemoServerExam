package com.aajju.memoserverexam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Api mApi;
    private ProgressDialog mProgressDialog;
    private EditText mEmail, mPassword;
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.email_et);
        mPassword = (EditText) findViewById(R.id.password_et);

        mApi = HttpHelper.getAPI();

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEmail.getText().toString()) || TextUtils.isEmpty(mPassword.getText().toString())) {
                    Toast.makeText(MainActivity.this, "둘다 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }


                showProgressDialog();

                mApi.login(mEmail.getText().toString(), mPassword.getText().toString()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        dismissDialog();
                        if (!response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "아이디와 비밀번호에 해당하는 회원 정보가 없습니다. \n 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        mToken = response.headers().get("x-auth-token");
                        startActivity(new Intent(MainActivity.this, ListActivity.class).putExtra("token", mToken));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dismissDialog();
                        Toast.makeText(MainActivity.this, "서버에서 데이터 가져오기 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.join_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, JoinActivity.class), 100);
            }
        });
    }


    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("로그인 시도중...");
        }

        mProgressDialog.show();
    }

    private void dismissDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            Toast.makeText(this, "회원 가입 성공", Toast.LENGTH_SHORT).show();
        }
    }

}
