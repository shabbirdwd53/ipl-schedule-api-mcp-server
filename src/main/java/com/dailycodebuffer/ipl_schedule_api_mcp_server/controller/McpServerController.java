/*
package com.dailycodebuffer.ipl_schedule_api_mcp_server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@RestController
public class McpServerController {

    private static final Logger logger = LoggerFactory.getLogger(McpServerController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/mcp")
    public ResponseEntity<String> handleMcpRequest(@RequestBody String requestBody) {
        try {
            // Parse the incoming JSON
            JsonNode requestNode = objectMapper.readTree(requestBody);

            // Validate JSON-RPC structure
            if (!requestNode.has("jsonrpc") || !requestNode.has("method")) {
                return ResponseEntity.badRequest().body(
                        "{\"jsonrpc\":\"2.0\",\"id\":null,\"error\":{\"code\":-32600,\"message\":\"Invalid Request\"}}"
                );
            }

            // Process the request based on method
            String method = requestNode.get("method").asText();
            JsonNode id = requestNode.has("id") ? requestNode.get("id") : null;

            // Build proper response
            ObjectNode responseNode = objectMapper.createObjectNode();
            responseNode.put("jsonrpc", "2.0");
            if (id != null) {
                responseNode.set("id", id);
            }

            // Example method handling
            if ("initialize".equals(method)) {
                ObjectNode resultNode = responseNode.putObject("result");
                resultNode.put("name", "webmvc-mcp-server");
                resultNode.put("version", "1.0.0");
                resultNode.put("status", "ready");
            } else {
                // Default result for other methods
                responseNode.put("result", "success");
            }

            return ResponseEntity.ok(objectMapper.writeValueAsString(responseNode));

        } catch (Exception e) {
            logger.error("Error processing MCP request", e);
            try {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        objectMapper.writeValueAsString(Map.of(
                                "jsonrpc", "2.0",
                                "id", null,
                                "error", Map.of(
                                        "code", -32603,
                                        "message", "Internal error"
                                )
                        ))
                );
            } catch (JsonProcessingException jsonEx) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        "{\"jsonrpc\":\"2.0\",\"id\":null,\"error\":{\"code\":-32603,\"message\":\"Internal error\"}}"
                );
            }
        }
    }

    @GetMapping(value = "/mcp/messages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamMessages() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        try {
            // Send initial connection established message
            emitter.send(SseEmitter.event()
                    .name("message")
                    .data("{\"jsonrpc\":\"2.0\",\"method\":\"connection/established\",\"params\":{}}")
            );

            // Set up completion callback
            emitter.onCompletion(() -> {
                logger.info("SSE connection completed");
            });

            emitter.onTimeout(() -> {
                logger.info("SSE connection timed out");
                emitter.complete();
            });

        } catch (IOException e) {
            logger.error("Error sending SSE message", e);
            emitter.completeWithError(e);
        }

        return emitter;
    }
}
*/
