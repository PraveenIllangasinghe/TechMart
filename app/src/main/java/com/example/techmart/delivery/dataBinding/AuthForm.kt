package com.example.techmart.delivery.dataBinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.techmart.delivery.utils.isValidEmail
import com.example.techmart.delivery.utils.isValidPassword

class AuthForm : BaseObservable() {

    var formInitialized: Boolean? = false
        @Bindable get
        set(formInitialized) {
            field = formInitialized
            notifyPropertyChanged(BR.formInitialized)
        }

    var nameInitialized: Boolean? = false
        @Bindable get
        set(nameInitialized) {
            field = nameInitialized
            notifyPropertyChanged(BR.nameInitialized)
        }


    var passwordInitialized: Boolean? = false
        @Bindable get
        set(passwordInitialized) {
            field = passwordInitialized
            notifyPropertyChanged(BR.passwordInitialized)
        }

    var confirmPasswordInitialized: Boolean? = false
        @Bindable get
        set(confirmPasswordInitialized) {
            field = confirmPasswordInitialized
            notifyPropertyChanged(BR.confirmPasswordInitialized)
        }

    var mobileInitialized: Boolean? = false
        @Bindable get
        set(mobileInitialized) {
            field = mobileInitialized
            notifyPropertyChanged(BR.mobileInitialized)
        }


    var userNameState: Boolean? = false
        @Bindable get
        set(userNameState) {
            field = userNameState
            notifyPropertyChanged(BR.userNameState)
        }

    var passwordState: Boolean? = false
        @Bindable get
        set(passwordState) {
            field = passwordState
            notifyPropertyChanged(BR.passwordState)
        }

    var confirmPasswordState: Boolean? = false
        @Bindable get
        set(confirmPasswordState) {
            field = confirmPasswordState
            notifyPropertyChanged(BR.confirmPasswordState)
        }

    var passwordMatchState: Boolean? = false
        @Bindable get
        set(passwordMatchState) {
            field = passwordMatchState
            notifyPropertyChanged(BR.passwordMatchState)
        }

    var mobileState: Boolean? = false
        @Bindable get
        set(mobileState) {
            field = mobileState
            notifyPropertyChanged(BR.mobileState)
        }

    var formValidState: Boolean = userNameState == true && passwordState == true && confirmPasswordState == true && mobileState == true
        @Bindable get
        set(formValidState) {
            field = formValidState
            notifyPropertyChanged(BR.formValidState)
        }


    var userName: String? = null
        @Bindable get
        set(userName) {
            field = userName
            userNameState = userName!!.isNotEmpty()
            nameInitialized = true
            formValidState = userNameState!! && passwordState!! && confirmPasswordState!! && mobileState!!
            formInitialized = nameInitialized!! && passwordInitialized!! && confirmPasswordInitialized!! && mobileInitialized!!
            notifyPropertyChanged(BR.userName)
        }

    var password: String? = null
        @Bindable get
        set(password) {
            field = password
            passwordInitialized = true
            passwordState = password!!.isValidPassword()

            if (!confirmPassword.equals(null))
            {
                confirmPasswordState = when {
                    confirmPassword.equals(password) -> {
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

            if (!confirmPassword.equals(null))
            {
                passwordMatchState = when {
                    confirmPassword.equals(password) -> {
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

            formValidState = userNameState!! && passwordState!! && confirmPasswordState!! && mobileState!!
            formInitialized = nameInitialized!! && passwordInitialized!! && confirmPasswordInitialized!! && mobileInitialized!!
            notifyPropertyChanged(BR.password)
        }

    var confirmPassword: String? = null
        @Bindable get
        set(confirmPassword) {
            field = confirmPassword
            confirmPasswordInitialized = true
            confirmPasswordState = password == confirmPassword
            passwordMatchState = password == confirmPassword
            formValidState = userNameState!! && passwordState!! && confirmPasswordState!! && mobileState!!
            formInitialized = nameInitialized!! && passwordInitialized!! && confirmPasswordInitialized!! && mobileInitialized!!
            notifyPropertyChanged(BR.confirmPassword)

        }

    var mobile: String? = null
        @Bindable get
        set(mobile) {
            field = mobile
            mobileInitialized = true
            mobileState = mobile?.isValidEmail()
            formValidState = userNameState!! && passwordState!! && confirmPasswordState!! && mobileState!!
            formInitialized = nameInitialized!! && passwordInitialized!! && confirmPasswordInitialized!! && mobileInitialized!!
            notifyPropertyChanged(BR.mobile)
        }






    var signInFormInitialized: Boolean? = false
        @Bindable get
        set(signInFormInitialized) {
            field = signInFormInitialized
            notifyPropertyChanged(BR.signInFormInitialized)
        }

    var signInNameInitialized: Boolean? = false
        @Bindable get
        set(signInNameInitialized) {
            field = signInNameInitialized
            notifyPropertyChanged(BR.signInNameInitialized)
        }


    var signInPasswordInitialized: Boolean? = false
        @Bindable get
        set(signInPasswordInitialized) {
            field = signInPasswordInitialized
            notifyPropertyChanged(BR.signInPasswordInitialized)
        }

    var signInUserNameState: Boolean? = false
        @Bindable get
        set(signInUserNameState) {
            field = signInUserNameState
            notifyPropertyChanged(BR.signInUserNameState)
        }

    var signInPasswordState: Boolean? = false
        @Bindable get
        set(signInPasswordState) {
            field = signInPasswordState
            notifyPropertyChanged(BR.signInPasswordState)
        }


    var signInFormValidState: Boolean = signInUserNameState == true && signInPasswordState == true
        @Bindable get
        set(signInFormValidState) {
            field = signInFormValidState
            notifyPropertyChanged(BR.signInFormValidState)
        }

    var signInUserName: String? = null
        @Bindable get
        set(signInUserName) {
            field = signInUserName
            signInUserNameState = signInUserName!!.isValidEmail()
            signInNameInitialized = true
            signInFormValidState = signInUserNameState!! && signInPasswordState!!
            signInFormInitialized = signInNameInitialized!! && signInPasswordInitialized!!
            notifyPropertyChanged(BR.signInUserName)
        }

    var signInPassword: String? = null
        @Bindable get
        set(signInPassword) {
            field = signInPassword
            signInPasswordState = signInPassword!!.isNotEmpty()
            signInPasswordInitialized = true
            signInFormValidState = signInUserNameState!! && signInPasswordState!!
            signInFormInitialized = signInNameInitialized!! && signInPasswordInitialized!!
            notifyPropertyChanged(BR.signInPassword)
        }


}