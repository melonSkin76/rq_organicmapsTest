package com.mapswithme.maps.editor;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mapswithme.maps.R;
import com.mapswithme.maps.base.BaseMwmDialogFragment;


public class SelectTimepickerModeFragment extends BaseMwmDialogFragment
{

    public interface OnSelectTimepickerModeListener
    {
        void onSetTimepickerMode(int mode);
    }

    public static void select(Context context, FragmentManager manager)
    {
        final SelectTimepickerModeFragment fragment =
                (SelectTimepickerModeFragment) Fragment.instantiate(context, SelectTimepickerModeFragment.class.getName(), null);
        fragment.show(manager, null);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {

        final androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(getActivity())
                                                            .setTitle(R.string.timepicker_select)
                                                            .setItems(R.array.timepicker_mode, new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dismiss();

                                                                    if (getParentFragment() instanceof OnSelectTimepickerModeListener)
                                                                        ((SelectTimepickerModeFragment.OnSelectTimepickerModeListener) getParentFragment()).onSetTimepickerMode(which);
                                                                }
                                                            })
                                                            .setCancelable(true)
                                                            .create();
        return dialog;
    }
}

