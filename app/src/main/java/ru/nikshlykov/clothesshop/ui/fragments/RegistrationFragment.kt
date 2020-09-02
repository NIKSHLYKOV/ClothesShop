package ru.nikshlykov.clothesshop.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import ru.nikshlykov.clothesshop.App
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.ui.OnChildFragmentInteractionListener
import ru.nikshlykov.clothesshop.viewmodels.RegistrationViewModel
import ru.nikshlykov.clothesshop.viewmodels.ViewModelFactory
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    private lateinit var registrationViewModel: RegistrationViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var onChildFragmentInteractionListener: OnChildFragmentInteractionListener

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
        if (parentFragment?.parentFragment is OnChildFragmentInteractionListener) {
            onChildFragmentInteractionListener =
                parentFragment?.parentFragment as OnChildFragmentInteractionListener
        } else {
            throw RuntimeException(
                parentFragment?.parentFragment.toString()
                        + " must implement OnChildFragmentInteractionListener"
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registrationViewModel = viewModelFactory.create(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationViewModel.registrationStatus.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                1 -> {
                    Toast.makeText(context, "Регистрация прошла успешно", Toast.LENGTH_SHORT)
                        .show()
                    val navDirections = RegistrationFragmentDirections.actionRegistrationFragmentToNavProfile()
                    onChildFragmentInteractionListener.onChildFragmentInteraction(navDirections)
                }
            }
        })

        val name =
            view.findViewById<TextInputEditText>(R.id.fragment_registration___edit_text___name)
        val email =
            view.findViewById<TextInputEditText>(R.id.fragment_registration___edit_text___email)
        val password =
            view.findViewById<TextInputEditText>(R.id.fragment_registration___edit_text___password)
        val photoUri =
            view.findViewById<TextInputEditText>(R.id.fragment_registration___edit_text___photo_uri)
        view.findViewById<MaterialButton>(R.id.fragment_registration___button___confirm_registration)
            .setOnClickListener {
                registrationViewModel.register(
                    email.text.toString(),
                    password.text.toString(),
                    name.text.toString(),
                    photoUri.text.toString()
                )
            }
    }
}