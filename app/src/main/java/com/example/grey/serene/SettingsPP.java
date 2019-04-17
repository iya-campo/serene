package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsPP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_pp);


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

        String pp = "<i>Effective as of April 22, 2019.</i>" +
                "<br>" +
                "<br>Serene is committed in respecting your privacy and data, and this explains how we (the researchers) collect, store, use, and analyze all data provided by our users." +
                "<br>" +
                "<br>We reserve the right to change and modify our Privacy Policy, but rest assured that users will be able to read the latest version. Continued usage of the application will indicate your acceptance of the modified version. We encourage you to check this page from time to time for the latest information and update regarding our data privacy activities. If you do not accept the privacy policy, and the privacy terms provided by Serene, we ask you to immediately exit the application and delete it." +
                "<br>" +
                "<br><b>1. Personal Data and Information Provision</b>" +
                "<br>" +
                "<br>When you sign up to use Serene, we collect personal data and information about you:" +
                "<br>" +
                "<br><b>A.</b> Full Name" +
                "<br><b>B.</b> Nickname" +
                "<br><b>C.</b> Email Address" +
                "<br><b>D.</b> Password" +
                "<br><b>E.</b> Age" +
                "<br>" +
                "<br>When you use the tools provided by Serene, we collect:" +
                "<br>" +
                "<br><b>A.</b> Journal Entries" +
                "<br><b>B.</b> Amount of Hours Slept" +
                "<br><b>C.</b> Food Intake" +
                "<br><b>D.</b> Medicine Intake" +
                "<br>" +
                "<br>The tools you allow or set:" +
                "<br>" +
                "<br><b>A.</b> Notifications" +
                "<br><b>B.</b> Alarms" +
                "<br>" +
                "<br><b>2. Personal Data and Information Usage</b>" +
                "<br>" +
                "<br>We use your personal data and information for the purpose of:" +
                "<br>" +
                "<br><b>A.</b> to verify you and to enable Serene to recognize you;" +
                "<br><b>B.</b> to analyze your input;" +
                "<br><b>C.</b> to provide the services you request, such as delivering an article analyzed from your journal entry;" +
                "<br><b>D.</b> to send you notices, information, alerts, and messages" +
                "<br><b>E.</b> tor scientific and academics research purposes; and" +
                "<br><b>F.</b> for any other purpose disclosed to you in this Privacy Policy or in any location from the application." +
                "<br>" +
                "<br>We will <b>NOT</b> use the personal data and information gathered from you for advertisement, exploitation, or any other purpose that is not disclosed in this section. We will NOT process the personal data and information you have given in a way that is not suited with the purposes for which it has been collected – for any new purpose, Serene will explicitly ask for your consent." +
                "<br>" +
                "<br><b>3. Personal Data and Information Rights and Security</b>" +
                "<br>" +
                "<br>You reserve the right to have your personal data and information modified and deleted through the Account Settings page or by contacting us at: jmlagmay@donbosco.edu.ph." +
                "<br>" +
                "<br>We take all reasonable measures to protect all collected Personal Data from loss, theft, misuse, unauthorized access, alteration, destruction, and disclosure." +
                "<br>" +
                "<br>Please take additional steps in securing your account by choosing and protecting your password effectively. Do understand that no security system is perfect, and thus, there is no guarantee that the application is absolutely secure.";

        TextView ppText = (TextView) findViewById(R.id.ppText);
        ppText.setText(Html.fromHtml(pp));
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
