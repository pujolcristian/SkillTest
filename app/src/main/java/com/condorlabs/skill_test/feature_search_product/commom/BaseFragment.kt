package com.condorlabs.skill_test.feature_search_product.commom

import androidx.fragment.app.Fragment
import com.condorlabs.skill_test.feature_search_product.dialogs.DialogInfo
import com.condorlabs.skill_test.feature_search_product.dialogs.DialogLoading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    private var dialogLoading: DialogLoading? = null

    private var dialogInfo: DialogInfo? = null


    fun showDialogLoading() {
        if (dialogLoading == null) {
            dialogLoading = DialogLoading()
            dialogLoading?.isCancelable = false
            if (!dialogLoading?.isAdded!!) {
                dialogLoading?.show(requireActivity().supportFragmentManager, TAG_LOADING_DIALOG)
            }
        }
    }

    fun dismissDialogLoading() {
        if (dialogLoading != null) {
            if (dialogLoading?.isAdded!!) {
                dialogLoading?.dismiss()
                dialogLoading = null
            }
        }
    }

    fun showInfoDialog(
        message: String
    ) {
        if (dialogInfo == null) {
            dialogInfo = DialogInfo.newInstance(message)
            dialogInfo?.isCancelable = false
            if (!dialogInfo?.isAdded!!) {
                dialogInfo?.show(requireActivity().supportFragmentManager, TAG_INFO_DIALOG)
            }
        }
    }

    companion object {
        private const val TAG_LOADING_DIALOG = "loadingDialog"
        private const val TAG_INFO_DIALOG = "infoDialog"
    }

}