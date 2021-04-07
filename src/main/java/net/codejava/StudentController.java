package net.codejava;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

	@Autowired
	private StudentService service;
	
	//get all student list
	@GetMapping("/students")
	public List<Student> list(){
		return service.listAll();
	}
	
	//get student by id
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> get(@PathVariable Integer id) {
		try {
			Student student = service.get(id);
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	
	//add student
	@PostMapping("/students")
	public void add(@RequestBody Student student) {
		service.save(student);
	}
	
	//update student
	@PutMapping("/students/{id}")
	public ResponseEntity<?> update(@RequestBody Student student, @PathVariable Integer id){
		try {
			Student existStud = service.get(id);
			service.save(student);
			
			return new ResponseEntity<>(HttpStatus.OK);
		
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//delete by id
	@DeleteMapping("/students/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
//	@GetMapping("/showStudent")
//	public ModelAndView showStudent(Model model) {
//		List<Student> students = this.service.listAll();
//		Map<String, Object> params = new HashMap<>();
//		params.put("students", students);
//		return new ModelAndView("showEmployee", params);
//	}
	//ini yg HTML
	
	//form student
	 @GetMapping("/studs")
	  public String greetingForm(Model model) {
	    model.addAttribute("studs", new Student());
	    return "studs";
	  }
	 
	 //Submit to database
	 @PostMapping("/adduser")
	    public String addUser(Student student, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "studs";
	        }
	        
	        service.save(student);
	        return "index";
	    }
	 
	 //print all user
	 @GetMapping("/allStuds")
	 public String showUserList(Model model) {
	     model.addAttribute("studs", service.listAll());
	     return "alldata";
	 }
	 
	 //edit user
	 @GetMapping("/edit/{id}")
	 public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
	     Student student = service.get(id);   
	     model.addAttribute("studs", student);
	     return "updateuser";
	 }
	 
	 //update
	 @PostMapping("/update/{id}")
	 public String updateUser(@PathVariable("id") Integer id, Student student, 
	   BindingResult result, Model model) {
	     if (result.hasErrors()) {
	         student.setId(id);
	         return "updateuser";
	     }
	         
	     service.save(student);
	     return "index";
	 }
	 
	 //delete user
	 @GetMapping("/delete/{id}")
	 public String deleteUser(@PathVariable("id") Integer id, Model model) {
		 Student student = service.get(id);
	     service.delete(id);
	     return "index";
	 }

}
