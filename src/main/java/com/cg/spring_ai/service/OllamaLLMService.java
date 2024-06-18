package com.cg.spring_ai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class OllamaLLMService {
    private OllamaChatModel chatModel;

    public OllamaLLMService(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String chat1(String queries){
        String response = chatModel.call(queries);
        return response;
    }

    public String chat(String query){
        String message = """
                <INST>You are an AI assistant that can answer everyone questions by using the content provided.
                If you don't know the answer then don't  make assumption just say, "I DON'T KNOW".</INST>
                content:{content}
                question:{input}
                """;

        PromptTemplate promptTemplate = new PromptTemplate(message);

        Prompt prompt = promptTemplate.create(Map.of("input",query,"content",myContent));

        return chatModel.call(prompt).getResult().getOutput().getContent();

    }

    private String myContent = """
            Sayan is a devOps guy working for Capgemini

            Sayan is under paid

            Feluda is a developer working for Capgemini

            Feluda is paid more than Sayan

            Capgemini is a service based MNC that employs IT
            """;
}
