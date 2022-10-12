package ru.students.test_rest_service.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.students.test_rest_service.model.Request;

@Service
public class ModifyRequestSystemTime implements ModifyRequestService {
    @Override
    public void modifyRq(Request request) {
        request.setSystemTime("test"); //изменяет поле systemTime запроса

        HttpEntity<Request> httpEntity = new HttpEntity<>(request); //подготовка сущности запроса

        new RestTemplate().exchange("http://localhost:8082/feedback", //отпраляет запрос на второй сервис
                HttpMethod.POST,
                httpEntity, new ParameterizedTypeReference<>() {
                });
    }
}
