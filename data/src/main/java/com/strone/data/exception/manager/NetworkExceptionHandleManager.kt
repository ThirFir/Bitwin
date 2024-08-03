package com.strone.data.exception.manager

import android.content.Context
import com.strone.data.R
import com.strone.data.constant.HttpCodeConstant.BAD_GATEWAY
import com.strone.data.constant.HttpCodeConstant.BAD_REQUEST
import com.strone.data.constant.HttpCodeConstant.FORBIDDEN
import com.strone.data.constant.HttpCodeConstant.GATEWAY_TIMEOUT
import com.strone.data.constant.HttpCodeConstant.INTERNAL_SERVER_ERROR
import com.strone.data.constant.HttpCodeConstant.NOT_FOUND
import com.strone.data.constant.HttpCodeConstant.NOT_IMPLEMENTED
import com.strone.data.constant.HttpCodeConstant.SERVICE_UNAVAILABLE
import com.strone.data.constant.HttpCodeConstant.UNAUTHORIZED
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkExceptionHandleManager @Inject constructor(
    @ApplicationContext private val context: Context
) : ExceptionHandleManager {

    override fun handleCommonMessage(exception: Throwable): String {
        return context.run {
            when (exception) {
                is HttpException -> handleHttpExceptionMessage(exception.code())
                is ConnectException -> getString(R.string.error_server_connection)
                is SocketException -> getString(R.string.error_network_connection)
                is SocketTimeoutException -> getString(R.string.error_network_connection)
                is UnknownHostException -> getString(R.string.error_network_connection)
                else -> getString(R.string.error_unknown_network)
            }
        }
    }

    private fun handleHttpExceptionMessage(code: Int): String {
        return context.run {
            when (code) {
                BAD_REQUEST -> getString(R.string.error_bad_request)
                UNAUTHORIZED -> getString(R.string.error_unauthorized)
                FORBIDDEN -> getString(R.string.error_forbidden)
                NOT_FOUND -> getString(R.string.error_not_found)

                INTERNAL_SERVER_ERROR -> getString(R.string.error_internal_server_error)
                NOT_IMPLEMENTED -> getString(R.string.error_not_implemented)
                BAD_GATEWAY -> getString(R.string.error_bad_gateway)
                SERVICE_UNAVAILABLE -> getString(R.string.error_service_unavailable)
                GATEWAY_TIMEOUT -> getString(R.string.error_gateway_timeout)
                else -> getString(R.string.error_unknown_network)
            }
        }
    }
}