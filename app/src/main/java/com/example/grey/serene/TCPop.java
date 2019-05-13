package com.example.grey.serene;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class TCPop extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tc_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.6));

        String tc = "<i>Effective as of May 08, 2019.</i>" +
                "<br>" +
                "<br>Serene’s Terms and Conditions is an agreement between you, the user, and the researchers, the " +
                "people responsible in developing this application." +
                "<br>" +
                "<br><b>1. Terms and Conditions Acceptance</b>" +
                "<br>" +
                "<br>Please read this agreement carefully." +
                "<br>" +
                "<br>By creating, accessing, and using an account in Serene, you are fully accepting to be bound by the " +
                "terms of this Agreement. If you do <b>NOT</b> agree, you may exit, and delete this application." +
                "<br>" +
                "<br>We reserve the right to change and modify our Terms and Conditions, but rest assured that users " +
                "will be able to read the latest version. Continued usage of the application will indicate your " +
                "acceptance of the modified version. We encourage you to check the Terms and Conditions page " +
                "inside the application from time to time for the latest information and update regarding our terms " +
                "of use." +
                "<br>" +
                "<br><b>2. Terms and Conditions Disclaimer</b>" +
                "<br>" +
                "<br>The researchers are <b>NOT</b> medical care professionals, and Serene is <b>NOT</b> intended to replace " +
                "professional medical care, advice, or diagnosis. Interpretation made by the application’s licensed " +
                "contributor may vary from what you actually feel, and does not claim to be 100% accurate, as " +
                "words used by the user may be different from what they actually mean, and the system is not yet " +
                "perfect and is still undergoing development. Please do consult with a professional before taking " +
                "any actions that may greatly affect your, or any other person’s, health or safety. Never disregard " +
                "seeking professional help, for any question or concern related to your health." +
                "<br>" +
                "<br>If you think you have a medical emergency, please contact your local emergency hotline or go to " +
                "the nearest open emergency room immediately." +
                "<br>" +
                "<br>Serene is not a perfect system, and the research itself is only <b>EXPLORATORY</b>. The research’s " +
                "purpose is to find the possibility of using technology to lessen depression that people experience, " +
                "and the possibility of this application to assist mental health professionals in interpreting the words " +
                "used by people. Serene is <b>NOT</b> the ultimate solution in overcoming depression." +
                "<br>" +
                "<br><b>3. Terms and Conditions Eligibility</b>" +
                "<br>" +
                "<br>To use Serene, you are required to create and register an account, in which all the personal data " +
                "and information you have provided will be used in accordance with our Privacy Policy. You agree " +
                "that you will provide accurate and legitimate information to Serene, and that you will modify any " +
                "information after it changes." +
                "<br>" +
                "<br>If you are under 18, you must let your parent or guardian review our Terms and Conditions, and " +
                "have them agree and accept the aforementioned. We reserve the right to restrict the availability " +
                "of the application to certain users in under certain circumstances, in our sole discretion, which " +
                "shall be disclosed to you promptly after we modify the eligibility terms." +
                "<br>" +
                "<br>Serene can only read text written in English, and so we ask of you to PLEASE write your journal " +
                "entries in the language of English. We do not discriminate nor gate keep, however, Serene does " +
                "not have the capability to read in other languages yet, as this is part of our limitation in our research." +
                "<br>" +
                "<br><b>4. Serene Usage</b>" +
                "<br>" +
                "<br>Any content you provide through Serene will be presided by the Privacy Policy. Usage of the " +
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
                "<br>Our goal is to try to assist users lessen the depressive episode they may be feeling, or their " +
                "diagnosed depression by providing you with relevant insights, however, we do not guarantee " +
                "health-related improvements as the developers nor the application are not licensed medical " +
                "practitioners. Your use of the application is your sole responsibility and at your own risk. We make " +
                "no warranty of any kind as to the accuracy of the information we provide you through Serene, as " +
                "even legitimate insights can be debunked from time to time as new proof may arise, but we, in our " +
                "good faith and understanding, that all research and insight provided to you through Serene’s " +
                "articles were all true in time of writing and application developing. Any information to be " +
                "debunked by new science or research that is included in the application will be removed " +
                "IMMEDIATELY. Finally, you agree and understand that the application is not intended to replace, " +
                "match, or serve the exact same purpose as a medical service or scientific device."+
                "<br>" +
                "<br><b>5. Role and Responsibility</b>" +
                "<br>" +
                "<br>There will be two types of respondents in this research." +
                "<br>" +
                "<br><b>A.</b> User/Respondent" +
                "<br>You will be the one to use and experience the mobile application itself, write on the journal every " +
                "day for 14 days, and evaluate the system." +
                "<br>" +
                "<br><b>B.</b> Professional" +
                "<br>You will be the repository of the graphs containing the frequency of the words used by the users " +
                "on their journal entries, the interpreter of the aforementioned graphs, and you will evaluate the " +
                "system as well.";

        TextView tcContentText = (TextView) findViewById(R.id.tcContent);
        tcContentText.setText(Html.fromHtml(tc));
    }
}
