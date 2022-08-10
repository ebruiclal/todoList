package com.example.todolistapp.controller;

import com.example.todolistapp.model.Todo;
import com.example.todolistapp.model.TodoRepository;
import com.example.todolistapp.model.User;
import com.example.todolistapp.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.NoSuchElementException;

@RequestMapping
@RestController
public class TodoListAppController {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Iterable<Todo> todos(){
        return todoRepository.findAll();
    }
    @GetMapping("/users")
    public Iterable<User> users(){
        return userRepository.findAll();
    }
    @PostMapping
    public User AddUser(@RequestBody User user){
        return null;
    }
    @PostMapping("/{userId}/todos")
    public void AddTodo(@PathVariable Long userId, @RequestBody Todo todo  ){
        Todo.setCompleted(todo.getCompleted());
        todoRepository.save(todo);
        User user = null;
        userRepository.save(user);
    }
    @PostMapping("/todos/{todoId}")
    public void toggelTodoCompleted(@PathVariable Long todoId){
        Todo todo = null;
        todo.setCompleted(todo.getCompleted());
        todoRepository.save(todo);
    }
    @DeleteMapping("/todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId){
        Todo todo = todoRepository.findById(Math.toIntExact(todoId)).orElseThrow(()-> new NoSuchElementException());
        todoRepository.delete(todo);
    }
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId){
        User user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(()-> new NoSuchElementException());
        userRepository.delete(user);
    }
    @PutMapping("users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId) {
        User user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new NoSuchElementException());
        user.setEmail(user.getEmail());
        user.setId(user.getId());
        user.setPhonenumber(user.getPhonenumber());
        user.setName(user.getName());
        return null;
    }
    @PutMapping("todos/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId){
        Todo todo = todoRepository.findById(Math.toIntExact(todoId)).orElseThrow(()-> new NoSuchElementException());
        return null;
    }


}
