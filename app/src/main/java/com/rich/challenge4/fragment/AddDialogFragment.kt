package com.rich.challenge4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.rich.challenge4.MainActivity
import com.rich.challenge4.database.Notes
import com.rich.challenge4.databinding.AddDialogBinding
import com.rich.challenge4.viewmodel.NotesViewModel

class AddDialogFragment : DialogFragment() {
    private lateinit var binding : AddDialogBinding
    private lateinit var notesVM : NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesVM = ViewModelProvider(requireActivity()).get(NotesViewModel::class.java)
        binding.btnSubmit.setOnClickListener {
            val addedNote : Notes = Notes(
                0,
                binding.etTitle.text.toString(),
                binding.etContent.text.toString()
            )
            notesVM.insertNotes(addedNote)
            dismiss()
        }

    }

}