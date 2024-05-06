package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag( 
		name="CRUD REST APIs FOR Post Resource"

)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post rest api
    @Operation(summary = "create post rest api",
    		  description = "create post rest api is used to post database ")
    @ApiResponse(responseCode = "201",
    	description = "http status 201 created")
    @SecurityRequirement(name="Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get all posts rest api
    @Operation(summary = "get all post rest api",
  		  description = "get post rest api is used to get all data form post database ")
    @ApiResponse(responseCode = "200",
  	description = "http status 200 success")
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    // get post by id
    @Operation(summary = "get post by rest api",
  		  description = "get post rest api is used to get single from  database ")
    @ApiResponse(responseCode = "201",
  	description = "http status 200 success")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // update post by id rest api
    @Operation(summary = "update post rest api",
  		  description = "update post rest api is used to post database ")
    @ApiResponse(responseCode = "200",
  	description = "http status 200 success")
    @SecurityRequirement(name="Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){

       PostDto postResponse = postService.updatePost(postDto, id);

       return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
    @Operation(summary = "delete post rest api",
  		  description = "delete post rest api is used to post database ")
    @ApiResponse(responseCode = "200",
  	description = "http status 200 success")
    @SecurityRequirement(name="Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        postService.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
   // http://localhost:8080/api/posts/category/{id}
    //build get posts by category rest api
    @Operation(summary = "create post rest api",
  		  description = "crate post rest api is used to post database ")
    @ApiResponse(responseCode = "200",
  	description = "http status 200 success")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long category){
    	List<PostDto> postDtos = postService.getPostsByCategory(category);
    	return ResponseEntity.ok(postDtos);
    }
}
