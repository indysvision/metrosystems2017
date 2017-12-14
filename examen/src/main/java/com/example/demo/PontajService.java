package com.example.demo;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PontajService {
	
	private final PontajRepository pontajRepo;
	public PontajService(PontajRepository repo) {
		pontajRepo = repo;
	}

	public void save(Pontaj part) {
		pontajRepo.save(part);
	}
	
	public Pontaj savePontaj(Pontaj part) {
		return pontajRepo.save(part);
	}
	
	public void deletePontaj(long id) {
		pontajRepo.delete(pontajRepo.findById(id));
		System.out.println("delete "+id);
	}
	
	public Pontaj getPontaj(long id) {
		return pontajRepo.findById(id);
	}
	
	public long diffMin(Date dateEnd, Date dateStart, String dayOfWeek) {
		long diff = dateEnd.getTime() - dateStart.getTime();
		long diffMinutes = diff / (60 * 1000);
		long minNormZi = 540; // 9 hours = 540 minutes; Mon, Tue, Wed, Thu
		long minFriZi = 390; // 6.5 hours = 390 minutes Fri
		long minWeekEnd = 0; // 0 minutes for SUN SAT
		long diferenta = 0;
		
        if (dayOfWeek.equals("Mon")) {
        	diferenta = diffMinutes - minNormZi;
        } else if (dayOfWeek.equals("Tue")) {
        	diferenta = diffMinutes - minNormZi;
        } else if (dayOfWeek.equals("Wed")) {
        	diferenta = diffMinutes - minNormZi;
        } else if (dayOfWeek.equals("Thu")) {
        	diferenta = diffMinutes - minNormZi;
        } else if (dayOfWeek.equals("Fri")) {
        	diferenta = diffMinutes - minFriZi;
        } else {
        	diferenta = minWeekEnd;
        }
		
		return diferenta;
	}
}
