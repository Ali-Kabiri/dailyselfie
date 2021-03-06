package com.mpandg.dailyselfie.util;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.mpandg.dailyselfie.R;
import com.mpandg.dailyselfie.model.Category;
import com.mpandg.dailyselfie.model.Photo;

/**
 * Created by Ali Kabiri on 8/17/2016.
 * Find me here: ali@kabiri.org
 */
public class DeleteCategoryDialogFragment extends DialogFragment {

    public static final String TAG = "delete_dialog";

    public static DeleteCategoryDialogFragment newInstance (Category category, int position) {

        DeleteCategoryDialogFragment fragment = new DeleteCategoryDialogFragment();
        // create a new bundle object and put the photo in it.
        Bundle args = new Bundle();
        args.putParcelable(Category.KEY, category);
        args.putInt(Category.POSITION_KEY, position);
        // put the bundle into the fragment.
        fragment.setArguments(args);
        // return the configured fragment.
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setTitle(R.string.title_delete_category)
                .setPositiveButton(R.string.alert_dialog_yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                // user wants to delete the photo.
                                // tell the parent activity to delete the photo using the interface.
                                DeleteListener listener = (DeleteListener) getActivity();
                                // get the photo in the bundle.
                                Category category = getArguments().getParcelable(Category.KEY);
                                listener.onDeleteCategory(category, getArguments().getInt(Category.POSITION_KEY));
                            }
                        })
                .setNegativeButton(R.string.alert_dialog_no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                // user does not want to delete the category.
                                // pass.
                            }
                        })
                .create();
    }

    public interface DeleteListener{
        void onDeleteCategory(Category category, int position);
    }
}
