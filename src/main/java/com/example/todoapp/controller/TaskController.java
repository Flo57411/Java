package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskStatus;
import com.example.todoapp.model.TaskPriority;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    // Liste des tâches
    @GetMapping
    public String listTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }
    
    // Formulaire d'ajout
    @GetMapping("/new")
    public String newTaskForm(Model model) {
        Task task = new Task();
        List<User> users = userRepository.findAll();
        model.addAttribute("task", task);
        model.addAttribute("users", users);
        model.addAttribute("priorities", TaskPriority.values());
        model.addAttribute("statuses", TaskStatus.values());
        return "tasks/form";
    }
    
    // Création d'une tâche
    @PostMapping
    public String createTask(@ModelAttribute Task task, 
                           @RequestParam Long userId,
                           RedirectAttributes redirectAttributes) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                redirectAttributes.addFlashAttribute("error", "Utilisateur non trouvé");
                return "redirect:/tasks";
            }
            
            task.setUser(user);
            task.setCreatedAt(LocalDateTime.now());
            if (task.getStatus() == null) {
                task.setStatus(TaskStatus.PENDING);
            }
            if (task.getPriority() == null) {
                task.setPriority(TaskPriority.MEDIUM);
            }
            
            taskRepository.save(task);
            redirectAttributes.addFlashAttribute("success", "Tâche créée avec succès !");
            return "redirect:/tasks";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la création : " + e.getMessage());
            return "redirect:/tasks/new";
        }
    }
    
    // Détail d'une tâche
    @GetMapping("/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return "redirect:/tasks";
        }
        model.addAttribute("task", task);
        return "tasks/show";
    }
    
    // Formulaire de modification
    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return "redirect:/tasks";
        }
        
        List<User> users = userRepository.findAll();
        model.addAttribute("task", task);
        model.addAttribute("users", users);
        model.addAttribute("priorities", TaskPriority.values());
        model.addAttribute("statuses", TaskStatus.values());
        return "tasks/form";
    }
    
    // Mise à jour d'une tâche
    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, 
                           @ModelAttribute Task task,
                           @RequestParam Long userId,
                           RedirectAttributes redirectAttributes) {
        try {
            Task existingTask = taskRepository.findById(id).orElse(null);
            if (existingTask == null) {
                redirectAttributes.addFlashAttribute("error", "Tâche non trouvée");
                return "redirect:/tasks";
            }
            
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                redirectAttributes.addFlashAttribute("error", "Utilisateur non trouvé");
                return "redirect:/tasks";
            }
            
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
            existingTask.setPriority(task.getPriority());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setUser(user);
            
            taskRepository.save(existingTask);
            redirectAttributes.addFlashAttribute("success", "Tâche mise à jour avec succès !");
            return "redirect:/tasks/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la mise à jour : " + e.getMessage());
            return "redirect:/tasks/" + id + "/edit";
        }
    }
    
    // Suppression d'une tâche
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Task task = taskRepository.findById(id).orElse(null);
            if (task != null) {
                taskRepository.delete(task);
                redirectAttributes.addFlashAttribute("success", "Tâche supprimée avec succès !");
            } else {
                redirectAttributes.addFlashAttribute("error", "Tâche non trouvée");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        return "redirect:/tasks";
    }
    
    // Validation d'une tâche (changement de statut)
    @PostMapping("/{id}/validate")
    public String validateTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Task task = taskRepository.findById(id).orElse(null);
            if (task != null) {
                task.setStatus(TaskStatus.COMPLETED);
                taskRepository.save(task);
                redirectAttributes.addFlashAttribute("success", "Tâche marquée comme terminée !");
            } else {
                redirectAttributes.addFlashAttribute("error", "Tâche non trouvée");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la validation : " + e.getMessage());
        }
        return "redirect:/tasks";
    }
    
    // Changement de statut
    @PostMapping("/{id}/status")
    public String changeStatus(@PathVariable Long id, 
                             @RequestParam TaskStatus status,
                             RedirectAttributes redirectAttributes) {
        try {
            Task task = taskRepository.findById(id).orElse(null);
            if (task != null) {
                task.setStatus(status);
                taskRepository.save(task);
                redirectAttributes.addFlashAttribute("success", "Statut de la tâche mis à jour !");
            } else {
                redirectAttributes.addFlashAttribute("error", "Tâche non trouvée");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors du changement de statut : " + e.getMessage());
        }
        return "redirect:/tasks";
    }
}
