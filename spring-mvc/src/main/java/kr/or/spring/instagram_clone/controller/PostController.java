package kr.or.spring.instagram_clone.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.spring.instagram_clone.dto.Comment;
import kr.or.spring.instagram_clone.dto.Likes;
import kr.or.spring.instagram_clone.dto.Post;
import kr.or.spring.instagram_clone.dto.User;
import kr.or.spring.instagram_clone.service.PostService;
import kr.or.spring.instagram_clone.service.UserService;


@Controller
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;
	
	String imageRoot = "C:\\Users\\choih\\Desktop\\project\\instagram-clone\\src\\main\\webapp\\resources\\img\\";

	@GetMapping(path="/list")
	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,
					   ModelMap model,
					   Principal principal,
					   HttpServletResponse response) {
		
		String loginId = principal.getName();
		User user = userService.getUserByEmail(loginId);
		
		List<Post> list = postService.getPosts(start, user.getId());
		
		model.addAttribute("user", user.getName());
		model.addAttribute("list", list);
		
		return "list";
	}
	
	@GetMapping(path="/detail")
	public String detail(@RequestParam(name="name", required=true) String name,
						@RequestParam(name="start", required=false, defaultValue="0") int start,
						Principal principle,
						ModelMap model) {
		String loginId = principle.getName();
		User user = userService.getUserByEmail(loginId);
		
		List<Post> list = postService.getPosts(start, name, user.getId());
		model.addAttribute("user", user.getName());
		model.addAttribute("list", list);
		model.addAttribute("name", name);
		return "detail";
	}
	
	@GetMapping(path="/upload")
	public String upload() {
		return "upload";
	}
	
	@PostMapping(path="/write")
	public String write(@ModelAttribute Post post,
						HttpServletRequest request,
						Principal principal,
						@RequestParam("file") MultipartFile file) {
		String path = imageRoot+file.getOriginalFilename();
		
		String loginId = principal.getName();
        User user = userService.getUserByEmail(loginId);
        
		String clientIp = request.getRemoteAddr();
		System.out.println("clientIp : " + clientIp);
		postService.addPost(post, clientIp, file.getOriginalFilename(), user);

		
		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 크기 : " + file.getSize());
		System.out.println(path);
		
        try(
                FileOutputStream fos = new FileOutputStream(path);
                InputStream is = file.getInputStream();
        ){
        	    int readCount = 0;
        	    byte[] buffer = new byte[1024];
            while((readCount = is.read(buffer)) != -1){
                fos.write(buffer,0,readCount);
                
                
            }
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }
		
		return "redirect:list";
	}
	
	@GetMapping(path="/delete")
	public String delete(@RequestParam(name="id", required=true) Long id, 
			             @SessionAttribute("isAdmin") String isAdmin,
			             HttpServletRequest request,
			             RedirectAttributes redirectAttr) {
		if(isAdmin == null || !"true".equals(isAdmin)) { // 세션값이 true가 아닐 경우
			redirectAttr.addFlashAttribute("errorMessage", "로그인을 하지 않았습니다.");
			return "redirect:loginform";
		}
		String clientIp = request.getRemoteAddr();
		postService.deletePost(id, clientIp);
		return "redirect:list";		
	}
	
	@GetMapping(path="/likes")
	public String likes(@ModelAttribute Likes likes,
						@RequestParam(name="id", required=true) Long id, 
						Principal principal) {
		String loginId = principal.getName();
        User user = userService.getUserByEmail(loginId);
        
		postService.addLikes(likes, user, id);	
		return "redirect:list";
	}
	
	@GetMapping(path="/likesdelete")
	public String likesDelete(@RequestParam(name="id", required=true) Long id, 
			Principal principle) {
		String loginId = principle.getName();
		User user = userService.getUserByEmail(loginId);

		postService.deleteLikes(id, user.getId());
		return "redirect:list";
	}

	@GetMapping(path="/comment")
	public String comment(@RequestParam(name="id", required=true) Long id) {
		return "comment";
	}
	
	@PostMapping(path="/comment")
	public String commentUpload(@ModelAttribute Comment comment,
						@RequestParam(name="id", required=true) Long id,
						Principal principal) {
		String loginId = principal.getName();
        User user = userService.getUserByEmail(loginId);
		postService.addComment(comment, user, id);
		return "redirect:list";
	}
}