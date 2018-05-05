package dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.hajar.firsttry.R;

/**
 * Created by Hajar on 10/04/2018.
 */

public class UpdateDialog extends AppCompatDialogFragment {

    private ConfirmationDialog.ConfirmationDialogListener listener;
    EditText updatedquantity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.updateconfirmation, null);


        builder.setView(view)
                .setTitle("Confirmation")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int quantity= Integer.valueOf(updatedquantity.getText().toString());

                    }
                });

        updatedquantity= view.findViewById(R.id.updatedquantity);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public interface UpdateDialogListener {
        void getUpdates(int quantity);
    }

}
