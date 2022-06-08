package com.example.mywallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mywallet.MyWalletViewModel.MyWalletViewModelFactory
import com.example.mywallet.databinding.AddItemFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddItemFragment : Fragment() {

    private var _binding: AddItemFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MyWalletViewModel by activityViewModels {
        MyWalletViewModelFactory(
            (activity?.application as TransactionApplication).database.transactionDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddItemFragmentBinding.inflate(inflater, container, false)


        val inOut = arrayListOf(R.string.input, R.string.output)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_layout, inOut)
        binding.inputEdit.setAdapter(arrayAdapter)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nowDate = SimpleDateFormat("EE, dd/MM/yyyy hh:mm:ss a", Locale.getDefault())
        val dateNow = nowDate.format(Date())



        binding.apply {
            dateEdit.setText(dateNow)
            saveButton.setOnClickListener { addNewTransaction() }
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.titleEdit.text.toString(),
            binding.inputEdit.text.toString(),
            binding.dateEdit.text.toString(),
            binding.amountEdit.text.toString(),
            binding.balanceEdit.text.toString(),
            binding.noteEdit.text.toString()
        )
    }

    private fun addNewTransaction() {
        if (isEntryValid() and save()) {
            viewModel.addNewTransaction(
                binding.titleEdit.text.toString(),
                binding.inputEdit.text.toString(),
                binding.dateEdit.text.toString(),
                binding.amountEdit.text.toString(),
                binding.balanceEdit.text.toString(),
                binding.noteEdit.text.toString()
            )
        }
    }


    private fun save(): Boolean {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.save_title_dialog))
            .setMessage(resources.getString(R.string.supporting_text_dialog))
            .setNegativeButton(resources.getString(R.string.no)) { _, _ ->
                return@setNegativeButton
                // Respond to negative button press
            }
            .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
            .show()
        return true
    }

    override fun onResume() {
        super.onResume()
        val inOut = resources.getStringArray(R.array.input_output)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_layout, inOut)
        binding.inputEdit.setAdapter(arrayAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}