package edu.mgrace31gatech.donationtracker.app.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.DonationModel;

/**
 * A fragment representing a single DataItem detail screen.
 * This fragment is either contained in a {@link InventoryListActivity}
 * in two-pane mode (on tablets) or a {@link DonationDetailActivity}
 * on handsets.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 * Alayna Panlilio, Julia Tang
 */
public class DonationDetailFragment extends Fragment {

     /**
      * The fragment argument representing the item ID that this
      * fragment represents.
      */
     public static final String ARG_DON_ID = "donation_key";

     /**
      * The dummy content this fragment is presenting.
      */
     private Donation mDonation;

    @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         Bundle arguments = Objects.requireNonNull(getArguments());
         if (arguments.containsKey(ARG_DON_ID)) {
             //Load the dummy content specified by the fragment
             //arguments. In a read-world scenario, use a Loader
             //to load content from a content provider.

             Bundle arguments1 = (getArguments());
             int donation_id = arguments1.getInt(ARG_DON_ID);
             Log.d("MYAPP", "Start details for: " + donation_id);
             DonationModel model = DonationModel.getInstance();
             mDonation = model.findDonationById(donation_id);
             if(mDonation == null) {
                 Log.d("MYAPP", "Null Donation: " + donation_id);
             } else {
                 Log.d("MYAPP", "Not Null Donation: " + donation_id);
             }
         }
     }

     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.donation_detail, container, false);
         Log.d("MYAPP", "Getting ready to set data");
         if(mDonation != null) {
             ((TextView) rootView.findViewById(R.id.nameDonation)).setText(mDonation.getName());
             ((TextView) rootView.findViewById(R.id.shortDescription)).setText(
                     mDonation.getShortDescription());
             ((TextView) rootView.findViewById(R.id.fullDescription)).setText(
                     mDonation.getLongDescription());
             ((TextView) rootView.findViewById(R.id.value)).setText(String.valueOf(
                     mDonation.getValue()));
             ((TextView) rootView.findViewById(R.id.category)).setText(mDonation.getCategory());
             ((TextView) rootView.findViewById(R.id.date)).setText(mDonation.getDate());
             ((TextView) rootView.findViewById(R.id.time)).setText(mDonation.getTime());
         }
         return rootView;
     }
}
