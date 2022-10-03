package com.fpter.wetalk.features

class ApiConstants {
    companion object {
        val TIMEOUT_IN_MINUTE = 2
    }


    class Url {
        companion object {
            const val LOGIN = "auth/users/login"
            const val REGISTER = "auth/users"
            const val REFRESH_TOKEN = "users/token/refresh"
        }
    }

    class Param {
        companion object {
            const val JSON_TYPE = "Content-Type: application/json"
        }
    }

    class HttpCode {
        companion object {
            const val UNAUTHORIZED = 401
        }
    }
}