package com.mapswithme.maps.editor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mapswithme.maps.R;
import com.mapswithme.maps.base.BaseMwmDialogFragment;

public class SelectTimepickerModeFragment extends BaseMwmDialogFragment
    implements RadioGroup.OnCheckedChangeListener
{
    private SelectTimepickerModeListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_select_timepicker, null);

        builder.setView(view);

        return builder.create();
    }

    public interface SelectTimepickerModeListener
    {
        void setTimepickerMode(int mode);
    }

    public static void select(Context context, FragmentManager manager)
    {
        final SelectTimepickerModeFragment fragment =
                (SelectTimepickerModeFragment) Fragment.instantiate(context, SelectTimepickerModeFragment.class.getName(), null);
        fragment.show(manager, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup radioGroup = view.findViewById(R.id.timepicker_modes);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (SelectTimepickerModeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + "should implement SelectTimepickerModeListener");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int id)
    {
        switch (id)
        {
            case R.id.timepicker_clock:
                mListener.setTimepickerMode(0);
                break;
            case R.id.timepicker_spinner:
                mListener.setTimepickerMode(1);
                break;
        }
    }
}

