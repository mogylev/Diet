package com.mohylov.diet.ui.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mohylov.diet.R
import com.mohylov.diet.databinding.MealAmountSheetLayoutBinding
import com.mohylov.diet.ui.presentation.base.viewBinding

class AmountBottomSheetDialog : BottomSheetDialogFragment() {

    companion object {
        const val resultInfoKey = "amountInfoKey"
        private const val productIdKey = "productId"
        private const val defaultAmountKey = "defaultAmount"

        fun instance(productId: Long, defaultAmount: Int): AmountBottomSheetDialog {
            val bundle = bundleOf(productIdKey to productId, defaultAmountKey to defaultAmount)
            return AmountBottomSheetDialog().also { it.arguments = bundle }
        }
    }

    private val productId: Long by lazy {
        arguments?.getLong(productIdKey) ?: 0
    }

    private val defaultAmount: Int by lazy {
        arguments?.getInt(defaultAmountKey) ?: 0
    }

    private val binding: MealAmountSheetLayoutBinding by viewBinding(MealAmountSheetLayoutBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.meal_amount_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.amountLayout.editText?.setText(defaultAmount.toString())
        binding.addAmountButton.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                resultInfoKey, bundleOf(
                    resultInfoKey to AmountInfo(amount = getAmount(), productId = productId)
                )
            )
            dismiss()
        }
    }

    private fun getAmount(): Int {
        return binding.amountLayout.editText?.text.toString().toIntOrNull() ?: defaultAmount
    }
}