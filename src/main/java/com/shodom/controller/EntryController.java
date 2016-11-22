package com.shodom.controller;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
@Secured({"ROLE_ADMIN"})
@RequestMapping(value={"/entry"},method=RequestMethod.GET)
public class EntryController {

	@Autowired
	EntryRepository entryRepository;
	
	@GetMapping({"","/{page}"})
	public String listEntries(@RequestParam(required = false, defaultValue = "0", value="page") Integer page, Model model){
		int recordCount = 20;
    	int realPage = page*recordCount;
		model.addAttribute("entryList",entryRepository.getAll(realPage, recordCount));
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
		entry.setPlain(entry.getContent().replaceAll("\\<.*?\\>", ""));
		entryRepository.addEntry(entry);
        return "redirect:/entry";
    }
	
	@PostMapping("/editPost")
    public String editEntryAction(@ModelAttribute Entry entry) {
		entry.setUrlRoute(Converters.toEnglish(entry.getTitle()));
		entry.setPlain(entry.getContent().replaceAll("\\<.*?\\>", ""));
		entryRepository.updateEntry(entry.getId(), entry);
        return "redirect:/entry";
    }
	
	@GetMapping("/editAll")
	public String editAll(){
		List<Entry> entryList = entryRepository.getAll(0, 1000);
		for (Entry entry : entryList) {
			entry.setUrlRoute(Converters.toEnglish(entry.getTitle()));
			entry.setPlain(entry.getContent().replaceAll("\\<.*?\\>", ""));
			entryRepository.updateEntry(entry.getId(), entry);
		}
        return "redirect:/entry";
	}
}
