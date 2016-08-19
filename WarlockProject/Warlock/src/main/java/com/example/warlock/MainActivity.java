package com.example.warlock;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.opengl.GLSurfaceView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    private int currentApiVersion;
    private CallbackManager mCallbackManager;
    private SurfaceView sView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private final int NUMBER_OF_UNITS=2;


    CallbackManager callbackManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_main);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);



        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email");

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("myTag", "Success");
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        System.out.print("SUCCESS");
                    }

                    @Override
                    public void onCancel() {
                        Log.d("myTag", "Cancel");
                        System.out.print("CANCEL");

                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("myTag", "Fail");
                        System.out.print("FAIL");
                    }
                });
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        Log.d("mytag", "onAuthStateChanged:signed_in:" + user.getUid());
                        System.out.println("SIGNED IN");

                    } else {
                        // User is signed out
                        Log.d("mytag", "onAuthStateChanged:signed_out");
                        System.out.println("SIGNED OUT");

                    }
                    // ...
                }
            };



        if (savedInstanceState == null) {
            //getSupportFragmentManager().beginTransaction()
                    //.add(R.id.container, new PlaceholderFragment())
                    //.commit();
        }

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LOW_PROFILE;
        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }



        ////////////////////////
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = user.getUid();

        System.out.println("userID" + userId);

        setFirstFirebase();


        sView = new SurfaceView(this);
        setContentView(sView);

        Firebase ref = new Firebase("https://arms-bb507.firebaseio.com");


        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
//                        System.out.println("VALUE AT:: "+dataSnapshot.child("player1").child("ma").child("0").child("o").child("0").getValue(Observation.class).val);
                        //User x = dataSnapshot.getValue(User.class);
//                        System.out.println("FULL NAME IS " + x.getFullName());
                        //System.out.println("Offense physical is called:  " + x.player1.offense_physical.getName());

                        System.out.println(sView.mRenderer.aaron.off_a[0].o[0]);

                            sView.mRenderer.user_information.fullName=dataSnapshot.child("fullname").getValue(String.class);
                            sView.mRenderer.user_information.g=dataSnapshot.child("g").getValue(int.class);

                        for (int i=0;i<NUMBER_OF_UNITS;i++){
                            sView.mRenderer.user_information.playa[i].name=dataSnapshot.child("player1").child("name").getValue(String.class);
                            for (int k=0;k<10;k++){
                                sView.mRenderer.user_information.playa[k].ma.name=dataSnapshot.child("player1").child("name").getValue(String.class);
                            }

                            //sView.mRenderer.aaron.off_a[0].o[i]=dataSnapshot.child("player1").child("ma").child("0").child("a").child("0").child("o").child(Integer.toString(i)).getValue(Observation.class).val;
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("myTag", "getUser:onCancelled", databaseError.toException());
                    }
                });


    }


    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        if (mAuthListener != null) {
            mAuth.addAuthStateListener(mAuthListener);
        }
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("myTag", "handleFacebookAccessToken:" + token);

        System.out.println("Handling facebook login"+token.getUserId());

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("myTag", "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("myTag", "signInWithCredential", task.getException());
                        }

                        // ...
                    }
                });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {




            View rootView = inflater.inflate(R.layout.fragment_main, container, false);





            return rootView;
        }
    }

    public void setFirstFirebase(){

        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://arms-bb507.firebaseio.com");
        if (ref.getAuth()==null){
            System.out.print("got null");
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Firebase alanRef = ref.child("users").child(user.getUid());
        User alan = new User(user.getDisplayName());

        alanRef.setValue(alan);

    }

    public static class Observation{
        public String name="hp";
        public int val=1;
        public Observation(){}
        public Observation(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    public static class Action{
        private String name="basic attack";
        public Observation o[] = new Observation[10];

        public Action() {
            for (int i =0;i<10;i++){
                o[i]=new Observation("hp");
            }
        }
        public Action(String name) {
            for (int i =0;i<10;i++){
                o[i]=new Observation("hp");
            }
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }




    public static class Meta_Action {
        private String name;
        public Action a[] = new Action[10];
        public Action t[] = new Action[4];
        public Observation o[] = new Observation[10];


        public Meta_Action() {
            for (int i =0;i<10;i++){
                a[i]=new Action("hp");
                o[i]=new Observation("hp");
            }
            for (int i =0;i<4;i++){
                t[i]=new Action("hp");
            }
        }
        public Meta_Action(String name) {
            for (int i =0;i<10;i++){
                a[i]=new Action("hp");
                o[i]=new Observation("hp");
            }
            for (int i =0;i<4;i++){
                t[i]=new Action("hp");
            }
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }


    public static class Player{
        private String name;

        public Meta_Action ma[] = new Meta_Action[3];

        public Player(){
            for (int i =0;i<3;i++){
                ma[i]=new Meta_Action("hp");
            }
        }
        public Player(String name){
            for (int i =0;i<3;i++){
                ma[i]=new Meta_Action("hp");
            }
            this.name = name;
        }
        public String getName(){return name;}
    }


    @IgnoreExtraProperties
    public static class User {
        private String fullName;
        private int g;

        public Player playa[] = new Player[NUMBER_OF_UNITS];

        public User() {

            for (int i=0; i<NUMBER_OF_UNITS;i++){
                playa[i] = new Player("Aaron");
            }

        }
        public User(String fullName) {
            this.fullName = fullName;
            this.g=0;
            for (int i=0; i<NUMBER_OF_UNITS;i++){
                playa[i] = new Player("Aaron");
            }

        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName=fullName;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g=g;
        }

    }


}
