package com.example.youci;

import android.icu.text.SymbolTable;

import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Object;
import android.os.AsyncTask;
import android.os.AsyncTask;
import android.os.StrictMode;

public class DictActivity {
    public WordValue getWordFromInternet(String searchedWord){
        WordValue wordValue=null;
        String tempWord=searchedWord;
        if(tempWord==null&& tempWord.equals(""))
            return null;
        char[] array=tempWord.toCharArray();
//        if(array[0]>256)           //是中文，或其他语言的的简略判断
//            tempWord="_"+ URLEncoder.encode(tempWord);
        InputStream in=null;
        String str=null;
        try{
            String tempUrl=query.iCiBaURL1+tempWord+query.iCiBaURL2;
            //System.out.println(tempUrl);
            //in=query.getInputStreamByUrl(tempUrl);   //从网络获得输入流
            //new query().execute();
            if(in!=null){
                //System.out.println("here");
                //new FileUtils().saveInputStreamToFile(in, "", "gfdgf.txt");
                XMLParser xmlParser=new XMLParser();
                InputStreamReader reader=new InputStreamReader(in,"utf-8");        //最终目的获得一个InputSource对象用于传入形参
                JinShanContentHandler contentHandler=new JinShanContentHandler();
                xmlParser.parseJinShanXml(contentHandler, new InputSource(reader));
                wordValue=contentHandler.getWordValue();
                wordValue.setWord(searchedWord);
                //System.out.println("here");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(wordValue);
        return wordValue;
    }
}
