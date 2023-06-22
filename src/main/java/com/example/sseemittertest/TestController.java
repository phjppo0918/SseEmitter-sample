package com.example.sseemittertest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TestController {
    List<SseEmitter> emitters = new ArrayList<>();

    @GetMapping("hello")
    @SneakyThrows
    @ResponseStatus(HttpStatus.CREATED)
    public SseEmitter connect() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.send(SseEmitter.event().data("hello"));
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));

        return emitter;
    }

    @Scheduled(fixedRate = 1000)
    @SneakyThrows
    public void schedule() {
        String message = "member Count" + emitters.size();
        for (SseEmitter se : emitters) {
            se.send(SseEmitter.event().data(message));
            se.send(SseEmitter.event().data(LocalDateTime.now()));
        }
    }
}
