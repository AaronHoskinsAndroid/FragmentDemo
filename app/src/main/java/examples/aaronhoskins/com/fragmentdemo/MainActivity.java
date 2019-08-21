package examples.aaronhoskins.com.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements BlueFragment.OnFragmentInteractionListener {
    BlueFragment blueFragment;
    BlueFragment blueFragmentTwo;
    RedFragment redFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blueFragment = BlueFragment.newInstance("Some data for init");
        blueFragmentTwo = BlueFragment.newInstance("Just another blue Fragment");
        redFragment = new RedFragment();
        fragmentManager = getSupportFragmentManager();

        //Use the fragment manager to commit a fragment transaction of adding
        //a fragment to the fragment backstack
        fragmentManager
                .beginTransaction()
                .add(R.id.frmBlueFragment, blueFragment)
                //Replace (remove and add at the same time) fragment
                //.replace(<LAYOUT XML ID>, <FRAGMENT TO REPLACE TOP FRAGMENT IN THAT PLACE>)
                .addToBackStack("BLUE_FRAGMENT_1")
                .commit();

        fragmentManager
                .beginTransaction()
                .add(R.id.frmBlueFragmentTwo, blueFragmentTwo)
                .addToBackStack("BLUE_FRAGMENT_2")
                .commit();

        fragmentManager
                .beginTransaction()
                .add(R.id.frmRedFragment, redFragment)
                .addToBackStack("RED_FRAGMENT_1")
                .commit();
    }

    @Override
    public void onDataSent(String dataToSend) {
        Toast.makeText(this, dataToSend, Toast.LENGTH_SHORT).show();
        redFragment.displayData(dataToSend);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPopOneFromStack:
                fragmentManager.popBackStack();
                break;
            case R.id.btnPopUptoTag:
                //fragmentManager.popBackStack("BLUE_FRAGMENT_1", 0);
                //Pop upto  and including
                fragmentManager.popBackStack("BLUE_FRAGMENT_1", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
            case R.id.btnRemoveInstance:
                fragmentManager.beginTransaction().remove(blueFragment).commit();
                break;
        }
    }
}
