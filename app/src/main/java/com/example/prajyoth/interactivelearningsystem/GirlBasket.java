package com.example.prajyoth.interactivelearningsystem;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class GirlBasket extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextView tv1;
    TextToSpeech t1;
    String text1 = "A sample string";
    String text;
    SpeechRecognizer s1;
    Intent intent;
    String current;
    String result;
    List<String> Story = Arrays.asList("She came across a dead end. She got confused as to head left or right. Can you help her decide where to go?", "The girl learnt a lesson that day. One must always speak the truth");
    List<String> Story_left = Arrays.asList("A monkey appears on the tree. He asks the girl what is in the basket? Should the girl say the truth she has apples? Or should she lie saying she has oranges?","The monkey believed what she was saying and went away", "The monkey caught her lying! He stole the apples in the basket and ran away!");
    List<String> Story_right = Arrays.asList("A wild bear hides among the bushes. He says what are you carrying in the basket little girl! Should the girl say the truth she has apples? Or should she lie saying she has oranges?","The bear knew she was telling the truth so he left her alone and went away","The bear caught her lying! and stole all the apples and ran away!");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl_basket);
        tv1 = (TextView) findViewById(R.id.GB_Text1);
        text = tv1.getText().toString();

        t1 = new TextToSpeech(GirlBasket.this, this);
        s1 = SpeechRecognizer.createSpeechRecognizer(GirlBasket.this);

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getApplication().getPackageName());

        t1.setOnUtteranceProgressListener(mProgressListener);
        s1.setRecognitionListener(rlistener);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    } */


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ttsGreater21(text, "ut1");
            } else {
                ttsUnder20(text, "ut1");
            }
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            t1.setLanguage(Locale.UK);
        }
    }

    public void myClickHandler(View target) {
        t1.speak(text1, TextToSpeech.QUEUE_FLUSH, null, null);
    }


    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text, String utid) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, utid);
        t1.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text, String utid) {
        t1.speak(text, TextToSpeech.QUEUE_FLUSH, null, utid);
    }

    private UtteranceProgressListener mProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {
        } // Do nothing

        @Override
        public void onError(String utteranceId) {
        } // Do nothing.

        @Override
        public void onDone(String utteranceId) {
            if (utteranceId.equals("ut1")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText(Story.get(0));
                        current = "Break1";
                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ttsGreater21(Story.get(0), "ut2");
                } else {
                    ttsUnder20(Story.get(0), "ut2");
                }

            }

            if (utteranceId.equals("ut2")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        s1.startListening(intent);
                    }
                });
            }

            if (utteranceId.equals("ut3")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result.equals("left")) {
                            tv1.setText(Story_left.get(0));
                            current = "Break2";
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ttsGreater21(Story_left.get(0), "ut4");
                            } else {
                                ttsUnder20(Story_left.get(0), "ut4");
                            }
                        }
                        else {
                            tv1.setText(Story_right.get(0));
                            current = "Break2";
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ttsGreater21(Story_right.get(0), "ut4");
                            } else {
                                ttsUnder20(Story_right.get(0), "ut4");
                            }
                        }
                    }
                });

            }
        }
    };

    private RecognitionListener rlistener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {
            Log.println(Log.ERROR,"there was an error","there was an error");
            if (error == 7) {
                return;
            }


        }

        @Override
        public void onResults(Bundle results) {
            if(current.equals("Break1"))
            {
                ArrayList<String> arr = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                result =arr.get(0);
                if(result == null)
                {
                    //TODO: recall speech
                }
                else if (result.equals("left"))
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText("Left, that is a good choice. She decided to move Right.");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ttsGreater21("Left, that is a good choice. She decided to move Right.", "ut3");
                            } else {
                                ttsUnder20("Left, that is a good choice. She decided to move Right.", "ut3");
                            }
                        }
                    });
                }
                else if (result.equals("right"))
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText("Right, interesting. She chose to move Right.");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ttsGreater21("Right, interesting. She chose to move Right.", "ut3");
                            } else {
                                ttsUnder20("Right, interesting. She chose to move Right.", "ut3");
                            }
                        }
                    });
                }
                else
                {
                    result = "left";
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText("Oh,i did not catch that. Lets say she decided to go Left.");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ttsGreater21("Oh,i did not catch that. Lets say she decided to go Left.", "ut3");
                            } else {
                                ttsUnder20("Oh,i did not catch that. Lets say she decided to go Left.", "ut3");
                            }
                        }
                    });
                }

            }

        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    };


}




