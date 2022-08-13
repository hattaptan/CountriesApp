package com.tr.countries.ui.countrydetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.countries.R;
import com.tr.countries.data.CountryDom;
import com.tr.countries.di.IntentKey;
import com.tr.countries.di.MyActivity;

import java.io.InputStream;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDetail extends MyActivity {
    ImageView countryFlag;
    TextView countryCode;
    Button countryMoreInfoBtn;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;
    ImageButton countryDetailBackBtn;
    public String flagUrl="";
    public String countryCodeText="";
    public String WikiDataId="";
    public String CountryPngCode="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_detail);
        countryFlag = findViewById(R.id.countryFlag);
        countryMoreInfoBtn = findViewById(R.id.countryMoreInfoBtn);
        countryCode = findViewById(R.id.countryCode);
        countryDetailBackBtn = findViewById(R.id.countryDetailBackBtn);

        String countryName = getIntent().getStringExtra(IntentKey.COUNTRY_NAME.name());



        getCountry(countryName);
        setListener();
    }
    private void getCountry(String countryName){
        Call<CountryDom> call = countryService.getCountry(countryName);
        call.enqueue(new Callback<CountryDom>() {
            @Override
            public void onResponse(Call<CountryDom> call, Response<CountryDom> response) {
                if (response.isSuccessful()) {
                    CountryDom responseBody ;
                    responseBody = response.body();

                    countryCodeText=responseBody.data.code;
                    flagUrl=responseBody.data.flagImageURI;
                    WikiDataId=responseBody.data.name;


                    //because of image value returned from the response is null, an image from another site was added by link.
                    countryCode.setText(countryCodeText);
                    String myCode=responseBody.data.name;;
                    String flagCode=myCode;
                    flagCode=flagCode.toLowerCase(Locale.ROOT);
                    boolean isEmptyChar=true;
                    String[] combStr=new String[2];
                    for (int i=0;i<flagCode.length();i++)
                    {
                        if (flagCode.charAt(i)==' ')
                        {
                            isEmptyChar=false;
                            combStr=flagCode.split(" ");
                            break;
                        }

                    }
                    if (isEmptyChar==false)
                    {
                        flagCode=combStr[0]+"-"+combStr[1];
                    }

                    String Url ="https://www.countryflags.com/wp-content/uploads/" +
                            flagCode +
                            "-flag-png-large.png";


                    new DownloadImageFromInternet((ImageView) findViewById(R.id.countryFlag)).execute(Url);


                }
            }
            @Override
            public void onFailure(Call<CountryDom> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
    private void setListener(){
        countryMoreInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (WikiDataId.contains(" "))
                {
                    WikiDataId.replace(" ","_");
                }

                String url = "https://tr.wikipedia.org/wiki/"+WikiDataId;

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        countryDetailBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
