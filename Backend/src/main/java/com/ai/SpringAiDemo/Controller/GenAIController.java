package com.ai.SpringAiDemo.Controller;

import com.ai.SpringAiDemo.Service.ChatService;
import com.ai.SpringAiDemo.Service.ImageService;
import com.ai.SpringAiDemo.Service.RecipeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GenAIController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;

    public GenAIController(ChatService chatService, ImageService imageService1, RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService1;
        this.recipeService = recipeService;
    }

    @GetMapping("hello-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("hello-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }


    @GetMapping("generate-image-v1")
    public void generateImage(HttpServletResponse response,@RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImageV1(prompt);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(imageUrl);
    }

    @GetMapping("generate-image-v2")
    public List<String> generateImages(HttpServletResponse response,
                                       @RequestParam String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "1") int n,
                                       @RequestParam(defaultValue = "1024") int width,
                                       @RequestParam(defaultValue = "1024") int height)throws IOException{

        ImageResponse imageResponse = imageService.generateImageV2(prompt,quality,n,width,height);

        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

        return imageUrls;
    }

    @GetMapping("generate-recipe")
    public String generateRecipe(@RequestParam String ingredients,
                                 @RequestParam(defaultValue = "any") String cuisine,
                                 @RequestParam(defaultValue = "") String dietaryRestriction){
        return recipeService.generateRecipe(ingredients,cuisine,dietaryRestriction);
    }
}
