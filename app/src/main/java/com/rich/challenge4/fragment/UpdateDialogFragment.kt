package com.rich.challenge4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.rich.challenge4.database.Notes
import com.rich.challenge4.databinding.UpdateDialogBinding
import com.rich.challenge4.viewmodel.NotesViewModel

class UpdateDialogFragment : DialogFragment() {
    private lateinit var binding : UpdateDialogBinding
    private lateinit var notesVM : NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UpdateDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesVM = ViewModelProvider(requireActivity()).get(NotesViewModel::class.java)
        val data = getData()

        binding.oldNotes = data
        binding.btnUpdate.setOnClickListener {
            val addedNote = Notes(
                data.id,
                binding.etEditTitle.text.toString(),
                binding.etEditContent.text.toString()
            )
            notesVM.updateNotes(addedNote)
            dismiss()
        }

    }

    private fun getData(): Notes {
        return arguments?.getParcelable("oldNote")!!
    }
}