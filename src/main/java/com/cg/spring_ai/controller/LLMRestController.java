package com.cg.spring_ai.controller;

import com.cg.spring_ai.model.LLMRequest;
import com.cg.spring_ai.model.LLMResponse;
import com.cg.spring_ai.service.OllamaLLMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/llm")
public class LLMRestController {

    @Autowired
    private OllamaLLMService ollamaLLMService;

    public LLMRestController(OllamaLLMService ollamaLLMService) {
        this.ollamaLLMService = ollamaLLMService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody LLMRequest llmRequest){
        return ollamaLLMService.chat(llmRequest.getQuery());
    }
    @PostMapping("/chat1")
    public ResponseEntity<LLMResponse> chat1(@RequestBody LLMRequest llmRequest){
        String chatResponse = ollamaLLMService.chat1(llmRequest.getQuery());
        LLMResponse llmResponse = new LLMResponse("Success",chatResponse);
        return ResponseEntity.ok(llmResponse);
    }

}
