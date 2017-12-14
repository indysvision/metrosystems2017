package com.example.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	@Autowired
	private PontajService pontajService;

	@Autowired
	private PontajRepository pontajRepo;
	
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateFormat dofw = new SimpleDateFormat("EE");
    private static final DateFormat month = new SimpleDateFormat("MMM");
    public static final long HOUR = 3600*1000; // in milli-seconds.
	
	@RequestMapping(value = "/return", method = RequestMethod.GET)
	public String back(@RequestParam(value = "luna") String luna, @RequestParam(value = "mode") String mode, Model model,
			RedirectAttributes redirectAttributes) {
		if (mode.equals("exit")) {
			redirectAttributes.addAttribute("luna", luna);
			redirectAttributes.addAttribute("mode", "view");
		}
		return "redirect:view";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String vizualizare(@RequestParam(value = "luna") String luna, @RequestParam(value = "mode") String mode,
			Model model) {
		model.addAttribute("luna", luna);
		model.addAttribute("mode", mode);
		if (mode.equals("view")) {	
			ArrayList<Pontaj> pontaje = new ArrayList<Pontaj>();
			pontaje = pontajRepo.findAll();
			
			long diffMonth = 0;
			
			ArrayList<Pontaj> pontajeMonth = new ArrayList<Pontaj>();
			for (Pontaj e:pontaje) {
				if (month.format(e.getDataPontajStart()).equals(luna)) {
					diffMonth= diffMonth + e.getDiffMinutes();
					pontajeMonth.add(e);
				}
			}
			model.addAttribute("pontaje", pontajeMonth);
			model.addAttribute("totalBal", diffMonth);
		}
		return "vizual";
	}
	@RequestMapping(value = "/adaugare", method = RequestMethod.GET)
	public String adauga(@RequestParam(value = "dataStart", required=false) Date dataStart,
			@RequestParam(value = "dataEnd", required=false) Date dataEnd,
			@RequestParam(value = "luna") String luna,
			@RequestParam(value = "mode") String mode,
			@RequestParam(value = "originalMode") String originalMode,
			Model model) {

	    model.addAttribute("luna", luna);
	    model.addAttribute("mode", mode);
	    model.addAttribute("originalMode", originalMode);
		if (mode.equals("view")) {
	        Date date = new Date();
	        String dataDefault = sdf.format(date);
	        model.addAttribute("defaultDate", dataDefault);	
		}
		if (mode.equals("edit")) {
	        Date date = new Date();
	        String dataDefault = sdf.format(date);
	        
	        Date dataX;
	        Date newDate;
			try {
				dataX = sdf.parse(dataDefault);
				newDate = new Date(dataX.getTime() + 7 * HOUR);
				long diferenta = pontajService.diffMin(newDate, dataX, dofw.format(dataX));
				pontajService.save(new Pontaj(dataX,newDate,dofw.format(dataX),diferenta));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        model.addAttribute("defaultDate", dataDefault);
		}
		
		return "addPontaj";
	}
}
