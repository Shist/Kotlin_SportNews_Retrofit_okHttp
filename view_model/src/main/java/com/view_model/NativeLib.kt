package com.view_model

class NativeLib {

    /**
     * A native method that is implemented by the 'view_model' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'view_model' library on application startup.
        init {
            System.loadLibrary("view_model")
        }
    }
}