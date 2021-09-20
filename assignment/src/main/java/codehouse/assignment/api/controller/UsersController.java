package codehouse.assignment.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import codehouse.assignment.business.abstracts.UserService;
import codehouse.assignment.entities.concretes.User;
import codehouse.assignment.core.utilities.result.concretes.ErrorDataResult;
import codehouse.assignment.core.utilities.result.concretes.DataResult;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class UsersController 
{
	private UserService userService;
	@Autowired
	public UsersController(UserService userService) 
	{
		this.userService = userService;
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody User user) 
	{		
		return ResponseEntity.ok(this.userService.add(user));
	}
	
	@PostMapping("delete")
	public ResponseEntity<?> delete(@RequestBody User user) 
	{		
		return ResponseEntity.ok(this.userService.delete(user));
	}
	
	@PostMapping("update")
	public ResponseEntity<?> update(@RequestBody User user) 
	{		
		return ResponseEntity.ok(this.userService.update(user));
	}
	
	@GetMapping("findAll")
	public DataResult<List<User>> findAll() 
	{
		return this.userService.findAll();
	}
	
	@GetMapping("findById")
	public DataResult<Optional<User>> findById(@RequestParam int id) 
	{		
		return this.userService.findById(id);
	}
	
	@GetMapping("findByPhone")
	public DataResult<Optional<User>> findById(@RequestParam String phone)
	{		
		return this.userService.findByPhone(phone);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) 
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions)
	{
		Map<String, String> validationErrors = new HashMap<String, String>(); 		
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) 
		{
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Validation Errors");
		return errors;
	}
}