package ru.intro.notes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.intro.notes.models.Note;
import ru.intro.notes.models.SearchRequest;
import ru.intro.notes.services.NotesService;

import javax.validation.Valid;

@Controller
public class MainController {

	private NotesService notesService;

	@Autowired
	public void setNotesService(NotesService notesService) {
		this.notesService = notesService;
	}

	/**
	 * Главная страница списка заметок
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(ModelMap model) {
		model.addAttribute("notes", notesService.findAll());
		model.addAttribute("search", new SearchRequest());
		return "main";
	}

	/**
	 * Сохранение новой заметки
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("note") Note note, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "create";
		}
		notesService.save(note);
		return "redirect:/";
	}

	/**
	 * Страница создания новой заметки
	 */
	@RequestMapping(value = "/create")
	public String create(Model model) {
		model.addAttribute("note", new Note());
		return "create";
	}

    /**
     * Удаление заметки
     */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "noteid") Long noteid, ModelMap model) {
		notesService.delete(noteid);
		return "redirect:/";
	}

	/**
	 * Просмотр заметки
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(@RequestParam(value = "noteid") Long noteid, ModelMap model) {
		model.addAttribute("note", notesService.get(noteid));
		model.addAttribute("search", new SearchRequest());
		return "show";
	}

    /**
     * Поиск заметки
     */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public String find(@Valid @ModelAttribute("search") SearchRequest searchRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errormsg", "Превышена максимальная длина");
		}
		List<Note> noteList = notesService.find(searchRequest);
		if (noteList.isEmpty()) {
			model.addAttribute("errormsg", "Нет результатов поиска");
		}
		model.addAttribute("notes",  noteList);
		return "main";
	}
}
