package com.example.history_of_kazakhstan

import android.content.Context
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import org.json.JSONObject
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DatabaseHandler {




    fun getKeyValue(context: Context, key: String): String? {
        val fileName = "data.json"
        val file = File(context.filesDir, fileName)

        if (file.exists()) {
            val jsonContent = file.readText()
            val jsonObject = JSONObject(jsonContent)
            if (jsonObject.has("current_user")) {
                return jsonObject.getString("current_user")
            } else {
                updateCurrentUser(context, "")
                return ""
            }


        } else {
            return ""
        }
    }



    fun getUserKeyValue(context: Context, username: String, key: String): String? {
        val fileName = "data.json"
        val file = File(context.filesDir, fileName)

        if (file.exists()) {
            val jsonContent = file.readText()
            val jsonObject = JSONObject(jsonContent)
            val users = jsonObject.getJSONObject("users")

            return if (users.has(username)) {
                val userObject = users.getJSONObject(username)
                userObject.optString(key, null.toString())
            } else {
                null
            }
        } else {
            return null
        }
    }


    fun updateCurrentUser(context: Context, newValue: String) {
        val fileName = "data.json"
        val file = File(context.filesDir, fileName)

        if (file.exists()) {
            val jsonContent = file.readText()
            val jsonObject = JSONObject(jsonContent)

            // Check if "current_user" is empty or does not exist
            if (!jsonObject.has("current_user") || jsonObject.getString("current_user").isEmpty()) {
                jsonObject.put("current_user", newValue)

                // Save the updated JSON content back to the file
                val updatedJsonContent = jsonObject.toString()
                FileOutputStream(file).use { output ->
                    output.write(updatedJsonContent.toByteArray())
                }
//                Toast.makeText(context, "current_user updated", Toast.LENGTH_SHORT).show()
            } else {
//                Toast.makeText(context, "current_user is not empty", Toast.LENGTH_SHORT).show()
            }
        } else {
//            Toast.makeText(context, "data.json file not found", Toast.LENGTH_SHORT).show()
        }
    }



    fun checkAndCreateJsonFile(context: Context) {
        val fileName = "data.json"
        val file = File(context.filesDir, fileName)

        if (!file.exists()) {
            // File does not exist, create it with the desired content
            val jsonContent = """{"current_user": "", "users": {}}"""
            FileOutputStream(file).use { output ->
                output.write(jsonContent.toByteArray())
            }
//            Toast.makeText(context, "File created!", Toast.LENGTH_SHORT).show()
        } else {
//            Toast.makeText(context, "File already exist!", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkUsernameExists(context: Context, username: String): Boolean {
        val fileName = "data.json"
        val file = File(context.filesDir, fileName)

        if (file.exists()) {
            val jsonContent = file.readText()
            val jsonObject = JSONObject(jsonContent)
            val users = jsonObject.getJSONObject("users")
            return users.has(username)
        } else {
//            Toast.makeText(context, "data.json file not found", Toast.LENGTH_SHORT).show()
            return false
        }
    }



    fun addUser(context: Context, username: String, password: String) {
        val fileName = "data.json"
        val file = File(context.filesDir, fileName)

        if (file.exists()) {
            val jsonContent = file.readText()
            val jsonObject = JSONObject(jsonContent)
            val users = jsonObject.getJSONObject("users")


            val newUser = JSONObject()
            newUser.put("password", sha256(password))
            users.put(username, newUser)

            val updatedJsonContent = jsonObject.toString()
            FileOutputStream(file).use { output ->
                output.write(updatedJsonContent.toByteArray())
            }
//            Toast.makeText(context, "User added", Toast.LENGTH_SHORT).show()

        } else {
//            Toast.makeText(context, "data.json file not found", Toast.LENGTH_SHORT).show()
        }
    }



    fun checkLogin(context: Context, username: String, password: String): Boolean {
        val fileName = "data.json"
        val file = File(context.filesDir, fileName)

        if (file.exists()) {
            val jsonContent = file.readText()
            val jsonObject = JSONObject(jsonContent)
            val users = jsonObject.getJSONObject("users")

            if (users.has(username)) {
                val storedPassword = users.getJSONObject(username).getString("password")
                return storedPassword == sha256(password)
            } else {
//                Toast.makeText(context, "Username not found", Toast.LENGTH_SHORT).show()
                return false
            }
        } else {
//            Toast.makeText(context, "data.json file not found", Toast.LENGTH_SHORT).show()
            return false
        }
    }



    fun addAccountCreated(context: Context, username: String) {
        val fileName = "data.json"
        val file = File(context.filesDir, fileName)

        if (file.exists()) {
            val jsonContent = file.readText()
            val jsonObject = JSONObject(jsonContent)
            val users = jsonObject.getJSONObject("users")

            if (users.has(username)) {
                val sdf = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault())
                val currentDateTime = sdf.format(Date())

                val userObject = users.getJSONObject(username)
                userObject.put("account_created", currentDateTime)

                val updatedJsonContent = jsonObject.toString()
                FileOutputStream(file).use { output ->
                    output.write(updatedJsonContent.toByteArray())
                }
//                Toast.makeText(context, "Account created date added", Toast.LENGTH_SHORT).show()
            } else {
//                Toast.makeText(context, "Username not found", Toast.LENGTH_SHORT).show()
            }
        } else {
//            Toast.makeText(context, "data.json file not found", Toast.LENGTH_SHORT).show()
        }
    }



    fun updateLastEnter(context: Context, username: String) {
        val fileName = "data.json"
        val file = File(context.filesDir, fileName)

        if (file.exists()) {
            val jsonContent = file.readText()
            val jsonObject = JSONObject(jsonContent)
            val users = jsonObject.getJSONObject("users")

            if (users.has(username)) {
                val sdf = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault())
                val currentDateTime = sdf.format(Date())

                val userObject = users.getJSONObject(username)
                userObject.put("last_enter", currentDateTime)

                val updatedJsonContent = jsonObject.toString()
                FileOutputStream(file).use { output ->
                    output.write(updatedJsonContent.toByteArray())
                }
//                Toast.makeText(context, "Last enter date updated", Toast.LENGTH_SHORT).show()
            } else {
//                Toast.makeText(context, "Username not found", Toast.LENGTH_SHORT).show()
            }
        } else {
//            Toast.makeText(context, "data.json file not found", Toast.LENGTH_SHORT).show()
        }
    }







    fun sha256(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }


}