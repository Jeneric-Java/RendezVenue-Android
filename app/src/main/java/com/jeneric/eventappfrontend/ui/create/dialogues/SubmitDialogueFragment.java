package com.jeneric.eventappfrontend.ui.create.dialogues;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.model.EventModel;

public class SubmitDialogueFragment extends DialogFragment {

    Context context;
    NavController navController;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);

        builder.setMessage(R.string.add_event_to_cal);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SubmitDialogueFragmentArgs args = SubmitDialogueFragmentArgs.fromBundle(getArguments());
                String eventTitle = args.getEventTitle();

                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, eventTitle)
                        .putExtra(CalendarContract.Events.DESCRIPTION, "Test")
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, "Test");

                context.startActivity(intent);
                navController.navigate(R.id.homeFragment);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                navController.navigate(R.id.homeFragment);

            }
        });
           return builder.create();
    }
}
