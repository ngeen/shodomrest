package com.shodom.controller;

import java.util.List;
import java.util.Optional;

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

import com.shodom.model.DownloadFile;
import com.shodom.model.Entry;
import com.shodom.repository.EntryRepository;
import com.shodom.service.Downloader;
import com.shodom.utils.Converters;

@Controller
@Secured({ "ROLE_ADMIN" })
@RequestMapping(value = { "/entry" }, method = RequestMethod.GET)
public class EntryController {

	@Autowired
	EntryRepository entryRepository;

	@Autowired
	Downloader downloader;

	@RequestMapping(value = { "", "/", "/{page}" }, method = RequestMethod.GET)
	public String listEntries(@PathVariable("page") Optional<Integer> page, Model model) {
		int activePage = 0;
		if (page.isPresent()) {
			activePage = page.get() - 1;
		}
		int recordsPerPage = 20;
		int fromRecords = activePage * recordsPerPage;
		model.addAttribute("entryList", entryRepository.getAllByPage(fromRecords, recordsPerPage));
		long recordCount = entryRepository.getCount();
		long pageCount = (recordCount % 20 == 0) ? (recordCount / 20) : ((recordCount / 20) + 1);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("entryCount", recordCount);
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
	public String editEntry(@PathVariable("id") String id, Model model) {
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
		try {
			if (entry.getGifImage() == null) {
				DownloadFile df = new DownloadFile();
				df.setUrl(entry.getLink());
				df.setFileName(entry.getUrlRoute());
				downloader.download(df);
				entry.setGifImage(df.getFileName() + ".gif");
			}
		} catch (Exception e) {
		}
		entryRepository.addEntry(entry);
		return "redirect:/entry";
	}

	@PostMapping("/editPost")
	public String editEntryAction(@ModelAttribute Entry entry) {
		entry.setUrlRoute(Converters.toEnglish(entry.getTitle()));
		entry.setPlain(entry.getContent().replaceAll("\\<.*?\\>", ""));
		try {
			if (entry.getGifImage() == null) {
				DownloadFile df = new DownloadFile();
				df.setUrl(entry.getLink());
				df.setFileName(entry.getUrlRoute());
				downloader.download(df);
				entry.setGifImage(df.getFileName() + ".gif");
			}
		} catch (Exception e) {
		}
		entryRepository.updateEntry(entry.getId(), entry);
		return "redirect:/entry";
	}

	@GetMapping("/editAll")
	public String editAll() {
		List<Entry> entryList = entryRepository.getAllByPage(0, 1000);
		for (Entry entry : entryList) {
			entry.setUrlRoute(Converters.toEnglish(entry.getTitle()));
			entry.setPlain(entry.getContent().replaceAll("\\<.*?\\>", ""));
			try {
				if (entry.getGifImage() == null) {
					System.out.println(entry.getUrlRoute());
					DownloadFile df = new DownloadFile();
					df.setUrl(entry.getLink());
					df.setFileName(entry.getUrlRoute());
					downloader.download(df);
					entry.setGifImage(df.getFileName() + ".gif");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			entryRepository.updateEntry(entry.getId(), entry);
		}
		return "redirect:/entry";
	}
}
