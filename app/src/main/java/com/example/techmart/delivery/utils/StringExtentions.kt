package com.example.techmart.delivery.utils

import java.util.regex.Pattern

fun String.isValidPassword(): Boolean = this.isNotEmpty() && Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[\\w~@#\$%^&*+=`|{}:;!.?\"()\\[\\]-]{5,}\$").matcher(this).matches()

fun String.isValidEmail(): Boolean = this.isNotEmpty() && Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
).matcher(this).matches()