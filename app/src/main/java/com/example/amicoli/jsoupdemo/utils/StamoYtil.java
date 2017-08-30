package com.example.amicoli.jsoupdemo.utils;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Amicoli on 2017/8/30.
 * author 李丰华
 * qq:739574055
 * sina:wallamico
 */
class StampUtil{
  public static String dataToStamp(String s,int days) throws ParseException {
      Date date_intput,date_output;
      String date;
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      int data_int = Integer.parseInt(s);
      date = simpleDateFormat.format(new Date(data_int * 1000L));
      Date date1 = simpleDateFormat.parse(date,new ParsePosition(0));
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date1);
      calendar.add(calendar.DATE,-days);
      date_intput = calendar.getTime();
      String out = simpleDateFormat.format(date_intput);
      date_output = simpleDateFormat.parse(out);
      String result = String.valueOf(date_output.getTime()/1000);
      return "";
  }
}

