package com.shodom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shodom.model.Entry;
import com.shodom.repository.EntryRepository;
import com.shodom.utils.Converters;

@Controller
@RequestMapping(value={"/entry"},method=RequestMethod.GET)
public class EntryController {

	@Autowired
	EntryRepository entryRepository;
	
	@GetMapping({"","/{page}"})
	public String listEntries(@RequestParam(required = false, defaultValue = "0", value="page") Integer page, Model model){
		model.addAttribute("entryList",entryRepository.getAll(page));
		return "entryList";
	}
	
	@GetMapping("/add") 
    public String addEntry(Model model) {
		Entry e = new Entry();
		e.setLink("https://www.youtube.com/embed/");
		model.addAttribute("entry", e);
		return "addEntry";
    }
	
	@GetMapping("/edit/{id}") 
    public String editEntry(@PathVariable("id") String id,Model model) {
		Entry e = entryRepository.getEntry(id);
		model.addAttribute("entry", e);
		return "editEntry";
    }
	
	@GetMapping("/delete/{id}") 
    public String deleteEntry(@PathVariable("id") String id) {
		entryRepository.deleteEntry(id);
		return "redirect:/entry";
    }
    

	@PostMapping("/addPost")
    public String addEntryAction(@ModelAttribute Entry entry) {
		entry.setUrlRoute(Converters.toEnglish(entry.getTitle()));
		entryRepository.addEntry(entry);
        return "redirect:/entry";
    }
	
	@PostMapping("/editPost")
    public String editEntryAction(@ModelAttribute Entry entry) {
		entryRepository.updateEntry(entry.getId(), entry);
        return "redirect:/entry";
    }
}
