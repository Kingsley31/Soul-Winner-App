package com.e.soulwinner.data

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val PRIVATE_MODE = 0
    private val PREF_NAME = "sojpr"
    private val USER_IS_LOGGED_IN="loggein"
    private val REMEMBER_LOGIN="rememberlogin"
    private val LOGGED_IN_USER_ID="user_id"

    var sharedPreferences:SharedPreferences?=null

    init{
       sharedPreferences= context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    }

    companion object{
        @Volatile
        private var INSTANCE: UserPreferences? = null

        fun getInstance(context: Context):UserPreferences{
            val tempInstance = INSTANCE
            if (tempInstance != null) {

                return tempInstance
            }

            synchronized(context){
                val instance = UserPreferences(context)
                INSTANCE = instance
                return instance
            }
        }
    }


    fun getLoggedInUserId():Int{
        val user_id= sharedPreferences?.getInt(LOGGED_IN_USER_ID,0)?:0
        return user_id
    }

    fun setLoggedInUserId(id:Int){
        val editor=sharedPreferences?.edit()
        editor?.putInt(LOGGED_IN_USER_ID,id)
        editor?.commit()
    }

    fun getUserIsLogged():Boolean{
        val user_is_logged_in= sharedPreferences?.getBoolean(USER_IS_LOGGED_IN,false)?:false
        return user_is_logged_in
    }

    fun setUserIsLoggedIn(userIsLoggedIn:Boolean){
        val editor=sharedPreferences?.edit()
        editor?.putBoolean(USER_IS_LOGGED_IN,userIsLoggedIn)
        editor?.commit()
    }

    fun getRememberLogin():Boolean{
        val remember_login= sharedPreferences?.getBoolean(REMEMBER_LOGIN,false)?:false
        return remember_login
    }

    fun setRememberLogin(rememberLogin:Boolean){
        val editor=sharedPreferences?.edit()
        editor?.putBoolean(REMEMBER_LOGIN,rememberLogin)
        editor?.commit()
    }

}