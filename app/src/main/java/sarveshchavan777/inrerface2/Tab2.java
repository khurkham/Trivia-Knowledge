package sarveshchavan777.inrerface2;


import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import info.hoang8f.widget.FButton;

public class Tab2 extends Fragment {

    ListView lv;
    //Context context;
    MediaPlayer ring;
    DemoHelperClass demoHelperClass;
    String x;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2, container, false);
        lv = (ListView) v.findViewById(R.id.listview10);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Integer[] img = {R.drawable.international_music, R.drawable.reset, R.drawable.google_play,
                R.drawable.facebook, R.drawable.share, R.drawable.error, R.drawable.about, R.drawable.shop};

        final String[] s = {"Sound", "Reset  Game", "Rate  on  PlayStore", "Like/join Us on Fb", "Share", "Report bug/contact us", "About ", "Shop"};

        mylistAdapter adapter = new mylistAdapter(getActivity(), img, s);
        lv.setAdapter(adapter);

        demoHelperClass = new DemoHelperClass(getActivity());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Toast.makeText(getActivity(), "U clicked" + s[position], Toast.LENGTH_SHORT).show();*/
                if (position == 0) {
                    final List list = demoHelperClass.getSound();
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.sound);
                    final ImageView soundImage = (ImageView) dialog.findViewById(R.id.sound);
                    FButton canelSound = (FButton) dialog.findViewById(R.id.cancelSound);
                    TextView soundText = (TextView) dialog.findViewById(R.id.soundText);
                    final Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/shablagooital.ttf");
                    soundText.setTypeface(typeface);

                    canelSound.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    if (list != null) {
                        if (list.size() % 2 == 0) {
                            soundImage.setImageResource(R.drawable.on);
                        } else {
                            soundImage.setImageResource(R.drawable.off);
                        }
                    }
                    soundImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            demoHelperClass.insertSound(1);
                            if (list != null) {
                                final List list2 = demoHelperClass.getSound();
                                if (list2.size() % 2 == 0) {
                                    soundImage.setImageResource(R.drawable.on);
                                    // Toast.makeText(getActivity(),"Sound: ON",Toast.LENGTH_LONG).show();
                                    // dialog.dismiss();
                                } else {
                                    soundImage.setImageResource(R.drawable.off);
                                    //   Toast.makeText(getActivity(),"Sound: OFF",Toast.LENGTH_LONG).show();
                                    //   dialog.dismiss();
                                }
                            }

                        }
                    });
                    dialog.show();
                }


                if (position == 1) {
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.setContentView(R.layout.resetgamedialog);
                    dialog.setCancelable(true);
                    TextView attention = (TextView) dialog.findViewById(R.id.attentiontext);
                    TextView resetText = (TextView) dialog.findViewById(R.id.resetText);
                    final Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/shablagooital.ttf");
                    attention.setTypeface(typeface);
                    resetText.setTypeface(typeface);
                    Button cancelreset = (Button) dialog.findViewById(R.id.cancelreset);
                    cancelreset.setTypeface(typeface);
                    cancelreset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    Button okreset = (Button) dialog.findViewById(R.id.okrest);
                    okreset.setTypeface(typeface);
                    okreset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            demoHelperClass.deleteAllRecord();
                            Toast toast = Toast.makeText(getActivity(), "\tGame restored successfully" + " ", Toast.LENGTH_LONG);
                            toast.getView().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.darkpink));
                            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                            v.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                            v.setTypeface(typeface);
                            v.setTextSize(16);
                            toast.show();
                            dialog.dismiss();

                            if (checkSound()) {
                                 ring = MediaPlayer.create(getActivity(), R.raw.gameaudio2);
                                ring.start();
                            }
                        }
                    });
                    dialog.show();
                }

                if (position == 2) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=sarveshchavan777.inrerface2"));
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=sarveshchavan777.inrerface2")));
                    }
                }
                if (position == 3) {
                    Intent intent = new Intent(getActivity(), FaceBookLogin.class);
                    startActivity(intent);
                   /* final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.facebooklogin);
                    dialog.setCancelable(false);
                    FButton fButton=(FButton)dialog.findViewById(R.id.cancelButton);
                    LoginButton loginButton = (LoginButton) dialog.findViewById(R.id.login_button);


                    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            x = loginResult.getAccessToken().getToken();
                            idOfUser = loginResult.getAccessToken().getUserId();
                            abc="1";

                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(getActivity(),"canceled",Toast.LENGTH_LONG).show();
                            abc="0";
                        }

                        @Override
                        public void onError(FacebookException error) {
                            Toast.makeText(getActivity(),"some error occurred"+error,Toast.LENGTH_LONG).show();
                            abc="2";
                        }
                    });


                    fButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });


                    dialog.show();*/


                    /*Intent facebookIntent = getOpenFacebookIntent(getActivity(),"1357616140975878");
                    startActivity(facebookIntent);*/
                }

                if (position == 4) {
                    try {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, "Trivia Knowledge");
                        String stringBody = "";
                        stringBody = stringBody + "https://play.google.com/store/apps/details?id=sarveshchavan777.inrerface2";
                        i.putExtra(Intent.EXTRA_TEXT, stringBody);
                        startActivity(Intent.createChooser(i, "Share using"));
                    } catch (Exception e) {
                        //e.toString();
                    }
                }
                if (position == 5) {
                    try {
                        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                        sendIntent.setType("plain/text");
                        sendIntent.setData(Uri.parse("charetakergames@gmail.com"));
                        sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"charetakergames@gmail.com"});
                        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Report Bug / Suggestions");
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Device:" + getDeviceName() + "\n" + "Message:" + "\n\n");
                        startActivity(sendIntent);
                    } catch (Exception e) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "charetakergames@gmail.com"));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Report Bug / Suggestions");
                        intent.putExtra(Intent.EXTRA_TEXT, "Device:" + getDeviceName() + "\n" + "Message:" + "\n\n");
                        startActivity(intent);
                    }
                }
                if (position == 6) {
                    Intent intent = new Intent(getActivity(), about.class);
                    startActivity(intent);
                }
                if (position == 7) {
                    Intent intent = new Intent(getActivity(), InAppPurchase.class);
                    intent.putExtra("key",20000);
                    startActivity(intent);

                }
            }
        });
    }

    /*public static Intent getOpenFacebookIntent(Context context, String facebookId) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/" + facebookId)); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/Trivia-knowledge-1357616140975878/")); //catches and opens a url to the desired page
        }
    }*/


    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if(abc.equals("1")){
            Toast.makeText(getActivity(),"login sussefully",Toast.LENGTH_LONG).show();
        } else if (abc.equals("0")) {
            //do nothing
        }else {
            //do nothing
        }
    }*/

    public Boolean checkSound() {
        List list = demoHelperClass.getSound();
        if (list != null) {
            if (list.size() % 2 == 0) {
                //  Toast.makeText(getActivity(),"true",Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(ring!=null) {
            ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {

                    ring.release();
                }
            });
        }
    }
}
