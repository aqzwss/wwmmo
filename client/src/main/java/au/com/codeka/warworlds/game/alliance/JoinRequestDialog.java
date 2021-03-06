package au.com.codeka.warworlds.game.alliance;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import au.com.codeka.warworlds.R;
import au.com.codeka.warworlds.StyledDialog;
import au.com.codeka.warworlds.model.AllianceManager;

public class JoinRequestDialog extends DialogFragment {
    private View mView;
    private int mAllianceID;

    public void setAllianceID(int allianceID) {
        mAllianceID = allianceID;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Activity activity = getActivity();
        LayoutInflater inflater = activity.getLayoutInflater();
        mView = inflater.inflate(R.layout.alliance_request_dlg, null);

        View amount = mView.findViewById(R.id.amount);
        amount.setVisibility(View.GONE);

        return new StyledDialog.Builder(getActivity())
                           .setView(mView)
                           .setTitle("Join Alliance")
                           .setPositiveButton("Join", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   onAllianceJoin();
                               }
                           })
                           .setNegativeButton("Cancel", null)
                           .create();
    }

    private void onAllianceJoin() {
        TextView message = (TextView) mView.findViewById(R.id.message);
        AllianceManager.i.requestJoin(mAllianceID, message.getText().toString());

        new StyledDialog.Builder(getActivity())
                        .setMessage("The request to join this alliance has been sent, you'll get a notification when your application is approved.")
                        .setNeutralButton("OK", null)
                        .create().show();

        dismiss();
    }
}

