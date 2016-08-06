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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
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


public class MainActivity extends ActionBarActivity {
    private int currentApiVersion;
    private CallbackManager mCallbackManager;
    private SurfaceView sView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;


    CallbackManager callbackManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);



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

                    }

                    @Override
                    public void onCancel() {
                        Log.d("myTag", "Cancel");

                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("myTag", "Fail");

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
                    } else {
                        // User is signed out
                        Log.d("mytag", "onAuthStateChanged:signed_out");
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
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        //User user = dataSnapshot.getValue(User.class);
                        System.out.println("!!!!!");

                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("myTag", "getUser:onCancelled", databaseError.toException());
                    }
                });


        setFirstFirebase();

        sView = new SurfaceView(this);
        setContentView(sView);


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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Firebase alanRef = ref.child("users").child(user.getUid());
        User alan = new User(user.getDisplayName());

        alanRef.setValue(alan);

    }

    public class O1{
        private String name="hp";
        private int val=1;
        public O1(){}
        public O1(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getVal() {
            return val;
        }
    }

//    public class o2{
//        private String name="distance";
//        public o2(){}
//        public o2(String name) {
//            this.name = name;
//        }
//    }

    public class Offensive_Physical_Action{
        private String name="basic attack";
        O1 o1 = new O1("hp");
        O1 o2 = new O1("action");
        O1 o3 = new O1("distance");
        O1 o4 = new O1("resistance: fire");
        O1 o5 = new O1("resistance: water");
        O1 o6 = new O1("etc");
        O1 o7 = new O1("hp");
        O1 o8 = new O1("hp");
        O1 o9 = new O1("hp");
        O1 o10 = new O1("hp");
        //o2 distance = new o2("distance");

        public Offensive_Physical_Action() {}
        public Offensive_Physical_Action(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public class Meta_Action{
        private String name="basic attack";
        O1 o1 = new O1("hp");
        O1 o2 = new O1("action");
        O1 o3 = new O1("distance");
        O1 o4 = new O1("resistance: fire");
        O1 o5 = new O1("resistance: water");
        O1 o6 = new O1("etc");
        O1 o7 = new O1("hp");
        O1 o8 = new O1("hp");
        O1 o9 = new O1("hp");
        O1 o10 = new O1("hp");
        //o2 distance = new o2("distance");

        public Meta_Action() {}
        public Meta_Action(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public class Defensive_Physical_Action{
        private String name="basic attack";
        O1 o1 = new O1("hp");
        O1 o2 = new O1("action");
        O1 o3 = new O1("distance");
        O1 o4 = new O1("resistance: fire");
        O1 o5 = new O1("resistance: water");
        O1 o6 = new O1("etc");
        O1 o7 = new O1("hp");
        O1 o8 = new O1("hp");
        O1 o9 = new O1("hp");
        O1 o10 = new O1("hp");
        //o2 distance = new o2("distance");

        public Defensive_Physical_Action() {}
        public Defensive_Physical_Action(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public class Offensive_Cast_Action{
        private String name="basic attack";
        O1 o1 = new O1("hp");
        O1 o2 = new O1("action");
        O1 o3 = new O1("distance");
        O1 o4 = new O1("resistance: fire");
        O1 o5 = new O1("resistance: water");
        O1 o6 = new O1("etc");
        O1 o7 = new O1("hp");
        O1 o8 = new O1("hp");
        O1 o9 = new O1("hp");
        O1 o10 = new O1("hp");
        //o2 distance = new o2("distance");

        public Offensive_Cast_Action() {}
        public Offensive_Cast_Action(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public class Defensive_Cast_Action{
        private String name="basic attack";
        O1 o1 = new O1("hp");
        O1 o2 = new O1("action");
        O1 o3 = new O1("distance");
        O1 o4 = new O1("resistance: fire");
        O1 o5 = new O1("resistance: water");
        O1 o6 = new O1("etc");
        O1 o7 = new O1("hp");
        O1 o8 = new O1("hp");
        O1 o9 = new O1("hp");
        O1 o10 = new O1("hp");
        //o2 distance = new o2("distance");

        public Defensive_Cast_Action() {}
        public Defensive_Cast_Action(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public class Supportive_Physical_Action{
        private String name="basic attack";
        O1 o1 = new O1("hp");
        O1 o2 = new O1("action");
        O1 o3 = new O1("distance");
        O1 o4 = new O1("resistance: fire");
        O1 o5 = new O1("resistance: water");
        O1 o6 = new O1("etc");
        O1 o7 = new O1("hp");
        O1 o8 = new O1("hp");
        O1 o9 = new O1("hp");
        O1 o10 = new O1("hp");
        //o2 distance = new o2("distance");

        public Supportive_Physical_Action() {}
        public Supportive_Physical_Action(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public class Supportive_Cast_Action{
        private String name="basic attack";
        O1 o1 = new O1("hp");
        O1 o2 = new O1("action");
        O1 o3 = new O1("distance");
        O1 o4 = new O1("resistance: fire");
        O1 o5 = new O1("resistance: water");
        O1 o6 = new O1("etc");
        O1 o7 = new O1("hp");
        O1 o8 = new O1("hp");
        O1 o9 = new O1("hp");
        O1 o10 = new O1("hp");
        //o2 distance = new o2("distance");

        public Supportive_Cast_Action() {}
        public Supportive_Cast_Action(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public class Meta_Offense_Physical {
        private String name;
        Offensive_Physical_Action a1 = new Offensive_Physical_Action("basic attack");
        Offensive_Physical_Action a2 = new Offensive_Physical_Action("small attack");
        Offensive_Physical_Action a3 = new Offensive_Physical_Action("basic attack");
        Offensive_Physical_Action a4 = new Offensive_Physical_Action("basic attack");
        Offensive_Physical_Action a5 = new Offensive_Physical_Action("basic attack");
        Offensive_Physical_Action a6 = new Offensive_Physical_Action("basic attack");

        public Meta_Offense_Physical() {}
        public Meta_Offense_Physical(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public class Meta_Offense_Cast {
        private String name;
        Offensive_Cast_Action a1 = new Offensive_Cast_Action("basic attack");
        Offensive_Cast_Action a2 = new Offensive_Cast_Action("small attack");
        Offensive_Cast_Action a3 = new Offensive_Cast_Action("basic attack");
        Offensive_Cast_Action a4 = new Offensive_Cast_Action("basic attack");
        Offensive_Cast_Action a5 = new Offensive_Cast_Action("basic attack");
        Offensive_Cast_Action a6 = new Offensive_Cast_Action("basic attack");
        Offensive_Cast_Action a7 = new Offensive_Cast_Action("basic attack");
        Offensive_Cast_Action a8 = new Offensive_Cast_Action("basic attack");
        Offensive_Cast_Action a9 = new Offensive_Cast_Action("basic attack");

        public Meta_Offense_Cast() {}
        public Meta_Offense_Cast(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public class Meta_Defense_Cast {
        private String name;
        Defensive_Cast_Action a1 = new Defensive_Cast_Action("basic attack");


        public Meta_Defense_Cast() {}
        public Meta_Defense_Cast(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public class Meta_Decision{
        private String name;
        Meta_Action a1 = new Meta_Action("Offensive Physical");
        Meta_Action a2 = new Meta_Action("Offensive Cast");
        Meta_Action a3 = new Meta_Action("Defensive Physical");
        Meta_Action a4 = new Meta_Action("Defensive Cast");
        Meta_Action a5 = new Meta_Action("Supportive Physical");
        Meta_Action a6 = new Meta_Action("Supportive Cast");



        public Meta_Decision() {}
        public Meta_Decision(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public class Meta_Defense_Physical {
        private String name;
        Defensive_Physical_Action a1 = new Defensive_Physical_Action("dodge");
        Defensive_Physical_Action a2 = new Defensive_Physical_Action("block");
        Defensive_Physical_Action a3 = new Defensive_Physical_Action("jump");
        Defensive_Physical_Action a4 = new Defensive_Physical_Action("run");


        public Meta_Defense_Physical() {}
        public Meta_Defense_Physical(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public class Meta_Support_Physical {
        private String name;
        Supportive_Physical_Action a1 = new Supportive_Physical_Action("dodge");
        Supportive_Physical_Action a2 = new Supportive_Physical_Action("block");
        Supportive_Physical_Action a3 = new Supportive_Physical_Action("jump");
        Supportive_Physical_Action a4 = new Supportive_Physical_Action("run");


        public Meta_Support_Physical() {}
        public Meta_Support_Physical(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public class Meta_Support_Cast {
        private String name;
        Supportive_Cast_Action a1 = new Supportive_Cast_Action("dodge");
        Supportive_Cast_Action a2 = new Supportive_Cast_Action("block");
        Supportive_Cast_Action a3 = new Supportive_Cast_Action("jump");
        Supportive_Cast_Action a4 = new Supportive_Cast_Action("run");


        public Meta_Support_Cast() {}
        public Meta_Support_Cast(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public class User {
        private String fullName;
        private int g=0;

        Meta_Offense_Physical offense_physical = new Meta_Offense_Physical("Offense Physical");
        Meta_Defense_Physical defense_physical  = new Meta_Defense_Physical("Defense Physical");
        Meta_Support_Physical support_physical  = new Meta_Support_Physical("Support Physical");
        Meta_Offense_Cast offense_cast = new Meta_Offense_Cast("Offense Cast");
        Meta_Defense_Cast defense_cast = new Meta_Defense_Cast("Defense Cast");
        Meta_Support_Cast support_cast = new Meta_Support_Cast("Support Cast");
        Meta_Decision meta_decision = new Meta_Decision("Meta_choices");


        public User() {}
        public User(String fullName) {
            this.fullName = fullName;
        }

        public String getFullName() {
            return fullName;
        }


    }


}
