package com.brisasensors.device.management.api.config.web;

import com.brisasensors.device.management.api.client.DeviceMonitoringClientBadGatewayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String HTTP_STATUS_504 = "https://httpstatuses.com/504";
    private static final String HTTP_STATUS_502 = "https://httpstatuses.com/502";
    private static final String GATEWAY_TIMEOUT = "Gateway Timeout";
    private static final String BAD_GATEWAY = "Bad Gateway";

    @ExceptionHandler({
            SocketTimeoutException.class,
            java.net.ConnectException.class,
            ClosedChannelException.class
    })
    public ProblemDetail handleIOException(final IOException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.GATEWAY_TIMEOUT);

        problemDetail.setTitle(GATEWAY_TIMEOUT);
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setType(URI.create(HTTP_STATUS_504));

        return problemDetail;
    }

    @ExceptionHandler(DeviceMonitoringClientBadGatewayException.class)
    public ProblemDetail handleDeviceMonitoringClientBadGatewayException(final DeviceMonitoringClientBadGatewayException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);

        problemDetail.setTitle(BAD_GATEWAY);
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setType(URI.create(HTTP_STATUS_502));

        return problemDetail;
    }
}
