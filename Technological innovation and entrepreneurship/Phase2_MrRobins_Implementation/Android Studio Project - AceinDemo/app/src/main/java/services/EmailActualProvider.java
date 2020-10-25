package com.example.georgiosmoschovis.aceindemo.services;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * This class illustrates an Email Provider; using GMail Services.
 */
public class EmailActualProvider extends Activity implements EmailProviderService {

    Intent emailIntent;

    public void sendEmail(EmailMessage message) {
        Log.i("Send email", "");
        String s1 = message.getTo().getAddress();
        String[] TO = {""}; TO[0] = s1;
        //String[] CC = {""};
        emailIntent = new Intent(Intent.ACTION_SEND);

        //emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, message.getSubject());
        emailIntent.putExtra(Intent.EXTRA_TEXT, message.getBody());
        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent, "Select your preferred Mail Service."));
            //finish();
            Log.i("Finished", " sending email...");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public Intent getEmailIntent() {
        return emailIntent;
    }

}
