package ru.students.test_rest_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.students.test_rest_service.model.Request;
import ru.students.test_rest_service.model.Response;
import ru.students.test_rest_service.service.MyModifyService;

@RestController
@Slf4j
public class MyController {

    private final MyModifyService myModifyService;

    @Autowired
    public MyController(@Qualifier("ModifyErrorMessage") MyModifyService myModifyService) {
        this.myModifyService = myModifyService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request) {
        log.info("Request accepted: " + request.toString());

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        Response responseAfterModify = myModifyService.modify(response);

        log.info("Response sent: " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
