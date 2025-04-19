//package com.dailycodebuffer.ipl_schedule_api_mcp_server.config;
//
//import jakarta.annotation.PreDestroy;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//@Component
//public class McpConnectionManager {
//    private final List<SseEmitter> connections = new CopyOnWriteArrayList<>();
//
//    public void addConnection(SseEmitter emitter) {
//        connections.add(emitter);
//        emitter.onCompletion(() -> connections.remove(emitter));
//        emitter.onTimeout(() -> connections.remove(emitter));
//        emitter.onError(e -> connections.remove(emitter));
//    }
//
//    public void broadcastMessage(String message) {
//        List<SseEmitter> deadEmitters = new ArrayList<>();
//        connections.forEach(emitter -> {
//            try {
//                emitter.send(SseEmitter.event().data(message));
//            } catch (Exception e) {
//                deadEmitters.add(emitter);
//            }
//        });
//        connections.removeAll(deadEmitters);
//    }
//
//    // Gracefully close all connections on shutdown
//    @PreDestroy
//    public void shutdown() {
//        connections.forEach(SseEmitter::complete);
//        connections.clear();
//    }
//}
