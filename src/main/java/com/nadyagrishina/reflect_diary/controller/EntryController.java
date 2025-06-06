package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.model.Entry;
import com.nadyagrishina.reflect_diary.model.ReflectionQuestion;
import com.nadyagrishina.reflect_diary.model.Tag;
import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.service.EntryService;
import com.nadyagrishina.reflect_diary.service.ReflectionQuestionService;
import com.nadyagrishina.reflect_diary.service.TagService;
import com.nadyagrishina.reflect_diary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/entries")
public class EntryController {

    private final EntryService entryService;
    private final ReflectionQuestionService reflectionQuestionService;
    private final TagService tagService;
    private final UserService userService;

    public EntryController(EntryService entryService, ReflectionQuestionService reflectionQuestionService, TagService tagService, UserService userService) {
        this.entryService = entryService;
        this.reflectionQuestionService = reflectionQuestionService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllEntries(Model model, Principal principal) {
        Long id = userService.findByUsername(principal.getName()).getId();
        List<Entry> entries = entryService
                .findByUserId(id)
                .stream()
                .sorted(Comparator.comparing(Entry::getDate).reversed())
                .toList();
        model.addAttribute("entries", entries);
        return "entries";
    }

    @GetMapping("/{id}")
    public String getEntryById(@PathVariable Long id, Model model) {
        Entry entry = entryService.findById(id);
        ReflectionQuestion reflectionQuestion = reflectionQuestionService.findById(entry.getReflectionQuestionId());
        List<Tag> tags = entry.getTags();
        model.addAttribute("tags", tags);
        model.addAttribute("reflectionQuestion", reflectionQuestion);
        model.addAttribute("entry", entry);
        return "entry-details";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Entry entry = new Entry();

        ReflectionQuestion q = reflectionQuestionService.getRandomQuestion();
        if (q != null) {
            entry.setReflectionQuestionId(q.getId());
            model.addAttribute("question", q);
        } else {
            model.addAttribute("questionMissing", true);
        }
        model.addAttribute("entry", entry);
        model.addAttribute("allTags", tagService.findAllTags());
        model.addAttribute("selectedTags", new ArrayList<>());
        return "create-entry";
    }

    @PostMapping("/create")
    public String createEntry(@ModelAttribute  Entry entry,
                              Principal principal,
                              @RequestParam(value = "tags", required = false) List<Long> tagIds) {
        if (tagIds != null) {
            List<Tag> tagList = tagService.findAllById(tagIds);
            entry.setTags(tagList);
        }

        User user = userService.findByUsername(principal.getName());
        entry.setUser(user);
        entryService.save(entry);

        return "redirect:/entries";
    }


    @PostMapping("/{id}/delete")
    public String deleteEntry(@PathVariable Long id) {
        entryService.deleteById(id);
        return "redirect:/entries";
    }

    @GetMapping("/{id}/edit")
    public String editEntry(@PathVariable Long id, Model model, Principal principal) {
        Entry entry = entryService.findById(id);

        if (!entry.getUser().getUsername().equals(principal.getName())) {
            return "redirect:/entries";
        }

        model.addAttribute("allTags", tagService.findAllTags());
        model.addAttribute("selectedTags",  entry.getTags());
        model.addAttribute("question", reflectionQuestionService.findById(entry.getReflectionQuestionId()));
        model.addAttribute("entry", entry);
        model.addAttribute("formAction", "/entries/update");

        return "create-entry";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,
                         @RequestParam(value = "tags", required = false) List<Long> tagIds,
                         @ModelAttribute Entry entryFromForm,
                         Principal principal) {

        Entry originalEntry = entryService.findById(id);
        if (!originalEntry.getUser().getUsername().equals(principal.getName())) {
            return "redirect:/entries";
        }

        if (tagIds != null) {
            originalEntry.setTags(tagService.findAllById(tagIds));
        } else {
            originalEntry.setTags(new ArrayList<>());
        }

        originalEntry.setScore(entryFromForm.getScore());
        originalEntry.setDate(entryFromForm.getDate());
        originalEntry.setReflectionQuestionId(entryFromForm.getReflectionQuestionId());
        originalEntry.setTomorrowPlan(entryFromForm.getTomorrowPlan());
        originalEntry.setDifficulties(entryFromForm.getDifficulties());
        originalEntry.setHighlights(entryFromForm.getHighlights());
        originalEntry.setMainText(entryFromForm.getMainText());
        originalEntry.setMood(entryFromForm.getMood());
        originalEntry.setQuestionAnswer(entryFromForm.getQuestionAnswer());

        entryService.save(originalEntry);
        System.out.println("originalEntry: " + originalEntry);

        return "redirect:/entries";
    }
}
