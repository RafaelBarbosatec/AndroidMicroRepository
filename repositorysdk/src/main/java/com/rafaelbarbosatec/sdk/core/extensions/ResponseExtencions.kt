package com.rafaelbarbosatec.sdk.core.extensions

import com.rafaelbarbosatec.sdk.core.response.ResponseAny
import com.rafaelbarbosatec.sdk.core.response.ResponseError
import com.rafaelbarbosatec.sdk.core.response.ResponseSuccess

fun <T>ResponseAny<T>.read(sucsess: (T) -> Unit, error: (ResponseError<T>) -> Unit){
    when(this){
        is ResponseSuccess ->  sucsess(this.body)
        is ResponseError -> error(this)
    }
}