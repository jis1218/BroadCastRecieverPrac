package com.project.broadcastprac;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 1. 등록된 리시버를 통해 브로드캐스트 메시지가 intent에 담겨 넘어온다

        if("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            Object[] msgs = (Object[]) bundle.get("pdus");

            SmsMessage smsMessage[] = new SmsMessage[msgs.length];
            for (int i = 0; i < msgs.length; i++) {
                smsMessage[i] = SmsMessage.createFromPdu((byte[]) msgs[i]);
                Log.d("smsMessage", smsMessage[i].getMessageBody());
                String msg = smsMessage[i].getMessageBody();
                if(smsMessage[i].getMessageBody().startsWith("[비틀즈]")){
                    String verification_num = msg.split(":")[1];
                    Log.d("sms인증번호는", verification_num.trim());
                }
            }
        }
    }
}
