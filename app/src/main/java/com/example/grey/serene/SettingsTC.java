package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SettingsTC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_tc);


        //Header Buttons
        Button profileButton = (Button) findViewById(R.id.profileButton);

        //Return Button
        Button settingsTitleButton = (Button) findViewById(R.id.settingsTitleButton);

        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(showProfile);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }

        });

        settingsTitleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }

        });

        String tc = "<i>Effective as of April 22, 2019.</i>" +
                "<br>" +
                "<br>Serene’s Terms and Conditions is an agreement between you, the user, and the researchers, the" +
                "people responsible in developing this application." +
                "<br>" +
                "<br><b>1. Terms and Conditions Acceptance</b>" +
                "<br>" +
                "<br>Please read this agreement carefully." +
                "<br>" +
                "<br>By creating, accessing, and using an account in Serene, you are fully accepting to be bound by" +
                "the terms of this Agreement. If you do <b>NOT</b> agree, you may exit, and delete this application." +
                "We reserve the right to change and modify our Terms and Conditions, but rest assured that users" +
                "will be able to read the latest version. Continued usage of the application will indicate your" +
                "acceptance of the modified version. We encourage you to check this page from time to time for" +
                "the latest information and update regarding our terms of use." +
                "<br>" +
                "<br><b>2. Terms and Conditions Disclaimer</b>" +
                "<br>" +
                "<br>The researchers are <b>NOT</b> medical care professionals, and Serene is <b>NOT</b> intended to replace" +
                "professional medical care, advice, or diagnosis. Please do consult with a professional before" +
                "taking any actions that may greatly affect your, or any other person’s, health or safety. Never" +
                "disregard seeking professional help, for any question or concern related to your health." +
                "If you think you have a medical emergency, please contact your local emergency hotline or go to" +
                "the nearest open emergency room immediately." +
                "<br>" +
                "<br><b>3. Terms and Conditions Eligibility</b>" +
                "<br>" +
                "<br>To use Serene, you are required to create and register an account, in which all the personal data" +
                "and information you have provided will be used in accordance with our Privacy Policy. You agree" +
                "that you will provide accurate and legitimate information to Serene, and that you will modify any" +
                "information after it changes." +
                "If you are under 18, you must let your parent or guardian review our Terms and Conditions, and" +
                "have them agree and accept the aforementioned. We reserve the right to restrict the availability" +
                "of the application to certain users in under certain circumstances, in our sole discretion, which" +
                "shall be disclosed to you promptly after we modify the eligibility terms." +
                "<br>" +
                "<br><b>4. Serene Usage</b>" +
                "<br>" +
                "<br>Any content you provide through Serene will be presided by the Privacy Policy. Usage of the" +
                "application denotes you understand that you shall <b>NOT</b>:" +
                "<br>" +
                "<br><b>A.</b> modify, decompile, disassemble, nor copy the application;" +
                "<br><b>B.</b> copy, alter, delete works of the application without the consent of the developers;" +
                "<br><b>C.</b> permit others to use your account;" +
                "<br><b>D.</b> use your account to advertise goods or services;" +
                "<br><b>E.</b> use your account to engage in any illegal conduct." +
                "<br>" +
                "<br>Any forbidden act or misuse shall immediately terminate your right to use Serene." +
                "<br>" +
                "<br>Our goal is to help users overcome depression by providing you with relevant insights, however," +
                "we do not guarantee health-related improvements as the developers nor the application are not" +
                "licensed medical practitioners. Your use of the application is your sole responsibility and at your" +
                "own risk. We make no warranty of any kind as to the accuracy of the information we provide you" +
                "through Serene, and you agree and understand that the application is not intended to replace," +
                "match, or serve the exact same purpose as a medical service or scientific device.";

        TextView tcText = (TextView) findViewById(R.id.tcText);
        tcText.setText(Html.fromHtml(tc));
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
