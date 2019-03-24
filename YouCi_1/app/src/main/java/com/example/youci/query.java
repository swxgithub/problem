package com.example.youci;

import java.io.InputStream;
import java.io.PipedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.PrecomputedText;
import android.widget.EditText;
import android.view.View;
import android.app.Activity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class query extends AppCompatActivity {
    public final static String iCiBaURL1 = "http://dict-co.iciba.com/api/dictionary.php?w=";
    public final static String iCiBaURL2 = "&key=ECBCD519B1ADA0D4C412A3EAB736A407";
    private EditText inputWord;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
    }

    public void btnQuery(View view){
        inputWord = findViewById(R.id.inputWord);
        String word = inputWord.getText().toString().trim();
        System.out.println("yes");
        new getInfoOnline().execute();
    }

    public void btnReview(View v){
        startActivity(new Intent(this, Review.class));
//        finish();
        //setContentView(R.layout.review);
    }

     class getInfoOnline extends AsyncTask<String, Void, InputStream>{
         //InputStream tempInput = null;
        public InputStream doInBackground(String... params){
            InputStream tempInput = null;
            inputWord = findViewById(R.id.inputWord);
            String word = inputWord.getText().toString().trim();
            //InputStream tempInput = null;
            URL url = null;
            HttpURLConnection connection = null;
            //设置超时时间
            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            try {
                String urlStr = iCiBaURL1+word+iCiBaURL2 ;
                url = new URL(urlStr);
                connection = (HttpURLConnection) url.openConnection();     //别忘了强制类型转换
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(10000);
                connection.setRequestMethod("GET");
                connection.connect();
                //System.out.println(connection);
                tempInput = connection.getInputStream();
                System.out.println(tempInput);
                return tempInput;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

         public void onPostExecute(InputStream tempInput){
            System.out.println(tempInput);
             inputWord = findViewById(R.id.inputWord);
             String word = inputWord.getText().toString().trim();
             System.out.println("hhhhhhhhhhhhhhh");
             WordValue wordValue=null;
             //String tempWord=searchedWord;
             //if(tempWord==null&& tempWord.equals(""))
                 //return null;
             //char[] array=tempWord.toCharArray();
//        if(array[0]>256)           //是中文，或其他语言的的简略判断
//            tempWord="_"+ URLEncoder.encode(tempWord);
             //InputStream in=tempInput;
             //String str=null;
             try{
                 //String tempUrl=query.iCiBaURL1+tempWord+query.iCiBaURL2;
                 //ystem.out.println(tempUrl);
                    //从网络获得输入流
                 if(tempInput!=null){
                     System.out.println("here");
                     //new FileUtils().saveInputStreamToFile(in, "", "gfdgf.txt");
                     XMLParser xmlParser=new XMLParser();
                     InputStreamReader reader=new InputStreamReader(tempInput,"utf-8");        //最终目的获得一个InputSource对象用于传入形参
                     JinShanContentHandler contentHandler=new JinShanContentHandler();
                     xmlParser.parseJinShanXml(contentHandler, new InputSource(reader));
                     wordValue=contentHandler.getWordValue();
                     wordValue.setWord(word);
                     System.out.println("here");
                     System.out.println(wordValue);
                     System.out.println(wordValue.getWord());
                     System.out.println(wordValue.getInterpret());
                     System.out.println(wordValue.getSentOrig());
                     System.out.println(wordValue.getSentTrans());
                 }
             }catch(Exception e){
                 e.printStackTrace();
             }

             View Word = ((TextView)findViewById(R.id.word));
             ((TextView) Word).setText(wordValue.getWord());

             View Interpret = ((TextView)findViewById(R.id.Interpret));
             ((TextView) Interpret).setText(wordValue.getInterpret());

             View Santance = ((TextView)findViewById(R.id.sentances));
             ((TextView) Santance).setText(wordValue.getSentOrig());

         }
        }
     }








