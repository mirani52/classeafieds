package com.hackathon.classeafieds.web.controller;

import com.hackathon.classeafieds.model.Post;
import com.hackathon.classeafieds.model.User;
import com.hackathon.classeafieds.service.PostService;
import com.hackathon.classeafieds.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String postList(Model model) {
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("posts",posts);
        return "post/index";
    }

    // Single POST page
    @RequestMapping("/posts/{postId}")
    public String postDetails(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId);

        model.addAttribute("post", post);
        return "post/details";
    }

    // Upload a new POST
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public String addPost(@Valid Post post, BindingResult result, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes, Principal principal) {
        if(result.hasErrors()){
            //Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", result);

            //Add the Category if invalid data was recieved
            redirectAttributes.addFlashAttribute("post", post);

            //Redirect back to the form
            return "redirect:/upload";
        }
        User user = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        post.setUser(user);
        postService.save(post, file);

        //Add flash message for success
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Post successfully uploaded!", FlashMessage.Status.SUCCESS));

        return String.format("redirect:/posts/%s", post.getId());
    }

    // Form for uploading a new POST
    @RequestMapping("/upload")
    public String formNewPost(Model model) {
        if(!model.containsAttribute("post"))
            model.addAttribute("post", new Post());

        model.addAttribute("action", "/posts");
        model.addAttribute("heading", "Upload");
        model.addAttribute("submit", "Upload");
        return "post/form";
    }

    // Form for editing an existing POST
    @RequestMapping(value = "/posts/{postId}/edit")
    public String formEditPost(@PathVariable Long postId, Model model) {
        if(!model.containsAttribute("post"))
            model.addAttribute("post", postService.findById(postId));

        model.addAttribute("action", String.format("/posts/%s",postId));
        model.addAttribute("heading", "Edit Post");
        model.addAttribute("submit", "Update");
        return "post/form";
    }

    // Update an existing POST
    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.POST)
    public String updatePost(Post post, @RequestParam MultipartFile file, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            //Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.post", result);

            //Add the Category if invalid data was recieved
            redirectAttributes.addFlashAttribute("post", post);
            //Redirect back to the form
            return String.format("redirect:/posts/%s/edit",post.getId());
        }

        postService.update(post, file);

        //Add flash message for success
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Post successfully updated!", FlashMessage.Status.SUCCESS));

        return String.format("redirect:/posts/%s", post.getId());
    }

    // Delete an existing POST
    @RequestMapping(value = "/posts/{postId}/delete", method = RequestMethod.GET)
    public String deletePost(@PathVariable Long postId, RedirectAttributes redirectAttributes) {
        postService.delete(postService.findById(postId));
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Post deleted successfully!", FlashMessage.Status.SUCCESS));
        return "redirect:/";
    }

    @RequestMapping(path = "/myposts")
    public String addTask(Model model) {
        Iterable<Post> posts = postService.findAllByUser();
        model.addAttribute("posts",posts);
        return "post/index";
    }

    // GIF image data
    @RequestMapping("/posts/{postId}/image")
    @ResponseBody
    public byte[] postImage(@PathVariable Long postId) {
        return postService.findById(postId).getBytes();
    }

    // Search results
    @RequestMapping(value = "/search")
    public String searchResults(@RequestParam String q, Model model) {
        model.addAttribute("posts", postService.search(q));
        return "post/index";
    }
}
